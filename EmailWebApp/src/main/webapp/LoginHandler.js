/* set ip */
var ip = "35.158.125.40";

$(document).on('click', '.loginBtn', function() {

	var username = $('#username').val();
	console.log("User: "+username);
		$.ajax({
	        url: "http://"+ip+":8080/EmailWebApp/rest/service/login/"+username
	    }).then(function(data) {
			window.location = "http://"+ip+":8080/EmailWebApp/app.html?username=" + username;
	    });

	
});
