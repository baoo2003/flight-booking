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
            background-color: #e6f2ff; /* Màu nền dịu */
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
            text-align: center; /* Căn giữa nội dung trong khung */
        }

        h2 {
            color: #007ACC;
            margin-bottom: 20px;
            font-size: 24px;
        }

        input[type="text"],
        input[type="password"] {
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
    </style>
</head>
<body>

<div class="form-container">
    <h2>Đăng nhập</h2>
    <form action="./login" method="POST">
        <input type="text" name="username" placeholder="Tên đăng nhập" required> <br>
        <input type="password" name="password" placeholder="Mật khẩu" required> <br>
        <button type="submit">Đăng nhập</button>
    </form>

    <%-- Hiển thị thông báo lỗi nếu có --%>
    <%
        String errorMessage = (String) session.getAttribute("errorMessage");
        if (errorMessage != null) {
            out.println("<p>" + errorMessage + "</p>");
            session.removeAttribute("errorMessage");
        }
    %>
</div>

</body>
</html>
