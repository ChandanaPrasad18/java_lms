package com.te.lms.dto;

import java.time.LocalDate;
import java.util.List;

import com.te.lms.entity.Book;
import com.te.lms.entity.Genre;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookDTO {
	private String title;
	private LocalDate publicationDate;
	private AuthorDTO author;
	private PublisherDTO publisher;
	private List<String> genres;
}
