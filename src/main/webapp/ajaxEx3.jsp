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
	$(document).ready(function() {
		$("#btn-1").click(function() {
			$.ajax({
				type : "POST",
				url : "/replies/new",
				contentType : "application/json",
				data : '{"bno":203,"reply":"새 댓글입니다","replyer":"user01"}',
				//응답이 왔을때 해야할일이 complete, ajax의 객체 : jqXHR
				complete : function(jqXHR, status) {
					console.log("complete");
					console.log(status);
				}
			});
		});
		
			$("#btn-2").click(function() {
				$.ajax({
					type : "POST",
					url : "/replies/new",
					contentType : "application/json",
					data : '{"reply":"새 댓글입니다","replyer":"user01"}',
					complete : function(jqXHR, status) {
						console.log("complete");
						console.log(status);
					}
				});
			});	
					$("#btn-3").click(function() {
					$.ajax({
						type : "POST",
						url : "/replies/new",
						contentType : "application/json",
						data : '{"bno":203,"reply":"새 댓글입니다","replyer":"user01"}',
						success:function(data, status, xhr) {
							console.log("등록 성공입니다.");
							//console.log(jqXHR.responseText);
							console.log(data);
						},
						error:function(){
							console.log("등록 실패입니다.");
						}
						
					});
				});	
					// list
				$("#btn-4").click(function() {
		$.ajax({
			url: "/replies/pages/196/1",
			type: "get",
			success: function(data) {
				console.log(data);
			}
		});
	});
					
					//modify
					$("#btn-5").click(function() {
						$.ajax({
						method :"PUT",
						url:"/replies/32",
					    contentType : "application/json",
					    data : '{"reply":"수정된 댓글입니다!!."}',
					    success:function(){
					    	console.log("수정 성공입니다");
					    },
						error:function(){
							console.log("수정 실패입니다.");
						}
					    
						});
					});
					
					
				       // delete
						$("#btn-6").click(function() {
							$.ajax({
								method : "DELETE",
								url : "/replies/26",
								success:function(){
									console.log("삭제 성공입니다");
								},
							error:function(){
								console.log("삭제 실패입니다.");
							}
							
								/*
								complete : function(jqXHR,status){
									if(status =="success"){
										console.log("삭제 성공입니다");
									}else if(status =="error"){
										console.log("삭제 실패입니다.");
									}
								}
							*/
							});
						});
				});
</script>
</head>
<body>
	<h1>AJAX ex3</h1>
	<div>
		<button id="btn-1">댓글 등록입니다.</button>
	</div>
	<div>
		<button id="btn-2">댓글 등록 실패입니다.</button>
	</div>
	<div>
		<button id="btn-3">댓글 등록 성공 또는 실패입니다.</button>
	</div>
	<div>
		<button id="btn-4">댓글 목록입니다.</button>
	</div>
	<div>
	<button id="btn-5">댓글 수정합니다.</button>
	</div>
	<div>
	<button id="btn-6">댓글을 삭제합니다.</button>
	</div>
</body>
</html>