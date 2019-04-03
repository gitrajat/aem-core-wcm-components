"use strict"
// Get the modal
var getModalWindowID = window.location.hash;
getModalWindowID = getModalWindowID.split("?");
var getModalWindowName = getModalWindowID[0];

$('.myModalClass').on("click", function () {
    $(getModalWindowName).css({
        'display': 'none'
    });
})

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
    if ('#' + event.target.id === getModalWindowName) {
        $(getModalWindowName).css({
            'display': 'none'
        });
    }
};

// Escape button event
$(document).keyup(function (event) {
    if (event.keyCode == 27) {
        // Close the modal/menu
        $(getModalWindowName).css({
            'display': 'none'
        });
    }
});

$(document).ready(function () {
    if ($(getModalWindowName).length) {
        var getPath = $(getModalWindowName).data("contentUrl");
        $(getModalWindowName).find(".cmp-modal-dialog .cmp-modal-content .cmp-modal-body").load(getPath, function () {
            $(getModalWindowName).css({
                'display': 'block'
            });
        });
    }
});