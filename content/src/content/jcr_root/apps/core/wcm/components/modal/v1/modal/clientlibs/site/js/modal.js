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

"use strict";
// Get the modal
var getModalWindowID = window.location.hash;
getModalWindowID = getModalWindowID.split("?");
var getModalWindowName = getModalWindowID[0];

$(".myModalClass").on("click", function() {
    $(getModalWindowName).css({
        "display": "none"
    });
});

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if ("#" + event.target.id === getModalWindowName) {
        $(getModalWindowName).css({
            "display": "none"
        });
    }
};

// Escape button event
$(document).keyup(function(event) {
    if (event.keyCode === 27) {
        // Close the modal/menu
        $(getModalWindowName).css({
            "display": "none"
        });
    }
});

$(document).ready(function() {
    if ($(getModalWindowName).length) {
        var getPath = $(getModalWindowName).data("contentUrl");
        $(getModalWindowName).find(".cmp-modal-dialog .cmp-modal-content .cmp-modal-body").load(getPath, function() {
            $(getModalWindowName).css({
                "display": "block"
            });
        });
    }
});
