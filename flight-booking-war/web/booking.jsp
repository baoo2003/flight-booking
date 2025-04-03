<%@page import="flightbooking.entity.Users"%>
<%@page import="flightbooking.entity.Flights"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Flights flight = (Flights) request.getAttribute("flight");
    Users user = (Users) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đặt vé</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #ffffff;
            margin: 0;
            padding: 20px;
        }

        h1, h2, h3 {
            text-align: center;
            color: #007ACC;
        }

        form {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }

        label {
            display: block;
            margin: 10px 0 5px;
            font-weight: bold;
        }

        input[type="text"],
        input[type="number"],
        input[type="date"],
        select {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 6px;
            box-sizing: border-box;
        }

        fieldset {
            border: 1px solid #ccc;
            border-radius: 8px;
            padding: 15px;
            margin-top: 20px;
            background-color: #f9f9f9;
        }

        legend {
            font-weight: bold;
            color: #333;
        }

        input[type="submit"] {
            width: 100%;
            padding: 12px;
            background-color: #007ACC;
            color: white;
            font-weight: bold;
            border: none;
            border-radius: 6px;
            margin-top: 30px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }

        input[type="submit"]:hover {
            background-color: #005f99;
        }
    </style>

    <script>
        function generatePassengerForms() {
            var numTickets = document.getElementById("numTickets").value;
            var container = document.getElementById("passengerForms");
            container.innerHTML = "";
            
            // Lấy ngày hiện tại để giới hạn max cho ngày sinh
            let today = new Date();
            let yyyy = today.getFullYear();
            let mm = String(today.getMonth() + 1).padStart(2, '0');
            let dd = String(today.getDate()).padStart(2, '0');
            let maxDate = yyyy + '-' + mm + '-' + dd;

            for (let i = 1; i <= numTickets; i++) {
                let fieldset = document.createElement("fieldset");

                let legend = document.createElement("legend");
                legend.textContent = "Hành khách " + i;
                fieldset.appendChild(legend);

                // Họ
                let labelFirstName = document.createElement("label");
                labelFirstName.textContent = "Họ:";
                let inputFirstName = document.createElement("input");
                inputFirstName.type = "text";
                inputFirstName.name = "passenger" + i + "_firstName";
                inputFirstName.required = true;

                // Tên
                let labelLastName = document.createElement("label");
                labelLastName.textContent = "Tên:";
                let inputLastName = document.createElement("input");
                inputLastName.type = "text";
                inputLastName.name = "passenger" + i + "_lastName";
                inputLastName.required = true;

                // Ngày sinh
                let labelDob = document.createElement("label");
                labelDob.textContent = "Ngày sinh:";
                let inputDob = document.createElement("input");
                inputDob.type = "date";
                inputDob.name = "passenger" + i + "_dob";
                inputDob.required = true;
                inputDob.max = maxDate; 

                // Giới tính
                let labelGender = document.createElement("label");
                labelGender.textContent = "Giới tính:";
                let selectGender = document.createElement("select");
                selectGender.name = "passenger" + i + "_gender";
                selectGender.innerHTML = `<option value="Male">Nam</option><option value="Female">Nữ</option>`;

                // Loại hành khách
                let labelType = document.createElement("label");
                labelType.textContent = "Loại hành khách:";
                let selectType = document.createElement("select");
                selectType.name = "passenger" + i + "_type";
                selectType.id = "passenger" + i + "_type";
                selectType.innerHTML = `
                    <option value="Adult">Người lớn</option>
                    <option value="Child">Trẻ em</option>
                    <option value="Infant">Em bé</option>`;

                // CMND và Hộ chiếu container
                let identityContainer = document.createElement("div");
                identityContainer.id = "identityContainer" + i;

                let labelPassport = document.createElement("label");
                labelPassport.textContent = "Hộ chiếu:";
                let inputPassport = document.createElement("input");
                inputPassport.type = "text";
                inputPassport.name = "passenger" + i + "_passport";

                let labelIdCard = document.createElement("label");
                labelIdCard.textContent = "CCCD/CMND:";
                let inputIdCard = document.createElement("input");
                inputIdCard.type = "text";
                inputIdCard.name = "passenger" + i + "_id_card_number";

                identityContainer.appendChild(labelPassport);
                identityContainer.appendChild(inputPassport);
                identityContainer.appendChild(labelIdCard);
                identityContainer.appendChild(inputIdCard);

                identityContainer.style.display = "block";

                selectType.addEventListener("change", function () {
                    identityContainer.style.display = this.value === "Adult" ? "block" : "none";
                });

                // Add to fieldset
                fieldset.appendChild(labelFirstName);
                fieldset.appendChild(inputFirstName);

                fieldset.appendChild(labelLastName);
                fieldset.appendChild(inputLastName);

                fieldset.appendChild(labelDob);
                fieldset.appendChild(inputDob);

                fieldset.appendChild(labelGender);
                fieldset.appendChild(selectGender);

                fieldset.appendChild(labelType);
                fieldset.appendChild(selectType);

                fieldset.appendChild(identityContainer);

                container.appendChild(fieldset);
                
                inputDob.addEventListener("change", function () {
                    const rawDate = this.value; // yyyy-mm-dd
                    if (rawDate) {
                        const parts = rawDate.split("-");
                        const formatted = `${parts[2]}/${parts[1]}/${parts[0]}`;
                        console.log("Ngày chọn:", formatted); // hoặc lưu vào input hidden nếu cần
                    }
                });

            }
        }
    </script>
</head>
<body>

    <h1>Đặt vé cho chuyến bay</h1>
    <h2>Mã chuyến bay: <%=flight.getFlightNumber()%></h2>
    <h3>Tên người dùng: <%=user.getFirstName()%> <%=user.getLastName()%></h3>

    <form action="./booking" method="post">
        <input type="hidden" name="flightId" value="<%=flight.getFlightId()%>">
        <input type="hidden" name="userId" value="<%=user.getUserId()%>">

        <label>Số vé:</label>
        <input type="number" id="numTickets" name="numTickets" min="1" max="10" required onchange="generatePassengerForms()">

        <div id="passengerForms"></div>

        <input type="submit" value="Xác nhận đặt vé">
    </form>

</body>
</html>
