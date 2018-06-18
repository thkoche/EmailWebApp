/* Set username in header */
$(document).ready(function() {
	$('.header-right-text').append(" "+GetUsernameFromURL());
	$('.display-inner').hide();
	$( "#refreshBtn" ).trigger( "click" );
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
	$('.display-newEmail').hide();
	$('.display-emailDetails').show();
    $.ajax({
        url: "http://localhost:8080/EmailWebApp/rest/service/showEmail/"+$(this).attr('id')
    }).then(function(data) {
       $('.email-title').append(data.title);
       $('.email-sender').append(data.receivers);
       $('.email-text').append(data.message);
       addEmailId($(this).attr('id'));
    });
})

/* refreshed the email history list (Button listener) */
$(document).on('click', '#refreshBtn', function() {
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

/* delete the selected email (Button listener) */
$(document).on('click', '#deleteBtn', function() {
	$.ajax({
        url: "http://localhost:8080/EmailWebApp/rest/service/deleteEmail/"+GetUsernameFromURL()+"/"+$('.hiddenId').attr('id')
    }).then(function(data) {
    	$('.display-emailDetails').hide();
    });
});

/* open the write window */
$(document).on('click', '#newMailBtn', function() {
	$('.display-emailDetails').hide();
	$('.display-newEmail').show();
	
	//TODO init receiverslist
	
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

/* adds the emailId (hidden) to the details */
function addEmailId(emailId) {
	var para = document.createElement("p");
	para.setAttribute("hidden", "true");
	para.id = emailId;
	para.classList.add("hiddenId");
	var details = document.getElementById("display-emailDetails");
	details.appendChild(para);
}

function initReceiversList() {
	
}

