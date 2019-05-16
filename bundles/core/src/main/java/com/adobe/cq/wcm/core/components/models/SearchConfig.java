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

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.annotation.versioning.ProviderType;

import java.util.Collections;
import java.util.List;

/**
 * Class that represents the overarching Search configuration that may span across components.
 *
 * These methods return the default values set by the Search Results component itself.
 *
 * This class should not take into account HTTP-provided parameters, but rather deal directly with the resource content.
 */
@ProviderType
public interface SearchConfig {
    /**
     * @return the resource that represents the Search Component resource.
     */
    default Resource getSearchResource() { return  null; }

    /**
     * @return a value map for the search results component.
     */
    ValueMap getProperties();

    /**
     * The mode derived from the request and if unavailable, then from the configured component state.
     *
     * @return the active search mode (ex. search, browse).
     */
    String getMode();

    /**
     * The layout derived from the request and if unavailable, then from the configured component state.
     *
     * @return the active layout mode (ex. card, list)
     */
    String getLayout();

    /**
     * @return a list of the paths that are eligible for searching.
     */
    List<String> getPaths();

    /**
     * @return the active order by property value.
     */
    String getOrderBy();

    /**
     * @return the active order by sort direction.
     */
    String getOrderBySort();

    /**
     * Note that ordering case-insensitive with QueryBuilder breaks sorting by anything by text data, so be CAREFUL when implementing this method.
     *
     * @return true if the sort order is case-sensitive, else false;
     */
    default boolean isOrderByCase() { return true; }

    /**
     * @return the limit of number of results to return for this search.
     */
    int getLimit();

    /**
     * @return the configured Guess Total.
     */
    String getGuessTotal();

    /**
     * @return the default
     */
    String getSearchProviderId();

    /**
     * @return a list of search predicate names, as configured on the search results component.
     */
    default List<String> getSearchPredicatesNames() { return Collections.EMPTY_LIST; }
}
