<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로필 설정</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/usersettingview/css/usersetting.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	 $('#phonetext').keypress(function(event) {
	        if (event.keyCode == 13) {
	            event.preventDefault();
	        }
	 });
	
	$('#filepath').on('change', function(){
		var str = $('#filepath').val();
	
		// 이미지 첨부파일인지 체크
		var patt = /(.jpeg$|.jpg$|.gif$|.png$)/gi;
		var result = str.match(patt);
		
		if($("#filepath").val() === ""){
			alert("사진이 리셋되었습니다.")
			$('#profilepic').attr("src", "${pageContext.request.contextPath}/usersettingview/images/person.png");
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
			$('#profilepic').attr('src', e.target.result);
		};
	}); // end change()
	
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

	var phoneNum = document.getElementById('phonetext');

	phoneNum.onkeyup = function(){
	  console.log(this.value);
	  this.value = autoHypenPhone( this.value ) ;  
	}
});
</script>
</head>
<body>
 	<nav id="top">
	  	<div class="nav-contents">
			<div class="nav-logo">
				<img src="/NOTE/images/logo.jpg">
			</div>
		</div>
	</nav>
	<form name="usersetting" action="usersetting.do" method="post" enctype="multipart/form-data">
	<div class="containerbox">
		<div class="container">
			<span id="maintext">추가 정보를 입력해주세요!</span>
			<div id="profiletext">프로필 사진 등록</div>
			<input type="file" name="filepath" id="filepath" />
			<label for="filepath" id="piclabel">업로드</label>
				<img id="profilepic" width="200" height="200" src="${pageContext.request.contextPath}/usersettingview/images/person.png"/>
			
			<hr style=width:100%>
			
			<div class="aboutbox">
				<label for="abouttext">소개</label>
				<textarea id="abouttext" name="abouttext" placeholder="Remember, be nice!" rows="8" cols="60" maxlength="150"></textarea>
			</div>
			<div class="phonebox">
				<label for="phonetext">전화번호</label>
				<textarea id="phonetext" name="phonetext" placeholder="010-1234-5678" rows="1" cols="50" maxlength="13"></textarea>
			</div>
			<span>
				<input type="submit" value="등록" class="buttons" id="reg"></input>
			</span>
		</div>
	</div>
	</form>
	<form name="usersetting" action="usersettingskip.do" method="post" enctype="multipart/form-data">
		<input type="submit" value="다음에 하기" class="buttons" id="next"/></input>
	</form>
</body>
</html>