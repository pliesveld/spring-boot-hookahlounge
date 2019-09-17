package tutorial.controller;

import java.util.List;

public class ErrorResponse {

	private String reason;
	private List<String> details;

	public ErrorResponse(String string, List<String> details) {
		this.reason = string;
		this.details = details;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}

	
}
