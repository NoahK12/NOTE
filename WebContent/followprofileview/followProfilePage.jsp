<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${requestScope.account_Name }의 프로필</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="/NOTE/followprofileview/js/profileAction.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
		$(".article_profile_flag").on('click',function(){
			$('.flag_box').attr('style', "display:block")
			return false;
		})
		
		
		$('.flag_cancel').on('click',function(){
			$('.flag_box').attr('style', "display:none")
			
		});
		
		$('#followProfile a').on('click', function(){
			$('#followProfile').submit();
		})
		
	});	

</script>

<style type="text/css">
* {
	margin: 0;
	border-width: 0;
	padding: 0;
	outline: none;
}

#wrap {
	width: 100vw;
	height: 120vh;
	background-color: rgba(var(--b3f,250,250,250),1);
}


#profile {
	background-color: white;
	width: 1000px;
	height: auto;
	box-shadow: 6px 6px 12px rgba(0,0,0,0.15),
						 -6px -6px 12px rgba(255,255,255,1);
	border: 2px solid rgba(var(--b3f,250,250,250),2);
	border-radius: 25px;
	margin-left: 460px;
	padding-right: 25px;
	padding-left: 25px;
}


#table1 {
	width: 850px;
	table-layout: fixed;
}

#table1 #nickname {
	padding-left: 55px;
	padding-top: 83px;
	font-size: 35px;
	pointer-events: none;
}


#table1 #profileImage {
	margin-left: 70px;
	margin-top: 50px;
	background-size: contain;
	padding : 0;
	width : 170px;
	height : 170px;
	border-radius: 50%;
}


#table1 #content {
	padding-left: 40px;
	font-weight: normal;
	font-size: 15px;
	position: relative;
	left: 20px;
	pointer-events: none;
}

#table1 td {
	width: 50px;
	padding-top: 40px;
	font-size: 18px;
	font-weight: bold;
}

#table {
	padding-left: 30px;
}

#table2 td {
	pointer-events: none;
	padding-left: 168px;
	font-weight: bold;
	font-size: 27px;
	padding-top: 40px;
	color: #303a52;
}

hr {
	margin-top: 15px;
	border: 1px solid gray;
}

#table3 td {
	border: 1px solid;
}

/*top style*/
			
		#top {
			width: 100vw;
			padding: 10px 20px;
			background-color: white;
			height: 4vh;
			position: fixed;
			border: 1px solid #f0f0f0;
			z-index: 2;
			box-shadow: 6px 6px 12px rgba(0,0,0,0.15),
						 -6px -6px 12px rgba(255,255,255,1);
			border: 1px solid rgba(var(--b3f,250,250,250),2);
			border-radius: 5px;
		}
		#logo{
		font-style:inherit;
		color:black;
        text-decoration: none;
        margin-left: 378px
        
		}
			
		.nav-contents {
			width: 1920px;
			margin: 0 auto;
			display: flex;
			justify-content: space-between;
			align-items: center;
		}
			
		.nav-logo {
		display: flex;
		align-items: center;
		font-style:inherit;
		color:black;
        text-decoration: none;
        margin-left: 460px;
		}
			
		.logo-text {
			height: 29px;
			margin-top: 5px;
		}
			
		.vertical-line {
			background-color: #262626;
			height: 28px;
			margin: 0 16px;
			width: 1px;
		}
			
		.nav-search {
			width: 215px;
			border: solid 1px #dbdbdb;
			color: #262626;
			font-weight: lighter;
			outline: 0;
			padding:9px 10px 8px 26px;
			float: right;
			box-shadow: inset -2px -2px 6px rgba(255,255,255,1),
							inset 2px 2px 6px rgba(0,0,0,0.1);
			border: 1px solid rgba(var(--b3f,250,250,250),1);
			border-radius: 15px;
		}
			
		.search-btn {
			width: 35px;
			height: 34px;
			float: right;
			box-shadow:  4px 4px 8px rgba(0,0,0,0.08),
						 -6px -6px 12px rgba(255,255,255,1);
			border: 1px solid rgba(var(--b3f,250,250,250),1);
			border-radius: 15px;
			margin-right: 10px;
		}
		
		.nav-link {
			margin-right: 400px;
			float: right;
		 	display: flex; 
			align-items: center;
		}
		
		.nav-link a {
			height: 0;
		}
		
		.nav-link img{
			height: 30px;
			margin-right: 35px;
			margin-top: -15px;
			box-shadow: 4px 4px 8px rgba(0,0,0,0.08),
						 -6px -6px 12px rgba(255,255,255,1);
			border: 1px solid rgba(var(--b3f,250,250,250),1);
			border-radius: 5px;
		}
		
		.nav-link img:hover{
			box-shadow: inset 6px 6px 12px rgba(0,0,0,0.08),
						inset -6px -6px 12px rgba(255,255,255,1);
			border: 1px solid rgba(var(--b3f,250,250,250),1);
			border-radius: 5px;
		}
		
		.nav-link img:not(:first-child ) {
			    margin-left: 0px;
		}
			
		/*top 반응형*/
		
		@media(max-width :1100px){
			.top {
				padding: 5px 20px;
				background-color: white;
				height: 37px;
				position: fixed;
				border: 1px solid #f0f0f0;
				border-right: none;
			}
				
			.nav-contents {
				width: 950px;
				margin: 0 auto;
				display: flex;
				justify-content: space-between;
				align-items: center;
			}
			
			.nav-logo {
				display: flex;
				align-items: center;
				font-style:inherit;
				color:black;
		        text-decoration: none;
		        margin-left: 378px;
			}
			
			.logo-text {
				height: 29px;
				margin-top: 5px;
			}
			
			.vertical-line {
				background-color: #262626;
				height: 28px;
				margin: 0 16px;
				width: 1px;
			}
			
			.nav-search {
				width: 215px;
				border: solid 1px #dbdbdb;
				color: #262626;
				font-weight: lighter;
				outline: 0;
				padding: 8px 10px 8px 26px;
				float: right;
				box-shadow: inset -2px -2px 6px rgba(255,255,255,1),
							inset 2px 2px 6px rgba(0,0,0,0.1);
				border: 1px solid rgba(var(--b3f,250,250,250),1);
				border-radius: 15px;
			}
			
			.search-btn {
				width: 35px;
				height: 34px;
				float: right;
				margin-right: 10px;
			}
			
			.nav-link {
		       float: right;
               display: flex;
               align-items: center;
               margin: -2px;
			}
			
			.nav-link img{
				height: 30px;
				margin-right: 35px;
				margin-top: -25px;
				box-shadow: 4px 4px 8px rgba(0,0,0,0.08),
							 -6px -6px 12px rgba(255,255,255,1);
				border: 1px solid rgba(var(--b3f,250,250,250),1);
				border-radius: 5px;
				float: right;
			}
		
			.nav-link img:hover{
				box-shadow: inset 6px 6px 12px rgba(0,0,0,0.08),
							inset -6px -6px 12px rgba(255,255,255,1);
				border: 1px solid rgba(var(--b3f,250,250,250),1);
				border-radius: 5px;
			}
			
			.nav-link img:not(:first-child ) {
				margin-left: 30px;
			}
		
	#profile {
		background-color: white;
		width: 1000px;
		height: auto;
		margin-left: 450px;
	}
}


