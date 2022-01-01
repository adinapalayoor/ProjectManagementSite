/**
 * Refresh constant list from server
 *
 *    GET list_url
 *    RESPONSE  list of [name, value, system] constants 
 */
function refreshTaskList() {
	
	var form = document.searchForm;
	var data = {};
	data["projectName"] = form.projectName.value;

	var js=JSON.stringify(data);
	var xhr = new XMLHttpRequest();
	xhr.open("POST", view_tasks, true);
	xhr.send(js);


	// This will procesconsole.log(js);s results and update HTML as appropriate. 
	xhr.onloadend = function () {
		if (xhr.readyState == XMLHttpRequest.DONE) {
			console.log ("XHR:" + xhr.responseText);
			processTasksListResponse(xhr.responseText);
		} else {
			processTasksListResponse("N/A");
		}
	};
}



/**
 * Respond to server JSON object.
 *
 * Replace the contents of 'constantList' with a <br>-separated list of name,value pairs.
 */
function processTasksListResponse(result) {
	Image green = "file:///C:/Users/adina_l1uzsjt/git/cs3733finalproject/NilDesperandum/html/green.png";
	//String gray ="file:///C:/Users/adina_l1uzsjt/git/cs3733finalproject/NilDesperandum/html/gray.png";
	console.log("res:" + result);
	// Can grab any DIV or SPAN HTML element and can then manipulate its contents dynamically via javascript
	var js = JSON.parse(result);
	var taskList = document.getElementById('taskList');
	var output = "";
	for (var i =0; i < js.list.length; i++) {
		var taskJson = js.list[i];
		var cname = taskJson["taskID"];
		var taskNumber=taskJson["taskOutlineNumber"];
		var taskCompleted =taskJson["taskisCompleted"];
		var checkMark = green;
		// output = output+ "<div id=" + taskNumber+">" + taskNumber + cname + "</div>";  // Update computation result
		if(taskCompleted == true){
			output = output +"<div id=\"" + taskNumber + "\">"+cname+"<input type= 'button' value='Rename Task' onClick='Javascript:renameTask(\""+cname+"\")' >" + "\">"+"<input type= 'button' value='Assign Teammate' onClick='Javascript:assignTeammate(\""+cname+"\")' >"+ "\">"+"<input type= 'button' value='Unassign Teammate' onClick='Javascript:unassignTeammate(\""+cname+"\")' >"+"<input type= 'image' value='green' >" +"</div>";
		}   
		else{
			output = output +"<div id=\"" + taskNumber + "\">"+cname+"<input type= 'button' value='Rename Task' onClick='Javascript:renameTask(\""+cname+"\")' >" + "\">"+"<input type= 'button' value='Assign Teammate' onClick='Javascript:assignTeammate(\""+cname+"\")' >"+ "\">"+"<input type= 'button' value='Unassign Teammate' onClick='Javascript:unassignTeammate(\""+cname+"\")' >" +"</div>";
			}
		}
		if(taskList !== null )
		{
			taskList.innerHTML = output;
		}

	}