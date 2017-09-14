<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-cn">
<head>
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>用户登录注册</title>
	<link rel="stylesheet" type="text/css" href="css/styles.css">
	<!--[if IE]>
		<script src="http://libs.baidu.com/html5shiv/3.7/html5shiv.min.js"></script>
	<![endif]-->
	<style>*{margin:0;padding:0;} html{background:url('bank.jpg') no-repeat;background-size:100% 100%}</style>
</head>
<body>
	<div class="jq22-container" style="padding-top:100px;" >
		<div class="login-wrap">
			<div class="login-html">
				<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">登录</label>
				<input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">注册</label>
				<div class="login-form">
					<div class="sign-in-htm">
					
					<form action="login.do" method="post">
					<input type="hidden" name="method" value="login"/>
						<div class="group">
							<label for="user" class="label">用户名</label>
							<input id="user" name="username" type="text" class="input">
						</div>
						<div class="group">
							<label for="pass" class="label">密码</label>
							<input id="pass" name="password" type="password" class="input">
						</div>
						<div class="group">
							<input id="check" type="checkbox" class="check" checked>
							<label for="check"><span class="icon"></span>记住我</label>
						</div>
						<div  class="group">
							<input type="submit" class="button" value="登录">
						</div>
						<div class="hr"></div>
						<div class="foot-lnk">
							<a href="#forgot">忘记密码？</a>
						</div>
					</form>
					
					</div>
					<div class="sign-up-htm">
					<p style="color: red; font-weight: 900">${msg }</p>
					
					<form action="regist.do" method="post">
					<input type="hidden" name="method" value="regist"/>
						<div class="group">
							用户名：<input type="text" class="input" name="username" value=""/>
							<span style="color: red; font-weight: 900">${errors.username}</span>
						</div>
							<br/>
							<div class="group">
							密　码：<input type="password" class="input" name="password" value=""/>
							<span style="color: red; font-weight: 900">${errors.password}</span>
							</div>
							<br/>
							<div class="group">
							身份证号：<input type="text" class="input" name="email" value=""/>
							<span style="color: red; font-weight: 900">${errors.email}</span>
							</div>
							<br/>
							<div class="group">
							<input type="submit" class="button" value="注册">
						   </div>
						</form>
						
						<div class="hr"></div>
						<div class="foot-lnk">
							<label for="tab-1">已注册？</a>
						</div>
					</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>