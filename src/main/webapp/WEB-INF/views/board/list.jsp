<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url='/WEB-INF/views/includes/header.jsp' />
		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value=""> <input
						type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>

					<c:forEach items="${pageList.list}" var="board">
						<tr>
							<td>${board.no}</td>
							<td style='text-align: left; padding-left:${20*board.depth }px'>
								<c:if test="${board.depth != 0 }">
									<img src="${pageContext.servletContext.contextPath }/assets/images/reply.png">
								</c:if> 
								<c:choose>
									<c:when test="${empty authUser }">
										${board.title }
									</c:when>
									<c:otherwise>
										<a href="${pageContext.servletContext.contextPath }/board/detail?no=${board.no }">${board.title }</a>							
									</c:otherwise>
								</c:choose>
							</td>
							<td>${board.name}</td>
							<td>${board.hit}</td>
							<td>${board.regDate}</td>
							<td>
								<c:if test="${authUser.no == board.userNo }">
									<a href="${pageContext.servletContext.contextPath }/board/delete?no=${board.no }" class="del">삭제</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>
				<div class="pager">
					<ul>
						<c:if test="${pageList.pb.previousPageGroup==true }">
							<li><a
								href="${pageContext.servletContext.contextPath }/board?nowPage=${pageList.pb.startPageOfPageGroup-1 }">◀</a></li>
						</c:if>
						<c:forEach var="i" begin="${pageList.pb.startPageOfPageGroup }"
							end="${pageList.pb.endPageOfPageGroup }">
							<c:choose>
								<c:when test="${i==pageList.pb.nowPage }">
									<li>${i }</li>
								</c:when>
								<c:otherwise>
									<li><a href="${pageContext.servletContext.contextPath }/board?nowPage=${i }">${i }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${pageList.pb.nextPageGroup==true }">
							<li><a href="${pageContext.servletContext.contextPath }/board?nowPage=${pageList.pb.endPageOfPageGroup+1 }">▶</a></li>
						</c:if>
					</ul>
				</div>
				<div class="bottom">
					<a href="${pageContext.servletContext.contextPath }/board/write" id="new-book">글쓰기</a>
				</div>
			</div>
		</div>
		<c:import url='/WEB-INF/views/includes/navigation.jsp'>
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url='/WEB-INF/views/includes/footer.jsp' />
	</div>
</body>
</html>