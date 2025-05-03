package com.nable.crib.cons.response;

public enum Status implements StatusCodeConstant {
	
	SUCCESS(CODE_S200, "Success"),
	SINGLE_HIT(CODE_S201, "Single Hit response received"),
	MULTI_HIT(CODE_S202, "Multi Hit response received"),
	NO_HIT(CODE_S203, "No Hit response received"),
	ERROR_HIT(CODE_E500, "Error response received"),
	API_ERROR(CODE_E501, "API response error"),
	APPLICATION_ERROR(CODE_E502, "Application level error");
	
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
