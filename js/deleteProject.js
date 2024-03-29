function processDeleteResponse(result) {
  // Can grab any DIV or SPAN HTML element and can then manipulate its
  // contents dynamically via javascript
  console.log("deleted :" + result);
  
  refreshProjectsList();
}

function requestDelete(val) {
   if (confirm("Request to delete " + val)) {
     processDelete(val);
   }
}

function processDelete(name) {

   var data={};
   data["projectName"]=name;
  var js=JSON.stringify(data);
   // ISSUE with POST v. DELETE in CORS/API Gateway
  // This will process results and update HTML as appropriate. 
  var xhr = new XMLHttpRequest();
  xhr.open("POST", delete_url, true); 
 xhr.send(js);

  xhr.onloadend = function () {
	  console.log(xhr);
	  console.log(xhr.request);
	  if (xhr.readyState == XMLHttpRequest.DONE) {
		  if (xhr.status == 200) {
			  console.log ("XHR:" + xhr.responseText);
			  processDeleteResponse(xhr.responseText);
		  } else {
			  console.log("actual:" + xhr.responseText)
			  var js = JSON.parse(xhr.responseText);
			  var err = js["error"];
			  alert (err);
		  }
	  } else {
		  processDeleteResponse("N/A");
	  }
  };
}

