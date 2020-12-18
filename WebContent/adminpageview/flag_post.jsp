<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>관리자 페이지</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="/NOTE/adminpageview/css/post.css">
<style type="text/css">
div .list {
	position: relative;
	left: 300px;
	border: 4px solid dimgrey;
	width: 800px;
	border-radius: 5px;
}
</style>
</head>
<body>
	<!-- top위치 -->
	<nav id="top">
		<div class="nav-contents">
			<div class="nav-logo">
				<img class="logo-img" src="" /> <img
					src="/NOTE/images/logo.jpg">
			</div>

			<h1>AdminPage</h1>

			<div class="nav-link">
				<a href="remain.do"><img src="/NOTE/images/home.JPG" /></a>
				<a style="cursor: pointer;" onclick="window.location.reload()"><img
					src="/NOTE/images/refresh.png" /></a> <a
					style="cursor: pointer;" href="logout.do"><img
					src="/NOTE/images/Logout.png" /></a>
			</div>
		</div>
	</nav>

	<div class="menu">
		<div class="menu_btn">
			<form action="flag_account.do">
				<input style="cursor: pointer;" type="submit" id="flag_ac"
					value="신고 계정" />
			</form>
			<form action="flag_post.do">
				<input style="cursor: pointer;" type="submit" id="flag_post"
					value="신고 게시물" />
			</form>
		</div>
		<c:forEach items="${requestScope.aList}" var="dto" varStatus="i">
			<c:if test="${dto.account_Name != 'admin'}">
				<div class="list">
					<form action="postDelete.do">
						<table>
							<tr>
								<td rowspan="5">
									<div style="width: 100%;">
										<img src="/NOTE/images/${dto.post_Img}"
											width="500px;" />
									</div>
								</td>
								<td>이름 : ${dto.account_Name }</td>
							</tr>
							<tr>
								<td>게시글 : ${dto.post_Content}</td>
							</tr>
							<tr>
								<td>신고종류</td>
							</tr>
							<tr>
								<td>스팸 : ${requestScope.aList2[i.index].spam}   욕설 : ${requestScope.aList2[i.index].abusive}</td>
							</tr>
							<tr>
								<td>불건전 : ${requestScope.aList2[i.index].obscene}  광고 : ${requestScope.aList2[i.index].illegaladv}</td>
							</tr>
						</table>
						<input type="submit" name="del" value="삭제" class="del" /> <input
							type="hidden" name="post_Num" value="${dto.post_Num}" />

					</form>
				</div>
				<br />
				<br />
				<br />
			</c:if>
		</c:forEach>
	</div>
</body>
</html>
