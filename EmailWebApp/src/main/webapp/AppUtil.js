$(document).ready( function() {
	console.log(window.location.search);
	var param = window.location.search.substring(1);
	param[0].split("=");
	var username = param[1]
	console.log("Logged in user:" +username);
	$('.header-user').append(username);
	
});