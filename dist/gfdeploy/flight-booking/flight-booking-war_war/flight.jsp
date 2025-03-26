<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="flightbooking.entity.Flights" %>
<%@ page import="java.util.List" %>

<%
    List<Flights> flights = (List<Flights>) request.getAttribute("flights");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chuyến bay</title>
</head>
<body>

<h2>Kết quả tìm kiếm</h2>

<%
    if (flights == null || flights.isEmpty()) {
%>
    <p>Không tìm thấy chuyến bay nào.</p>
<%
    } else {
%>
    <table border="1">
        <tr>
            <th>Mã chuyến bay</th>
            <th>Sân bay khởi hành</th>
            <th>Sân bay đến</th>
            <th>Thời gian khởi hành</th>
            <th>Thời gian đến</th>
            <th>Giá vé</th>
            <th>Số ghế còn trống</th>
            <th>Đặt chỗ</th>
        </tr>
        <%
            for (Flights flight : flights) {
        %>
        <tr>
            <td><%= flight.getFlightNumber() %></td>
            <td><%= flight.getDepartureAirportId().getName()%></td>
            <td><%= flight.getArrivalAirportId().getName() %></td>
            <td><%= flight.getDepartureTime() %></td>
            <td><%= flight.getArrivalTime() %></td>
            <td><%= flight.getPrice() %> VNĐ</td>
            <td><%= flight.getAvailableSeats() %></td>
            <td><a href="booking?flightId=<%= flight.getFlightId() %>">Đặt</a></td>
        </tr>
        <%
            }
        %>
    </table>
<%
    }
%>

<a href="home">Quay lại trang chủ</a>

</body>
</html>
