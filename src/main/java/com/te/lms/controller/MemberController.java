package com.te.lms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.te.lms.constant.MemberConstant;
import com.te.lms.dto.MemberDTO;
import com.te.lms.response.SuccessResponse;
import com.te.lms.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1")
@RestController

public class MemberController {

	private final MemberService memberService;

	@PostMapping(path = "/member")
	public ResponseEntity<SuccessResponse<String>> saveMember(@RequestBody MemberDTO memberDTO) {
		log.info("service method called");
		String memberId = memberService.saveMember(memberDTO);
		log.info("");
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(SuccessResponse.<String>builder().data(memberId).message(MemberConstant.MEMBER_SAVED).build());
	}

//	@GetMapping(path = "/member")
//	public MemberDTO getJSON(MemberDTO memberDTO) {
//		return MemberDTO.builder().build();
//	}

	@GetMapping(path = "/member")
	public ResponseEntity<SuccessResponse<MemberDTO>> getMember(@RequestParam("mailId") String mailId) {
		MemberDTO memberDTO = memberService.getMember(mailId);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(
				SuccessResponse.<MemberDTO>builder().data(memberDTO).message(MemberConstant.MEMBER_FOUND).build());

	}
}
