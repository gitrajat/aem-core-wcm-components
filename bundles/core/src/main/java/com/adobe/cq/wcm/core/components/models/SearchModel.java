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
package com.adobe.cq.wcm.core.components.models;

import org.jetbrains.annotations.NotNull;

import com.adobe.cq.export.json.ComponentExporter;

public interface SearchModel extends ComponentExporter {

	String getPath();

	int getLimit();

	String getGuessTotal();

	String getOrderBy();

	String getOrderBySort();

	String getLayout();

	/**
	 * @see ComponentExporter#getExportedType()
	 * @since com.adobe.cq.wcm.core.components.models 12.8.0
	 */
	@NotNull
	@Override
	default String getExportedType() {
		throw new UnsupportedOperationException();
	}

}
