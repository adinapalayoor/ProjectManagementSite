

package com.nildesperandumcs3733.finalproject.http;

import java.util.ArrayList;
import java.util.List;

import com.nildespernadumcs3733.finalproject.model.Project;

public class ViewTaskResponse {
	public final Project project;
	public final int statusCode;
	public final String error;

	public ViewTaskResponse(Project project, int code) {
		this.project = project;
		this.statusCode = code;
		this.error = "";
	}

	public ViewTaskResponse(int code, String errorMessage) {
		this.project = null;
		this.statusCode = code;
		this.error = errorMessage;
	}

}

