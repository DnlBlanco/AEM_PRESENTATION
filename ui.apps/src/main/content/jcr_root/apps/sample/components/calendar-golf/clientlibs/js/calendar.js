function addEvent() {
        $(document).ready(() => {
        var pagePath = document.getElementById("addEvent").value;
        console.log("Add Event");

        $.post(pagePath, function (response) {
        console.log("Event Added");
        $(".custom-event").append("Golf Day");});
    });
}


function golfDetails() {
  document.getElementById("eventDetails").classList.toggle("show");
}