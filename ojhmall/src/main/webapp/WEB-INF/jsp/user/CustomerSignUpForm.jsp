<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Private Customer Sign In</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- Custom styles for this template -->
<link href="signin.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="../../assets/js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<div class="container">

		<form id="frm" class="form-signin" method="post" action="insertCustomer.do">
			<h2 class="form-signin-heading">Please sign up</h2>
			<input	type="hidden" id="userType" name="userType" value="1">
			<h4>ID</h4>
			 <input	type="email" id="id" name="id" class="form-control"
				placeholder="Email address" required autofocus>
			<h4>Password</h4>
			<label for="inputPassword" class="sr-only">Password</label> <input
				type="password" id="userPassword" name="userPassword" class="form-control"
				placeholder="Password" required>
			<h4>Name</h4>
			<label for="inputName" class="sr-only">Name</label> <input
				type="text" id="userName" name="userName" class="form-control" placeholder="Name"
				required autofocus>
			<h4>Phone Number</h4>
			<label for="inputPhoneNumber" class="sr-only">Phone Number</label> <input
				type="text" id="phNumber" name="phNumber" class="form-control"
				placeholder="Phone Number" required>
			<h4>Home Address</h4>
			<label for="inputHomeAddress" class="sr-only">Home Address</label> <input
				type="text" id="address" name="address" class="form-control"
				placeholder="Home address" required>
			<h4>ZipCode</h4>
			<label for="inputZipCode" class="sr-only">Zip Code</label> <input
				type="text" id="zipCode" name="zipCode" class="form-control"
				placeholder="Zip Code" required>
			<input class="btn btn-lg btn-primary btn-block" id="write"	type="submit" value="Sgin up" />
		</form>

	</div>
	<!-- /container -->



	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
