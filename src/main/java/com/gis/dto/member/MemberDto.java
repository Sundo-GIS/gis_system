package com.gis.dto.member;

import lombok.Data;

@Data
public class MemberDto {

	private String loginId;
	private String password;
	private String salt; // 비밀번호 해싱용 솔트
}
