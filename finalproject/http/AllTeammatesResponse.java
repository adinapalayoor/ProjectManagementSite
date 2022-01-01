package com.nildesperandumcs3733.finalproject.http;

import java.util.ArrayList;
import java.util.List;

import com.nildespernadumcs3733.finalproject.model.Project;
import com.nildespernadumcs3733.finalproject.model.Teammate;

public class AllTeammatesResponse {
	public final List<Teammate> list;
	public final int statusCode;
	public final String error;

	public AllTeammatesResponse(List<Teammate> list, int code) {
		this.list = list;
		this.statusCode = code;
		this.error = "";
	}

	public AllTeammatesResponse(int code, String errorMessage) {
		this.list = new ArrayList<Teammate>();
		this.statusCode = code;
		this.error = errorMessage;
	}

}