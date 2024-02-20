package com.te.lms.dto;

import java.time.LocalDate;
import java.util.List;

import com.te.lms.entity.Author;
import com.te.lms.entity.Book;
import com.te.lms.entity.Genre;
import com.te.lms.entity.Loan;
import com.te.lms.entity.Publisher;

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
public class AuthorDTO {
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private String country;
}
