package com.study.member;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("com.study.member.MemberServiceImpl")
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper mapper;
	
	@Override
	public int duplicatedId(String id) {
		// TODO Auto-generated method stub
		return mapper.duplicatedId(id);
	}

	@Override
	public int duplicatedEmail(String email) {
		// TODO Auto-generated method stub
		return mapper.duplicatedEmail(email);
	}

	@Override
	public int create(MemberVO vo) {
		// TODO Auto-generated method stub
		return mapper.create(vo);
	}

	@Override
	public int loginCheck(Map<String, String> map) {
		// TODO Auto-generated method stub
		return mapper.loginCheck(map);
	}

	@Override
	public String getGrade(String id) {
		// TODO Auto-generated method stub
		return mapper.getGrade(id);
	}

	@Override
	public MemberVO read(String id) {
		// TODO Auto-generated method stub
		return mapper.read(id);
	}

	@Override
	public int update(MemberVO vo) {
		// TODO Auto-generated method stub
		return mapper.update(vo);
	}

	@Override
	public int total(Map map) {
		// TODO Auto-generated method stub
		return mapper.total(map);
	}

	@Override
	public List<MemberVO> list(Map map) {
		// TODO Auto-generated method stub
		return mapper.list(map);
	}

	@Override
	public int updateFile(Map map) {
		// TODO Auto-generated method stub
		return mapper.updateFile(map);
	}

	@Override
	public int updatePw(MemberVO vo) {
		// TODO Auto-generated method stub
		return mapper.updatePw(vo);
	}

	@Override
	public int passwd(Map map) {
		// TODO Auto-generated method stub
		return mapper.passwd(map);
	}

}
