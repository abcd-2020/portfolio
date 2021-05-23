/**********************************/
/* Table Name: 게시판 */
/**********************************/
CREATE TABLE bbs(
		bbsno                         		NUMBER(7)		 NOT NULL,
		wname                         		VARCHAR2(20)		 NOT NULL,
		title                         		VARCHAR2(100)		 NOT NULL,
		content                       		VARCHAR2(4000)		 NOT NULL,
		passwd                        		VARCHAR2(15)		 NOT NULL,
		viewcnt                       		NUMBER(5)		 NULL ,
		wdate                         		DATE		 NOT NULL,
		grpno                         		NUMBER(7)		 NULL ,
		indent                        		NUMBER(2)		 NULL ,
		ansnum                        		NUMBER(5)		 NULL ,
		filename                      		VARCHAR2(50)		 NULL ,
		filesize                      		NUMBER(7)		 NULL ,
		refnum                        		NUMBER(7)		 NULL 
);

COMMENT ON TABLE bbs is '게시판';
COMMENT ON COLUMN bbs.bbsno is '게시판글번호';
COMMENT ON COLUMN bbs.wname is '글쓴이';
COMMENT ON COLUMN bbs.title is '제목';
COMMENT ON COLUMN bbs.content is '글내용';
COMMENT ON COLUMN bbs.passwd is '게시글비밀번호';
COMMENT ON COLUMN bbs.viewcnt is '조회수';
COMMENT ON COLUMN bbs.wdate is '게시글쓴날짜';
COMMENT ON COLUMN bbs.grpno is '게시글그룹번호';
COMMENT ON COLUMN bbs.indent is '답변차수';
COMMENT ON COLUMN bbs.ansnum is '답변순서';
COMMENT ON COLUMN bbs.filename is '파일이름';
COMMENT ON COLUMN bbs.filesize is '파일크기';
COMMENT ON COLUMN bbs.refnum is '참조번호';


/**********************************/
/* Table Name: 회원 */
/**********************************/
CREATE TABLE member2(
		id                            		VARCHAR2(10)		 NOT NULL,
		passwd                        		VARCHAR2(20)		 NOT NULL,
		mname                         		VARCHAR2(20)		 NOT NULL,
		tel                           		VARCHAR2(14)		 NULL ,
		email                         		VARCHAR2(50)		 NOT NULL,
		zipcode                       		VARCHAR2(7)		 NULL ,
		address1                      		VARCHAR2(150)		 NULL ,
		address2                      		VARCHAR2(50)		 NULL ,
		job                           		VARCHAR2(20)		 NOT NULL,
		mdate                         		DATE		 NOT NULL,
		fname                         		VARCHAR2(50)		 NULL ,
		grade                         		CHAR(1)		 NULL 
);

COMMENT ON TABLE member2 is '회원';
COMMENT ON COLUMN member2.id is 'id';
COMMENT ON COLUMN member2.passwd is '패스워드';
COMMENT ON COLUMN member2.mname is '이름';
COMMENT ON COLUMN member2.tel is 'tel';
COMMENT ON COLUMN member2.email is '이메일';
COMMENT ON COLUMN member2.zipcode is '우편번호';
COMMENT ON COLUMN member2.address1 is '주소';
COMMENT ON COLUMN member2.address2 is '상세주소';
COMMENT ON COLUMN member2.job is '직업';
COMMENT ON COLUMN member2.mdate is '가입일';
COMMENT ON COLUMN member2.fname is '파일이름';
COMMENT ON COLUMN member2.grade is '등급';


