/**
 * Refresh constant list from server
 *
 *    GET list_url
 *    RESPONSE  list of [name, value, system] constants 
 */
function refreshProjectsList() {
   var xhr = new XMLHttpRequest();
   xhr.open("GET", list_url, true);
   xhr.send();
   
   //console.log("sent");

  // This will process results and update HTML as appropriate. 
  xhr.onloadend = function () {
    if (xhr.readyState == XMLHttpRequest.DONE) {
      console.log ("XHR:" + xhr.responseText);
      processListResponse(xhr.responseText);
    } else {
      processListResponse("N/A");
    }
  };
}


/**
 * Respond to server JSON object.
 *
 * Replace the contents of 'constantList' with a <br>-separated list of name,value pairs.
 */
function processListResponse(result) {
  console.log("res:" + result);
  // Can grab any DIV or SPAN HTML element and can then manipulate its contents dynamically via javascript
  var js = JSON.parse(result);
  var projectList = document.getElementById('projectList');
  
  var output = "";
  for (var i = 0; i < js.list.length; i++) {
    var constantJson = js.list[i];
    console.log(constantJson);
    
    var cname = constantJson["name"];
    var cid = constantJson["id"];
    var sys = constantJson["isArchived"];
   
   calculateProgress(cname); 
    	 output = output + cname+ "<div id=\"const" + cname + "\"><button>" + "Archive" + "</button> <input type="+ "'button'" +"value='Delete'"+ "onClick='JavaScript:requestDelete(\""+ cname +"\")'>" +"</div>";
}
  // Update computation result
if(projectList !== null )
{
  projectList.innerHTML = output;
}
}
