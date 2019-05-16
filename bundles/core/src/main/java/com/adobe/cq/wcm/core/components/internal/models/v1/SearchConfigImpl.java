/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  ~ Copyright 2017 Adobe Systems Incorporated
 ~
 ~ Licensed under the Apache License, Version 2.0 (the "License");
 ~ you may not use this file except in compliance with the License.
 ~ You may obtain a copy of the License at
 ~
 ~     http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ Unless required by applicable law or agreed to in writing, software
 ~ distributed under the License is distributed on an "AS IS" BASIS,
 ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 ~ See the License for the specific language governing permissions and
 ~ limitations under the License.
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
package com.adobe.cq.wcm.core.components.internal.models.v1;

import com.adobe.cq.wcm.core.components.models.SearchConfig;
import com.day.cq.dam.api.DamConstants;
import com.day.cq.search.Predicate;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.factory.ModelFactory;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Model(
        adaptables = {SlingHttpServletRequest.class},
        adapters = {SearchConfig.class},
        resourceType = {SearchConfigImpl.RESOURCE_TYPE}
)
public class SearchConfigImpl implements SearchConfig {
    public static final String RESOURCE_TYPE = "asset-share-commons/components/search/results";

    private static final int MAX_GUESS_TOTAL = 2000;
    private static final int DEFAULT_LIMIT = 50;

    private static final String DEFAULT_GUESS_TOTAL = "250";
    private static final String DEFAULT_ORDER_BY = "@jcr:score";
    private static final String DEFAULT_ORDER_BY_SORT = Predicate.SORT_DESCENDING;
    private static final boolean DEFAULT_ORDER_BY_CASE = true;

    private static final String DEFAULT_LAYOUT = "card";
    private static final String DEFAULT_SPID = "search";

    private final String[] DEFAULT_PATHS = {"/content/dam"};

    private String PN_ORDER_BY = "orderBy";
    private String PN_ORDER_BY_SORT = "orderBySort";
    private String PN_ORDER_BY_CASE = "orderByCase";
    private String PN_LIMIT = Predicate.PARAM_LIMIT;
    private String PN_PATHS = "paths";
    private String PN_LAYOUT = "layout";
    private String PN_GUESS_TOTAL = Predicate.PARAM_GUESS_TOTAL;
    private String PN_SPID = "searchProviderId";
    private String PN_SEARCH_PREDICATES = "searchPredicates";

    @Self
    private SlingHttpServletRequest request;

    @ScriptVariable
    private Page currentPage;

    @OSGiService
    private ModelFactory modelFactory;

    @SlingObject
    @Optional
    private Resource resource;

    private ValueMap properties;

    List<String> paths;

    @PostConstruct
    protected void init() {
        resource = resolveSearchConfigResource(request.getResourceResolver().adaptTo(PageManager.class),
                request.getResource());

        if (resource == null) {
            throw new IllegalArgumentException("Adaptable must resolve a search results component.");
        }

        properties = resource.getValueMap();
    }

    @Override
    public ValueMap getProperties() {
        return properties;
    }

    @Override
    public String getMode() {
        return properties.get(PN_SPID, DEFAULT_SPID);
    }

    @Override
    public String getLayout() {
        return properties.get(PN_LAYOUT, DEFAULT_LAYOUT);
    }

    @Override
    public String getGuessTotal() {
        final String guessTotal = properties.get(PN_GUESS_TOTAL, DEFAULT_GUESS_TOTAL);

        if (Boolean.TRUE.toString().equalsIgnoreCase(guessTotal)) {
            return Boolean.TRUE.toString();
        }

        try {
            int guessTotalAsNumber = Integer.parseInt(guessTotal);
            return (guessTotalAsNumber < 1 || guessTotalAsNumber > MAX_GUESS_TOTAL) ? DEFAULT_GUESS_TOTAL : String.valueOf(guessTotalAsNumber);
        } catch (NumberFormatException e) {
            return DEFAULT_GUESS_TOTAL;
        }
    }

    @Override
    public String getSearchProviderId() {
        return properties.get(PN_SPID, DEFAULT_SPID);
    }

    @Override
    public int getLimit() {
        return properties.get(PN_LIMIT, DEFAULT_LIMIT);
    }

    @Override
    public String getOrderBy() {
        return properties.get(PN_ORDER_BY, DEFAULT_ORDER_BY);
    }

    @Override
    public String getOrderBySort() {
        return properties.get(PN_ORDER_BY_SORT, DEFAULT_ORDER_BY_SORT);
    }

    @Override
    public boolean isOrderByCase() {
        return properties.get(PN_ORDER_BY_CASE, DEFAULT_ORDER_BY_CASE);
    }

    @Override
    public List<String> getPaths() {
        paths  = Arrays.stream(properties.get(PN_PATHS, DEFAULT_PATHS)).filter(path ->
          StringUtils.equals(path, DamConstants.MOUNTPOINT_ASSETS) || StringUtils.startsWith(path, DamConstants.MOUNTPOINT_ASSETS)
        ).collect(Collectors.toList());

        return (paths.isEmpty()) ? Arrays.asList(DEFAULT_PATHS) : paths;
    }

    @Override
    public List<String> getSearchPredicatesNames() {
        return Arrays.asList(properties.get(PN_SEARCH_PREDICATES, new String[]{}));
    }

    private Resource resolveSearchConfigResource(final PageManager pageManager, final Resource currentResource) {
        if (!isValidResource(currentResource)) {
            // Hit the sites tree root; stop looking!
            return null;
        } else {
            // We won the powerball! this passed in resource is the right resource!
            // Ok, not really the powerball, this happens when the Search Results component uses this model.
            return currentResource;
        }

        // Go look under each page, up the tree for the component.
//        final ResourceTypeVisitor visitor = new ResourceTypeVisitor(new String[]{RESOURCE_TYPE});
//        final Page page = pageManager.getContainingPage(currentResource);
//
//        visitor.accept(page.getContentResource());
//
//        if (visitor.getResources().size() > 0) {
//            return visitor.getResources().iterator().next();
//        } else {
//            return resolveSearchConfigResource(pageManager, page.getParent().getContentResource());
//        }
    }


    private boolean isValidResource(Resource resource) {
        return resource != null && StringUtils.startsWith(resource.getPath(), "/content/");
    }
}
