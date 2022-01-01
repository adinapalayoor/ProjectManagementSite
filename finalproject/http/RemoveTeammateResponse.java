package com.nildesperandumcs3733.finalproject.http;

public class RemoveTeammateResponse {
	public final String teammateName;
	public final int statusCode;
	public final String error;
	
	public RemoveTeammateResponse (String name, int statusCode) {
		this.teammateName = name;
		this.statusCode = statusCode;
		this.error = "";
	}
	
	// 200 means success
	public RemoveTeammateResponse (String teammateName, int statusCode, String errorMessage) {
		this.statusCode = statusCode;
		this.error = errorMessage;
		this.teammateName = teammateName;
	}
}