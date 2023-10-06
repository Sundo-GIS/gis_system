package com.gis.service.member;

import com.gis.dto.member.MemberDto;

public interface IMemberService {

	/**
	 * 회원 Id 조회 : 로그인 시 & 회원가입 시 중복된 아이디 찾기 사용
	 * @author 임연서
	 */
    public MemberDto findByLoginId(String loginId);

	/**
	 * 회원가입 
	 * @author 임연서
	 */
    public void insertMember(MemberDto memberDTO);
    
	/**
	 * 회원가입 시 중복된 아이디 조회
	 * @author 임연서
	 */
    public boolean existMember(String loginId);
}




