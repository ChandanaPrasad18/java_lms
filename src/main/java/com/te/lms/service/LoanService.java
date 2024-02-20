package com.te.lms.service;

import java.util.List;

import com.te.lms.dto.FetchBookAuthorByFNnLNDTO;
import com.te.lms.entity.Book;
import com.te.lms.entity.Member;

public interface LoanService {

	String saveLoan(String title, String mailId);

	List<String> getLoanDetails();

	List<String> getAuthors(String firstName, String lastName);

	List getBooksAuthor(FetchBookAuthorByFNnLNDTO bookAuthorByFNnLNDTO);

}
