<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.example.study.Constants" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Login</title>
  </head>
  <link href="<%=request.getContextPath()%>/static/style/login.css" rel="stylesheet" type="text/css"/>
  <link href="<%=request.getContextPath()%>/static/style/reset.css" rel="stylesheet" type="text/css"/>
  
  <style>
  .errorMessage{
  color:red
  }
  </style>
  <script>
    function login(){
    	var loginFormObj=document.getElementById("loginForm");
    	var userNameValue=document.getElementById("userName").value;
    	var passwordValue=document.getElementById("password").value;
    	var isSubmit=true;
    	/* if(!userNameValue){
    		document.getElementById("errorUserName").innerHTML="User name is required";
    		isSubmit=false;
    	}else{
    		document.getElementById("errorUserName").innerHTML="";
    	}
    	if(!passwordValue){
    		document.getElementById("errorPassword").innerHTML="Password is required";
    		isSubmit=false;
    	}else{
    		document.getElementById("errorPassword").innerHTML="";
    	} */
    	if(isSubmit){
        	loginFormObj.submit();
    	}
    }
  </script>
  <body>
  <%
  String errorMessage=(String)request.getAttribute(Constants.ERROR_MESSAGE); 
  if(errorMessage != null){
	  out.println(errorMessage);
  }
  
  Map<String, String> errorFields = (Map<String, String>)request.getAttribute(Constants.ERROR_FIELDS);
  if(errorFields==null){
	  errorFields = new HashMap<String, String>();
  }
  %>
    <div id="warpper">
    <div class="header">登录</div>
	<form action="login" method="POST" id="loginForm">
	  <input type="text" name="userName" id="userName" placeholder="user name" class="input-item"/>
	  <label id="errorUserName" class="errorMessage">
	  <%
	  String errorUserName = errorFields.get("errorUserName") == null? "" : errorFields.get("errorUserName");
	  out.print(errorUserName);
	  %>
	  </label>
	  <br/>
	  <input type="password" name="password" id="password" placeholder="password" class="input-item"/>
	  <label id="errorPassword" class="errorMessage">
	  <%
	  String errorPassword = errorFields.get("errorPassword") == null? "" : errorFields.get("errorPassword");
	  out.print(errorPassword);
	  %>
	  </label>
	  <br/>
	  <input type="button" value="Login" onclick="login()" class="login-button">
	</form>
	</div>
  </body>
</html>