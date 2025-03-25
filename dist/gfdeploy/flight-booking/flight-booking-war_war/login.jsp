<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin-top: 50px;
        }
        .form-container {
            width: 300px;
            margin: auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-shadow: 2px 2px 10px #aaa;
        }
        input, button {
            width: 90%;
            padding: 8px;
            margin-top: 10px;
        }
    </style>
</head>
<body>

<h2>Đăng nhập</h2>

<div class="form-container">
    <form action="./login" method="POST">
        <input type="text" name="username" placeholder="Tên đăng nhập" required> <br>
        <input type="password" name="password" placeholder="Mật khẩu" required> <br>
        <button type="submit">Đăng nhập</button>
    </form>
    <%-- Hiển thị thông báo lỗi nếu có --%>
    <%
        String errorMessage = (String) session.getAttribute("errorMessage");
        if (errorMessage != null) {
            out.println("<p style='color:red;'>" + errorMessage + "</p>");
            session.removeAttribute("errorMessage"); // Xóa sau khi hiển thị để tránh hiển thị lại sau refresh
        }
    %>
</div>

</body>
</html>
