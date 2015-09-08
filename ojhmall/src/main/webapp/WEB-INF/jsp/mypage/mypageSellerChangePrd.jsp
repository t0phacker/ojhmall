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
	function initRadio() {
		var nowChecked = document.getElementById("ctgrNumber").value;
		var count = parseInt(document.getElementById("radioCount").value);
		for (var n=1; n <= count; n++) {
			if (document.getElementById("ctgr"+n).value == nowChecked) {
				document.getElementById("ctgr"+n).checked = true;
			}
		}
	}
	
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

<body onload="initRadio()">

	<div class="container">
		<!-- 로고 & 회원메뉴 -->
		<jsp:include page="/WEB-INF/jsp/layout/userBar.jsp" />
		<!-- 카테고리 바 -->
		<jsp:include page="/WEB-INF/jsp/layout/ctgrBar.jsp" />
		<!-- 상품 검색 -->
		<jsp:include page="/WEB-INF/jsp/layout/srchBar.jsp" />
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
			<h2>상품 변경 페이지</h2>
			<div class="col-md-12">
				<h4>상품 정보를 입력해주세요</h4>
				<form class="form-signin" action="changedPrdBySeller.do" method="POST">
					<table class="table table-striped" id="payTable">
						<tbody>
							<tr>
								<td>상품 이름</td>
								<td><input type="text" id="prdName" name="prdName" value="${prdInfo.prdName }"></td>
							</tr>
							<tr>
								<td>상품 이미지(URL)</td>
								<td><input type="text" id="image" name="image" value="${prdInfo.image }"></td>
							</tr>
							<tr>
								<td>가격</td>
								<td><input type="text" id="price" name="price" value="${prdInfo.price }"></td>
							</tr>
							<tr>
								<td>수량</td>
								<td><input type="text" id="stock" name="stock" value="${prdInfo.stock }"></td>
							</tr>
							<tr>
								<td>설명</td>
								<td><textarea rows="10" cols="30" id="text" name="text">${prdInfo.text }</textarea></td>
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
											<input type="hidden" id="radioCount" name="radioCount" value="${fn:length(lowerCategoryList)}">
										</c:when>
									</c:choose></td>
									<input type="hidden" id="prdNumber" name="prdNumber" value="${prdInfo.prdNumber }">
									<input type="hidden" id="ctgrNumber" name="ctgrNumber" value="${prdInfo.ctgrNumber }">
							</tr>
						</tbody>
					</table>
					<div style="text-align: center">
						<input type="submit" class="btn btn-lg btn-danger" value="변경">
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
