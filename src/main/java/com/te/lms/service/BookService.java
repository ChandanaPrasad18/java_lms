package com.te.lms.service;

import java.util.List;
import java.util.Optional;

import com.te.lms.dto.BookDTO;
import com.te.lms.entity.Book;
import com.te.lms.entity.Member;

public interface BookService {

	String saveBook(BookDTO bookDTO);

	BookDTO getBook(String title);

	List<BookDTO> getBook();

	List<String> getMembers(String title);

}
