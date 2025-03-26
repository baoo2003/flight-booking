<%@page import="flightbooking.entity.Bookings"%>
<%@page import="flightbooking.entity.Passengers"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int bookingId = Integer.parseInt(request.getParameter("bookingId"));
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Xác nhận đặt vé</title>
    </head>
    <body>
        <h1>Đặt vé thành công!</h1>
        <h2>Mã đặt vé: <%= bookingId %></h2>
        <p>Cảm ơn bạn đã đặt vé với chúng tôi.</p>
        <a href="home">Về trang chủ</a>
    </body>
</html>
