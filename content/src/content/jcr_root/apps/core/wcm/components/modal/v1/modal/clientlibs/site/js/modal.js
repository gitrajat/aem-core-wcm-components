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
/* Getting Page URL  Modal ID */

var getUrlModalID = window.location.hash.substr(1).split("?")[0];
var getValOnPage = document.createElement("div");
getValOnPage.setAttribute("id", "data-modal-content");
document.body.appendChild(getValOnPage);
var modalContentUrl = document.getElementById(getUrlModalID).getAttribute("data-content-url");

function fetchData(url, insertModalContent) {
    var xhttp;
    xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            insertModalContent(this.responseText);
        }
    };
    xhttp.open("GET", url, true);
    xhttp.send();
}

function initializeModal(xhttp) {
    document.getElementById("data-modal-content").innerHTML = xhttp;
    var modalOpen = new tingle.modal({});
    modalOpen.open();
    modalOpen.setContent(document.getElementById("data-modal-content").innerHTML);
}

document.addEventListener('DOMContentLoaded', function (event) {
    document.getElementById("data-modal-content").style.display = "none";
    fetchData(modalContentUrl, initializeModal);
})