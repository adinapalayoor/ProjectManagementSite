package com.nildesperandumcs3733.finalproject.http;

import com.nildespernadumcs3733.finalproject.model.Project;

public class ArchiveProjectResponse {

		public String result;
		public int statusCode;
		public String error;
		public Project project;
		
		public ArchiveProjectResponse (String name, int statusCode) { // revisit this function
			this.result = "" + name; // doesn't matter since error
			this.statusCode = statusCode;
			this.error = "";
		}
		
		public ArchiveProjectResponse (int statusCode, String errorMessage) {
			this.result = ""; // doesn't matter since error
			this.statusCode = statusCode;
			this.error = errorMessage;
		}
		
		
		public String toString() {
			if (statusCode / 100 == 2) {  // too cute?
				return "Result(" + result + ")";
			} else {
				return "ErrorResult(" + statusCode + ", err=" + error + ")";
			}
		}
	}