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

<title>User Information</title>

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
		<c:choose>
			<c:when test="${sessionScope.userLogInInfo.userType == 0}">
				<form id="frm" class="form-signin" method="post"
					action="changeUserInfo.do">
					<input type="hidden" id="userNumber" name="userNumber"
						class="form-control" placeholder="text"
						value="${sessionScope.userLogInInfo.userNumber}" required>
					<input type="hidden" id="id" name="id" class="form-control"
						placeholder="text" value="${sessionScope.userLogInInfo.id}"
						required>
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
					<input type="text" id="userName" name="userName"
						class="form-control" placeholder="Name"
						value="${sessionScope.userLogInInfo.userName
				}" required>
				<input class="btn btn-lg btn-primary btn-block" id="change"
			type="submit" value="Change" />
				</form>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${sessionScope.userLogInInfo.userType == 1}">
				<form id="frm" class="form-signin" method="post"
					action="changeCustomerInfo.do">
					<input type="hidden" id="userNumber" name="userNumber"
						class="form-control" placeholder="text"
						value="${sessionScope.userLogInInfo.userNumber}" required>
					<input type="hidden" id="id" name="id" class="form-control"
						placeholder="text" value="${sessionScope.userLogInInfo.id}"
						required>
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
					<input type="text" id="userName" name="userName"
						class="form-control" placeholder="Name"
						value="${sessionScope.userLogInInfo.userName
				}" required>
					<h4>Phone Number</h4>
					<label for="inputPhoneNumber" class="sr-only">Phone Number</label>
					<input type="text" id="phNumber" name="phNumber"
						class="form-control" placeholder="Phone Number"
						value="${sessionScope.userLogInInfo.phNumber
				}" required>
					<h4>Home Address</h4>
					<label for="inputHomeAddress" class="sr-only">Home Address</label>
					<input type="text" id="address" name="address" class="form-control"
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
			</c:when>
		</c:choose>

		<c:choose>
			<c:when test="${sessionScope.userLogInInfo.userType == 2}">
				<form id="frm" class="form-signin" method="post"
					action="changeUserInfo.do">
					<input type="hidden" id="userNumber" name="userNumber"
						class="form-control" placeholder="text"
						value="${sessionScope.userLogInInfo.userNumber}" required>
					<input type="hidden" id="id" name="id" class="form-control"
						placeholder="text" value="${sessionScope.userLogInInfo.id}"
						required>
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
					<input type="text" id="userName" name="userName"
						class="form-control" placeholder="Name"
						value="${sessionScope.userLogInInfo.userName
				}" required>
					<h4>Phone Number</h4>
					<label for="inputPhoneNumber" class="sr-only">Phone Number</label>
					<input type="text" id="phNumber" name="phNumber"
						class="form-control" placeholder="Phone Number"
						value="${sessionScope.userLogInInfo.phNumber
				}" required>
					<h4>Home Address</h4>
					<label for="inputHomeAddress" class="sr-only">Home Address</label>
					<input type="text" id="address" name="address" class="form-control"
						placeholder="Home address"
						value="${sessionScope.userLogInInfo.address
				}" required>
					<h4>ZipCode</h4>
					<label for="inputZipCode" class="sr-only">Zip Code</label> <input
						type="text" id="zipCode" name="zipCode" class="form-control"
						placeholder="Zip Code"
						value="${sessionScope.userLogInInfo.zipCode
				}" required>
				<h4>Account Number</h4>
				<label for="accNumber" class="sr-only">Account Number</label>
				<input type="text" id="accNumber" name="accNumber"
					class="form-control" placeholder="Zip Code"
					value="${sessionScope.userLogInInfo.accNumber
				}" required>
				<h4>Business Number</h4>
				<label for="bizNumber" class="sr-only">Business Number</label>
				<input type="text" id="bizNumber" name="bizNumber"
					class="form-control" placeholder="Zip Code"
					value="${sessionScope.userLogInInfo.bizNumber
				}" required>
				<input class="btn btn-lg btn-primary btn-block" id="change"
			type="submit" value="Change" />
				</form>
			</c:when>
		</c:choose>
		<input class="btn btn-lg btn-primary btn-block" id="later"
			type="button" value="Later" />
		<form id="frm" class="form-signin" method="post"
			action="removeUserInfo.do">
			<h4>Password</h4>
			<input type="password" id="userPassword" name="userPassword"
				class="form-control" placeholder="text"
				value="${sessionScope.userLogInInfo.userPassword
				}" required>
			<input class="btn btn-lg btn-primary btn-block" id="discard"
				type="submit" value="Discard" />
		</form>

	</div>
</body>
</html>