/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 ~ Copyright 2019 Adobe Systems Incorporated
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

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.adobe.cq.wcm.core.components.models.SearchModel;

@Model(adaptables = SlingHttpServletRequest.class, adapters = { SearchModel.class,
		ComponentExporter.class }, resourceType = SearchModelImpl.RESOURCE_TYPE)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class SearchModelImpl implements SearchModel {

	protected static final String RESOURCE_TYPE = "core/wcm/components/results";

	@SlingObject
	private Resource resource;

	@ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
	private String path;

	@ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
	private int limit;

	@ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
	private String guessTotal;

	@ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
	private String orderBy;

	@ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
	private String orderBySort;

	@ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
	private String layout;;

	@Override
	public String getPath() {
		return path;
	}

	@Override
	public int getLimit() {
		return limit;
	}

	@Override
	public String getGuessTotal() {
		return guessTotal;
	}

	@Override
	public String getOrderBy() {
		return orderBy;
	}

	@Override
	public String getOrderBySort() {
		return orderBySort;
	}

	@Override
	public String getLayout() {
		return layout;
	}

}
