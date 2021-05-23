<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<script type="text/javascript">
	function read(bbsno) {
		var url = "read";
		url += "?bbsno=" + bbsno;
		url += "&col=${col}";
		url += "&word=${word}";
		url += "&nowPage=${nowPage}";
		location.href = url;

	}

	function fileDown(filename){
		var url = "./fileDown";
		url += "?filename=" + filename;
		url += "&dir=/storage";
		location.href=url;
		}
	
</script>

</head>
<body>
	<div class="container">

		<h2>게시판 목록</h2><br><br>
		<form class="form-inline" action="./list">
			<div class="form-group">
				<select class="form-control" name="col">
					<option value="wname" <c:if test="${col=='wname' }">selected</c:if>>성명</option>
					<option value="title" <c:if test="${col=='title' }">selected</c:if>>제목</option>
					<option value="content"
						<c:if test="${col=='content' }">selected</c:if>>내용</option>
					<option value="title_content"
						<c:if test="${col=='title_content' }">selected</c:if>>제목+내용</option>
					<option value="total" <c:if test="${col=='total' }">selected</c:if>>전체출력</option>
				</select>
			</div>
			<div class="form-group">
				<input type="text" class="form-control" placeholder="Enter 검색어"
					name="word" value="${word}">
			</div>
			<button type="submit" class="btn btn-default">검색</button>
			<button type="button" class="btn btn-default"
				onclick="location.href='./create'">등록</button>
		</form><br>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>grpno</th>
					<th>indent</th>
					<th>파일</th>
				</tr>
			</thead>
			<tbody>



				<c:choose>
					<c:when test="${empty list }">
						<tr>
							<td colspan="6">등록된 글이 없습니다.</td>
					</c:when>
					<c:otherwise>
						<c:forEach var="bbsVO" items="${list }">
							<tr>
								<td>${bbsVO.bbsno }</td>
								<td><c:forEach begin="1" end="${bbsVO.indent }">&nbsp;&nbsp;</c:forEach>
									<c:if test="${bbsVO.indent>0 }">
										<img src='../images/re.jpg'>
									</c:if> 
									<c:set var="rcount" value="${bbsVO.bbsno}"/>
									<a href="javascript:read('${bbsVO.bbsno}')">${bbsVO.title}</a> <%--     <% if(Utility.compareDay(dto.getWdate().substring(0,10))){ %>  --%>
									<c:if test="${rcount>0 }">
									<span class="badge">${rcount }</span>
									</c:if>
									<c:if test="${fn:substring(bbsVO.wdate,0,10)}">
									<img src="../images/new.gif"> </c:if>     </td>
								<td>${bbsVO.wname }</td>
								<td>${bbsVO.grpno }</td>
								<td>${bbsVO.indent }</td>
								
								<td>
								<c:choose>
								<c:when test="${empty bbsVO.filename }">파일 없음</c:when>
								<c:otherwise>
								<a href="javascript:fileDown('${bbsVO.filename }')">
								${bbsVO.filename }
								</a>
								</c:otherwise>
								</c:choose>
								</td>
							</tr>
							

						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>

		</table>
		<div>${paging }</div>
	</div>
</body>
</html>
