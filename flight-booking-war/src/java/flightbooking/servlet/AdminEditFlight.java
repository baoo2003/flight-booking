/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package flightbooking.servlet;

import flightbooking.ejb.AirportsFacadeLocal;
import flightbooking.ejb.FlightsFacadeLocal;
import flightbooking.entity.Airports;
import flightbooking.entity.Bookings;
import flightbooking.entity.Flights;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Quang Trung
 */
@WebServlet(name = "AdminEditFlight", urlPatterns = {"/admin/editFlight"})
public class AdminEditFlight extends HttpServlet {
    @EJB
    private FlightsFacadeLocal flightsFacade;
    
    @EJB
    private AirportsFacadeLocal airportsFacade;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminEditFlight</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminEditFlight at " + request.getContextPath() + "</h1>");
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
        int flightId = Integer.parseInt(request.getParameter("id"));
        Flights flight = flightsFacade.find(flightId);
        request.setAttribute("flight", flight);
        List<Airports> airports = airportsFacade.findAll(); // hoặc airportBean.getAllAirports()
        request.setAttribute("airports", airports);
        request.getRequestDispatcher("/admin/editFlight.jsp").forward(request, response);
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
        int flightId = Integer.parseInt(request.getParameter("flightId"));
        Flights flight = flightsFacade.find(flightId);
        String flightNumber = request.getParameter("flightNumber");
        String departureTimeStr = request.getParameter("departureTime");
        String arrivalTimeStr = request.getParameter("arrivalTime");

        // Chuyển chuỗi thành định dạng "yyyy-MM-dd HH:mm:ss"
        departureTimeStr = departureTimeStr.replace("T", " ") + ":00";
        arrivalTimeStr = arrivalTimeStr.replace("T", " ") + ":00";

        // Chuyển sang java.sql.Timestamp
        Timestamp departureTime = Timestamp.valueOf(departureTimeStr);
        Timestamp arrivalTime = Timestamp.valueOf(arrivalTimeStr);

        int duration = Integer.parseInt(request.getParameter("duration"));
        BigDecimal price = new BigDecimal(request.getParameter("price"));

        int maxSeats = Integer.parseInt(request.getParameter("maxSeats"));
        
        int departureAirportId = Integer.parseInt(request.getParameter("departureAirportId"));
        int arrivalAirportId = Integer.parseInt(request.getParameter("arrivalAirportId"));

        Airports departure = airportsFacade.find(departureAirportId);
        Airports arrival = airportsFacade.find(arrivalAirportId);
        
        flight.setFlightNumber(flightNumber);
        flight.setDepartureTime(departureTime);
        flight.setArrivalTime(arrivalTime);
        flight.setDuration(duration);
        flight.setPrice(price);
        flight.setMaxSeats(maxSeats);
        flight.setAvailableSeats(maxSeats);

        flight.setDepartureAirportId(departure);  
        flight.setArrivalAirportId(arrival);     

        
        flightsFacade.edit(flight);
         HttpSession session = request.getSession();
        // session.setAttribute("adminId", "admin1");
        response.sendRedirect(request.getContextPath() + "/admin/flight");
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
