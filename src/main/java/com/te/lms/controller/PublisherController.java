package com.te.lms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.te.lms.dto.BookDTO;
import com.te.lms.response.SuccessResponse;
import com.te.lms.service.PublisherService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
public class PublisherController {

	private final PublisherService publisherService;

	@GetMapping(path = "/getbookdetails")
	public ResponseEntity<SuccessResponse<List>> getBooks(@RequestParam("publisherName") String publisherName) {
		List list = publisherService.getBooks(publisherName);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(SuccessResponse.<List>builder().data(list)
				.message("here are the details of books for the publisher mentioned").build());

	}
}
