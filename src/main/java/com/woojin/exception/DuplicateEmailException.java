package com.woojin.exception;


public class DuplicateEmailException extends Exception {
	public DuplicateEmailException(String message) {
		super(message);
	}
}
