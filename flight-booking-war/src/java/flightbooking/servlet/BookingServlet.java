/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package flightbooking.servlet;

import flightbooking.ejb.BookingsFacadeLocal;
import flightbooking.ejb.FlightsFacadeLocal;
import flightbooking.ejb.PassengersFacadeLocal;
import flightbooking.entity.Bookings;
import flightbooking.entity.Flights;
import flightbooking.entity.Passengers;
import flightbooking.entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

/**
 *
 * @author vuquo
 */
@WebServlet(name = "BookingServlet", urlPatterns = {"/booking"})
public class BookingServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @EJB
    private FlightsFacadeLocal flightsFacade;
    
    @EJB
    private BookingsFacadeLocal bookingsFacade;
    
    @EJB
    private PassengersFacadeLocal passengersFacade;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BookingServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BookingServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String flightId = request.getParameter("flightId");

        if (flightId == null || flightId.isEmpty()) {
            response.getWriter().println("Không tìm thấy chuyến bay.");
            return;
        }

        Flights flight = null;
        try {
            int flightIdInt = Integer.parseInt(flightId); // Convert to integer
            flight = flightsFacade.find(flightIdInt); // Pass the correct type
        } catch (NumberFormatException e) {
            response.getWriter().println("ID chuyến bay không hợp lệ.");
            return;
        }

        // Check if flight exists
        if (flight == null) {
            response.getWriter().println("Không tìm thấy chuyến bay.");
            return;
        }

        // Forward to JSP with flight details
        request.setAttribute("flightId", flightId);
        request.setAttribute("flight", flight);
        request.getRequestDispatcher("booking.jsp").forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        UserTransaction transaction = null;
        
        try {
            // Lấy transaction từ context
            transaction = (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
            transaction.begin(); // Bắt đầu transaction
            
            // Lấy thông tin chuyến bay và người dùng
            int flightId = Integer.parseInt(request.getParameter("flightId"));
            int userId = Integer.parseInt(request.getParameter("userId"));
            int numTickets = Integer.parseInt(request.getParameter("numTickets"));
            
            System.out.println("Thông tin chuyến bay:");
            System.out.println("flightId: " + flightId);
            System.out.println("userId" + userId);
            System.out.println("numTickets" + numTickets);
            System.out.println("Lay chuyen bay");
            Flights flight = flightsFacade.find(flightId);
            System.out.println("flight: " + flight);
            System.out.println("Lay user");
            Users user = new Users();
            user.setUserId(userId);
            System.out.println("User: " + user);         
            
            System.out.println("Tao booking");
            // Tạo đặt vé mới
            Bookings booking = new Bookings();
            booking.setFlightId(flight);
            booking.setUserId(user);
            booking.setBookingDate(new Date());
            booking.setTotalPrice(BigDecimal.valueOf(numTickets * 100)); // Giả sử giá 100$/vé
            booking.setBookingReference("BK" + System.currentTimeMillis());
            
            bookingsFacade.create(booking);
            
            // Lưu thông tin hành khách
            for (int i = 1; i <= numTickets; i++) {
                Passengers passenger = new Passengers();
                passenger.setBookingId(booking);
                
                String firstName = request.getParameter("passenger" + i + "_firstName");
                String lastName = request.getParameter("passenger" + i + "_lastName");
                String gender = request.getParameter("passenger" + i + "_gender");
                String passport = request.getParameter("passenger" + i + "_passport");
                Date date = java.sql.Date.valueOf(request.getParameter("passenger" + i + "_dob"));                
                String idCardNumber = request.getParameter("passenger" + i + "_id_card_number");                
                String type = request.getParameter("passenger" + i + "_type");      
                             
                passenger.setFirstName(firstName);
                passenger.setLastName(lastName);
                passenger.setGender(gender);
                passenger.setPassportNumber(passport);
                passenger.setDateOfBirth(date);
                passenger.setType(type);
                passenger.setIdCardNumber(idCardNumber);
                passenger.setType(type);
                
                passengersFacade.create(passenger);
                
                if (flight.getAvailableSeats() >= numTickets) {
                    flight.setAvailableSeats(flight.getAvailableSeats() - numTickets);
                    flightsFacade.edit(flight);
                } else {
                    response.getWriter().println("Số lượng vé không đủ");
                    transaction.rollback();
                }
            }
            
            // Commit transaction nếu không có lỗi
            transaction.commit();
            
            response.sendRedirect("confirmation.jsp?bookingId=" + booking.getBookingId());
        } catch (Exception e) {
            response.getWriter().println("Lỗi khi đặt vé: " + e.getMessage());
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>    

}
