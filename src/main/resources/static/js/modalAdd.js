// Get the button that opens the modal
var btn3 = document.getElementById("elementAdd");
// Get the <span> element that closes the modal
var btn4 = document.getElementById("buttoncloseAdd");

// open
btn3.onclick = function () {
    $("#AddModal").css("display", "block");
}
btn4.onclick = function () {
    $("#AddModal").css("display", "none");
}