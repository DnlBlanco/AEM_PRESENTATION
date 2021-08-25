function callServlet() {
    var itemName = document.getElementById("review-comp__next-item-btn").value;
	$.get("/bin/nextItemServlet?itemName=" + itemName, function (data) {
        $(".review-comp").load(" .review-comp > *"); //JQuery to refresh the component.
	});
}