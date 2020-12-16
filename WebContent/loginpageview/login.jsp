<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NOTE</title>
<style type="text/css">
/* all page css start /////////////////////////////////////////////////////////*/

 *{
 	margin: 0;
 	padding: 0;
 	box-sizing: border-box;
 	font-family: 'Poppins', sans-serif;
 	outline: none;
 }
 
 body{
 	min-height: 200vh;
 	overflow-x: hidden;
 }
 
 html{ /* For goTopBtn scroll effect */
 	scroll-behavior: smooth;
 }
 
 header{
 	background: rgba(var(--b3f,250,250,250),1);
 }
 
 .logo{
 	position: absolute;
 	top: 25px;
 	left: 35px;
 	font: bold 40px Montserrat;
 	pointer-events: none;
 }
 
 #logoWord{
 	font-family: 'Itim', cursive;
 	font-size: 70px;
 	position: relative;
 	top: -20px;
 	pointer-events: none;
 }

/* all page css end /////////////////////////////////////////////////////////*/

/* signin box css start /////////////////////////////////////////////////////////*/

.signin{
	background: rgba(var(--b3f,250,250,250),1);
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
	font-family: 'Montserrat', sans-serif;
	height: 100vh;
}


.signinbox h1 {
	font-weight: bold;
	margin-bottom: 20px;
	white-space: nowrap;
	pointer-events: none;
}

.signinbox p {
	font-size: 16px;
	font-weight: 100;
	line-height: 20px;
	letter-spacing: 0.5px;
	margin: 20px 0 30px;
	pointer-events: none;
}

.signinbox a {
	color: #333;
	font-size: 14px;
	text-decoration: none;
	margin: 15px 0;
}

.signinbox button {
	border-radius: 20px;
	border: #446491;
	background-color: #446491;
	color: #FFFFFF;
	font-size: 12px;
	font-weight: bold;
	padding: 12px 45px;
	letter-spacing: 1px;
	text-transform: uppercase;
	transition: transform 80ms ease-in;
	margin-top: 20px;
}

.signinbox button:active {
	transform: scale(0.95);
}

.signinbox button:focus {
	outline: none;
}

.signinbox button.ghost {
	background-color: #446491;
}

.signinbox form {
	background-color: #ebf5fc;
	display: flex;
	align-items: center;
	justify-content: center;
	flex-direction: column;
	padding: 0 50px;
	height: 100%;
	border-radius: 10px;
	text-align: center;
	box-shadow: -5px -5px 15px rgba(255,255,255,0.8),
				5px 5px 10px rgba(0,0,0,0.1);
}

.signinbox input {
	background: #ebf5fc;
	height: 40px;
	border: none;
	outline: none;
	padding: 5px 15px;
	border-radius: 40px;
	padding: 5px 15px;
	color: #fff;
	color: black;
	margin: 8px 0;
	width: 100%;
	box-shadow: inset -2px -2px 6px rgba(255,255,255,1),
				inset 2px 2px 6px rgba(0,0,0,0.1);
}

.signinbox button:active{
	color: #ebf5fc;
	box-shadow: inset -2px -2px 6px rgba(255,255,255,0.1),
				inset 2px 2px 6px rgba(0,0,0,0.1);
}

.signinbox {
	background-color: #f6f5f7;
	border-radius: 10px;
  	box-shadow: 0 14px 28px rgba(0,0,0,0.25), 
			0 10px 10px rgba(0,0,0,0.22);
	position: relative;
	overflow: hidden;
	width: 768px;
	max-width: 100%;
	min-height: 480px;
}

.form-container {
	position: absolute;
	top: 0;
	height: 100%;
	transition: all 0.6s ease-in-out;
}

.sign-in-container {
	left: 0;
	width: 50%;
	z-index: 2;
}

.signinbox.right-panel-active .sign-in-container {
	transform: translateX(100%);
}

.sign-up-container {
	left: 0;
	width: 50%;
	opacity: 0;
	z-index: 1;
}

.signinbox.right-panel-active .sign-up-container {
	transform: translateX(100%);
	opacity: 1;
	z-index: 5;
	animation: show 0.6s;
}

@keyframes show {
	0%, 49.99% {
		opacity: 0;
		z-index: 1;
	}
	
	50%, 100% {
		opacity: 1;
		z-index: 5;
	}
}

