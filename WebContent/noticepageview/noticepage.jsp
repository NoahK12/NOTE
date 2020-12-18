<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" href="#" />
<meta charset="UTF-8">
<title>작성글</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="/NOTE/noticepageview/css/notice.css">
<script type="text/javascript">
$(document).ready(function(){
	$("#picture").click(function() {
	    $("#filepath").click();
	});
	
	$('#filepath').on('change', function(){
		var str = $('#filepath').val();
	
		// 이미지 첨부파일인지 체크
		var patt = /(.jpeg$|.jpg$|.gif$|.png$)/gi;
		var result = str.match(patt);
		
		if($("#filepath").val() === ""){
			alert("사진이 리셋되었습니다.")
			$('#picture').attr("src", "${pageContext.request.contextPath}/noticepageview/images/tapme.gif");
			return false;
		}
		
		if(!result){
			alert('jpeg, jpg, gif, png만 가능합니다.');
			return false;
		}
		
		// 파일첨부 사이즈 체크
		if(this.files[0].size > 100000000){
			alert('100MB 이하만 가능합니다.');
			$('#filepath').val("");
			return false;
		}
		
		// 파일을 읽기 위한 FileReader객체 생성
		var reader = new FileReader();
		
		// File내용을 읽어 dataURL형식의 문자열 저장
		reader.readAsDataURL(this.files[0]); // 배열 형식이기에 꼭 [i] 배열의 순서를 알려줘야한다.
		
		// 파일 일거들이기를 성공했을 때 호출되는 이벤트 메소드
		reader.onload = function(e){
			// img요소의 src속성에 읽어들인 File내용을 지정해준다.
			$('#picture').attr('src', e.target.result);
		};
	}); // end change()
	
	$('.search-btn').click(function(){
		$('form').attr('action','search.do');
		$('form').submit();
	});
	
});
</script>
</head>
<body id="wrap">
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
	<form action="upload.do" method="post" enctype="multipart/form-data">
		<div class="notice">
			<div style="height: 10px"></div>

			<div id="profile">
				<a href="remain.do"><img src="/NOTE/images/${sessionScope.account_Img }" /></a> <a
					href="remain.do" id="profile_name">${sessionScope.account_Name }</a>
			</div>
			<img id="picture"
				src="../noticepageview/images/tapme.gif" />
				<input type="file" name="filepath" id="filepath" />
			<div id="notice1">
				<textarea class="noticetext" name="noticetext" placeholder="내용을 입력하세요" rows="10"
					style="width: 95%; border: 0;" maxlength="500"></textarea>
			</div>
			<div class="writer">
				<span id="span1"></span> <input type="image"
					src="../noticepageview/images/write.JPG" width="40">
			</div>
		</div>
	</form>
	
	<div class=subbox2>
		<div class="subbox2_fixed">
			<div class="myprofile">
				<!-- 메인페이지 오른쪽 -->
				<a href="profile.do" class="myprofile_img"><img src="/NOTE/images/${sessionScope.account_Img }"/></a>
				<a href="profile.do" class="myprofile_name">${sessionScope.account_Name }</a>
			</div>
			<div class="follower" >
				<c:forEach items="${requestScope.aList}" var="dto">
					<c:if test="${dto.account_Name != 'admin'}">
						<div class="follower_profile">
							<a href="followProfile.do?following_name=${dto.account_Name }" class="follower_profile_img"><img src = "/NOTE/images/${dto.account_Img}"/></a>
							<a href="followProfile.do?following_name=${dto.account_Name }" class="follower_profile_name">${dto.account_Name}</a>
						</div>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>