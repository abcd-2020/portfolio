package com.study.member;

import java.util.List;
import java.util.Map;

public interface MemberService {

	int duplicatedId(String id);

	int duplicatedEmail(String email);

	int create(MemberVO vo);

	int loginCheck(Map<String, String> map);

	String getGrade(String id);

	MemberVO read(String id);

	int update(MemberVO vo);

	int total(Map map);

	List<MemberVO> list(Map map);

	int updateFile(Map map);
	
	int updatePw(MemberVO vo);
	
	int passwd(Map map);
}
