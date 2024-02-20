package com.te.lms.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.te.lms.constant.BookConstant;
import com.te.lms.dto.BookDTO;
import com.te.lms.entity.Author;
import com.te.lms.entity.Book;
import com.te.lms.entity.Loan;
import com.te.lms.entity.Member;
import com.te.lms.entity.Publisher;
import com.te.lms.exception.BookTitleNotAvailableException;
import com.te.lms.repository.AuthorRepository;
import com.te.lms.repository.BookRepository;
import com.te.lms.repository.PublisherRepository;
import com.te.lms.utils.BookUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;
	private final AuthorRepository authorRepository;
	private final PublisherRepository publisherRepository;

	@Transactional
	@Override
	public String saveBook(BookDTO bookDTO) {
		Optional<Book> bookFromDB = bookRepository.findByTitle(bookDTO.getTitle());
		Optional<Author> authorFromDB = authorRepository.findByFirstNameAndLastNameAndDateOfBirthAndCountry(
				bookDTO.getAuthor().getFirstName(), bookDTO.getAuthor().getLastName(),
				bookDTO.getAuthor().getDateOfBirth(), bookDTO.getAuthor().getCountry());

		Optional<Publisher> publisherFromDB = publisherRepository.findByPublisherNameAndLocation(
				bookDTO.getPublisher().getPublisherName(), bookDTO.getPublisher().getLocation());

		if (!bookFromDB.isPresent()) {
			Book book = BookUtil.convertBookDTOToEntity(bookDTO);
			Author author = null;
			Publisher publisher = null;
			if (authorFromDB.isPresent()) {
				author = authorFromDB.get();
				book.setAuthor(author);
			}
			if (publisherFromDB.isPresent()) {
				publisher = publisherFromDB.get();
				book.setPublisher(publisher);
			}
			bookRepository.save(book);
			return book.getBookId();
		}
		throw new BookTitleNotAvailableException(BookConstant.BOOK_TITLE_ALREADY_EXISTS);
	}

	@Override
	public BookDTO getBook(String title) {
		Optional<Book> bookFromDB = bookRepository.findByTitle(title);
		Book book = bookFromDB.get();
		BookDTO bookDTO = BookUtil.convertBookToBookDTO(book);
		return bookDTO;
	}

	@Override
	public List<BookDTO> getBook() {
		List<Book> bookFromDB = bookRepository.findAll();
		List<BookDTO> bookDTO = bookFromDB.stream().map(c -> BookUtil.convertBookToBookDTO(c))
				.collect(Collectors.toList());
		return bookDTO;
	}

	@Override
	public List<String> getMembers(String title) {
		Optional<Book> findByTitle = bookRepository.findByTitle(title);
		Book book = findByTitle.get();
		List<Loan> loans = book.getLoans();
		List<String> list = loans.stream().map(c -> "Members who borrowed the given book--> "
				+ c.getMembers().get(0).getFirstName() + c.getMembers().get(0).getLastName())
				.collect(Collectors.toList());
		return list;
	}
}
