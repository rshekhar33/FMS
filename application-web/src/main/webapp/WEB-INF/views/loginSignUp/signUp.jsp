<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ include file="../common/cmnCntxtPath.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<sec:csrfMetaTags/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>${appTitle} | Sign Up</title>
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<link rel="stylesheet" href="${contextPath}css/normalize.css">
	<link rel="stylesheet" href="${contextPath}css/style.css">
	<link rel="shortcut icon" href="${contextPath}images/favicon.ico" />
	<style type="text/css">
	.logmod__wrapper {
		margin: 30px auto;
	}
	</style>
</head>
<body>
	<div class="logmod">
		<div class="logmod__wrapper">
			<div class="logmod__container">
				<ul class="logmod__tabs">
					<li data-tabtar="lgm-1"><a href="#">Sign Up</a></li>
				</ul>
				<div class="logmod__tab-wrapper">
					<div class="logmod__tab lgm-1 show">
						<div class="logmod__heading">
							<span class="logmod__heading-subtitle">
								Enter your personal details <strong>to create an account</strong>
							</span>
						</div>
						<div class="logmod__msg">
							<span class="logmod__msg-subtitle">
								<strong id="successMsg"></strong>
							</span>
						</div>
						<div class="logmod__form">
							<form accept-charset="utf-8" action="#" onsubmit="return false;" method="post" id="regForm" class="simform">
								<sec:csrfInput/>
								<div class="sminputs">
									<div class="input string optional">
										<label class="string optional" for="userName">Username*</label>
										<input class="string optional" maxlength="50" id="userName" name="userName" placeholder="Username" type="text" size="50" />
										<label class="string optional error" id="userNameError"></label>
									</div>
									<div class="input string optional">
										<label class="string optional" for="firstName">First Name*</label>
										<input class="string optional" maxlength="100" id="firstName" name="firstName" placeholder="First Name" type="text" size="100" />
										<label class="string optional error" id="firstNameError"></label>
									</div>
								</div>
								<div class="sminputs">
									<div class="input string optional">
										<label class="string optional" for="middleName">Middle Name</label>
										<input class="string optional" maxlength="100" id="middleName" name="middleName" placeholder="Middle Name" type="text" size="100" />
										<label class="string optional error" id="middleNameError"></label>
									</div>
									<div class="input string optional">
										<label class="string optional" for="lastName">Last Name</label>
										<input class="string optional" maxlength="100" id="lastName" name="lastName" placeholder="Last Name" type="text" size="100" />
										<label class="string optional error" id="lastNameError"></label>
									</div>
								</div>
								<div class="sminputs">
									<div class="input string optional">
										<label class="string optional" for="password">Password*</label>
										<input class="string optional" maxlength="100" id="password" name="password" placeholder="Password" type="text" size="100" />
										<label class="string optional error" id="passwordError"></label>
									</div>
									<div class="input string optional">
										<label class="string optional" for="passwordRepeat">Repeat password *</label>
										<input class="string optional" maxlength="100" id="passwordRepeat" name="passwordRepeat" placeholder="Repeat password" type="text" size="100" />
										<label class="string optional error" id="passwordRepeatError"></label>
									</div>
								</div>
								<div class="sminputs">
									<div class="input string optional">
										<label class="string optional" for="emailId">Email ID*</label>
										<input class="string optional" maxlength="100" id="emailId" name="emailId" placeholder="Email ID" type="email" size="100" />
										<label class="string optional error" id="emailIdError"></label>
									</div>
									<div class="input string optional">
										<label class="string optional" for="mobileNo">Mobile No</label>
										<input class="string optional" maxlength="10" id="mobileNo" name="mobileNo" placeholder="Mobile No" type="text" size="10" />
										<label class="string optional error" id="mobileNoError"></label>
									</div>
								</div>
								<div class="simform__actions">
									<input class="sumbit" id="register" name="register" type="submit" onclick="validateSubmitFun();" value="Create Account" />
									<span class="simform__actions-sidetext">
										Click here to <a href="${contextPath}login">Sign In</a><br/>
										By creating an account you agree to our 
										<a class="special" href="#" target="_blank" role="link">Terms &amp; Privacy</a>
									</span>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="${contextPath}resources/jquery/jquery.min.js"></script>
	<script>
		var contextPath = "${contextPath}";
	</script>
	<script src="${contextPath}js/common.js"></script>
	<script src="${contextPath}js/pages/loginSignUp/signUp.js"></script>
</body>
</html>