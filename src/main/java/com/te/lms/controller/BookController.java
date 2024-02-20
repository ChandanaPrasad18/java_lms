package com.te.lms.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.te.lms.constant.BookConstant;
import com.te.lms.dto.AuthorDTO;
import com.te.lms.dto.BookDTO;
import com.te.lms.dto.PublisherDTO;
import com.te.lms.entity.Author;
import com.te.lms.entity.Book;
import com.te.lms.entity.Genre;
import com.te.lms.entity.Member;
import com.te.lms.entity.Publisher;
import com.te.lms.response.SuccessResponse;
import com.te.lms.service.BookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1")
@RestController
public class BookController {

	private final BookService bookService;

	@GetMapping(path = "/pattern")
	public BookDTO getJson(BookDTO bookDTO) {
		log.info("Pattern checking");
		return BookDTO.builder().author(AuthorDTO.builder().build()).publisher(PublisherDTO.builder().build())
				.genres(new ArrayList<String>()).publicationDate(LocalDate.now()).build();
	}

	@PostMapping(path = "/book")
	public ResponseEntity<SuccessResponse<String>> saveBook(@RequestBody BookDTO bookDTO) {
		log.info("Saving books methhod is called");
		String bookId = bookService.saveBook(bookDTO);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(SuccessResponse.<String>builder().data(bookId).message(BookConstant.BOOK_SAVED).build());
	}

	@GetMapping(path = "/book")
	public ResponseEntity<SuccessResponse<BookDTO>> getBook(@RequestParam("title") String title) {
		BookDTO bookDTO = bookService.getBook(title);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(SuccessResponse.<BookDTO>builder().data(bookDTO).message(BookConstant.BOOK_FOUND).build());
	}

	@GetMapping(path = "/books")
	public ResponseEntity<SuccessResponse<List<BookDTO>>> getBooks() {
		List<BookDTO> bookDTO = bookService.getBook();
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(SuccessResponse.<List<BookDTO>>builder().data(bookDTO).message(BookConstant.BOOK_FOUND).build());
	}

	@GetMapping(path = "/members")
	public ResponseEntity<SuccessResponse<List<String>>> getMembers(@RequestParam("title") String title) {
		List<String> members = bookService.getMembers(title);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(SuccessResponse.<List<String>>builder().data(members)
				.message("List of members who borrowed loan").build());

	}
}
