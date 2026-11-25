package in.om.excetion;

import java.time.LocalDateTime;

/**
 * 
 */
public class ExceptionResponse {

	private int status;
	private String message;
	private LocalDateTime timestamp;

	public ExceptionResponse() {

	}

	public ExceptionResponse(int status, String message, LocalDateTime timestamp) {
		this.status = status;
		this.message = message;
		this.timestamp = timestamp;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

}
