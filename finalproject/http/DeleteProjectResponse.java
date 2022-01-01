package com.nildesperandumcs3733.finalproject.http;

public class DeleteProjectResponse {
	public final String projectName;
	public final int statusCode;
	public final String error;
	
	public DeleteProjectResponse (String name, int statusCode) {
		this.projectName = name;
		this.statusCode = statusCode;
		this.error = "";
	}
	
	// 200 means success
	public DeleteProjectResponse (String name, int statusCode, String errorMessage) {
		this.statusCode = statusCode;
		this.error = errorMessage;
		this.projectName = name;
	}

}
