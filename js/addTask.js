function processAddTaskResponse(task) {
  // Can grab any DIV or SPAN HTML element and can then manipulate its
  // contents dynamically via javascript
  console.log("result:" + task);
	refreshTaskList();
}

function handleAddTaskClick(e) {

  var form = document.addTaskForm;
  var projectForm =document.searchForm;
  var data = {};
  data["taskName"] = form.taskName.value;
  data["projectID"]=projectForm.projectName.value;
	let taskName =prompt("Enter task name with task level Example: "," 1.Task1 ");
	processTask(taskName);
}

function processTask(taskName) {
 // var form = document.addTaskForm;
  var projectForm =document.searchForm;
  var taskCounter=parseInt(taskName);

  var data = {};
  data["taskName"] = taskName.substring(2);
  data["projectID"]= projectForm.projectName.value;
  data["taskCounter"]=taskCounter.toString() + ".";
  
   var js = JSON.stringify(data);
  console.log("JS:" + js);
  var xhr = new XMLHttpRequest();
  xhr.open("POST", add_task_url, true);
  // send the collected data as JSON
  xhr.send(js);

  // This will process results and update HTML as appropriate. 
  xhr.onloadend = function () {
    console.log(xhr);
    console.log(xhr.request);
    if (xhr.readyState == XMLHttpRequest.DONE) {
    	 if (xhr.status == 200) {
	      console.log ("XHR:" + xhr.responseText);
	      processCreateResponse(xhr.responseText);
    	 } else {
    		 console.log("actual:" + xhr.responseText)
			  var js = JSON.parse(xhr.responseText);
			  var err = js["response"];
			  alert (err);
    	 }
    } else {
      processCreateResponse("N/A");
    }
  };
refreshTaskList();

  var form = document.createForm;
  var data = {};
  data["taskName"] = form.taskName.value;
   var js = JSON.stringify(data);
  console.log("JS:" + js);
  var xhr = new XMLHttpRequest();
  xhr.open("POST", create_url, true);
  // send the collected data as JSON
  xhr.send(js);

  // This will process results and update HTML as appropriate. 
  xhr.onloadend = function () {
    console.log(xhr);
    console.log(xhr.request);
    if (xhr.readyState == XMLHttpRequest.DONE) {
    	 if (xhr.status == 200) {
	      console.log ("XHR:" + xhr.responseText);
	      processCreateResponse(xhr.responseText);
    	 } else {
    		 console.log("actual:" + xhr.responseText)
			  var js = JSON.parse(xhr.responseText);
			  var err = js["response"];
			  alert (err);
    	 }
    } else {
      processCreateResponse("N/A");
    }
  };
}
