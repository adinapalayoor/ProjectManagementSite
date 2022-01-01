function processmarkTask(task) {
  // Can grab any DIV or SPAN HTML element and can then manipulate its
  // contents dynamically via javascript
  console.log("result:" + project);
	refreshTaskList();
}

function markTask(task) {
  //let newTaskName =prompt("Enter new name ");
  //var form = document.searchForm;
  var data = {};
  data["oldTaskName"] = oldTaskName;
  data["newTaskName"]  =newTaskName; 
  //data["projectID"]=form.projectName.value;
   var js = JSON.stringify(data);
  console.log("JS:" + js);
  var xhr = new XMLHttpRequest();
  xhr.open("POST", mark_task_url, true);
  // send the collected data as JSON
  xhr.send(js);

  // This will process results and update HTML as appropriate. 
  xhr.onloadend = function () {
    console.log(xhr);
    console.log(xhr.request);
    if (xhr.readyState == XMLHttpRequest.DONE) {
    	 if (xhr.status == 200) {
	      console.log ("XHR:" + xhr.responseText);
	      processMarkTask(xhr.responseText);
    	 } else {
    		 console.log("actual:" + xhr.responseText)
			  var js = JSON.parse(xhr.responseText);
			  var err = js["response"];
			  alert (err);
    	 }
    } else {
      processMarkTask("N/A");
    }
  };
}