package br.com.agenda.util;

public class HTTPResponse {

	private boolean success;
	private String message;
	private Object data;

	private HTTPResponse(String message, boolean success, Object data) {
		this.message = message;
		this.success = success;
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static HTTPResponse error(String message) {
		return new HTTPResponse(message, false, null);
	}

	public static HTTPResponse error(String message, Object data) {
		return new HTTPResponse(message, false, data);
	}

	public static HTTPResponse error(Object data) {
		return new HTTPResponse("", false, data);
	}

	public static HTTPResponse sucess(String message) {
		return new HTTPResponse(message, true, null);
	}

	public static HTTPResponse sucess(String message, Object data) {
		return new HTTPResponse(message, true, data);
	}

	public static HTTPResponse sucess(Object data) {
		return new HTTPResponse("", true, data);
	}

}
