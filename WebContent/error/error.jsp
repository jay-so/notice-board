<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<jsp:include page="../common/header.jsp"/>

<script src="/controller/resources/js/jquery.form.min.js"></script>

<div class="container">
	<div class="col-lg-8 col-lg-offset-2 text-center">
		<div class="logo">
			<h1>오류입니다! 다시 확인해주세요!</h1>
		</div>
		<!-- 오류 메시지 저장할 변수 errorMsg -->
		<p class="lead text-muted">${errorMsg} </p>
		
		<div class="clearfix"></div>
		
		<div class="clearfix"></div>
		<br />
		<c:choose>
		  <c:when test="${empty loginUser}">
		      <div class="col-lg-6  col-lg-offset-3">
			<div class="btn-group btn-group-justified">
				<a href="${pageContext.request.contextPath}/user/login.jsp" class="btn btn-primary">로그인 페이지로 돌아가기</a>
				<a href="${pageContext.request.contextPath}" class="btn btn-success">웹 사이트로 돌아가기</a>
				<a href="javascript:history.back()" class="btn btn-success">뒤로가기</a>
			</div>
		    </div>
		  </c:when>
		  <c:otherwise>
		      <div class="col-lg-6  col-lg-offset-3">
			<div class="btn-group btn-group-justified">
				<a href="javascript:history.back()" class="btn btn-primary">뒤로 가기</a>
				<a href="${pageContext.request.contextPath}" class="btn btn-success">웹 사이트로 돌아가기</a>
			</div>

		</div>
		  </c:otherwise>
		</c:choose>	
	</div>

</div>


<jsp:include page="../common/footer.jsp"/>