.overlay-container {
	position: absolute;
	top: 0;
	left: 50%;
	width: 50%;
	height: 100%;
	overflow: hidden;
	transition: transform 0.6s ease-in-out;
	z-index: 100;
}

.signinbox.right-panel-active .overlay-container{
	transform: translateX(-100%);
}

.overlay {
	background: -webkit-linear-gradient(to right, #FF4B2B, #FF416C);
	background: #86a6df;
	background-repeat: no-repeat;
	background-size: cover;
	background-position: 0 0;
	color: #FFFFFF;
	position: relative;
	left: -100%;
	height: 100%;
	width: 200%;
  	transform: translateX(0);
	transition: transform 0.6s ease-in-out;
}

.signinbox.right-panel-active .overlay {
  	transform: translateX(50%);
}

.overlay-panel {
	position: absolute;
	display: flex;
	align-items: center;
	justify-content: center;
	flex-direction: column;
	padding: 0 40px;
	text-align: center;
	top: 0;
	height: 100%;
	width: 50%;
	transform: translateX(0);
	transition: transform 0.6s ease-in-out;
}

.overlay-left {
	transform: translateX(-20%);
}

.signinbox.right-panel-active .overlay-left {
	transform: translateX(0);
}

.overlay-right {
	right: 0;
	transform: translateX(0);
}

.signinbox.right-panel-active .overlay-right {
	transform: translateX(20%);
}

/* signin box css end /////////////////////////////////////////////////////////*/

/* mainPage css start /////////////////////////////////////////////////////////*/

 .mainPage{
 	position: relative;
 	width: 100%;
 	height: 100vh;
 	overflow: hidden;
 	display: flex;
 	justify-content: center;
 	align-items: center;
 }
 
 .mainPage:before{
 	content: '';
 	position: absolute;
 	bottom: 0;
 	width: 100%;
 	height: 100px;
 	background: linear-gradient(to top, #0a2a43, transparent);
 	z-index: 3;
 }
 
 .mainPage:after{
 	content: '';
 	position: absolute;
 	top: 0;
 	left: 0;
 	width: 100%;
 	height: 100%;
 	background: #0a2a43;
 	z-index: 3;
 	mix-blend-mode: color;
 }
 
 .mainPage img{
 	position: absolute;
 	top: 0;
 	left: 0;
 	width: 100%;
 	height: 100%;
 	object-fit: cover;
 	pointer-events: none;
 }
 
 #mainText{
 	position: relative;
 	color: #f5f7fa;
 	font-size: 10em;
 	z-index: 1;
 }
 
 #road{
 	z-index: 2;
 }
 
/* mainPage css end /////////////////////////////////////////////////////////*/

/* upArrow css start /////////////////////////////////////////////////////////*/

.goTopBtn{
	position: fixed;
	width: 50px;
	height: 50px;
	background: rgba(var(--b3f,250,250,250),1);
	bottom: 40px;
	right: 50px;
	text-decoration: none;
	text-align: center;
	line-height: 50px;
	color: white;
	font-size: 30px;
	z-index: 10;
	box-shadow: 6px 6px 9px rgba(0,0,0,0.2),
						 3px 3px 10px rgba(255,255,255,1);
	border: 1px solid rgba(var(--b3f,250,250,250),1);
	border-radius: 50%;
}

/* upArrow css end /////////////////////////////////////////////////////////*/

/* image blur and button effect start////////////////////////////*/

.container{
	position: relative;
	width: 100%;
	min-height: 100vh;
	display: flex;
	justify-content: center;
	align-items: center;
	transition: 0.5s;
	padding: 20px;
	background: rgba(var(--b3f,250,250,250),1);
}

.container#blur.active{
	filter: blur(20px);
	pointer-events: none;
	user-select: none;
}

.container .content{
	position: relative;
	max-width: 800px;
	margin-right: 50px;
}

.container h2{
	margin-bottom: 10px;
	color: #333;
    margin-bottom: 30px;
    margin-right: 100px;
    font-family: 'Nanum Pen Script', cursive;
    font-size: 52px;
    white-space: nowrap;
    pointer-events: none;
}

