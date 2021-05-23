package com.study.bbs;

import java.util.List;
import java.util.Map;

public interface BbsService {
	List<BbsVO> list(Map map);
	int total(Map map);
	int create (BbsVO vo);
	int upViewcnt(int bbsno);
	BbsVO read(int bbsno);
	int update(BbsVO vo);
	int passCheck(Map map);
	int checkRefnum(int bbsno);
	int delete(int bbsno);
	BbsVO readReply(int bbsno);
	int upAnsnum(Map map);
	int createReply(BbsVO vo);

}
