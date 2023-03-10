package com.ts.mvc.infra.exception;

import com.ts.mvc.infra.code.ErrorCode;

public class HandlableException extends RuntimeException{

	private static final long serialVersionUID = 1073263278744244571L;
	
	public ErrorCode error;
	
	public HandlableException(ErrorCode error) {
		this.error = error;
	}
	
	public HandlableException(ErrorCode error, Exception e) {
		this.error = error;
		e.printStackTrace();
	}

}
