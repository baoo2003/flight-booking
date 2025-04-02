<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lịch Sử Đặt Vé</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f5f7fa;
                margin: 0;
                padding: 20px;
            }
            h1 {
                color: #2c3e50;
                text-align: center;
                margin-bottom: 30px;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                background-color: #ffffff;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                border-radius: 8px;
                overflow: hidden;
            }
            th, td {
                padding: 15px;
                text-align: left;
                border-bottom: 1px solid #e0e4e8;
            }
            th {
                background-color: #3498db;
                color: #ffffff;
                font-weight: bold;
                text-transform: uppercase;
                letter-spacing: 0.5px;
            }
            td {
                color: #34495e;
            }
            tr:hover {
                background-color: #f1f3f5;
                transition: background-color 0.3s ease;
            }
            tr.open {
                background-color: #e8f4fd; /* Light blue when open */
                font-weight: bold; /* Optional: make text bold when open */
            }
            .passenger-details {
                display: none; /* Initially hidden */
                background-color: #ecf0f1;
            }
            .passenger-details h3 {
                color: #2980b9;
                margin-top: 0;
            }
            .passenger-details ul {
                list-style-type: none;
                padding-left: 0;
            }
            .passenger-details li {
                padding: 10px 0;
                border-bottom: 1px dashed #d5dbde;
            }
            .passenger-details li:last-child {
                border-bottom: none;
            }
            .action-btn {
                display: inline-block;
                padding: 8px 12px;
                margin-right: 10px;
                text-decoration: none;
                border-radius: 4px;
                font-size: 14px;
                transition: background-color 0.3s ease, transform 0.2s ease;
            }
            .edit-btn {
                background-color: #f39c12;
                color: #ffffff;
            }
            .edit-btn:hover {
                background-color: #e67e22;
                transform: translateY(-2px);
            }
            .delete-btn {
                background-color: #e74c3c;
                color: #ffffff;
            }
            .delete-btn:hover {
                background-color: #c0392b;
                transform: translateY(-2px);
            }
            a[href="home"] {
                display: inline-block;
                margin-top: 20px;
                padding: 10px 20px;
                background-color: #2ecc71;
                color: #ffffff;
                text-decoration: none;
                border-radius: 4px;
                transition: background-color 0.3s ease;
            }
            a[href="home"]:hover {
                background-color: #27ae60;
            }
            p {
                color: #7f8c8d;
                text-align: center;
                font-size: 16px;
            }
            .toggle-icon {
                margin-right: 8px;
                display: inline-block;
                width: 12px; /* Fixed width for consistency */
                text-align: center;
                transition: transform 0.3s ease;
            }
            .toggle-icon::before {
                content: '▼'; /* Default: down arrow */
            }
            tr.open .toggle-icon::before {
                content: '▲'; /* Up arrow when open */
            }
        </style>
        <script>
            function toggleDetails(bookingId) {
                var detailsRow = document.getElementById('details-' + bookingId);
                var row = document.getElementById('row-' + bookingId);
                if (detailsRow.style.display === 'none' || detailsRow.style.display === '') {
                    detailsRow.style.display = 'table-row';
                    row.classList.add('open');
                } else {
                    detailsRow.style.display = 'none';
                    row.classList.remove('open');
                }
            }
        </script>
    </head>
    <body>
        <h1>Lịch Sử Đặt Vé</h1>

        <c:choose>
            <c:when test="${empty bookings}">
                <p>Bạn chưa đặt chuyến bay nào.</p>
            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                        <tr>
                            <th>Mã Đặt Vé</th>
                            <th>Số Hiệu Chuyến Bay</th>
                            <th>Ngày Đặt Vé</th>
                            <th>Số lượng</th>
                            <th>Tổng Giá</th>
                            <th>Hành Động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="booking" items="${bookings}">
                            <tr id="row-${booking.bookingId}" onclick="toggleDetails(${booking.bookingId})" style="cursor: pointer;">
                                <td>
                                    <span class="toggle-icon"></span>
                                    ${booking.bookingReference}
                                </td>
                                <td>${booking.flightId.flightNumber}</td>
                                <td>${booking.bookingDate}</td>
                                <td>${booking.passengersCollection.size()}</td>
                                <td>${booking.totalPrice}</td>
                                <td>
                                    <a href="editBooking?id=${booking.bookingId}" class="action-btn edit-btn">Sửa</a>
                                    <a href="deleteBooking?id=${booking.bookingId}" class="action-btn delete-btn" onclick="return confirm('Bạn có chắc chắn muốn xóa vé này không?');">Xóa</a>
                                </td>
                            </tr>
                            <!-- Passenger Details Row -->
                            <tr id="details-${booking.bookingId}" class="passenger-details">
                                <td colspan="6">
                                    <h3>Thông tin hành khách:</h3>
                                    <c:choose>
                                        <c:when test="${empty booking.passengersCollection}">
                                            <p>Không có thông tin hành khách.</p>
                                        </c:when>
                                        <c:otherwise>
                                            <ul>
                                                <c:forEach var="passenger" items="${booking.passengersCollection}">
                                                    <li>
                                                        Tên: ${passenger.firstName} ${passenger.lastName} <br>
                                                        Giới tính: ${passenger.gender} <br>
                                                        Ngày sinh: ${passenger.dateOfBirth}
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </c:otherwise>
                                    </c:choose>
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