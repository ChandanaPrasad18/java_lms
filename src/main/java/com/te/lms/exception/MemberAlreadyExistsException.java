package com.te.lms.exception;

public class MemberAlreadyExistsException extends RuntimeException {
	public MemberAlreadyExistsException(String message) {
		super(message);
	}
}
