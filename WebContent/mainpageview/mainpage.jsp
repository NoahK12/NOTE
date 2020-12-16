<%@page import="webproject.dto.PostDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<!-- <link rel="shortcut icon" href="#"/> -->
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="/webproject2020/mainpageview/css/mainpage.css">
<script type="text/javascript">
	$(document).ready(function(){
		/* $( 'html, body' ).stop().animate( { scrollTop : ${requestScope.height} } ) */
		/* $('.?').on('click', function(){
			
			var height = $(document).scrollTop();
			$('.height').val(height);
		
		}); */
		
		const article_profile_flag = document.querySelectorAll(".article_profile_flag");
		const flag_box = document.querySelectorAll(".flag_box");
		
		
		for(let i = 0; i < article_profile_flag.length; i++){
			article_profile_flag[i].addEventListener("click", function(){
				
				flag_box[i].style.display = "block";
					return false;
				
			})
		}
		
		
		$('.flag_cancel').on('click',function(){
			$('.flag_box').attr('style', "display:none")
			
		});
		
		$('#followProfile a').on('click', function(){
			$('#followProfile').submit();
		})
		
	});	

</script>

<title>${sessionScope.account_Name }의 메인페이지</title>

</head>	
<body class="wrap">
	<nav class="top">
	  <div class="nav-contents">
	  
			<div class="nav-logo">
				<img src="/webproject2020/images/logo.jpg">
			</div>
				
	    	 <div>
	     			<form action="search.do">
					<input type="text"  class="nav-search" placeholder="검색" name="searchWord"/>
					<input type="image" class="search-btn" src="/webproject2020/images/search.JPG"/>
		  		</form>
			</div>
		
			<div class="nav-link">
				<a href="remain.do"><img src="/webproject2020/images/home.JPG"/></a>
				<a href="write.do"><img src="/webproject2020/images/write.JPG"/></a>
				<a href="profile.do"><img src="/webproject2020/images/profile.JPG"/></a>
				<a href="logout.do"><img src="/webproject2020/images/Logout.png" /></a>
				<c:if test="${sessionScope.account_Id == 'admin' }">
					<a href="admin.do"><img src="/webproject2020/images/spanner.png"></a>	
				</c:if>
			</div>
			
		</div>
		
	</nav>
	
	<div class="total_article">
		<c:forEach items="${requestScope.aList2}" var="dto2" varStatus="i">
			<div class="article" >
				<div class="article_profile">
				<form action="followProfile.do">
					<input type="image" class="article_profile_img" src="/webproject2020/images/${dto2.account_Img}">
					<input type="text" name="following_Name" class="article_profile_name" value="${dto2.account_Name}">
				</form>
				<c:if test="${dto2.account_Name != 'admin'}">
					<c:if test="${requestScope.aList4[i.index] == '1'}">
						<img class="article_profile_flag" style="float:right;width: 32px;height: 32px;" src = "/webproject2020/images/fullflag.png"/>
					</c:if>
					
					<c:if test="${requestScope.aList4[i.index] == '0'}">
						<button type="button" style="float:right;height:0px;cursor:pointer"><img class="article_profile_flag" src = "/webproject2020/images/emptyflag.png"/></button>
					</c:if>
					
					</c:if>
					<form action="addflag.do" method="post" name="flag_box" class="flag_box"  >
						<fieldset style="color:white">
							<p style="text-align:right"><button type="button" class="flag_cancel" style="height:0; cursor:pointer"><img style="width: 20px"src="/webproject2020/images/cancel.png" alt=""></button></p>
							<p style="text-align:center;font-size: 23px;font-weight: bold;"><label>신고할 종류를 선택해주세요.</label></p>
							<p class="flag_radio"><input type="radio" name="selected_Flag" value="SPAM" checked="checked" >스팸</p>
							<p class="flag_radio"><input type="radio" name="selected_Flag" value="ABUSIVE" >욕설</p>
							<p class="flag_radio"><input type="radio" name="selected_Flag" value="OBSCENE" >불건전한 내용</p>
							<p class="flag_radio"><input type="radio" name="selected_Flag" value="ILLEGALADV" >불법 광고</p>
							<p style="text-align:center;">
								<input type="hidden" name = "post_Num" value="${dto2.post_Num}" />
								<input type="hidden" name = "account_Num" value="${sessionScope.account_Num}" />
								<input type="submit" style="margin-right:30px;font-size: 20px;background-color: transparent;color:rgba(125 91 190); font-weight:bold; cursor:pointer; outline:none;" value="신고하기">
								<input type="button" class="flag_cancel" style="margin-left:40px;font-size:20px;background-color: transparent;color:rgba(125 91 190); font-weight:bold; cursor:pointer; outline:none;" value="취소">	
							</p>
						</fieldset>
					</form>
					
				</div>
				
				<img class="article_img" src="/webproject2020/images/${dto2.post_Img}"/>
				
				<div class="article_likeline" style="display: inline-flex;">
				
				<c:url var="unlike" value="unlike.do">
					<c:param name="post_Num" value="${dto2.post_Num}"/>
					<c:param name="height" value=""/>
				</c:url>
				
				<c:url var="like" value="like.do">
					<c:param name="post_Num" value="${dto2.post_Num}"></c:param>
				</c:url>
				
				<c:if test="${requestScope.aList3[i.index] == 1}">
						<a href="${unlike}" >
						<img src="/webproject2020/images/fullheart.png" class="article_likeline_unlikebtn" ></a>
				</c:if>
				
				<c:if test="${requestScope.aList3[i.index] == 0}">
						<a href="${like}" > 
						<img src="/webproject2020/images/emptyheart.png" class="article_likeline_likebtn"	></a>
				</c:if>
					<form action="list.do" method="post">
						<input type="hidden" value="${dto2.post_Num}" name="post_Num">
						<input type="hidden" value="${dto2.account_Name}" name="account_Name">
						<input type="hidden" value="${dto2.account_Img}" name="account_Img">
						<input type="hidden" value="${dto2.post_Like}" name="post_Like">
						<input type="hidden" value="${dto2.post_Img}" name="post_Img">
						<input type="hidden" value="${dto2.post_Content}" name="post_Content">
						<input type="hidden" value="${dto2.post_Sysdate}" name="post_Sysdate">
						<input type="image" style="position:relative;left:16px;" src="/webproject2020/images/comment.png" class="article_likeline_commentbtn">
					</form>
				</div>
				
				<div class="article_likecnt"><label>좋아요 ${dto2.post_Like} 개</label></div>
				
				<div class="article_content"><a href="" class="article_content_name">${dto2.account_Name}</a><label> ${dto2.post_Content}</label></div>
				<div class="article_date">${dto2.post_Sysdate}</div>
				
			</div>
		</c:forEach>
	</div>
	
	<div class=subbox2>
		<div class="subbox2_fixed">
			<div class="myprofile">
				<!-- 메인페이지 오른쪽 -->
				<a href="profile.do" class="myprofile_img"><img src = "/webproject2020/images/${sessionScope.account_Img }"/></a>
				<a href="profile.do" class="myprofile_name">${sessionScope.account_Name }</a>
			</div>
			<div class="follower" >
				<c:forEach items="${requestScope.aList10}" var="dto">
					<c:if test="${dto.account_Name != 'admin'}">
						<div class="follower_profile">
						<form action="followProfile.do" id="followProfile" style="margin-top: 20px;">
						<input type="hidden" name="following_Name" value="${dto.account_Name}">
						<input type="hidden" name="account_Num" value="${dto.account_Num}">
							<a href="#"><img class="follower_profile_img" src = "/webproject2020/images/${dto.account_Img}"/></a>
							<a href="#" class="follower_profile_name">${dto.account_Name}</a>
						</form>
						</div>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</div>
	
</body>
</html>