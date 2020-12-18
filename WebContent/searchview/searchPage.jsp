<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색 결과</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="/NOTE/searchview/css/searchpage.css">
<script type="text/javascript">
	
	$(document).ready(function(){
		
		
		$('.search-btn').click(function(){
			
				$('form').attr('action','search.do');
				$('form').submit();
				
		});
		
		$('.follbtnT').click(function(){
			
			alert("팔로우 되었습니다.");
		})
		
		$('.follbtnF').click(function(){
			
			alert("언팔로우 되었습니다.");
		})
		
	});
</script>
</head>
<body id="wrap">
	
	
	<!-- top위치 -->
	<div id="top">
	  <div class="nav-contents">
			<div class="nav-logo">
				<img src="/NOTE/images/logo.jpg">
			</div>
			
          <form action="search.do">
           
			<input type="text"  class="nav-search" placeholder="검색" name="searchWord"/>
			<input type="image" class="search-btn" src="/NOTE/images/search.JPG"/>
		  </form>
			
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
	</div>
	
	
	<!-- session위치 -->
	
	<div id="searchPage">
		
		<table id="table1">
			<tbody>
			<c:if test='${requestScope.aList3.size()!=0}'>
				<tr>
					<td width="700px" id="td1">
						
						<table id="table2">
							<tbody>
								<c:forEach items="${requestScope.aList3}" var="dto" varStatus="status">
									<c:if test="${dto.account_Name == 'admin' && requestScope.aList3.size()==1}">
			                              <p> 검색된 결과가 없습니다. 다시 검색해주세요</p>
			                        </c:if>
			                        
			                        <c:if test="${dto.account_Name == sessionScope.account_Name && requestScope.aList3.size()==1}">
			                              <p> 검색된 결과가 없습니다. 다시 검색해주세요</p>
			                        </c:if>
			                        
			                        <c:if test="${dto.account_Name == 'admin' && dto.account_Name == sessionScope.account_Name && requestScope.aList3.size()==2}">
			                              <p> 검색된 결과가 없습니다. 다시 검색해주세요</p>
			                        </c:if>
									<c:if test="${dto.account_Name != 'admin'}">
									<c:if test="${dto.account_Name != sessionScope.account_Name }">
										<td rowspan="3" width="80px" height="50" id="td2">
											<img src="/NOTE/images/${dto.account_Img}" id="searchpro"/> 
										</td>
										<td width="200px">닉네임 : ${dto.account_Name}</td>
										<td rowspan="3" width="100px" id="followbtn">
											
											<c:url var="cpageF" value="followF">
												<c:param name="num" value="${dto.account_Num}"/> 
												<c:param name="name" value="${dto.account_Name}"/>
												<c:param name="searchWord" value="${requestScope.search}" /> 
											</c:url>
											<c:url var="cpageT" value="followT">
												<c:param name="num" value="${dto.account_Num}"/> 
												<c:param name="name" value="${dto.account_Name}"/>
												<c:param name="searchWord" value="${requestScope.search }" /> 
											</c:url>
											
											 <c:if test='${requestScope.followlist[status.index]== "1"}'>
												 <a href="${cpageF}" class="follbtnF"  style="background-color: white ; color: black; font-size: 11px";>언팔로우</a>
											 </c:if>
											  <c:if test='${requestScope.followlist[status.index]== "0"}'>
											 	 <a href="${cpageT}" class="follbtnT"  style="background-color: #5d5d5a ; color: white" >팔로우</a>
											 </c:if>
										</td>
									</tr>
									<tr>
										<td>팔로워: ${requestScope.fList[status.index]}</td>
									</tr>
									<tr>
										<td  colspan="3" height="40px" class="enter"></td>
									</tr>
									<tr><td></td></tr>
									</c:if>
									</c:if>
								</c:forEach>
								
								
							</tbody>
						</table>
					</td>
				</tr>
			</c:if>			
			<c:if test='${requestScope.aList3.size()==0 }'>
				<tr>
					<td width="700px" id="td1">
					<p style="padding-bottom:35px;">검색된 결과가 없습니다. 다시 검색해주세요.</p>
						
						
					</td>
				</tr>
			</c:if>
					<div class="subbox2" >
						<div id="subbox2_fixed">
							<div style="height:50px"></div>
							<div id="me">
								<!-- 메인페이지 오른쪽 -->
								<a href="profile.do" id="subbox2_myprofile"><img src = "/NOTE/images/${sessionScope.account_Img}"/></a>
								<a href="profile.do" id="subbox2_myname">${sessionScope.account_Name }</a>
							</div>
							<div id="follower" >
								<c:forEach items="${requestScope.aList}" var="dto2">
									<c:if test="${dto2.account_Name != 'admin'}">
									<div id="follower_ind">
										<a href="followProfile.do?following_name=${dto2.account_Name }" id="subbox2_followerprofile"><img src = "/NOTE/images/${dto2.account_Img}"/></a>
										<a href="followProfile.do?following_name=${dto2.account_Name }" id="subbox2_followername">${dto2.account_Name}</a>
									</div>
									</c:if>
								</c:forEach>
							</div>
						</div>
					</div>
				
				
			</tbody>
		</table>
	</div>
	
	
	
</body>
</html>