<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chỉnh sửa đặt vé</title>
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
        input, select {
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
    </style>
</head>
<body>

    <h2 style="text-align: center;">Chỉnh sửa đặt vé máy bay</h2>

    <form action="editBooking" method="post">
        <input type="hidden" name="bookingId" value="${booking.bookingId}" />
        <input type="hidden" name="userId" id="userId" value="${booking.userId.userId} "/>
        <input type="hidden" name="flightId" id="flightId" value="${booking.flightId.flightId} "/>

        <label for="userId">Khách hàng</label>
        <input type="text" name="bookingUser" id="bookingUser" value="${booking.userId.firstName} ${booking.userId.lastName}" readonly />

        <label for="flightId">Chuyến bay</label>
        <input type="text" name="bookingFlight" id="bookingFlight" value="${booking.flightId.flightNumber}" readonly />

        <label for="bookingDate">Ngày đặt</label>
        <input type="text" name="bookingDate" id="bookingDate" value="${booking.bookingDate}" readonly />

        <label for="totalPrice">Tổng tiền</label>
        <input type="text" name="totalPrice" id="totalPrice" value="${booking.totalPrice}" readonly />

        <label for="status">Trạng thái</label>
        <select name="status" id="status">
            <option value="Da xac nhan" ${booking.status == 'Da xac nhan' ? 'selected' : ''}>Đã xác nhận</option>
            <option value="Chua xac nhan" ${booking.status == 'Chua xac nhan' ? 'selected' : ''}>Chưa xác nhận</option>
            <option value="Da huy" ${booking.status == 'Da huy' ? 'selected' : ''}>Đã hủy</option>
        </select>

        <button type="submit" class="submit-btn">Lưu thay đổi</button>
    </form>

</body>
</html>
