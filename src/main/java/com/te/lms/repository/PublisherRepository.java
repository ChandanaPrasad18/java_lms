package com.te.lms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.lms.entity.Book;
import com.te.lms.entity.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, String> {

	Optional<Publisher> findByPublisherNameAndLocation(String publisherName, String location);

	Publisher findByPublisherName(String publisherName);

}
