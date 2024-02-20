package com.te.lms.utils;

import java.util.UUID;

import com.te.lms.dto.MemberDTO;
import com.te.lms.entity.Member;

public class MemberUtil {

	public static Member convertMemberDTOToMemberEntity(MemberDTO memberDTO) {
		return Member.builder().memberId(UUID.randomUUID().toString()).firstName(memberDTO.getFirstName())
				.lastName(memberDTO.getLastName()).dateOfBirth(memberDTO.getDateOfBirth()).mailId(memberDTO.getMailId())
				.build();

	}

	public static MemberDTO convertMemberEntityToMemberDTO(Member memberFromDB) {
		return MemberDTO.builder().firstName(memberFromDB.getFirstName()).lastName(memberFromDB.getLastName())
				.dateOfBirth(memberFromDB.getDateOfBirth()).mailId(memberFromDB.getMailId()).build();

	}
}
