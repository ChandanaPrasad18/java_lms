package com.te.lms.dto;

import java.util.List;

import javax.persistence.Entity;

import com.te.lms.entity.Book;
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

public class PublisherDTO {

	private String publisherName;
	private String location;
}
