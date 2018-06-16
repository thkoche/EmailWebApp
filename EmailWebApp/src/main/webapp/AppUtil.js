$(document).ready(function() {
	$('.header-right-text').append(" "+GetUsernameFromURL());
});

function GetUsernameFromURL() {
	var sURLParam = window.location.search.substring(1);
	var sParameter = sURLParam.split("=");
	console.log("User logged in: " + sParameter[1]);
	return sParameter[1];
}

$(document).on('click', '.email-container', function() {
    $.ajax({
        url: "http://localhost:8080/EmailWebApp/rest/service/getEmailByUsername/"+GetUsernameFromURL()
    }).then(function(data) {
       $('.email-title').append(data.title);
       $('.email-text').append(data.message);
    });
})
