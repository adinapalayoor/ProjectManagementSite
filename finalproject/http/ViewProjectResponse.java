

package com.nildesperandumcs3733.finalproject.http;

import java.util.ArrayList;
import java.util.List;

import com.nildespernadumcs3733.finalproject.model.Project;

public class ViewProjectResponse {
	public final Project project;
	public final int statusCode;
	public final String error;

	public ViewProjectResponse(Project project, int code) {
		this.project = project;
		this.statusCode = code;
		this.error = "";
	}

	public ViewProjectResponse(int code, String errorMessage) {
		this.project = null;
		this.statusCode = code;
		this.error = errorMessage;
	}

}

