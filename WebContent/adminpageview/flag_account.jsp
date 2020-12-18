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
	href="/NOTE/adminpageview/css/account.css">

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
				<a style="cursor: pointer;" onclick="window.location.reload()">
					<img src="/NOTE/images/refresh.png" />
				</a> <a style="cursor: pointer;" href="logout.do"> <img
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
			<div class="list">
				<form action="accountDelete.do">

					<table>
						<tr>
							<td rowspan="5">
								<div style="width: 400px;">
									<img src="/NOTE/images/${dto.account_Img}"
										width="100%" />
								</div>
							</td>
							<td>이름 : ${dto.account_Name}</td>
						</tr>
						<tr>
							<td>신고 수 : ${dto.account_Flag}</td>
						</tr>
						<tr>
							<td>신고 종류</td>
						</tr>
						<tr>
							<td>스팸 : ${requestScope.aList2[i.index].spam} 욕설 : ${requestScope.aList2[i.index].abusive}</td>
						</tr>
						<tr>
							<td>불건전 : ${requestScope.aList2[i.index].obscene} 광고 : ${requestScope.aList2[i.index].illegaladv}</td>
						</tr>
					</table>
					<input type="submit" name="accountdel" value="삭제" class="del" /> <input
						type="hidden" name="account_Num" value="${dto.account_Num}">
				</form>
			</div>
			<br />
			<br />
			<br />
		</c:forEach>
	</div>
</body>
</html>
