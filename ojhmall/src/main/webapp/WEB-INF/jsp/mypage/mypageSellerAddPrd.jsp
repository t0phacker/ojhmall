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

<title>My Page For Customer</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/bootstrap-theme.min.css">
<link href="../css/justified-nav.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="../css/theme.css" rel="stylesheet">
<!-- 검색 js -->
<script src="../js/ojhfunction.js"></script>

<script type="text/javascript">
	function radioCtgr(selected, count) {
		if (document.getElementById("ctgr"+selected).checked == true) {
			document.getElementById("ctgr"+selected).checked = true;
			document.getElementById("ctgrNumber").value = document.getElementById("ctgr"+selected).value;
			for (var i = 1; i < selected; i++) {
				document.getElementById("ctgr"+i).checked = false;
			}
			for (var j = selected+1; j <= count; j++) {
				document.getElementById("ctgr"+j).checked = false;
			}
		}
		else {
			document.getElementById("ctgr"+selected).checked = false;
			for (var k = 1; k < selected; k++) {
				document.getElementById("ctgr"+k).checked = true;
			}
			for (var l = selected+1; l <= count; l++) {
				document.getElementById("ctgr"+l).checked = true;
			}
		}
	}
</script>
</head>

<body>

	<div class="container">
		<div class="masthead">
			<a href="http://localhost:8080/ojhmall"><img
				src="../image/11st.png"></a>

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
						<li><a href="../cart/cartView.do">장바구니</a></li>
						<li><a href="mypageForCustomer.do">마이페이지</a></li>
					</c:when>

					<c:when test="${sessionScope.userLogInInfo.userType == 0}">
						<li><a href="../user/AdminInfoForm.do">회원정보</a></li>
						<li><a href="../cart/cartView.do">장바구니</a></li>
						<li><a href="mypageForAdmin.do">마이페이지</a></li>
					</c:when>
					<c:when test="${sessionScope.userLogInInfo.userType == 1}">
						<li><a href="../user/CustomerInfoForm.do">회원정보</a></li>
						<li><a href="../cart/cartView.do">장바구니</a></li>
						<li><a href="mypageForCustomer.do">마이페이지</a></li>
					</c:when>
					<c:when test="${sessionScope.userLogInInfo.userType == 2}">
						<li><a href="../user/SellerInfoForm.do">회원정보</a></li>
						<li><a href="../cart/cartView.do">장바구니</a></li>
						<li><a href="mypageForSeller.do">마이페이지</a></li>
					</c:when>

				</c:choose>
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
						placeholder="검색어를 입력하세요" value="${prdName}" />
				</div>
				<button type="submit" class="btn btn-default" onclick="search();">검색</button>
			</form>
		</div>
		<!-- 주문 정보 확인 -->
		<div class="container">
			<ul class="nav navbar-nav navbar-right">
				<li><h3>
						<a href="sellerAddPrd.do"> <span class="label label-danger"
							style="margin-right: 10px;">상품 등록</span>
						</a>
					</h3></li>
				<li><h3>
						<a href="mypageForSeller.do"> <span
							class="label label-primary" style="margin-right: 10px;">입금
								확인/배송 요청</span>
						</a>
					</h3></li>
				<li><h3>
						<a href="sellerCheckPrd.do"> <span class="label label-success"
							style="margin-right: 30px;">내가 등록한 상품</span>
						</a>
					</h3></li>
			</ul>
			<h2>상품 등록 페이지</h2>
			<div class="col-md-12">
				<h4>상품 정보를 입력해주세요</h4>
				<form class="form-signin" action="addPrdBySeller.do" method="POST">
					<table class="table table-striped" id="payTable">
						<tbody>
							<tr>
								<td>상품 이름</td>
								<td><input type="text" id="prdName" name="prdName" value=""></td>
							</tr>
							<tr>
								<td>상품 이미지(URL)</td>
								<td><input type="text" id="image" name="image" value=""></td>
							</tr>
							<tr>
								<td>가격</td>
								<td><input type="text" id="price" name="price" value=""></td>
							</tr>
							<tr>
								<td>수량</td>
								<td><input type="text" id="stock" name="stock" value=""></td>
							</tr>
							<tr>
								<td>설명</td>
								<td><textarea rows="10" cols="30" id="text" name="text">길이 제한 : 200자</textarea></td>
							</tr>
							<tr>
								<td>카테고리</td>
								<td><c:choose>
										<c:when test="${fn:length(lowerCategoryList) > 0 }">
											<c:forEach items="${lowerCategoryList}" var="col"
												varStatus="status">
												<input type="radio" id="ctgr${status.count}"
													onclick="radioCtgr(${status.count}, ${fn:length(lowerCategoryList)});
													" value="${col.ctgrNumber }">
													${col.ctgrName}
											</c:forEach>
										</c:when>
									</c:choose></td>
									<input type="hidden" id="ctgrNumber" name="ctgrNumber" value="">
							</tr>
						</tbody>
					</table>
					<div style="text-align: center">
						<input type="submit" class="btn btn-lg btn-danger" value="등록">
						<input type="reset" class="btn btn-lg btn-success" value="초기화">
						<a href="http://localhost:8080/ojhmall"
							class="btn btn-lg btn-default">취소</a>
					</div>
				</form>
			</div>

			<!-- Site footer -->
			<footer class="footer">
				<p>&copy; Company 2014</p>
			</footer>
		</div>
	</div>
	<!-- /container -->
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
