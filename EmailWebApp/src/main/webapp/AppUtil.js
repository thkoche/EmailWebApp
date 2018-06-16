$(document).ready(function() {
	$('.header-right-text').append(" "+GetUsernameFromURL());
});

function GetUsernameFromURL() {
	var sURLParam = window.location.search.substring(1);
	var sParameter = sURLParam.split("=");
	console.log("User logged in: " + sParameter[1]);
	return sParameter[1];
}
