<%@page import="in.shri.DB.DBCon"%>
<%@page import="in.shri.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	User auth=(User)request.getSession().getAttribute("auth");
	if(auth!=null){
		response.sendRedirect("index.jsp");
	}
%>
<!DOCTYPE html>
<html>
<head>
<%@include file="includes/header.jsp" %>
<style>
        body {
            background-color: #f8f9fa;
        }

        .login-container {
            max-width: 400px;
            margin: auto;
            margin-top: 100px;
        }

        .login-form {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        #unique{
        	margin-top:10px;
        }
    </style>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<%@include file="includes/navbar.jsp" %>
	<div class="container login-container">
    <div class="login-form">
        <h2 class="text-center">Login</h2>
        
        <form action="login" method="post">
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="text" class="form-control" id="email" name="email" placeholder="Enter Your Email" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Enter Your Password" required>
            </div>
            <center><input type="submit" value="submit" class="btn btn-success btn-block" id="unique"/></center>
        </form>
        
    </div>
</div>

<%@include file="includes/footer.jsp" %>
</body>
</html>