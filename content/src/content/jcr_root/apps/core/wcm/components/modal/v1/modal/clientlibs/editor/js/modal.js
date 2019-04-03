/*******************************************************************************
 * Copyright 2019 Adobe Systems Incorporated
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
/* global
    Granite, Coral
 */
(function(document, $) {
    "use strict";

    // when dialog gets injected
    $(document).on("foundation-contentloaded", function(e) {
        // if there is already an inital value make sure the according target element becomes visible
        $(".cq-dialog-checkbox-showhide").each(function() {
            showHide($(this));
        });

    });

    $(document).on("change", ".cq-dialog-checkbox-showhide", function(e) {
        showHide($(this));
    });

    function showHide(el) {

        // get the selector to find the target elements. its stored as data-.. attribute
        var target = el.data("cqDialogCheckboxShowhideTarget");


        // is checkbox checked?
        var checked = el.prop("checked");

        // get the selected value
        // if checkbox is not checked, we set the value to empty string
        var value = checked ? el.val() : "";

        // make sure all unselected target elements are hidden.
        $(target).not(".hide").addClass("hide");

        // unhide the target element that contains the selected value as data-showhidetargetvalue attribute
        $(target).filter("[data-showhidetargetvalue='" + value + "']").removeClass("hide");

    }

})(document, Granite.$);
