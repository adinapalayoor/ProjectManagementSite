package com.nildesperandumcs3733.finalproject.http;

import com.nildespernadumcs3733.finalproject.model.Project;

public class RenameTaskResponse {

	public String result;
	public int statusCode;
	public String error;
	
	public RenameTaskResponse  (String name, int statusCode) { // revisit this function
		this.result = "" + name; 
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public RenameTaskResponse (int statusCode, String errorMessage) {
		this.result = ""; 
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
	
	
	public String toString() {
		if (statusCode / 100 == 2) {  
			return "Result(" + result + ")";
		} else {
			return "ErrorResult(" + statusCode + ", err=" + error + ")";
		}
	}
}


