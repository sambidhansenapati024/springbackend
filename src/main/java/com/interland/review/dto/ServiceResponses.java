package com.interland.review.dto;

import java.util.List;

import com.interland.review.entity.Subscription;



public class ServiceResponses {

	public ServiceResponses(String code,String message, List<Object> details) {
		super();
		this.message = message;
		this.details = details;
		this.code = code;
	}

	public ServiceResponses(String string, List<Subscription> getStudent) {
	}

	// General error message about nature of error
	private String message;

	private String code;

	// Specific errors in API request processing
	private List<Object> details;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Object> getDetails() {
		return details;
	}

	public void setDetails(List<Object> details) {
		this.details = details;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}

