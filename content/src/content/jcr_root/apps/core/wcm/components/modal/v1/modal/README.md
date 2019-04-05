<!--
Copyright 2019 Adobe Systems Incorporated

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
-->
Modal (v1)
====
Modal component written in HTL that renders a configurable modal view for the content.

## Features
*Content Fragment
*Experience Fragment

### Use Object
The Modal component uses the `com.adobe.cq.wcm.core.components.models.Modal` Sling model as its Use-object.

### Component Policy Configuration Properties
The following configuration properties are used:

1. `./dateFormat` - defines the formatting string for when the list items are set to render their last modification date;
2. `./disableChildren` - allows to disable the ability to build a list from the child pages of a root page
3. `./disableStatic` - allows to disable the ability to build a list with static elements
4. `./disableSearch` - allows to disable the ability to build a list using search results
5. `./disableTags` - allows to disable the ability to build a list using the tagged child pages of a root page

### Edit Dialog Properties
The following properties are written to JCR for this Modal component and are expected to be available as `Resource` properties:

1. `./modal` - checkbox to show or hide modalId field
2. `./modalId` - represents the hash generated for the component path
3. `./pagePath` - allows to select page path to be shown in modal view

## Client Libraries
The component provides a `core.wcm.components.modal.v1` client library category that contains a recommended base
CSS styling. It should be added to a relevant site client library using the `embed` property.

It also provides a `core.wcm.components.modal.v1.editor` editor client library category that includes
JavaScript handling for dialog interaction. It is already included by its edit dialog.

## Information
* **Vendor**: Adobe
* **Version**: v1
* **Compatibility**: AEM 6.3
* **Status**: production-ready

