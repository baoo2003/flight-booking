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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đặt vé</title>
        <script>
            function generatePassengerForms() {
                var numTickets = document.getElementById("numTickets").value;
                var container = document.getElementById("passengerForms");
                container.innerHTML = ""; // Xóa nội dung cũ

                for (let i = 1; i <= numTickets; i++) {
                    let fieldset = document.createElement("fieldset");

                    let legend = document.createElement("legend");
                    legend.textContent = "Hành khách " + i;
                    fieldset.appendChild(legend);

                    // Họ
                    let labelFirstName = document.createElement("label");
                    labelFirstName.textContent = "Họ:";
                    let inputFirstName = document.createElement("input");
                    inputFirstName.setAttribute("type", "text");
                    inputFirstName.setAttribute("name", "passenger" + i + "_firstName");
                    inputFirstName.required = true;

                    // Tên
                    let labelLastName = document.createElement("label");
                    labelLastName.textContent = "Tên:";
                    let inputLastName = document.createElement("input");
                    inputLastName.setAttribute("type", "text");
                    inputLastName.setAttribute("name", "passenger" + i + "_lastName");
                    inputLastName.required = true;

                    // Ngày sinh
                    let labelDob = document.createElement("label");
                    labelDob.textContent = "Ngày sinh:";
                    let inputDob = document.createElement("input");
                    inputDob.setAttribute("type", "date");
                    inputDob.setAttribute("name", "passenger" + i + "_dob");
                    inputDob.required = true;

                    // Giới tính
                    let labelGender = document.createElement("label");
                    labelGender.textContent = "Giới tính:";
                    let selectGender = document.createElement("select");
                    selectGender.setAttribute("name", "passenger" + i + "_gender");
                    let optionMale = new Option("Nam", "Male");
                    let optionFemale = new Option("Nữ", "Female");
                    selectGender.appendChild(optionMale);
                    selectGender.appendChild(optionFemale);

                    // Loại hành khách (Adult, Children, Infant)
                    let labelType = document.createElement("label");
                    labelType.textContent = "Loại hành khách:";
                    let selectType = document.createElement("select");
                    selectType.setAttribute("name", "passenger" + i + "_type");
                    selectType.setAttribute("id", "passenger" + i + "_type");

                    let optionAdult = new Option("Người lớn", "Adult");
                    let optionChildren = new Option("Trẻ em", "Child");
                    let optionInfant = new Option("Em bé", "Infant");
                    selectType.appendChild(optionAdult);
                    selectType.appendChild(optionChildren);
                    selectType.appendChild(optionInfant);

                    // Container chứa CMND và Hộ chiếu
                    let identityContainer = document.createElement("div");
                    identityContainer.setAttribute("id", "identityContainer" + i);

                    // Hộ chiếu
                    let labelPassport = document.createElement("label");
                    labelPassport.textContent = "Hộ chiếu:";
                    let inputPassport = document.createElement("input");
                    inputPassport.setAttribute("type", "text");
                    inputPassport.setAttribute("name", "passenger" + i + "_passport");                    

                    // CCCD/CMND
                    let labelIdCardNumber = document.createElement("label");
                    labelIdCardNumber.textContent = "CCCD/CMND:";
                    let inputIdCardNumber = document.createElement("input");
                    inputIdCardNumber.setAttribute("type", "text");
                    inputIdCardNumber.setAttribute("name", "passenger" + i + "_id_card_number");

                    // Thêm Hộ chiếu và CMND vào container
                    identityContainer.appendChild(labelPassport);
                    identityContainer.appendChild(inputPassport);
                    identityContainer.appendChild(document.createElement("br"));

                    identityContainer.appendChild(labelIdCardNumber);
                    identityContainer.appendChild(inputIdCardNumber);
                    identityContainer.appendChild(document.createElement("br"));

                    // Mặc định ẩn container
                    identityContainer.style.display = "block";

                    // Thêm sự kiện để xử lý thay đổi loại hành khách
                    selectType.addEventListener("change", function () {
                        if (this.value === "Adult") {
                            identityContainer.style.display = "block"; // Hiện CMND + Hộ chiếu
                        } else {
                            identityContainer.style.display = "none"; // Ẩn CMND + Hộ chiếu
                        }
                    });

                    // Thêm tất cả vào fieldset
                    fieldset.appendChild(labelFirstName);
                    fieldset.appendChild(inputFirstName);
                    fieldset.appendChild(document.createElement("br"));

                    fieldset.appendChild(labelLastName);
                    fieldset.appendChild(inputLastName);
                    fieldset.appendChild(document.createElement("br"));

                    fieldset.appendChild(labelDob);
                    fieldset.appendChild(inputDob);
                    fieldset.appendChild(document.createElement("br"));

                    fieldset.appendChild(labelGender);
                    fieldset.appendChild(selectGender);
                    fieldset.appendChild(document.createElement("br"));

                    fieldset.appendChild(labelType);
                    fieldset.appendChild(selectType);
                    fieldset.appendChild(document.createElement("br"));

                    // Thêm container chứa CMND và Hộ chiếu
                    fieldset.appendChild(identityContainer);

                    container.appendChild(fieldset);
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
            <input type="number" id="numTickets" name="numTickets" min="1" max="10" required onchange="generatePassengerForms()"><br>

            <div id="passengerForms"></div>

            <input type="submit" value="Xác nhận đặt vé">
        </form>
    </body>
</html>
