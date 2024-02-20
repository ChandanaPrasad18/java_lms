package com.te.lms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.te.lms.dto.BookDTO;
import com.te.lms.entity.Book;
import com.te.lms.entity.Publisher;
import com.te.lms.repository.PublisherRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PublisherServiceImpl implements PublisherService {

	private final PublisherRepository publisherRepository;

	@Override
	public List getBooks(String publisherName) {
		Publisher publisherFromDB = publisherRepository.findByPublisherName(publisherName);
		List<Book> books = publisherFromDB.getBooks();
		List list = books.stream()
				.map(b -> b.getTitle()
						.concat("--> " + b.getAuthor().getFirstName().concat(" " + b.getAuthor().getLastName())))
				.collect(Collectors.toList());
		return list;
	}

}
