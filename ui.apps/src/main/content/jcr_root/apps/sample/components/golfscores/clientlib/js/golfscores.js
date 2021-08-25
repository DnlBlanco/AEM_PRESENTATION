function readjson(){

	var pagePath = document.getElementById("path").getAttribute("value");

    $.get("/bin/golfscores",{pagepath:pagePath}, function(e){
        if(e){
			$("#addressdiv").load(window.location.href + " #addressdiv > *");
            console.log(e);
		}
    });
}

