<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm chuyến bay mới</title>
    <style>
        form {
            width: 50%;
            margin: auto;
        }
        label {
            display: block;
            margin-top: 10px;
            font-weight: bold;
        }
        input {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
        }
        .submit-btn {
            margin-top: 15px;
            padding: 10px 20px;
            background-color: #2196F3;
            color: white;
            border: none;
            border-radius: 4px;
        }
        .submit-btn:hover {
            background-color: #1976D2;
        }
         input, select{
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            box-sizing: border-box;
        }
    </style>
</head>
<body>

    <h2 style="text-align: center;">Thêm chuyến bay mới</h2>

    <form action="createFlight" method="post">
        <label for="flightNumber">Số hiệu chuyến bay</label>
        <input type="text" name="flightNumber" id="flightNumber" required />
       <!-- Sân bay khởi hành -->
<label for="departureAirport">Sân bay khởi hành</label>
<select name="departureAirportId" id="departureAirport" required>
    <c:forEach var="airport" items="${listairports}">
        <option value="${airport.airportId}">
            ${airport.name} - ${airport.code}
        </option>
    </c:forEach>
</select>

<!-- Sân bay hạ cánh -->
<label for="arrivalAirport">Sân bay hạ cánh</label>
<select name="arrivalAirportId" id="arrivalAirport" required>
    <c:forEach var="airport" items="${listairports}">
        <option value="${airport.airportId}">
            ${airport.name} - ${airport.code}
        </option>
    </c:forEach>
</select>
        
        <label for="departureTime">Khởi hành</label>
        <input type="datetime-local" name="departureTime" id="departureTime" required />

        <label for="arrivalTime">Đến nơi</label>
        <input type="datetime-local" name="arrivalTime" id="arrivalTime" required />

        <label for="duration">Thời gian bay (phút)</label>
        <input type="number" name="duration" id="duration" required />

        <label for="price">Giá (VND)</label>
        <input type="number" name="price" id="price" required />

        <label for="availableSeats">Số ghế trống</label>
        <input type="number" name="availableSeats" id="availableSeats" required />

        <label for="maxSeats">Tổng số ghế</label>
        <input type="number" name="maxSeats" id="maxSeats" required />

        <button type="submit" class="submit-btn">Thêm chuyến bay</button>
    </form>

</body>
</html>
