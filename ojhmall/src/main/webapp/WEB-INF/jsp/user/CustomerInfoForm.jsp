<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Admin Information</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- Custom styles for this template -->
<link href="justified-nav.css" rel="stylesheet">
</head>
<body>
	<div class="container">

		<h2 class="form-signin-heading">If you change your user
			information, please click change button!</h2>
		<!-- 구매자 회원 정보 표시, ID, Password, 이름, 전화번호, 주소, 우편번호 -->
		<form id="frm" class="form-signin" method="post"
			action="changeCustomerInfo.do">
			<!-- 회원 번호, id, 회원 구분 번호 변경 불가 -->
			<input type="hidden" id="userNumber" name="userNumber"
				class="form-control" placeholder="text"
				value="${sessionScope.userLogInInfo.userNumber}" required> <input
				type="hidden" id="id" name="id" class="form-control"
				placeholder="text" value="${sessionScope.userLogInInfo.id}" required>
			<input type="hidden" id="userType" name="userType"
				class="form-control" placeholder="text"
				value="${sessionScope.userLogInInfo.userType}" required>
			<h4>ID</h4>
			<div class="well">
				<p>${sessionScope.userLogInInfo.id}</p>
			</div>
			<h4>Password</h4>
			<input type="password" id="userPassword" name="userPassword"
				class="form-control" placeholder="text"
				value="${sessionScope.userLogInInfo.userPassword
				}" required>
			<h4>Name</h4>
			<input type="text" id="userName" name="userName" class="form-control"
				placeholder="Name"
				value="${sessionScope.userLogInInfo.userName
				}" required>
			<h4>Phone Number</h4>
			<label for="inputPhoneNumber" class="sr-only">Phone Number</label> <input
				type="text" id="phNumber" name="phNumber" class="form-control"
				placeholder="Phone Number"
				value="${sessionScope.userLogInInfo.phNumber
				}" required>
			<h4>Home Address</h4>
			<label for="inputHomeAddress" class="sr-only">Home Address</label> <input
				type="text" id="address" name="address" class="form-control"
				placeholder="Home address"
				value="${sessionScope.userLogInInfo.address
				}" required>
			<h4>ZipCode</h4>
			<label for="inputZipCode" class="sr-only">Zip Code</label> <input
				type="text" id="zipCode" name="zipCode" class="form-control"
				placeholder="Zip Code"
				value="${sessionScope.userLogInInfo.zipCode
				}" required>
			<input class="btn btn-lg btn-primary btn-block" id="change"
				type="submit" value="Change" />
		</form>
		<!-- 나중에 변경하기, 메인 페이지로 이동 -->
		<form id="frm" class="form-signin" method="post"
			action="backToMain.do">
			<input class="btn btn-lg btn-primary btn-block" id="later"
				type="submit" value="Later" />
		</form>
		<!-- 회원 삭제, 회원번호와 Password 필요 -->
		<form id="frm" class="form-signin" method="post"
			action="removeUserInfo.do">
			<h4>Password</h4>
			<input type="password" id="userPassword" name="userPassword"
				class="form-control" placeholder="text"
				value="${sessionScope.userLogInInfo.userPassword
				}" required>
			<input type="hidden" id="userNumber" name="userNumber"
				class="form-control" placeholder="text"
				value="${sessionScope.userLogInInfo.userNumber}" required> <input
				type="hidden" id="id" name="id" class="form-control"
				placeholder="text" value="${sessionScope.userLogInInfo.id}"
				required> <input class="btn btn-lg btn-primary btn-block"
				id="discard" type="submit" value="Discard" />
		</form>

	</div>
</body>
</html>