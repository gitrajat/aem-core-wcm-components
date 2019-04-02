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

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.adobe.cq.wcm.core.components.models.Modal;

@Model(adaptables = SlingHttpServletRequest.class, adapters = { Modal.class,
		ComponentExporter.class }, resourceType = ModalImpl.RESOURCE_TYPE)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class ModalImpl implements Modal {

	protected static final String RESOURCE_TYPE = "core/wcm/components/modal/v1/modal";

	private static final Logger LOGGER = LoggerFactory.getLogger(ModalImpl.class);

	private static final String JCR_CONTENT = "/jcr:content";
	private static final String KEY_MODAL_ID = "modalId";

	@SlingObject
	private Resource resource;

	private String modalId;

	@Override
	public String getExportedType() {
		return resource.getResourceType();
	}

	@PostConstruct
	private void initModel() {
		if (modalId == null) {
			generateModalId();
		}
	}

	void generateModalId() {
		String absoluteComponentPath = resource.getPath();
		int index = absoluteComponentPath.indexOf(JCR_CONTENT);
		String relativeComponentPath = absoluteComponentPath.substring(index);
		modalId = String.valueOf(Math.abs(relativeComponentPath.hashCode() - 1));

		ModifiableValueMap map = resource.adaptTo(ModifiableValueMap.class);
		if (map != null) {
			map.put(KEY_MODAL_ID, modalId);
		}

		try {
			resource.getResourceResolver().commit();
		} catch (PersistenceException e) {
			LOGGER.error("Error occured while saving the modalId for {}: {}", absoluteComponentPath, e);
		}
	}

	@Override
	public String getModalId() {
		return modalId;
	}

}
