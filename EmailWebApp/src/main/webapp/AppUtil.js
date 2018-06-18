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

/* refreshed the email history list */
$(document).on('click', '.refreshBtn', function() {
	$.ajax({
        url: "http://localhost:8080/EmailWebApp/rest/service/getHistoryByUsername/"+GetUsernameFromURL()
    }).then(function(data) {
    	$('.historylist-emaillist').empty();
    	for ( var email in data) {
    		console.log("Emailtitle: "+ data[email].title+" ID: "+data[email].id);
    		addEmailToList(data[email].title, data[email].id);
    		
    	}
    });
});

/* adds an email to the email history list (received emails) */
function addEmailToList(emailtitle, emailId) {
	var email = document.createElement("input");
	email.classList.add("email-container");
	email.setAttribute("type", "button");
	email.setAttribute("value", emailtitle);

	email.id = emailId;
	var emaillist = document.getElementById("historylist-emaillist");
	emaillist.appendChild(email);
	console.log("Added Email");
}

