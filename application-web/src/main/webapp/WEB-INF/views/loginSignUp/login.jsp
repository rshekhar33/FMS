<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ include file="../common/cmnCntxtPath.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<sec:csrfMetaTags/>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>${appTitle} | Login</title>
	<link rel="stylesheet" href="${contextPath}static/css/normalize.css">
	<link rel="stylesheet" href="${contextPath}static/css/style.css">
	<link rel="stylesheet" href="${contextPath}static/css/override.css">
	<link rel="shortcut icon" href="${contextPath}static/images/favicon.ico" />
</head>
<body>
	<div class="logmod">
		<div class="logmod__wrapper">
			<div class="logmod__container">
				<ul class="logmod__tabs">
					<li data-tabtar="lgm-1"><a href="#">Login</a></li>
				</ul>
				<div class="logmod__tab-wrapper">
					<div class="logmod__tab lgm-1 show">
						<div class="logmod__heading">
							<span class="logmod__heading-subtitle">Enter your Username and Password <strong>to Sign In</strong>
							</span>
						</div>
						<div class="logmod__form">
							<form accept-charset="utf-8" action="${contextPath}login" method="post" id="loginForm" class="simform">
								<sec:csrfInput/>
								<div class="sminputs">
									<div class="input full">
										<label class="string optional" for="userName">Username*</label>
										<input class="string optional" maxlength="255" id="userName" name="userName" placeholder="Username" type="text" size="50" autocomplete="off" />
										<label class="string optional error" id="userNameError"></label>
									</div>
								</div>
								<div class="sminputs">
									<div class="input full">
										<label class="string optional" for="password">Password*</label>
										<input class="string optional" maxlength="255" id="password" name="password" placeholder="Password" type="password" size="50" autocomplete="off" />
										<span class="hide-password">Show</span>
										<input class="string optional" id="passwordEnc" name="passwordEnc" type="hidden" autocomplete="off" />
										<label class="string optional error" id="loginError">${loginError}${loginError=""}</label>
									</div>
								</div>
								<div class="sminputs">
									<div class="input full">
										<label class="string optional" for="remember">
											<input class="inputCheck" id="remember" name="remember" placeholder="remember" type="checkbox" checked="checked" />
											Remember Me
										</label>
									</div>
								</div>
								<div class="simform__actions">
									<input class="sumbit" id="login" name="login" type="submit" onclick="return false;" value="Log In" />
									<span class="simform__actions-sidetext">
										Click here to <a href="${contextPath}signUp">Sign Up</a><br/>
									</span>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="${contextPath}static/resources/jquery/jquery.min.js"></script>
	<script src="${contextPath}static/js/enc/aes.js"></script>
	<script src="${contextPath}static/js/enc/pbkdf2.js"></script>
	<script src="${contextPath}static/js/enc/AesUtil.js"></script>
	<script src="${contextPath}static/js/enc/encryption.js"></script>
	<script>
		var contextPath = "${contextPath}";
	</script>
	<script src="${contextPath}static/js/common.js"></script>
	<script src="${contextPath}static/js/pages/loginSignUp/login.js"></script>
</body>
</html>