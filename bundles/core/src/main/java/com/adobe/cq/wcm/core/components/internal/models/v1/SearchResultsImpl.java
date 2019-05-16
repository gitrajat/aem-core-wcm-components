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
import com.adobe.cq.wcm.core.components.models.SearchResults;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Required;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Model(adaptables = { SlingHttpServletRequest.class }, adapters = { SearchResults.class }, resourceType = {SearchResultsImpl.RESOURCE_TYPE})
public class SearchResultsImpl implements SearchResults {
	private static final Logger log = LoggerFactory.getLogger(SearchResultsImpl.class);

	private static final String PN_MODE = "mode";
	
	protected static final String RESOURCE_TYPE = "core/wcm/components/results";

	private String DEFAULT_MODE = "search";


	@Self
	@Required
	private SlingHttpServletRequest request;

	@Self
	@Required
	private SearchConfig searchConfig;

	@ScriptVariable
	private Page currentPage;

	// Results component properties
	//private ValueMap properties;
	
	@Inject
	private Resource resource;

	@PostConstruct
	protected void init() {
		resource = searchConfig.getSearchResource();
		//properties = request.getResource().getValueMap();
	}

	public String getFormId() {
		return "TEST";
	}

	public List<Resource> getResults() {
		final List<Resource> resources = new ArrayList<>();
		final Map<String, String> map = new HashMap<>();
		map.put("type", "cq:Page");
		map.put("path", "/content");
		map.put("fulltext", "en");

		resource = request.getResource();
		ResourceResolver resourceResolver = resource.getResourceResolver();
		
		QueryBuilder queryBuilder = resourceResolver.adaptTo(QueryBuilder.class);
		Query query = null;
		if (queryBuilder != null) {
			query = queryBuilder.createQuery(PredicateGroup.create(map),
					(Session) resourceResolver.adaptTo(Session.class));

			SearchResult result = query.getResult();
			ResourceResolver leakingResourceResolver = null;

			try {
				for (final Hit hit : result.getHits()) {
					if (leakingResourceResolver == null) {
						// Get a reference to QB's leaking ResourceResolver
						leakingResourceResolver = hit.getResource().getResourceResolver();
					}
					resources.add(resourceResolver.getResource(hit.getPath()));
					//log.info("TEST RUN {}", resourceResolver.getResource(hit.getPath()));
				}
			} catch (RepositoryException e) {
				log.error("Error collecting search results", e);
			}
		}
		
		getPageDetails(resources);

		return resources;
	}
	
	private void getPageDetails(List<Resource> resources) {
		for(Resource resource :  resources) {
			resource.getValueMap().get("jcr:title");
			Page page = resource.adaptTo(Page.class);
			if(page != null) {
				log.info("TEST RUN13 {}",page.getTitle());
				log.info("TEST RUN23 {}",page.getPageTitle());
				log.info("TEST RUN2 {}",page.getDescription());
				log.info("TEST RUN3 {}",page.getTags().length);
				log.info("TEST RUN4 {}",page.getLastModifiedBy().length());
			}
			
//			log.info("TEST RUN2 {}",resource.getValueMap());
//			log.info("TEST RUN2 {}",resource.getValueMap().get("jcr:title"));
//			log.info("TEST RUN3 {}",resource.getResourceMetadata());
		}
		
	}

	public String getMode() {
		return currentPage.getProperties().get(PN_MODE, DEFAULT_MODE);
	}

}
