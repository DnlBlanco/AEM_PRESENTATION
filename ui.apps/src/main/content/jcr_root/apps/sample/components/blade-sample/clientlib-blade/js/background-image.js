/**
 * the goal is to taken in the required images from the dialog and put them in a list.
 * the list will be formed from the back-end.
 * and the javascript will handle ONLY displaying the images.
 */
//function will be served the current image to be shown...so i need to grab the image from the backend.
//the problem will when to call the js function to call to the backend after the schedule runs.

// 'let' or 'var' is used so that the variable can identified as a global variable.
var listOfImages = []; //create an ArrayList to keep the values and iterate over them
var currentIndex = 0; //this will vreak when a user tries to set the current index to any number out of bounds of what was defined in list

window.addEventListener('load',(event) =>{
    console.log('DOM fully loaded and parsed');
	let paths = $('#image_container'); //using jQuery to grab the element that has the unique ID value
	let intervalData = $('#image_container');
	var interval = 0;
	//...so

	//parameters that the .each method takes in is the function that will using key,value pairs 
	//(if one variable present in function, it's automatically seen as a INDEX or KEY, type intergers)
	$(paths.data('img-refs')).each(function(key, value){ //grab the element's data-* attribute to ITERATE OVER
			if(!listOfImages.includes(value)){
				listOfImages.push(value);
			}
        }
	);

var test = 0;
$(intervalData.data('interval')).each(function(key,value){
	test = value;
});
//console.log("The interval recorded is: " + test);


	let backgroundElement = document.querySelector('.background_container'); //grabbing the class name of the container
	backgroundElement.style.setProperty('--set-image', 'url('+listOfImages[currentIndex]+')'); //enables the changing of the pseudo element css properties.

	console.log(listOfImages);

	//running function that is set to call a function every 5 seconds.
	setInterval(function (){
	//if (currentIndex == listOfImages.length-1){
	//	currentIndex = 0;
	//}else {
	//	currentIndex+=1;
	//}
    //console.log("current background is: "+ listOfImages[currentIndex]);
	//let backgroundElement = document.querySelector('.background_container'); //grabbing the class name of the container
	//backgroundElement.style.setProperty('--set-image', 'url('+listOfImages[currentIndex]+')'); //i need to check the back end every second


	//edited on 08/23/2021


        $.get("/bin/backgroundServlet", {check:"switch", images:listOfImages, index:currentIndex}, function (e){
        console.log("inside of ajax");
		let backgroundElement = document.querySelector('.background_container'); //grabbing the class name of the container
		backgroundElement.style.setProperty('--set-image', 'url('+e+')'); //i need to check the back end every second
		console.log(listOfImages.indexOf(e));
        currentIndex = listOfImages.indexOf(e);

	})
    //console.log("outside of the ajax")

}, test*1000);


});
