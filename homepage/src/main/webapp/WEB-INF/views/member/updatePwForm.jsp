<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <title>패스워드수정</title>
  <meta charset="utf-8">
  <style type="text/css">
  	#need, #emailcheck{
  		color:red;
  	}
  </style>
  <script type="text/javascript">
  


</script>
 
</head>
<body>
<div class="container">

<h2 class="col-sm-offset-2 col-sm-10">패스워드수정</h2>
<label class="col-sm-offset-2 col-sm-10">(<span id="need">*</span> 필수입력사항)</label>
  <form class="form-horizontal" 
        action="updatePw"
        method="post"
        name = 'frm'
        onsubmit="return inCheck(this)"
        >
     <input type="hidden" name="id" value="${memberVO.id}">
    <div class="form-group">
      <label class="control-label col-sm-2" for="id">아이디</label>
      <div class="col-sm-3">          
       ${memberVO.id}
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="mname">이름</label>
      <div class="col-sm-4">          
        ${memberVO.mname}
      </div>
    </div>
    
    <div class="form-group">
       <label class="control-label col-sm-2" for="passwd"><span id="need">*</span>새로운패스워드</label>
      <div class="col-sm-3">          
        <input type="password" class="form-control" id="passwd" 
        value=""   name="passwd"> 영문과 숫자를 조합하세요
      </div>
      </div>
      <div class="form-group">
      <label class="control-label col-sm-2" for="passwd"><span id="need">*</span>패스워드확인</label>
      <div class="col-sm-3">          
        <input type="password" class="form-control" id="passwd" 
        value=""   name="passwd"> 다시입력하세요
      </div>
      </div>
    
      

    
  
       
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-5">
        <button type="submit" class="btn btn-default">수정</button>
        <button type="reset" class="btn btn-default">취소</button>
      </div>
    </div>
  </form>

<br><br>
</div>
</body>
</html>