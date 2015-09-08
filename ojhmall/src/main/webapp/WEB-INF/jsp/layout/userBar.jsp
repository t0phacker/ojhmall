<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="masthead">
	<a href="http://localhost:8080/ojhmall"><img src="/ojhmall/image/11st.png"></a>

	<ul class="nav navbar-nav navbar-right">
		<c:choose>
			<c:when test="${empty sessionScope.userLogInInfo.userName}">
				<li><a href="/ojhmall/user/LogInForm.do">�α���</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="/ojhmall/logOut.do">${sessionScope.userLogInInfo.userName}��</a></li>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${empty sessionScope.userLogInInfo.userName}">
				<li><a href="/ojhmall/user/SiginUpForm.do">ȸ������</a></li>
				<li><a href="/ojhmall/cart/cartView.do">��ٱ���</a></li>
				<li><a href="#">����������</a></li>
			</c:when>

			<c:when test="${sessionScope.userLogInInfo.userType == 'ADMIN'}">
				<li><a href="/ojhmall/user/AdminInfoForm.do">ȸ������</a></li>
				<li><a href="/ojhmall/cart/cartView.do">��ٱ���</a></li>
				<li><a href="/ojhmall/mypage/mypageForAdmin.do">����������</a></li>
			</c:when>
			<c:when test="${sessionScope.userLogInInfo.userType == 'CUSTOMER'}">
				<li><a href="/ojhmall/user/CustomerInfoForm.do">ȸ������</a></li>
				<li><a href="/ojhmall/cart/cartView.do">��ٱ���</a></li>
				<li><a href="/ojhmall/mypage/mypageForCustomer.do">����������</a></li>
			</c:when>
			<c:when test="${sessionScope.userLogInInfo.userType == 'SELLER'}">
				<li><a href="/ojhmall/user/SellerInfoForm.do">ȸ������</a></li>
				<li><a href="/ojhmall/cart/cartView.do">��ٱ���</a></li>
				<li><a href="/ojhmall/mypage/mypageForSeller.do">����������</a></li>
			</c:when>

		</c:choose>
	</ul>
</div>