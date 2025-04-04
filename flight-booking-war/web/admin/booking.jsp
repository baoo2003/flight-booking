<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Danh sách đặt vé máy bay</title>
        <style>
            table {
                width: 100%;
                border-collapse: collapse;
            }
            th, td {
                border: 1px solid #ddd;
                padding: 8px;
                text-align: left;
            }
            th {
                background-color: #f2f2f2;
            }
            .action-btn {
                padding: 5px 10px;
                text-decoration: none;
                border-radius: 4px;
                color: white;
            }
            .edit-btn { background-color: #4CAF50; }
            .delete-btn { background-color: #f44336; }
        </style>
    </head>
    <body>
        <h2>Danh sách đặt vé máy bay</h2>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
            <a href="../../../flight-booking-ejb/src/java/flightbooking/entity/Bookings.java"></a>
                    <th>Tên khách hàng</th>
                    <th>Chuyến bay</th>
                    <th>Ngày đặt</th>
                    <th>Tổng tiền</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="booking" items="${bookings}">
                    <tr>
                        <td>${booking.bookingId}</td>
                        <td>${booking.userId.firstName} ${booking.userId.lastName}</td>
                        <td>${booking.flightId.flightNumber}</td>
                        <td>${booking.bookingDate}</td>
                        <td>${booking.totalPrice}</td>
                        <td>
                            <a href="editBooking?id=${booking.bookingId}" class="action-btn edit-btn">Sửa</a>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>