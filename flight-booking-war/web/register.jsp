<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng ký</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #e6f2ff;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }

        .form-container {
            background-color: #ffffff;
            width: 350px;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        h2 {
            color: #007ACC;
            margin-bottom: 20px;
            font-size: 24px;
        }

        input[type="text"],
        input[type="password"],
        input[type="email"] {
            width: 100%;
            padding: 10px;
            margin-top: 12px;
            border: 1px solid #ccc;
            border-radius: 6px;
            box-sizing: border-box;
        }

        button {
            width: 100%;
            padding: 10px;
            margin-top: 20px;
            background-color: #007ACC;
            border: none;
            color: white;
            font-weight: bold;
            border-radius: 6px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #005f99;
        }

        p {
            margin-top: 15px;
            color: red;
        }

        .login-link {
            padding-top: 25px;
            color: #007ACC;
            text-decoration: none;
        }

        .login-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="form-container">
    <h2>Đăng ký</h2>
    <form action="./register" method="POST">
        <input type="text" name="username" placeholder="Tên đăng nhập" required> <br>
        <input type="email" name="email" placeholder="Email" required> <br>
        <input type="password" name="password" placeholder="Mật khẩu" required> <br>
        <input type="password" name="confirmPassword" placeholder="Xác nhận mật khẩu" required> <br>
        <button style="margin-bottom: 20px" type="submit">Đăng ký</button>
    </form>

    <%-- Hiển thị thông báo lỗi nếu có --%>
    <%
        String errorMessage = (String) session.getAttribute("errorMessage");
        if (errorMessage != null) {
            out.println("<p>" + errorMessage + "</p>");
            session.removeAttribute("errorMessage");
        }
    %>

    <a href="./login.jsp" class="login-link">Đã có tài khoản? Đăng nhập</a>
</div>

</body>
</html>