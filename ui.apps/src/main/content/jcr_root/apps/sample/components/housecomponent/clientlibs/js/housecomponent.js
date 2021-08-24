function callServlet() {
    var address = document.getElementById("next-house-btn").value;
	$.get("/bin/nextHouseServlet?address=" + address, function (data) {
        $(".house-comp").load(" .house-comp > *"); //JQuery to refresh the component.
	});
}