$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/EmailWebApp/rest/service/getHistory"
    }).then(function(data) {
       $('.user-id').append(data.id);
       $('.user-email').append(data.emailAddress);
       $('.user-name').append(data.name);
    });
})