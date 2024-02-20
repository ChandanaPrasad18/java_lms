package com.te.lms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.te.lms.dto.FetchBookAuthorByFNnLNDTO;
import com.te.lms.entity.Book;
import com.te.lms.entity.Member;
import com.te.lms.response.SuccessResponse;
import com.te.lms.service.LoanService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(path = "/api/v1")
@RestController
public class LoanController {

	private final LoanService loanService;

	@PostMapping(path = "/loan")
	public ResponseEntity<SuccessResponse<String>> saveLoan(@RequestParam("title") String title,
			@RequestParam("mailId") String mailId) {
		String loanId = loanService.saveLoan(title, mailId);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(SuccessResponse.<String>builder().data(loanId).message("loan saved").build());
	}

	@GetMapping(path = "/loanDetails")
	public ResponseEntity<SuccessResponse<List<String>>> getLoanDetails() {
		List<String> list = loanService.getLoanDetails();
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(SuccessResponse.<List<String>>builder().data(list).message("loan saved").build());
	}

	@GetMapping(path = "/authors")
	public ResponseEntity<SuccessResponse<List<String>>> getAuthors(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) {
		List<String> list = loanService.getAuthors(firstName, lastName);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(SuccessResponse.<List<String>>builder().data(list).message("here is the author's list").build());
	}

	@GetMapping(path = "/member-loanhistory")
	public ResponseEntity<SuccessResponse<List>> getBooksAuthor(
			@RequestBody FetchBookAuthorByFNnLNDTO bookAuthorByFNnLNDTO) {

		List listOfAuthors = loanService.getBooksAuthor(bookAuthorByFNnLNDTO);

		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(SuccessResponse.<List>builder().data(listOfAuthors).message("list of author").build());
	}
}
