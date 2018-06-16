$(document).on('click', '.loginbtn', function() {

	var username = document.getElementById("username").value;
	if (username) {
		window.location = "http://localhost:8080/EmailWebApp/app.html?username=" + username;
	}
});
