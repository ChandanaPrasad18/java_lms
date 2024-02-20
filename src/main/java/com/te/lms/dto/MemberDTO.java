package com.te.lms.dto;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;

import com.te.lms.entity.Loan;
import com.te.lms.entity.Member;

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

public class MemberDTO {
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private String mailId;
}
