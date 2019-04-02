"use strict"
// Get the modal
var modal = document.getElementById("myModal");

// Get the <span> element that closes the modal
//var span = document.getElementsByClassName("close")[0];
//console.log(span,'hnh');
//
// When the user clicks on <span> (x), close the modal
//span.onclick = function() {
//    modal.style.display = "none";
//};
$('.myModalClass').on("click",function(){
     modal.style.display = "none";
})
//
//// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
    if (event.target === modal) {
        modal.style.display = "none";
    }
};
//
//
//$(document).on("click", "[data-model-trigger]", function () {
//    var path = $(this).data("contentUrl");
//        modal = $("#" + $(this).data("target"));
//    modal.find(".cmp-modal - body").load(path, function () {
//        modal.style.display = "block";
//        //  modal.modal('show');
//    });
//
//});
//$(window).on("onhashchange", function () {
//    console.log("onhash calling");
//    var url = window.location.href;
//    var hash = url.substring(url.indexOf("#") + 1);
//    if ($("#" + hash).length) {
//        $("#" + hash).modal("show'");
//    }
//});
$(document).ready(function () {
    var k = window.location.hash;
    k = k.split("?");
    var hash = k[0];
    if ($(hash).length) {
        console.log(hash);
        var path = $(".cmp_modal").data("contentUrl");
        $(hash).find(".cmp-modal-dialog .cmp-modal-content .cmp-modal-body").load(path, function () {
            modal.style.display = "block";
            // $(hash).modal('show');
        });
    }
});