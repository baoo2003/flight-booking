<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Danh sách chuyến bay</title>
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
            .create-btn {
                display: inline-block;
                padding: 10px 20px;
                margin: 10px 0;
                background-color: #2196F3;
                color: white;
                text-decoration: none;
                border-radius: 4px;
            }
            .create-btn:hover {
                background-color: #1976D2;
            }
        </style>
    </head>
    <body>
        <h2>Danh sách chuyến bay</h2>
        <a href="createFlight" class="create-btn">Thêm chuyến bay mới</a>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Số hiệu chuyến bay</th>
                    <th>Giờ Khởi hành</th>
                    <th>Sân bay khởi hành</th>
                    <th>Đến nơi</th>
                    <th>Sân bay hạ cánh</th>
                    <th>Thời gian bay</th>
                    <th>Giá</th>
                    <th>Số ghế trống</th>
                    <th>Tổng số ghế</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="flight" items="${flights}">
                    <tr>
                        <td>${flight.flightId}</td>
                        <td>${flight.flightNumber}</td>
                        <td>${flight.departureTime}</td>
                        <td>${flight.departureAirportId.name}</td>
                        <td>${flight.arrivalTime}</td>
                        <td>${flight.arrivalAirportId.name}</td>
                        <td>${flight.duration}</td>
                        <td>${flight.price}</td>
                        <td>${flight.availableSeats}</td>
                        <td>${flight.maxSeats}</td>
                        <td>
                            <a href="editFlight?id=${flight.flightId}" class="action-btn edit-btn">Sửa</a>
                           
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>