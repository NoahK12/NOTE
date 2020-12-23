<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글페이지</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="/NOTE/commentview/css/comment.css">
<script type="text/javascript">
$(document).ready(function(){
	
	$(".content").on('click', function(){
		var content = this.innerText;
		var newContent = content.replace(/[#]/g, '@');
		$("#getText").val(newContent + '  ');
		document.getElementById("getText").focus();
		return false;
	});
	
	$('.search-btn').click(function(){
		$('form').attr('action','search.do');
		$('form').submit();
	});
	
		$("#text2 form").on("submit", function(){
			
		var content = document.getElementById('getTag').innerHTML; // html 안에 'content'라는 아이디를 content 라는 변수로 정의한다.
	
		var splitedArray = content.split(' '); // 공백을 기준으로 문자열을 자른다.
		var linkedContent = '';
		for(var word in splitedArray)
		{
		  word = splitedArray[word];
		   if(word.indexOf('@') == 0) // # 문자를 찾는다.
		   {
		      word = '<a href="#">'+word+'</a>'; 
		   }
		   linkedContent += word+' ';
		}
		document.getElementById('getTag').innerHTML = linkedContent;

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

	<!-- 댓글창위치 -->

	<div id="comment">
		<table id="comment1">
			<tbody>
			
			<tr class="nic1">

				<td id="postImg" rowspan="4" width="500" height="600" >
				<img src="/NOTE/images/${requestScope.post_Img }" style="height:500px;width:700px;"/>
				</td>
				<td id="nickname" >
				<img id="accImg" src="/NOTE/images/${requestScope.account_Img}" width="70" height="70">
					<a href="followProfile.do?following_name=${requestScope.account_Name}" id="nic">${requestScope.account_Name }</a></td>
			</tr>

				<tr style="height: 0px">
					<td id="commenttext" height="350">
						<c:forEach items="${requestScope.aList }" var="dto">
							<a href="#" id="getTag" class="content">#${dto.comment_Name }</a><span>  ${dto.comment_Content }</span>
							<br/>
						</c:forEach>
					</td>
				</tr>
			<tr>
				<td style="height: 72px">
				
				<c:url var="like" value="colike.do">
					<c:param name="post_Num" value="${requestScope.post_Num}"/>
				</c:url>
				<c:if test="${requestScope.num == 1}">
					<form action="counlike.do">
						<input type="image" src="/NOTE/images/fullheart.png" class="article_likeline_unlikebtn" >
						<input type="hidden" value="${requestScope.post_Num }" name="post_Num"/>
						<input type="hidden" value="${requestScope.account_Name}" name="account_Name"/>
						<input type="hidden" value="${requestScope.account_Img}" name="account_Img"/>
						<input type="hidden" value="${requestScope.post_Img}" name="post_Img"/>
						<input type="hidden" value="${requestScope.post_Content}" name="post_Content"/>
						<input type="hidden" value="${requestScope.post_Sysdate}" name="post_Sysdate">
					</form>
				</c:if>
				
				<c:if test="${requestScope.num == 0}">
						<form action="colike.do">
						<input type="image" src="/NOTE/images/emptyheart.png" class="article_likeline_unlikebtn" >
						<input type="hidden" value="${requestScope.post_Num }" name="post_Num"/>
						<input type="hidden" value="${requestScope.account_Name}" name="account_Name"/>
						<input type="hidden" value="${requestScope.account_Img}" name="account_Img"/>
						<input type="hidden" value="${requestScope.post_Img}" name="post_Img"/>
						<input type="hidden" value="${requestScope.post_Content}" name="post_Content"/>
						<input type="hidden" value="${requestScope.post_Sysdate}" name="post_Sysdate">
					</form>	</c:if>
 				 
				<label style="margin-left: 10px;">좋아요 ${requestScope.post_Like}개</label></td>
			</tr>
			
				<tr>
				<td id="text2">
					<form action ="chat" method="post">
						<a id="cm">
							<input type="hidden" value="${requestScope.post_Num }" name="post_Num"/>
							<input type="hidden" value="${requestScope.account_Name}" name="account_Name"/>
							<input type="hidden" value="${requestScope.account_Img}" name="account_Img"/>
							<input type="hidden" value="${requestScope.post_Img}" name="post_Img"/>
							<input type="hidden" value="${requestScope.post_Content}" name="post_Content"/>
							<input type="hidden" value="${requestScope.post_Sysdate}" name="post_Sysdate">
							<input type="hidden" value="${requestScope.post_Like}" name="post_Like"/>
							<input type="text" name="text" id="getText" placeholder="내용을 입력해주세요">
						</a>
					</form>
				</td>
				</tr>
				<tr>
					<td id="notice" height="50">
						<p id="contentdate">${requestScope.post_Sysdate}</p>
						<p id="contenttext">${requestScope.post_Content }</p>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>