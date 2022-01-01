package com.nildesperandumcs3733.finalproject.http;

import java.util.ArrayList;
import java.util.List;

import com.nildespernadumcs3733.finalproject.model.Project;
import com.nildespernadumcs3733.finalproject.model.Task;

public class AllTasksResponse {
	public final List<Task> list;
	public final int statusCode;
	public final String error;

	public AllTasksResponse(List<Task> list, int code) {
		this.list = list;
		this.statusCode = code;
		this.error = "";
	}

	public AllTasksResponse(int code, String errorMessage) {
		this.list = new ArrayList<Task>();
		this.statusCode = code;
		this.error = errorMessage;
	}

}