<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="flightbooking.entity.Users" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="flightbooking.entity.Airports" %>
<%@ page import="java.util.List" %>

<%
    Users user = (Users) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("index.jsp");
        return;
    }

    List<Airports> airports = (List<Airports>) request.getAttribute("airports");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang chủ</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #ffffff;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 600px;
            margin: 40px auto;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            background-color: #fff;
        }

        h2 {
            text-align: center;
            color: #007ACC;
            margin-bottom: 30px;
        }

        label {
            display: block;
            margin: 12px 0 6px;
            font-weight: bold;
        }

        select, input[type="date"] {
            width: 100%;
            padding: 10px;
            border-radius: 6px;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }

        button {
            width: 100%;
            margin-top: 20px;
            padding: 12px;
            background-color: #007ACC;
            color: white;
            border: none;
            border-radius: 6px;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #005f99;
        }

        .logout {
            display: block;
            text-align: center;
            margin-top: 20px;
            color: #007ACC;
            text-decoration: none;
            font-weight: bold;
        }

        .logout:hover {
            text-decoration: underline;
        }

        p {
            text-align: center;
            font-size: 16px;
            color: #333;
        }
    </style>

    <script>
        function setMinDate() {
            let today = new Date();
            let yyyy = today.getFullYear();
            let mm = String(today.getMonth() + 1).padStart(2, '0');
            let dd = String(today.getDate()).padStart(2, '0');
            let minDate = yyyy + '-' + mm + '-' + dd;
            document.getElementById("flightDate").setAttribute("min", minDate);
        }
        window.onload = setMinDate;
    </script>
</head>
<body>

<div class="container">
    <h2>Chào mừng, <%= user.getUsername() %>!</h2>

    <p>Hãy chọn thông tin để tìm chuyến bay phù hợp:</p>

    <form action="./search-flights" method="post">
        <label for="departure">Sân bay khởi hành:</label>
        <select name="departure" id="departure" required>
            <option value="">-- Chọn sân bay --</option>
            <% for (Airports airport : airports) { %>
                <option value="<%= airport.getCode() %>"><%= airport.getName() %> - <%= airport.getCode() %></option>
            <% } %>
        </select>

        <label for="arrival">Sân bay đến:</label>
        <select name="arrival" id="arrival" required>
            <option value="">-- Chọn sân bay --</option>
            <% for (Airports airport : airports) { %>
                <option value="<%= airport.getCode() %>"><%= airport.getName() %> - <%= airport.getCode() %></option>
            <% } %>
        </select>

        <label for="flightDate">Ngày bay:</label>
        <input type="date" id="flightDate" name="flightDate" required>

        <button type="submit">Tìm chuyến bay</button>
    </form>

    <a class="logout" href="logout">Đăng xuất</a>
</div>

<script>
    document.getElementById("departure").addEventListener("change", function() {
        let departureCode = this.value;
        let arrivalSelect = document.getElementById("arrival");

        Array.from(arrivalSelect.options).forEach(option => {
            option.hidden = false;
        });

        if (departureCode) {
            Array.from(arrivalSelect.options).forEach(option => {
                if (option.value === departureCode) {
                    option.hidden = true;
                }
            });
        }

        if (arrivalSelect.value === departureCode) {
            arrivalSelect.value = "";
        }
    });
</script>

</body>
</html>
