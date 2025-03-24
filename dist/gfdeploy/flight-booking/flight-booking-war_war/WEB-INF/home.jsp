<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="flightbooking.entity.Users" %>
<%@ page import="javax.servlet.http.HttpSession" %>

<%
    Users user = (Users) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("index.jsp"); // Chưa đăng nhập → Về trang đăng nhập
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang chủ</title>
</head>
<body>

<h2>Chào mừng, <%= user.getUsername() %>!</h2>

<p>Đây là trang Home sau khi đăng nhập.</p>

<a href="logout">Đăng xuất</a>

</body>
</html>
