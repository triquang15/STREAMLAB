
$(document).ready(function() {
	
	$("#description").richText();
	
});

function checkUnique(form) {
	movieId = $("#id").val();
	movieName = $("#name").val();
	
	csrfValue = $("input[name='_csrf']").val();
	
	params = {id: movieId, name: movieName, _csrf: csrfValue};
	
	$.post(checkUniqueUrl, params, function(response) {
		if (response == "OK") {
			form.submit();
		} else if (response == "Duplicate") {
			showWarningModal("There is another movie having the name " + movieName);	
		} else {
			showErrorModal("Unknown response from server");
		}
		
	}).fail(function() {
		showErrorModal("Could not connect to the server");
	});
	
	return false;
}	