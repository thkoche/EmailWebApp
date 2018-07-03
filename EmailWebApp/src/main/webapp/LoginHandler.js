/* set ip */
var ip = "localhost";

$(document).on('click', '.loginBtn', function() {

	var username = $('#username').val();
	console.log("User: "+username);
		$.ajax({
	        url: "http://"+ip+":8080/EmailWebApp/rest/service/login/"+username
	    }).then(function(data) {
			window.location = "http://"+ip+":8080/EmailWebApp/app.html?username=" + username;
	    });

	
});
