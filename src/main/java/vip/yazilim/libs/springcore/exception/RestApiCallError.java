package vip.yazilim.libs.springcore.exception;

public class RestApiCallError extends RuntimeException {
	
	private static final long serialVersionUID = -1855909767677656982L;

	public <E, ID> RestApiCallError(Exception e) {
		super("API call returned an error message", e);
	}
}
