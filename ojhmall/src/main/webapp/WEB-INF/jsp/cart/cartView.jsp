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
<link rel="icon" href="../../favicon.ico">

<title>Product</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/bootstrap-theme.min.css">
<link href="../css/justified-nav.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="../css/theme.css" rel="stylesheet">
<!-- 검색 js -->
<script src="../js/ojhfunction.js"></script>

<script src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
<script>

</script>
</head>

<body>

	<div class="container">
		<div class="masthead">
			<a href="#"><img src="../image/11st.png"></a>

			<ul class="nav navbar-nav navbar-right">
				<c:choose>
					<c:when test="${empty sessionScope.userLogInInfo.userName}">
						<li><a href="../user/LogInForm.do">로그인</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="../logOut.do">${sessionScope.userLogInInfo.userName}님</a></li>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${empty sessionScope.userLogInInfo.userName}">
						<li><a href="../user/SiginUpForm.do">회원가입</a></li>
					</c:when>

					<c:when test="${sessionScope.userLogInInfo.userType == 0}">
						<li><a href="../user/AdminInfoForm.do">회원정보</a></li>
					</c:when>
					<c:when test="${sessionScope.userLogInInfo.userType == 1}">
						<li><a href="../user/CustomerInfoForm.do">회원정보</a></li>
					</c:when>
					<c:when test="${sessionScope.userLogInInfo.userType == 2}">
						<li><a href="../user/SellerInfoForm.do">회원정보</a></li>
					</c:when>

				</c:choose>
				<li><a
					href="cartView.do?userNumber=${sessionScope.userLogInInfo.userNumber}">장바구니</a></li>
				<li><a href="#">마이페이지</a></li>
			</ul>
		</div>
		<div class="masthead">

			<nav class="navbar navbar-default">
				<ul class="nav nav-justified">
					<c:choose>
						<c:when test="${fn:length(upperCategoryList) > 0 }">
							<c:forEach items="${upperCategoryList}" var="row">
								<li class="dropdown-toggle"><a href="#"
									class="dropdown-toggle" data-toggle="dropdown" role="button"
									aria-expanded="false"> ${row.ctgrName } <span class="caret"></span>
								</a>
									<ul class="dropdown-menu" role="menu">
										<c:forEach items="${lowerCategoryList}" var="col">
											<c:if
												test="${col.ctgrNumber > row.ctgrNumber && col.ctgrNumber < (row.ctgrNumber + 100)}">
												<li><a
													href="../category/ctgrView.do?ctgrVal=${col.ctgrNumber }">${col.ctgrName }</a></li>
											</c:if>
										</c:forEach>

									</ul></li>

							</c:forEach>
						</c:when>
					</c:choose>
				</ul>
			</nav>
		</div>
		<!-- 상품 검색 -->
		<div class="container">
			<form class="navbar-form navbar-left" id="form_search" role="search"
				action="../product/prdSearch.do" method="POST">
				<div class="form-group">
					<input type="text" class="form-control" id="prdName" name="prdName"
						placeholder="Search" value="${prdName}" />
				</div>
				<button type="submit" class="btn btn-default" onclick="search();">Submit</button>
			</form>
		</div>
		<!-- 장바구니 테이블 -->
		<div class="col-md-12">
			<table class="table table-striped">
				<thead>
					<tr>
						<th><label for="bcktSeq_All"><input
								name="bcktSeq_All" type="checkbox" value="" id="bcktSeq_All"
								onclick="allCheckAction(this);" title="전체상품선택"></label></th>
						<th>상품정보</th>
						<th>수량</th>
						<th>상품금액</th>
						<th>배송비</th>
						<th>주문</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${fn:length(cartList) > 0 }">
							<c:forEach items="${cartList}" var="row">
								<tr>
									<td><input name="bcktSeq_All" type="checkbox" value=""
										id="bcktSeq_All" onclick="allCheckAction(this);"
										title="전체상품선택"></td>
									<td width=40% heigth="120"><img style="float: left"
										src="${row.image }" width="120" height="120">
										<h5">${row.prdName}</h5></td>
									<td>${row.prdAmount}</td>
									<td>${row.cartPrice}</td>
									<td>${row.deliveryFee}</td>
									<td>
										<form>
											<button type="button" class="btn btn-danger">주문하기</button>
										</form>
										<form>
											<button type="button" class="btn btn-default">삭제하기</button>
										</form>
									</td>
								</tr>
							</c:forEach>
						</c:when>
					</c:choose>
				</tbody>
			</table>
		</div>
		<input type="checkbox" name="bcktSeq_All_bottom"
			id="bcktSeq_All_bottom" onclick="allCheckAction(this);"
			title="장바구니 전체 상품 선택"> 선택상품 <b id="checkPrdCnt">(1개)</b> <a
			href="javascript:funcCheckDel();" class="btn btn-default"
			onclick="doCommonStat('SCSP001');"><span>삭제하기</span></a>

		<div class="col-md-12">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>주문금액</th>
						<th>배송금액</th>
						<th>총금액</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${totalCartPrice}원</td>
						<td>5000원</td>
						<td>${totalCartPrice}원</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<!-- Site footer -->
	<footer class="footer">
		<p>&copy; Company 2014</p>
	</footer>

	<!-- /container -->

	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