/*이미지 css*/


#pictureview{
	width: 998px;
	height: 690px;
	overflow: hidden;
}

.c td{
	padding: 15px; 
}


#button1{
	background-color: #a1eafb;
	height: 50px;
	text-align: center;
	font-size: 25px;
	font-weight:bold;
	line-height: 45px;
	
	width: 1000px;
}
	
		.flag_box{
			width: 350px;
    		height: 200px;
		    background-color: rgba(20 21 24);
		    position: absolute;
		    margin-left: 105px;
		    margin-top: 70px;
		    padding: 10px 18px 65px 25px;
		    border: 1px black solid;
		    display: none;
		}
		
		.flag_radio {
			margin-bottom: 13px;
    		margin-top: 13px;
    		font-size: 17px;
			
		} 
		
		input[type='radio'],
		input[type='radio']:checked {
		  appearance: none;
		  width: 0.9rem;
		  height: 0.9rem;
		  border-radius: 100%;
		  margin-right: 5px;
		  outline: none;
		  cursor: pointer;
		}

		input[type='radio'] {
		  background-color: white;
		  border: 2px solid white;
		}
		
		input[type='radio']:checked {
		  background-color: rgba(125 91 190);
		}
		
		.article_profile_flag {
			width: 32px;
			height: 32px;

			
		}


</style>
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
				<td id="table1_profile" rowspan="2"><img style="height: 200px; width: 200px" id="profileImage" src="/NOTE/images/${requestScope.account_Img }"
					/></td>
				<td id="nickname" height="20">${requestScope.account_Name}</td>
				<td>
				<c:if test="${sessionScope.account_Id != 'admin' }">
					<c:if test="${requestScope.num == '1'}">
						<img class="article_profile_flag"  id="aaa" src = "/NOTE/images/fullflag.png"/>
					</c:if>
					
					<c:if test="${requestScope.num == '0'}">
						<button type="button" ><img class="article_profile_flag" src = "/NOTE/images/emptyflag.png"/></button>
					</c:if>
					</c:if>
				</td>
				
			</tr>
			
			
			
			<tr>
				<td id="content">${requestScope.account_About }</td>
			</tr>
			</tbody>
		</table>
		<table id="table2">
			<tr id="count">
				<td>게시물 ${requestScope.countPost }</td>
				<td>팔로워 ${requestScope.countFollower }</td>
				<td>팔로우 ${requestScope.countFollow-1}</td>
			</tr>
		</table>
		<form action="addaccountflag.do" method="post" name="flag_box" class="flag_box"  >
				<fieldset style="color:white">
							<p style="text-align:right"><button type="button" class="flag_cancel" style="height:0; cursor:pointer"><img style="width: 20px"src="/NOTE/images/cancel.png" alt=""></button></p>
							<p style="text-align:center;font-size: 23px;font-weight: bold;"><label>신고할 종류를 선택해주세요.</label></p>
							<p class="flag_radio"><input type="radio" name="selected_Flag" value="SPAM" checked="checked" >스팸</p>
							<p class="flag_radio"><input type="radio" name="selected_Flag" value="ABUSIVE" >욕설</p>
							<p class="flag_radio"><input type="radio" name="selected_Flag" value="OBSCENE" >불건전한 내용</p>
							<p class="flag_radio"><input type="radio" name="selected_Flag" value="ILLEGALADV" >불법 광고</p>
							<p style="text-align:center;">
								<input type="hidden" name = "flag_account_Num" value="${param.account_Num}" />
								<input type="hidden" name = "account_Num" value="${sessionScope.account_Num}" />
								<input type="submit" style="margin-right:30px;font-size: 20px;background-color: transparent;color:rgba(125 91 190); font-weight:bold; cursor:pointer; outline:none;" value="신고하기">
								<input type="button" class="flag_cancel" style="margin-left:40px;font-size:20px;background-color: transparent;color:rgba(125 91 190); font-weight:bold; cursor:pointer; outline:none;" value="취소">	
							</p>
					</fieldset>
		</form>

		<hr />

		<div id="pictureview">
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