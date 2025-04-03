<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="flightbooking.entity.Flights" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.List" %>

<%
    List<Flights> flights = (List<Flights>) request.getAttribute("flights");
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm - dd/MM/yyyy");
    NumberFormat currencyFormat = NumberFormat.getInstance(new Locale("vi", "VN"));
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chuyến bay</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #ffffff;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 1200px;
            margin: 40px auto;
            padding: 20px;
        }

        h2 {
            text-align: center;
            color: #007ACC;
            margin-bottom: 30px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            table-layout: fixed;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        }

        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: center;            
        }
                
        th:nth-child(2), td:nth-child(2),  /* Sân bay khởi hành */
        th:nth-child(3), td:nth-child(3) { /* Sân bay đến */
            width: 12%;
        }

        th:nth-child(4), td:nth-child(4),  /* Thời gian khởi hành */
        th:nth-child(5), td:nth-child(5) { /* Thời gian đến */
            width: 12%;
        }

        th {
            background-color: #007ACC;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        a.booking-link {
            display: inline-block;
            padding: 6px 12px;
            background-color: #28a745;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            font-weight: bold;
        }

        a.booking-link:hover {
            background-color: #218838;
        }

        .back-home {
            display: block;
            margin-top: 30px;
            text-align: center;
            text-decoration: none;
            color: #007ACC;
            font-weight: bold;
        }

        .back-home:hover {
            text-decoration: underline;
        }

        p {
            text-align: center;
            color: #333;
            font-size: 16px;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Kết quả tìm kiếm</h2>

    <%
        if (flights == null || flights.isEmpty()) {
    %>
        <p>Không tìm thấy chuyến bay nào.</p>
    <%
        } else {
    %>
        <table>
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
                <td><%= flight.getDepartureAirportId().getName() %></td>
                <td><%= flight.getArrivalAirportId().getName() %></td>
                <td><%= dateFormat.format(flight.getDepartureTime()) %></td>
                <td><%= dateFormat.format(flight.getArrivalTime()) %></td>
                <td><%= currencyFormat.format(flight.getPrice()) %> VNĐ</td>
                <td><%= flight.getAvailableSeats() %></td>
                <td><a class="booking-link" href="booking?flightId=<%= flight.getFlightId() %>">Đặt</a></td>
            </tr>
            <%
                }
            %>
        </table>
    <%
        }
    %>

    <a class="back-home" href="home">← Quay lại trang chủ</a>
</div>

</body>
</html>
