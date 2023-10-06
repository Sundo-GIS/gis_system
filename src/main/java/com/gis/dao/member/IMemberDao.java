package com.gis.dao.member;

import org.apache.ibatis.annotations.Mapper;

import com.gis.dto.member.MemberDto;

@Mapper
public interface IMemberDao {

	/**
	 * 회원 Id 조회 : 로그인 시 & 회원가입 시 중복된 아이디 찾기 사용
	 * @author 임연서
	 */
	MemberDto findByLoginId(String loginId);
	
	/**
	 * 회원가입
	 * @author 임연서
	 */
	void insertMember(MemberDto memberDTO);
}
