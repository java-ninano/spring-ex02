<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>

<title>Insert title here</title>
<script>

    // new register
	$(document).ready(function() {
		$("#btn-1").click(function() {
			$.ajax({
				method : "POST",
				url : "/replies/new",
				data : '{"bno":200, "reply":"new reply","replyer":"user00"}',
				contentType : "application/json"

			});
		});

		//list
		$("#btn-2").click(function() {
			$.ajax({
				method : "GET",
				url : "/replies/pages/200/1"
			});
		});
		
       // delete
		$("#btn-3").click(function() {
			$.ajax({
				method : "DELETE",
				url : "/replies/24",
				data : '{"rno":24}',
				complete : function(jqXHR,status){
					if(status =="success"){
						console.log("삭제 성공입니다");
						console.log(jqXHR.responseText);
					}else if(status =="error"){
						console.log("삭제 실패입니다.");
					}
				}
			});
		});
       
		//modify
		$("#btn-4").click(function() {
			$.ajax({
			method :"PUT",
			url:"/replies/25",
		    contentType : "application/json",
		    data : '{"bno":199,"reply":"new reply999","replyer":"user00"}',
		    complete : function(jqXHR,status){
		    	if(status =="success") {
		    		console.log("수정 성공입니다");
		    		console.log(jqXHR.responseText);
		    	}else if(status =="error"){
		    		console.log("수정 실패입니다.");
		    	}
		    }
			});
		});
		
		//댓글 하나보기
		$("#btn-5").click(function() {
			$.ajax({
			method: "GET",
			//method == type
			url:"/replies/26"
			});
		});
	});
</script>
</head>
<body>

	<h1>AJAX ex1</h1>
	<!-- http://localhost:8080/ajaxEx1.jsp
     응답이 올때까지 기다림(동기화) 
 -->
	<div>
		<button id="btn-1">새 댓글</button>
		<!--"#btn-1 ==  id="btn-1  -->
	</div>
	<div>
		<button id="btn-2">댓글 목록</button>
	</div>
	<div>
		<button id="btn-3">댓글 삭제</button>
	</div>
	<div>
		<button id="btn-4">댓글 수정</button>
	</div>
	<div>
	<button id="btn-5">댓글 하나만 보기</button>
	</div>
</body>
</html>