function processAssignTeammate(taskID) {
  // Can grab any DIV or SPAN HTML element and can then manipulate its
  // contents dynamically via javascript
  console.log("result:" + assignments);
	refreshTaskList();
}

function assignTeammate(memberName) {
  let memberName= prompt("Enter teammate name");
  var form = document.searchForm;
  var data = {};
  data["taskID"] = taskID;
  data["memberID"]  =memberName; 
  data["projectID"]=form.projectName.value;
   var js = JSON.stringify(data);
  console.log("JS:" + js);
  var xhr = new XMLHttpRequest();
  xhr.open("POST", assign_teammate_url, true);
  // send the collected data as JSON
  xhr.send(js);

  // This will process results and update HTML as appropriate. 
  xhr.onloadend = function () {
    console.log(xhr);
    console.log(xhr.request);
    if (xhr.readyState == XMLHttpRequest.DONE) {
    	 if (xhr.status == 200) {
	      console.log ("XHR:" + xhr.responseText);
	      processAssignTeammate(xhr.responseText);
    	 } else {
    		 console.log("actual:" + xhr.responseText)
			  var js = JSON.parse(xhr.responseText);
			  var err = js["response"];
			  alert (err);
    	 }
    } else {
      processAssignTeammate("N/A");
    }
  };
}