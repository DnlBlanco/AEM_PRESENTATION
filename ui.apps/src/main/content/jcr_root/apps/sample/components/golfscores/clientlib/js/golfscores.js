function readjson(){

	var pagePath = document.getElementById("path").getAttribute("value");

    $.get("/bin/sample/golfscores",{pagepath:pagePath}, function(e){
        if(e){
			$("#scoretable").load(window.location.href + " #scoretable > *");
            console.log(e);
		}
    });
}

