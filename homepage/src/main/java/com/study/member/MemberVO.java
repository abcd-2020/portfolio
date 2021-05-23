package com.study.member;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class MemberVO {      
	 private String id        ;
	 private String passwd    ;
	 private String  mname    ;
	 private String  tel      ;
	 private String  email    ;
	 private String zipcode   ;
	 private String  address1 ;
	 private String address2  ;
	 private String job       ;
	 private String  mdate    ;
	 private String  fname    ;
	 private String  grade    ;
	 
	 private MultipartFile fnameMF;

	
}
