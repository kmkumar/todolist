/**
 * 
 */
package com.rackspace.services.util.exception;

/**
 * @author 185577
 * 
 */
public class BaseException extends RuntimeException {

	private String errMsg = null;

	private String errCode = null;

	/**
	 * 
	 */
	public BaseException() {
	}

	/**
	 * @param message
	 */
	public BaseException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public BaseException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public BaseException(String message, Throwable cause, String errCode) {
		super(message, cause);
		this.errCode = errCode;
		this.errMsg = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public String getErrCode() {
		return errCode;
	}
}
