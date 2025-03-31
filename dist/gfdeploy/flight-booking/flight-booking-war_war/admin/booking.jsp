<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Lịch Sử Đặt Vé</title>
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
            .detail-btn { background-color: #2196F3; }
            .edit-btn { background-color: #4CAF50; }
            .delete-btn { background-color: #f44336; }
        </style>
    </head>
    <body>
        <h2>Lịch Sử Đặt Vé</h2>

        <c:choose>
            <c:when test="${empty bookings}">
                <p>Bạn chưa đặt chuyến bay nào.</p>
            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                        <tr>
                            <th>Mã Đặt Vé</th>
                            <th>Tên Khách Hàng</th>
                            <th>Số Hiệu Chuyến Bay</th>
                            <th>Ngày Đặt Vé</th>
                            <th>Tổng Giá</th>
                            <th>Hành Động</th>
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
                                    <a href="${booking.bookingId}" class="action-btn detail-btn">Chi tiết</a>
                                    <a href="editBooking?id=${booking.bookingId}" class="action-btn edit-btn">Sửa</a>
                                    <a href="deleteBooking?id=${booking.bookingId}" class="action-btn delete-btn" onclick="return confirm('Bạn có chắc chắn muốn xóa vé này không?');">Xóa</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>

        <br>
        <a href="home">Quay lại Trang Chủ</a>
    </body>
</html>