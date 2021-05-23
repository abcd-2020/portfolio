package com.study.bbs;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("com.study.bbs.BbsServiceImpl")
public class BbsServiceImpl implements BbsService {
	
	@Autowired
	private BbsMapper mapper;

	@Override
	public List<BbsVO> list(Map map) {
		// TODO Auto-generated method stub
		return mapper.list(map);
	}

	@Override
	public int total(Map map) {
		// TODO Auto-generated method stub
		return mapper.total(map);
	}

	@Override
	public int create(BbsVO vo) {
		// TODO Auto-generated method stub
		return mapper.create(vo);
	}

	@Override
	public int upViewcnt(int bbsno) {
		// TODO Auto-generated method stub
		return mapper.upViewcnt(bbsno);
	}

	@Override
	public BbsVO read(int bbsno) {
		// TODO Auto-generated method stub
		return mapper.read(bbsno);
	}

	@Override
	public int update(BbsVO vo) {
		// TODO Auto-generated method stub
		return mapper.update(vo);
	}

	@Override
	public int passCheck(Map map) {
		// TODO Auto-generated method stub
		return mapper.passCheck(map);
	}

	@Override
	public int checkRefnum(int bbsno) {
		// TODO Auto-generated method stub
		return mapper.checkRefnum(bbsno);
	}

	@Override
	public int delete(int bbsno) {
		// TODO Auto-generated method stub
		return mapper.delete(bbsno);
	}

	@Override
	public BbsVO readReply(int bbsno) {
		// TODO Auto-generated method stub
		return mapper.readReply(bbsno);
	}

	@Override
	public int upAnsnum(Map map) {
		// TODO Auto-generated method stub
		return mapper.upAnsnum(map);
	}

	@Override
	public int createReply(BbsVO vo) {
		// TODO Auto-generated method stub
		return mapper.createReply(vo);
	}

}