.container .content img{
	width: 900px;
	display: block;
	margin-left: 100px;
	box-shadow: 6px 6px 12px rgba(0,0,0,0.15),
						 -6px -6px 12px #dee1ec;
	border: 2px solid #dee1ec;
	border-radius: 25px;
}

.container a, #popup a{
	font-family: 'Poppins', sans-serif;
	color: #000;
	position: relative;
	padding: 5px 20px;
	display: inline-block;
	margin-top: 20px;
	text-decoration: none;
	transition: 0.5s;
}

.container a{
	margin-left: 100px;
}

.container a span, #popup a span{
	position: relative;
	z-index: 1;
	font-family: 'Nanum Pen Script', cursive;
	font-size: 30px;
}

.container a:hover, #popup a:hover{
	color: #fff;
}

.container a:before, #popup a:before{
	content: '';
	position: absolute;
	top: 0;
	left: 0;
	width: 0;
	height: 100%;
	background: url("/webproject2020/images/crayonBtn.png");
	background-size: 100% 100%;
	transform-origin: left;
	transition: 0.5s;
}

.container a:hover:before, #popup a:hover:before{
	width: 100%;
}

#popup{
	position: fixed;
	top: 40%;
	left: 50%;
	transform: translate(-50%,-50%);
	width: 600px;
	padding: 50px;
	box-shadow: 0 5px 30px rgba(0,0,0,.30);
	background: #fff;
	visibility: hidden;
	opacity: 0;
	transition: 0.5s;
}

#popup.active{
	top: 50%;
	visibility: visible;
	opacity: 1;
	transition: 0.5s;
}

#popup h2{
	font-family: 'Jua', sans-serif;
	font-size: 30px;
	word-break: break-all;
	word-spacing: 5px;
	margin-bottom: 20px;
	pointer-events: none;
}

#popup p{
	font-family: 'Jua', sans-serif;
	font-size: 22px;
	white-space: nowrap;
	margin-top: 20px;
	pointer-events: none;
}

#popup img{
	max-width: 500px;
	max-height: 500px;
}

/* image blur and button effect end////////////////////////////*/

/* mountain parallax effect start////////////////////////////*/

.zoom{
	width: 100%;
	height: 100vh;
	position: relative;
	overflow: hidden;
	background: url(/webproject2020/images/bg.jpg);
	background-size: cover;
}

.zoom:before{
	content: '';
	position: absolute;
	bottom: 0;
	width: 100%;
	height: 200px;
	z-index: 5;
	background: linear-gradient(transparent, #fff);
}

.zoom #layer1{
	position: absolute;
	width: 100%;
	z-index: 4;
}

.zoom #layer2{
	position: absolute;
	right: 0;
	width: 100%;
	z-index: 3;
}

.zoom #mountainText{
	position: absolute;
	height: 70vh;
	right: -4em;
	width: 100%;
	transform: translateY(50%);
	color: #f5f7fa;
 	font-size: 10em;  
}

/* mountain parallax effect end////////////////////////////*/

/* slideshow effect start////////////////////////////*/

.container2{
	position: relative;
	overflow: hidden;
	width: 100vw;
	height: 100vh;
}

.container2 .slider{
	position: absolute;
	top: 0;
	left: 0;
	width: 500%;
	height: 100%;
	animation: animate 10s linear infinite;
}

.container2 .slider .slide{
	width: 20%;
	height: 100%;
	float: left;
}

.container2 .slider .slide.slide1{
	background: url(/webproject2020/images/mainpage.PNG);
	background-size: 50vw;
	background-repeat: no-repeat;
	background-position: center;
}

.container2 .slider .slide.slide2{
	background: url(/webproject2020/images/postpage.PNG);
	background-size: 40vw;
	background-repeat: no-repeat;
	background-position: center;
}

.container2 .slider .slide.slide3{
	background: url(/webproject2020/images/replypage.JPG);
	background-size: 50vw;
	background-repeat: no-repeat;
	background-position: center;
}

.container2 .slider .slide.slide4{
	background: url(/webproject2020/images/searchpage.JPG);
	background-size: 50vw;
	background-repeat: no-repeat;
	background-position: center;
}

@keyframes animate{
	0%
	{
		left: 0;
	}
	20%
	{
		left: 0;
	}
	25%
	{
		left: -100%;
	}
	45%
	{
		left: -100%;
	}
	50%
	{
		left: -200%;
	}
	70%
	{
		left: -200%;
	}
	75%
	{
		left: -300%;
	}
	95%
	{
		left: -300%;
	}
	100%
	{
		left: 0;
	}
}

