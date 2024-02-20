package com.te.lms.service;

import java.util.List;

import com.te.lms.dto.BookDTO;

public interface PublisherService {

	List getBooks(String publisherName);

}
