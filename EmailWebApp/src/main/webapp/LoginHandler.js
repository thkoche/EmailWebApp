$(document).on('click', '.loginBtn', function() {

	var username = document.getElementById("username").value;
	if (username) {
		window.location = "http://localhost:8080/EmailWebApp/app.html?username=" + username;
	}
	$.ajax({
        url: "http://localhost:8080/EmailWebApp/rest/service/login/"+GetUsernameFromURL()
    })
});
