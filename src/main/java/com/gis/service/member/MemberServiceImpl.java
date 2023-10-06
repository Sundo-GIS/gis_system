package com.gis.service.member;

import org.springframework.stereotype.Service;

import com.gis.dao.member.IMemberDao;
import com.gis.dto.member.MemberDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

// 로그인 정보를 데이터베이스에서 가져오기 위해 MyBatis 매퍼를 사용
@Log4j2
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements IMemberService{

    private final IMemberDao memberDao;
    
	/**
	 * 회원 Id 조회 : 로그인 시 & 회원가입 시 중복된 아이디 찾기 사용
	 * @author 임연서
	 */
    @Override
    public MemberDto findByLoginId(String loginId) {
        return memberDao.findByLoginId(loginId);
    }
    
	/**
	 * 회원가입
	 * @author 임연서
	 */
    @Override
    public void insertMember(MemberDto memberDTO) {
    	memberDao.insertMember(memberDTO);
    }
    
	/**
	 * 회원가입 시 중복된 아이디 조회 
	 * @author 임연서
	 */
    @Override
    public boolean existMember(String loginId) {
    	// 데이터베이스에 존재하는 로그인 아이디 확인하여 변수에 담음
        MemberDto existMember = memberDao.findByLoginId(loginId);
        // 동일한 아이디가 데이터베이스에 존재하는 경우(true)
        return existMember != null;
    }
}




