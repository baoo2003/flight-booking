<%@page import="flightbooking.entity.Bookings"%>
<%@page import="flightbooking.entity.Passengers"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int bookingId = Integer.parseInt(request.getParameter("bookingId"));
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Xác nhận đặt vé</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #ffffff;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            text-align: center;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 6px 20px rgba(0, 0, 0, 0.1);
            background-color: #f9f9f9;
            max-width: 500px;
        }

        h1 {
            color: #28a745;
            margin-bottom: 20px;
        }

        h2 {
            color: #007ACC;
            margin-bottom: 20px;
        }

        p {
            font-size: 16px;
            color: #333;
            margin-bottom: 30px;
        }

        a {
            display: inline-block;
            padding: 10px 20px;
            background-color: #007ACC;
            color: white;
            text-decoration: none;
            font-weight: bold;
            border-radius: 6px;
            transition: background-color 0.3s;
        }

        a:hover {
            background-color: #005f99;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Đặt vé thành công!</h1>
    <h2>Mã đặt vé: <%= bookingId %></h2>
    <p>Cảm ơn bạn đã đặt vé với chúng tôi.</p>
    <a href="home">Về trang chủ</a>
</div>

</body>
</html>
