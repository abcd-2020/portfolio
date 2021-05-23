<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:set var="root" value="${pageContext.request.contextPath}" />
<c:choose>
	<c:when test="${not empty sessionScope.id && sessionScope.grade =='A'}"> <!-- 로그인 했는데.. -->
		<c:set var="str">관리자 페이지입니다..</c:set>
	</c:when>
	<c:when test="${not empty sessionScope.id && sessionScope.grade !='A'}">
		<c:set var='str'>
		<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-stars" viewBox="0 0 16 16">
  <path d="M7.657 6.247c.11-.33.576-.33.686 0l.645 1.937a2.89 2.89 0 0 0 1.829 1.828l1.936.645c.33.11.33.576 0 .686l-1.937.645a2.89 2.89 0 0 0-1.828 1.829l-.645 1.936a.361.361 0 0 1-.686 0l-.645-1.937a2.89 2.89 0 0 0-1.828-1.828l-1.937-.645a.361.361 0 0 1 0-.686l1.937-.645a2.89 2.89 0 0 0 1.828-1.828l.645-1.937zM3.794 1.148a.217.217 0 0 1 .412 0l.387 1.162c.173.518.579.924 1.097 1.097l1.162.387a.217.217 0 0 1 0 .412l-1.162.387A1.734 1.734 0 0 0 4.593 5.69l-.387 1.162a.217.217 0 0 1-.412 0L3.407 5.69A1.734 1.734 0 0 0 2.31 4.593l-1.162-.387a.217.217 0 0 1 0-.412l1.162-.387A1.734 1.734 0 0 0 3.407 2.31l.387-1.162zM10.863.099a.145.145 0 0 1 .274 0l.258.774c.115.346.386.617.732.732l.774.258a.145.145 0 0 1 0 .274l-.774.258a1.156 1.156 0 0 0-.732.732l-.258.774a.145.145 0 0 1-.274 0l-.258-.774a1.156 1.156 0 0 0-.732-.732L9.1 2.137a.145.145 0 0 1 0-.274l.774-.258c.346-.115.617-.386.732-.732L10.863.1z"/>
</svg>
		안녕하세요. ${sessionScope.id}님 !</c:set>
	</c:when>
	<c:otherwise>
	<c:set var="str">반갑습니다.로그인 해주세요.</c:set>
	</c:otherwise>
</c:choose>

<!DOCTYPE html>
<html>
<head>

<title>memo</title>
<meta charset="utf-8">

	<title>Movement that inspires</title>

	<link href="/css/style.css" rel="stylesheet">
	
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<style type="text/css">
#grade {
	color: #779dba;
}
</style>

</head>
<body>


	<!--상단메뉴-->
		<div class="sticky_wrap">
		<div class="navbar sticky-top">
			<a href="${root}/">Home</a>
			<a href="${root}/bbs/list">Board list</a>
			<a id="grade" class="right">${str}</a>
			<c:choose>
				<c:when test="${empty sessionScope.id }">
			<a href="${root}/member/agree">Sign up</a>
			<a href="${root}/member/login">Sign in</a>
			
			</c:when>
				<c:otherwise>
				<a href="${root}/member/read">My Info</a>
				<a href="${root}/member/update">Update</a>
				<a href="${root}/member/logout">logout</a>
				</c:otherwise>
			</c:choose>
			<c:if test="${not empty sessionScope.id && sessionScope.grade =='A'}">
			
			</c:if>
		</div>
	
	
	
</body>
</html>