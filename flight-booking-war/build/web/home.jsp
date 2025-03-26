<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="flightbooking.entity.Users" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="flightbooking.entity.Airports" %>
<%@ page import="java.util.List" %>

<%
    Users user = (Users) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("index.jsp"); // Chưa đăng nhập → Về trang đăng nhập
        return;
    }
    
    List<Airports> airports = (List<Airports>) request.getAttribute("airports");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang chủ</title>
    <script>
        // Đặt min cho ngày bay là hôm nay
        function setMinDate() {
            let today = new Date();
            let yyyy = today.getFullYear();
            let mm = String(today.getMonth() + 1).padStart(2, '0'); // Tháng bắt đầu từ 0
            let dd = String(today.getDate()).padStart(2, '0');
            let minDate = yyyy + '-' + mm + '-' + dd;
            
            document.getElementById("flightDate").setAttribute("min", minDate);
        }
        window.onload = setMinDate; // Gọi khi trang tải xong
    </script>
</head>
<body>

<h2>Chào mừng, <%= user.getUsername() %>!</h2>

<p>Đây là trang Home sau khi đăng nhập.</p>

<form action="./search-flights" method="post">
    <label for="departure">Sân bay khởi hành:</label>
    <select name="departure" id="departure" required>
        <option value="">-- Chọn sân bay --</option>
        <% for (Airports airport : airports) { %>
            <option value="<%= airport.getCode() %>"><%= airport.getName() %> - <%= airport.getCode() %></option>
        <% } %>
    </select><br>

    <label for="arrival">Sân bay đến:</label>
    <select name="arrival" id="arrival" required>
        <option value="">-- Chọn sân bay --</option>
        <% for (Airports airport : airports) { %>
            <option value="<%= airport.getCode() %>"><%= airport.getName() %> - <%= airport.getCode() %></option>
        <% } %>
    </select><br>

    <label for="flightDate">Ngày bay:</label>
    <input type="date" id="flightDate" name="flightDate" required><br>

    <button type="submit">Tìm chuyến bay</button>
</form>
    
<script>
    document.getElementById("departure").addEventListener("change", function() {
        let departureCode = this.value;
        let arrivalSelect = document.getElementById("arrival");

        // Reset danh sách sân bay đến
        Array.from(arrivalSelect.options).forEach(option => {
            option.hidden = false;
        });

        // Ẩn sân bay được chọn làm điểm đi khỏi danh sách điểm đến
        if (departureCode) {
            Array.from(arrivalSelect.options).forEach(option => {
                if (option.value === departureCode) {
                    option.hidden = true;
                }
            });
        }

        // Nếu điểm đến hiện tại trùng với điểm đi thì reset về mặc định
        if (arrivalSelect.value === departureCode) {
            arrivalSelect.value = "";
        }
    });
</script>

<a href="logout">Đăng xuất</a>

</body>
</html>
