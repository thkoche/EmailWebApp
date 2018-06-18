$(document).on('click', '.loginBtn', function() {

	var username = document.getElementById("username").value;
	console.log("User: "+username);
	if (username) {
		$.ajax({
	        url: "http://localhost:8080/EmailWebApp/rest/service/login/"+username
	    }).then(function(data) {
	    	window.location = "http://localhost:8080/EmailWebApp/app.html?username=" + username;
	    });
	}
});
