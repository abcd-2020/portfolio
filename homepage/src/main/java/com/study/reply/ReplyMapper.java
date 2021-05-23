package com.study.reply;

import java.util.List;
import java.util.Map;



public interface ReplyMapper {

	int create(ReplyVO vo);
	List<ReplyVO> list(Map map);
	int total(int bbsno);
	ReplyVO read(int rnum);
	int update(ReplyVO vo);
	int delete(int rnum);
	int rcount(int bbsno);
	int bdelete(int bbsno);
}
