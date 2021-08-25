function myfunc(){
	console.log("reads js");
            var xhr = $.get("/bin/subscribeServlet", 
                        {firstName: $('#firstName').val(), lastName: $('#lastName').val(), phone: $('#phone').val(), email: $('#email').val()},
                        function(e){
                           //$('#listID').remove();
                           //$("#listID").load(window.location.href + " #listID > *");
                            alert("Welcome!");
    })
}