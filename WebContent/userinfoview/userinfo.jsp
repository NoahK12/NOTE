<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로필 설정</title>
<link rel="stylesheet" type="text/css"
	href="/webproject2020/userinfoview/css/userinfo.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	
	$("#goback").click(function(){
		window.history.back();
	});
	
	//선택한 이미지 img요소에 보이기
	$('#filepath').on('change', function() {
		var str = $('#filepath').val();

		//이미지 첨부파일인지 체크
		var patt = /(.jpg$|.jpeg$|.gif$|.png$)/gi;
		var result = str.match(patt);

		if($("#filepath").val() === ""){
			alert("사진이 리셋되었습니다.")
			$('#profilepic').attr("src", "${pageContext.request.contextPath}/userinfoview/images/person.png");
			return false;
		}
		
		if (!result) {
			alert('jpg, gif, png만 가능합니다.');
			$('#filepath').val('');
			return false;
		}

		//파일첨부 사이즈 체크
		if (this.files[0].size > 1000000000) {/* 1GB보다 작아야한다 */
			alert('1GB 이하만 가능합니다.');
			$('#filepath').val('');
			return false;
		}

		//(https://developer.mozilla.org/ko/docs/Web/API/FileReader/FileReader)
		//파일을 읽기 위한 FileReader라는 객체 생성
		var reader = new FileReader();

		//File내용을 읽어 dataURL형식의 문자열 저장
		reader.readAsDataURL(this.files[0]);

		//파일 읽어들이기를 성공했을때 호출되는 이벤트 메소드
		reader.onload = function(e) {
			//img요소의 src속성에 읽어들인 File내용을 지정
			$('.selfie').attr('src', e.target.result);
		};
	});//end change

	var autoHypenPhone = function(str){
	      str = str.replace(/[^0-9]/g, '');
	      var tmp = '';
	      if( str.length < 4){
	          return str;
	      }else if(str.length < 7){
	          tmp += str.substr(0, 3);
	          tmp += '-';
	          tmp += str.substr(3);
	          return tmp;
	      }else if(str.length < 11){
	          tmp += str.substr(0, 3);
	          tmp += '-';
	          tmp += str.substr(3, 3);
	          tmp += '-';
	          tmp += str.substr(6);
	          return tmp;
	      }else{              
	          tmp += str.substr(0, 3);
	          tmp += '-';
	          tmp += str.substr(3, 4);
	          tmp += '-';
	          tmp += str.substr(7);
	          return tmp;
	      }
	  
	      return str;
	}

	var phoneNum = document.getElementById('phoneNumber');

	phoneNum.onkeyup = function(){
	  console.log(this.value);
	  this.value = autoHypenPhone( this.value ) ;  
	}
	
	$('.search-btn').click(function(){
		$('form').attr('action','search.do');
		$('form').submit();
	});
	
});//end ready()
</script>
</head>
<body>
	<!-- top위치 -->
	<nav id="top">
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

	<form name="userupdate.do" action="userupdate.do" method="post"
		enctype="multipart/form-data">
		<div class="containerbox">
			<div class="container">

				<h2>프로필 편집</h2>


				<div class='photo'>
				<img class='selfie' src="/webproject2020/images/${requestScope.account_Img }"/>
				<label for='filepath' style="none" >프로필 사진 바꾸기</label>
				<input type="file" name='filepath' id='filepath' class='filepath'/>
			</div>
			
			<div class='box'>
				<label for='introduce'>소개</label>
				<br/><br/><br/><br/> 
				<label for='email'>이메일</label> 
				<label for='tel'>전화번호</label> 
			</div>
			<div class='introduce'>
				<textarea name='introduce' id='introduce' rows="5" cols="50" style='resize: none;' maxlength="100">${requestScope.account_About }</textarea>
				<input type='email' name="email" id='email' style='resize: vertical;' placeholder="이메일 입력" maxlength="50" value="${requestScope.account_Email }"/>
				<input type='text' id='phoneNumber' name='tel' placeholder='전화번호 입력' value="${requestScope.account_Phone_Num }" maxlength="13"/>

			</div>
			<span>
				<input type="submit" value="변경" class="update infoBtn"/>
				<input type="button" value="취소" id="goback" class="update infoBtn"/>
			</span>
		</div>
	</div>
	</form>
</body>
</html>