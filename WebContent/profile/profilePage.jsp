<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${sessionScope.account_Name }의 프로필</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="/NOTE/profile/css/myprofile.css">

<script type="text/javascript">
$(document).ready(function(){
	
	$('#button1').click( function() {
	    if( $(this).val() == '접기' ) {
	      $(this).val('더보기');
	      $('#pictureview').css({
				'height': '680px'
			})
			
			$('#profile').css({
				'height' : '1135px'
			})
			
			$( 'html, body' ).stop().animate( { scrollTop : '0' } )

			
	    }
	    
	    else {
	      $(this).val('접기');
	      $('#pictureview').css({
				'height': 'auto',
			   'overflow': 'hidden'
			})
			
			$('#profile').css({
				'height' : 'auto'
			})
			
	    }
	});
	
});
</script>
</head>
<body id="wrap">

	<!-- top위치 -->
	<nav id="top">
	  <div class="nav-contents">
			<div class="nav-logo">
				<img src="/NOTE/images/logo.jpg">
			</div>
			
          <div>
				<form action="search.do">
					<input type="text"  class="nav-search" placeholder="검색" name="searchWord"/>
					<input type="image" class="search-btn" src="/NOTE/images/search.JPG"/>
		 		</form>
		  </div>

			<div class="nav-link">
				<a href="remain.do"><img src="/NOTE/images/home.JPG"/></a>
				<a href="write.do"><img src="/NOTE/images/write.JPG"/></a>
				<a href="profile.do"><img src="/NOTE/images/profile.JPG"/></a>
				<a href="logout.do"><img src="/NOTE/images/Logout.png" /></a>
					<c:if test="${sessionScope.account_Id == 'admin' }">
					<a href="admin.do"><img src="/NOTE/images/spanner.png"></a>	
				</c:if>
			</div>
		</div>
	</nav>

	<div style="height:60px"></div>
	<!-- session위치 -->
	<div id="profile">
		<table id="table1">
			<tbody>
			<colgroup>
				<col style="width: 30%;">
				<col style="width: 70%;">
			</colgroup>
			<tr>
				<td id="table1_profile" rowspan="2"><img style="height: 200px; width: 200px" id="profileImage" src="/NOTE/images/${sessionScope.account_Img }"
					/></td>
				<td id="nickname" height="20">${sessionScope.account_Name }</td>
				<td><a href="userinfo.do"><img src="/NOTE/images/gear.png" id="gear"></a></td>
				
				
				
			</tr>
					
			<tr>
				<td id="content">${sessionScope.account_About }</td>
			</tr>
			</tbody>
		</table>
		<table id="table2">
			<tr id="count">
				<td>게시물 ${requestScope.countPost }</td>
				<td>팔로워 ${requestScope.countFollower }</td>
				<td>팔로우 ${requestScope.countFollow -1}</td>
			</tr>
		</table>

		<hr />

		<div id="pictureview" style="height: 680px;">
		<form method="post" action="previewDel"> <!-- 버튼에 해당하는 submit이벤트를 만들어 해당 주소로 이동하게함=> 해당주소에서 Db데이터를 삭제해줌 -->
				<c:set var="i" value="0"/>
				<c:set var="j" value="3"/>
			<table class="c">
				<c:forEach items="${requestScope.aList}" var="dto">
					<c:if test="${i %j==0 }">
						<tr>
					</c:if>			
						<td>
							<img src="/NOTE/images/${dto.post_Img}" width="300" height="300"/> <!-- c드라이브 경로 못받음 -->
						</td>
					<c:if test="${i%j== j-1 }">
						</tr>
					</c:if>
					<c:set var="i" value="${i+1}"/>	
				</c:forEach>
			</table>
		</form>
	
		</div>
		<!-- end pictureview -->
		<c:if test="${requestScope.countPost > 6}">
			<input type='button' value='더보기' id='button1' /> 
		</c:if>
		
	</div>
	<!-- end profile -->
	
</body>
</html>