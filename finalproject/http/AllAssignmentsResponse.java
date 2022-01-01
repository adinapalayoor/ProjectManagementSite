package com.nildesperandumcs3733.finalproject.http;

import java.util.ArrayList;
import java.util.List;

import com.nildespernadumcs3733.finalproject.model.Assignments;


public class AllAssignmentsResponse {
	public final List<Assignments> list;
	public final int statusCode;
	public final String error;

	public AllAssignmentsResponse(List<Assignments> list, int code) {
		this.list = list;
		this.statusCode = code;
		this.error = "";
	}

	public AllAssignmentsResponse(int code, String errorMessage) {
		this.list = new ArrayList<Assignments>();
		this.statusCode = code;
		this.error = errorMessage;
	}

}
