package com.nable.crib.pdf.response;

import com.nable.crib.pdf.response.StatusCodeConstant;

public enum Status implements StatusCodeConstant {
	
	SUCCESS(CODE_S200, "Success"),
	ERROR(CODE_E500, "Error");
	
	private final String statusCode;
	private final String status;
	
	Status(String statusCode, String status) {
		this.statusCode = statusCode;
		this.status = status;
	}

	public String getStatusCode() {
		return this.statusCode;
	}

	public String getStatus() {
		return this.status;
	}

}