/* slideshow effect end////////////////////////////*/

</style>
<link href="https://fonts.googleapis.com/css2?family=Itim&family=Mali:wght@600&family=Marcellus+SC&family=Nanum+Pen+Script&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Itim&family=Jua&family=Mali:wght@600&family=Marcellus+SC&family=Nanum+Pen+Script&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Itim&family=Raleway:wght@100&display=swap" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		init('${requestScope.id}');
		checkId('${requestScope.existId}');
		checkName('${requestScope.existName}');
		checkEmail('${requestScope.existEmail}');
	});
</script>
</head>
<body>
<section class="signin">
<div id="logoWord">note</div>
<div class="signinbox" id="signinbox">
	<div class="form-container sign-up-container">
		<form action="signup.do" method="post">
			<h1>Create Account</h1>
			<input type="text" name="fid" id="fid" placeholder="ID" />
			<input type="password" name="fpass" id="fpass" placeholder="Password" />
			<input type="text" name="fname" id="fname" placeholder="Nickname" />
			<input type="email" name="email" id="email" placeholder="Email" />
			<button type="submit">Sign Up</button>
		</form>
	</div>
	<div class="form-container sign-in-container">
		<form action="main.do" method="post">
			<h1>Sign in</h1>
			<input type="text" name="fid" id="fid" placeholder="ID" />
			<input type="password" name="fpass" id="fpass" placeholder="Password" />
			<button type="submit">Sign In</button>
		</form>
	</div>
	<div class="overlay-container">
		<div class="overlay">
			<div class="overlay-panel overlay-left">
				<h1>NOTE에 오신걸 환영합니다!</h1>
				<p>새로운 계정을 만들어 당신의 이야기를 공유하세요!</p>
				<button class="ghost" id="signIn">Sign In</button>
			</div>
			<div class="overlay-panel overlay-right">
				<h1>안녕하세요!</h1>
				<p>오늘의 사진을 올려보세요!</p>
				<button class="ghost" id="signUp">Sign Up</button>
			</div>
		</div>
	</div>
</div>
</section>
	
	<section class="mainPage">
		<img src="${pageContext.request.contextPath}/loginpageview/images/skybg.jpg" id="skybg">
		<img src="${pageContext.request.contextPath}/loginpageview/images/moon.png" id="moon">
		<img src="${pageContext.request.contextPath}/loginpageview/images/mountain.png" id="mountain">
		<img src="${pageContext.request.contextPath}/loginpageview/images/road.png" id="road">
		<h2 id="mainText">Connect</h2>
	</section>
	
	<a class="goTopBtn" href="#"><img src="${pageContext.request.contextPath}/loginpageview/images/upArrow.png" id="upArrow"></a>
	
	<div class="container" id="blur">
	<h2>당신의 이야기를 모두와 공유해보세요!</h2>
		<div class="content">
			<img src="${pageContext.request.contextPath}/images/example1.PNG">
			<a href="#" onclick="toggle()"><span>더 보기</span></a>
		</div>
	</div>
	<div id="popup">
		<h2>note에서 여러분의 사진을 자유롭게 공유할 수 있습니다!</h2>
		<img src="${pageContext.request.contextPath}/images/example2.PNG">
		<p>당신의 취미를 많은 사람들과 함께 나누어보세요!</p>
		<a href="#" onclick="toggle()"><span>닫기</span></a>
	</div>
	
	<section class="zoom">
		<img src="${pageContext.request.contextPath}/images/mountain1.png" id="layer1">
		<img src="${pageContext.request.contextPath}/images/mountain2.png" id="layer2">
		<h2 id="mountainText">Explore</h2>
	</section>
	
	<div class="container2">
		<div class="slider">
			<div class="slide slide1">
				<div class="caption">
				</div>
			</div>
			
			<div class="slide slide2">
				<div class="caption">
				</div>
			</div>
			
			<div class="slide slide3">
				<div class="caption">
				</div>
			</div>
			
			<div class="slide slide4">
				<div class="caption">
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/loginpageview/js/login.js"></script>
</body>
</html>
