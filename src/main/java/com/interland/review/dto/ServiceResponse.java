package com.interland.review.dto;

import java.util.List;

import org.json.simple.JSONObject;

import com.interland.review.entity.Subscription;



public class ServiceResponse {

	public ServiceResponse(String code,String message, List<JSONObject> details) {
		super();
		this.message = message;
		this.details = details;
		this.code = code;
	}

	public ServiceResponse(String string, List<Subscription> getStudent) {
	}

	// General error message about nature of error
	private String message;

	private String code;

	// Specific errors in API request processing
	private List<JSONObject> details;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<JSONObject> getDetails() {
		return details;
	}

	public void setDetails(List<JSONObject> details) {
		this.details = details;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}

