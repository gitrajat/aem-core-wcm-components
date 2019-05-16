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
package com.adobe.cq.wcm.core.components.models;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.osgi.annotation.versioning.ProviderType;

@ProviderType
public interface SearchResults {

    /**
     * The ID of the form. This is used to associate results w a particular form.
     * As of Asset Share Commons 1.0.0, only 1 "search" is allowed on the page so this value is not well used.
     *
     * @return the form's id.
     */
    String getFormId();

    /**
     * This is used to expose state around what mode was requested for this search. The mode typically is used to engage a specific {@link com.adobe.aem.commons.assetshare.search.providers.SearchProvider}, however this is not mandatory.
     * In the initial release of Asset Share Commons, the only supported mode is "search".
     *
     * @return the search model.
     */
    String getMode();

    /**
     * @return the {@link Results} object, that represents the results of the search. {@link Results} contains a list of all results as well as heuristics and other metadata about the search.
     */
    List<Resource> getResults();
}
