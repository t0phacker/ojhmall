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
	
</script>
</head>

<body>

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
			<h2>${userInfo.userName }님이등록하신 상품</h2>
			<div class="col-md-12">
				<table class="table table-striped" id="payTable">
					<thead>
						<tr>
							<th>상품 번호</th>
							<th>상품 정보</th>
							<th>등록일</th>
							<th>가격</th>
							<th>재고</th>
							<th>상태</th>
							<th>변경</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${fn:length(prdList) > 0 }">
								<c:forEach items="${prdList }" var="row" varStatus="status">
									<tr>
										<td>${row.prdNumber }<br>
										<td width=500><img style="float: left"
											src="${row.image }" width="120" height="120"> 이름:
											${row.prdName} <br> 설명: ${row.text }</td>
										<td>${row.newDate }</td>
										<td>${row.price }</td>
										<td>${row.stock}</td>
										<td><c:if test="${row.prdStatus == 1 }">
												<h4>
													<span class="label label-info">전시대기</span>
												</h4>
											</c:if> <c:if test="${row.prdStatus == 2 }">
												<h4>
													<span class="label label-info">전시</span>
												</h4>
											</c:if></td>
										<td><form action="changePrdInfo.do" method="POST">
												<input type="hidden" name="prdNumber"
													value="${row.prdNumber }"> <input type="submit"
													class="btn btn-danger" value="변경" role="button">
											</form></td>
									</tr>
								</c:forEach>
							</c:when>
						</c:choose>

					</tbody>
				</table>
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
