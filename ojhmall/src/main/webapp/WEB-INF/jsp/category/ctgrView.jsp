<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="true"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">

<title>Cart</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/bootstrap-theme.min.css">
<link href="../css/justified-nav.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="../css/theme.css" rel="stylesheet">
<!-- 검색 js -->
<script src="../js/ojhfunction.js"></script>
</head>

<body>

	<div class="container">
		<!-- 로고 & 회원메뉴 -->
		<jsp:include page="/WEB-INF/jsp/layout/userBar.jsp" />
		<!-- 카테고리 바 -->
		<jsp:include page="/WEB-INF/jsp/layout/ctgrBar.jsp" />
		<!-- 상품 검색 -->
		<jsp:include page="/WEB-INF/jsp/layout/srchBar.jsp" />
		<div class="container">
			<ul class="nav navbar-nav navbar-right">
				<li><h3>
						<a href="ctgrView.do?ctgrVal=${ctgrNumber}"> <span
							class="label label-danger" style="margin-right: 10px;">인기순</span>
						</a>
					</h3></li>
				<li><h3>
						<a href="ctgrViewByPrice.do?ctgrVal=${ctgrNumber}"> <span
							class="label label-primary" style="margin-right: 30px;">가격순</span>
						</a>
					</h3></li>
			</ul>
		</div>
		<!-- Jumbotron -->
		<div class="container">

			<c:choose>
				<c:when test="${fn:length(prdList) > 0 }">
					<c:forEach items="${prdList}" var="row">
						<div class="col-lg-4">
							<a href=../product/prdView.do?prdNum=${row.prdNumber}> <img
								src="${row.image}" width="240" height="240">
								<h3>${row.prdName}</h3> <font color="orange">${row.price}원</font>
							</a> 관심지수 : ${row.hitCount}
						</div>
					</c:forEach>
				</c:when>
			</c:choose>

		</div>
		<!-- Site footer -->
		<footer class="footer">
			<p>&copy; Company 2014</p>
		</footer>

	</div>
	<!-- /container -->


	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
