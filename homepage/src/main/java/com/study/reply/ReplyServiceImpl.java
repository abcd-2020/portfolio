package com.study.reply;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("com.study.reply.ReplyServiceImpl")
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	private ReplyMapper mapper;

	@Override
	public int create(ReplyVO vo) {
		// TODO Auto-generated method stub
		return mapper.create(vo);
	}

	@Override
	public List<ReplyVO> list(Map map) {
		// TODO Auto-generated method stub
		return mapper.list(map);
	}

	@Override
	public int total(int bbsno) {
		// TODO Auto-generated method stub
		return mapper.total(bbsno);
	}

	@Override
	public ReplyVO read(int rnum) {
		// TODO Auto-generated method stub
		return mapper.read(rnum);
	}

	@Override
	public int update(ReplyVO vo) {
		// TODO Auto-generated method stub
		return mapper.update(vo);
	}

	@Override
	public int delete(int rnum) {
		// TODO Auto-generated method stub
		return mapper.delete(rnum);
	}

	@Override
	public int rcount(int bbsno) {
		// TODO Auto-generated method stub
		return mapper.rcount(bbsno);
	}

	@Override
	public int bdelete(int bbsno) {
		// TODO Auto-generated method stub
		return mapper.bdelete(bbsno);
	}

}
