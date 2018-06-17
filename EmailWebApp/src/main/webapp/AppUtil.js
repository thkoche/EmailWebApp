/* Set username in header */
$(document).ready(function() {
	$('.header-right-text').append(" "+GetUsernameFromURL());
});

/* returns the used username */
function GetUsernameFromURL() {
	var sURLParam = window.location.search.substring(1);
	var sParameter = sURLParam.split("=");
	console.log("User logged in: " + sParameter[1]);
	return sParameter[1];
}

/* print the clicked email */
$(document).on('click', '.email-container', function() {
    $.ajax({
        url: "http://localhost:8080/EmailWebApp/rest/service/getEmailByUsername/"+GetUsernameFromURL()
    }).then(function(data) {
       $('.email-title').append(data.title);
       $('.email-text').append(data.message);
    });
})

/* reload history (received emails) */
$(document).on('click', '.refreshBtn', function() {
	var email = document.createElement("input");
	var emailtitle = document.createTextNode("This is new");
	email.appendChild(emailtitle);

	var emaillist = document.getElementById("historylist-emaillist");
	emaillist.appendChild(email);
	console.log("Refreshed history");
});