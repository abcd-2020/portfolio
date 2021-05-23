package com.study.bbs;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BbsVO  {
	
	private int bbsno;
	private String wname;
	private String title;
	private String content;
	private String passwd;
	private int Viewcnt;
	
	private String wdate;
	
	private int grpno;
	private int indent;
	private int ansnum;
	private String filename;
	private int filesize;
	
	private MultipartFile filenameMF;
	
	
}
