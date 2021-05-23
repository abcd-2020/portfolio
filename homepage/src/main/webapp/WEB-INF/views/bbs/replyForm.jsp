<%@ page contentType="text/html; charset=UTF-8" %> 


<!DOCTYPE html> 
<html> 
<head>
  <title>homepage</title>
  <meta charset="utf-8">
  
 <script type="text/JavaScript">
   window.onload=function(){
     CKEDITOR.replace('content');  // <TEXTAREA>태그 id 값
  };
  function checkIn(f){
		if(f.wname.value==""){
			alert("작성자를 입력하세요")
			f.wname.focus();
			return false;
			}
		if(f.title.value==""){
			alert("제목을 입력하세요")
			f.title.focus();
			return false;
			}
		if(CKEDITOR.instances['content'].getData()==""){
			alert("내용을 입력하세요")
			CKEDITOR.instances['content'].focus();
			return false;
			}
		if(f.passwd.value==""){
			alert("비번을 입력하세요")
			f.passwd.focus();
			return false;
			}
		
	}
 </script>
</head>
<body> 
<div class="container">
<h1 class="col-sm-offset-2 col-sm-10">게시판 답변</h1>
<form class="form-horizontal" 
      action="reply"
      method="post"
      enctype="multipart/form-data"
      onsubmit="return checkIn(this)">
      
<input type="hidden" name="bbsno" value="${bbsVO.bbsno }">
<input type="hidden" name="grpno" value="${bbsVO.grpno }">
<input type="hidden" name="indent" value="${bbsVO.indent }">
<input type="hidden" name="ansnum" value="${bbsVO.ansnum }">
  <div class="form-group">
    <label class="control-label col-sm-2" for="wname">작성자</label>
    <div class="col-sm-6">
      <input type="text" name="wname" id="wname" class="form-control">
    </div>
  </div>
  <div class="form-group">
    <label class="control-label col-sm-2" for="title">제목</label>
    <div class="col-sm-8">
      <input type="text" name="title" id="title" class="form-control" value=${bbsVO.title }>
    </div>
  </div>
  
  <div class="form-group">
    <label class="control-label col-sm-2" for="content">내용</label>
    <div class="col-sm-8">
    <textarea rows="12" cols="7" id="content" name="content" class="form-control"></textarea>
    </div>
  </div>
  
  <div class="form-group">
    <label class="control-label col-sm-2" for="filenameMF">파일</label>
    <div class="col-sm-6">
      <input type="file" name="filenameMF" id="filenameMF" class="form-control">
    </div>
  </div>
  
  <div class="form-group">
    <label class="control-label col-sm-2" for="passwd">비밀번호</label>
    <div class="col-sm-6">
      <input type="password" name="passwd" id="passwd" class="form-control">
    </div>
  </div>
  
   <div class="form-group">
   <div class="col-sm-offset-2 col-sm-5">
    <button class="btn">등록</button>
    <button type="reset" class="btn">취소</button>
   </div>
 </div>
</form>
</div>
</body> 
</html> 