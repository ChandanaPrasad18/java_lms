package com.te.lms.service;

import java.util.Optional;

import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Service;

import com.te.lms.constant.MemberConstant;
import com.te.lms.dto.MemberDTO;
import com.te.lms.entity.Member;
import com.te.lms.exception.MemberAlreadyExistsException;
import com.te.lms.repository.MemberRepository;
import com.te.lms.utils.MemberUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;

	@Override
	public String saveMember(MemberDTO memberDTO) {
		log.info("its hitting saveMember mtd service layer");
		Optional<Member> memberFromDB = memberRepository.findByFirstNameAndLastNameAndDateOfBirth(
				memberDTO.getFirstName(), memberDTO.getLastName(), memberDTO.getDateOfBirth());
		if (!memberFromDB.isPresent()) {
			return memberRepository.save(MemberUtil.convertMemberDTOToMemberEntity(memberDTO)).getMemberId();
		}
		throw new MemberAlreadyExistsException(MemberConstant.MEMBER_ALREADY_EXISTS);
	}

	@Override
	public MemberDTO getMember(String mailId) {
		Optional<Member> memberFromDB = memberRepository.findByMailId(mailId);
		Member member = memberFromDB.get();
		MemberDTO memberDTO = MemberUtil.convertMemberEntityToMemberDTO(member);
		return memberDTO;
	}

}
