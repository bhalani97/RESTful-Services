package com.rest.webservices.restfulwebservices.exception;

import java.util.Date;

public class ExceptionResponse {
	private String message;
	private String details;
	private Date timestamp;

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

	public ExceptionResponse(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

}
