/**
 * Refresh constant list from server
 *
 *    GET list_url
 *    RESPONSE  list of [name, value, system] constants 
 */
function refreshTeammateList() {
   	var form = document.searchForm;
    var data = {};
    data["projectName"] = form.projectName.value;
   var js=JSON.stringify(data);
   var xhr = new XMLHttpRequest();
   xhr.open("POST", view_teammates , true);
   xhr.send(js);
   //console.log("sent");

  // This will process results and update HTML as appropriate. 
  xhr.onloadend = function () {
    if (xhr.readyState == XMLHttpRequest.DONE) {
      console.log ("XHR:" + xhr.responseText);
      processTeammatesListResponse(xhr.responseText);
    } else {
      processTeammatesListResponse("N/A");
    }
  };
}

/**
 * Respond to server JSON object.
 *
 * Replace the contents of 'constantList' with a <br>-separated list of name,value pairs.
 */
function processTeammatesListResponse(result) {
  console.log("res:" + result);
  // Can grab any DIV or SPAN HTML element and can then manipulate its contents dynamically via javascript
  var js = JSON.parse(result);
  var teammatesList = document.getElementById('teammateList');
  
  var output = "";
  for (var i = 0; i < js.list.length; i++) {
    var teammateJson = js.list[i];
    console.log(teammateJson);
    
    var cname = teammateJson["memberName"];

     output = output+ cname + "<div id=\"const" + cname + "\">" + "</div>";  // Update computation result
if(teammatesList !== null )
{
  teammatesList.innerHTML = output;
}

}}