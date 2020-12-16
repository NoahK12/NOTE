/* login fail alert start /////////////////////////////////////////////////////////*/

function init(id){
	if(id){
		alert("회원정보가 없거나 틀렸습니다.");
		$("#fid").val(id);
	}
}

/* login fail alert end /////////////////////////////////////////////////////////*/

/* signup fail alert start /////////////////////////////////////////////////////////*/

function checkId(cid){
	if(cid){
		alert("중복된 아이디입니다.");
		$("#fid").val("");
	}
}

function checkName(cname){
	if(cname){
		alert("중복된 닉네임입니다.");
		$("#fname").val("");
	}
}

function checkEmail(email){
	if(email){
		alert("중복된 이메일입니다.");
		$("#email").val("");
	}
}

/* signup fail alert end /////////////////////////////////////////////////////////*/

/* password number alert start /////////////////////////////////////////////////////////*/

$(document).ready(function(){
	$(".sign-up-container form").submit(function(){
		if($("#fid").val().length===0 || $("#fpass").val().length===0
				|| $("#fname").val().length===0 || $("#email").val().length===0){
			alert("모든 정보를 채워주세요!");
			return false;
		}
		
		if($("#fpass").val().length < 6 || $("#fpass").val().length > 20){
			alert("비밀번호는 6자 이상 20자 이하이여야 합니다.")
			return false;
		}
		
	});
});

/* password number alert end /////////////////////////////////////////////////////////*/

/* signin form effect start /////////////////////////////////////////////////////////*/

const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const signinbox = document.getElementById('signinbox');

signUpButton.addEventListener('click', () => {
	signinbox.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
	signinbox.classList.remove("right-panel-active");
});

/* signin form effect end /////////////////////////////////////////////////////////*/

/* parallax scroll effect start /////////////////////////////////////////////////////////*/

let moon = document.getElementById("moon");
let mountain = document.getElementById("mountain");
let road = document.getElementById("road");
let mainText = document.getElementById("mainText");

window.addEventListener('scroll', function(){
	var value = window.scrollY;
	
	moon.style.left = -value * 0.25 + 'px';
	mountain.style.top = -value * 0.1 + 'px';
	road.style.top = value * 0.05 + 'px';
	mainText.style.top = value * 0.1 + 'px';
});

/* parallax scroll effect end /////////////////////////////////////////////////////////*/

/* image blur and button effect start /////////////////////////////////////////////////////////*/

$(document).ready(function(){
	$('.content a').click(function(){
		return false;
	});
	$('#popup a').click(function(){
		return false
	});
});

function toggle(){
	var blur = document.getElementById('blur');
	blur.classList.toggle('active')
	var popup = document.getElementById('popup');
	popup.classList.toggle('active');
}

/* image blur and button effect end /////////////////////////////////////////////////////////*/

/* mountain parallax effect start /////////////////////////////////////////////////////////*/

let layer1 = document.getElementById("layer1");
let layer2 = document.getElementById("layer2");

window.addEventListener('scroll', function(){
	var valueMountain = window.scrollY - 2910;
	
	layer1.style.left = -valueMountain * 0.15 + 'px';
	layer2.style.left = valueMountain * 0.15 + 'px';
});

/* mountain parallax effect end /////////////////////////////////////////////////////////*/

