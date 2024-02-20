package com.te.lms.exception.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.te.lms.exception.BookTitleNotAvailableException;
import com.te.lms.response.ErrorResponse;

@RestControllerAdvice
public class BookControllerAdvice {
	@ExceptionHandler(value = { BookTitleNotAvailableException.class })
	public ErrorResponse handler(BookTitleNotAvailableException exe) {
		return ErrorResponse.builder()
				.error(exe.getMessage())
				.build();
	}
}
