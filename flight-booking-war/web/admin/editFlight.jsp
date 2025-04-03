<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chỉnh sửa chuyến bay</title>
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
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
            border-radius: 4px;
        }
        .submit-btn:hover {
            background-color: #45a049;
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

    <h2 style="text-align: center;">Chỉnh sửa thông tin chuyến bay</h2>

    <form action="editFlight" method="post">
        <input type="hidden" name="flightId" value="${flight.flightId}" />

        <label for="flightNumber">Số hiệu chuyến bay</label>
        <input type="text" name="flightNumber" id="flightNumber" value="${flight.flightNumber}" required />
        
        <!-- Sân bay khởi hành -->
<label for="departureAirport">Sân bay khởi hành</label>
<select name="departureAirportId" id="departureAirport" required>
    <c:forEach var="airport" items="${airports}">
        <option value="${airport.airportId}"
            ${airport.airportId == flight.departureAirportId.airportId ? 'selected' : ''}>
            ${airport.name} - ${airport.code}
        </option>
    </c:forEach>
</select>

<!-- Sân bay hạ cánh -->
<label for="arrivalAirport">Sân bay hạ cánh</label>
<select name="arrivalAirportId" id="arrivalAirport" required>
    <c:forEach var="airport" items="${airports}">
        <option value="${airport.airportId}"
            ${airport.airportId == flight.arrivalAirportId.airportId ? 'selected' : ''}>
            ${airport.name} - ${airport.code}
        </option>
    </c:forEach>
</select>
        
        <label for="arrivalTime">Khởi hành</label>
<input type="datetime-local" name="departureTime" id="departureTime"
       value="<fmt:formatDate value='${flight.departureTime}' pattern='yyyy-MM-dd\'T\'HH:mm'/>" required />

        <label for="arrivalTime">Đến nơi</label>
<input type="datetime-local" name="arrivalTime" id="arrivalTime"
       value="<fmt:formatDate value='${flight.arrivalTime}' pattern='yyyy-MM-dd\'T\'HH:mm'/>" required />


        <label for="duration">Thời gian bay (phút)</label>
        <input type="number" name="duration" id="duration" value="${flight.duration}" required />

        <label for="price">Giá (VND)</label>
        <input type="number" name="price" id="price" value="${flight.price}" required />

        <label for="availableSeats">Số ghế trống</label>
        <input type="number" name="availableSeats" id="availableSeats" value="${flight.availableSeats}" required />

        <label for="maxSeats">Tổng số ghế</label>
        <input type="number" name="maxSeats" id="maxSeats" value="${flight.maxSeats}" required />

        <button type="submit" class="submit-btn">Lưu thay đổi</button>
    </form>

</body>
</html>
