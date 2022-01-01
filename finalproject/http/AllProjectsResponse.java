package com.nildesperandumcs3733.finalproject.http;

import java.util.ArrayList;
import java.util.List;

import com.nildespernadumcs3733.finalproject.model.Project;

public class AllProjectsResponse {
	public final List<Project> list;
	public final int statusCode;
	public final String error;

	public AllProjectsResponse(List<Project> list, int code) {
		this.list = list;
		this.statusCode = code;
		this.error = "";
	}

	public AllProjectsResponse(int code, String errorMessage) {
		this.list = new ArrayList<Project>();
		this.statusCode = code;
		this.error = errorMessage;
	}

}
