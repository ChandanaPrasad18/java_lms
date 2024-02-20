package com.te.lms.utils;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.te.lms.dto.AuthorDTO;
import com.te.lms.dto.BookDTO;
import com.te.lms.dto.PublisherDTO;
import com.te.lms.entity.Author;
import com.te.lms.entity.Book;
import com.te.lms.entity.Genre;
import com.te.lms.entity.Publisher;

public class BookUtil {
	public static Book convertBookDTOToEntity(BookDTO bookDTO) {
		Book book = Book.builder().bookId(UUID.randomUUID().toString()).title(bookDTO.getTitle())
				.publicationDate(bookDTO.getPublicationDate())
				.author(Author.builder().authorId(UUID.randomUUID().toString())
						.firstName(bookDTO.getAuthor().getFirstName()).lastName(bookDTO.getAuthor().getLastName())
						.dateOfBirth(bookDTO.getAuthor().getDateOfBirth()).country(bookDTO.getAuthor().getCountry())
						.build())
				.publisher(Publisher.builder().publisherId(UUID.randomUUID().toString())
						.publisherName(bookDTO.getPublisher().getPublisherName())
						.location(bookDTO.getPublisher().getLocation()).build())
				.genres(bookDTO.getGenres().stream()
						.map(genreName -> Genre.builder().name(genreName).genreId(UUID.randomUUID().toString()).build())
						.collect(Collectors.toList()))
				.build();
		return book;

	}

	public static BookDTO convertBookToBookDTO(Book bookFromDB) {
		return BookDTO.builder().title(bookFromDB.getTitle()).publicationDate(bookFromDB.getPublicationDate())
				.author(AuthorDTO.builder().firstName(bookFromDB.getAuthor().getFirstName())
						.lastName(bookFromDB.getAuthor().getLastName())
						.dateOfBirth(bookFromDB.getAuthor().getDateOfBirth())
						.country(bookFromDB.getAuthor().getCountry()).build())
				.publisher(PublisherDTO.builder().publisherName(bookFromDB.getPublisher().getPublisherName())
						.location(bookFromDB.getPublisher().getLocation()).build())
				.genres(bookFromDB.getGenres().stream().map(g -> g.getName()).collect(Collectors.toList())).build();
	}
}
