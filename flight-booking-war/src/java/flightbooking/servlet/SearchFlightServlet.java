/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package flightbooking.servlet;

import flightbooking.ejb.FlightsFacadeLocal;
import flightbooking.entity.Flights;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vuquo
 */
@WebServlet(name = "SearchFlightServlet", urlPatterns = {"/search-flights"})
public class SearchFlightServlet extends HttpServlet {

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
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SearchFlightServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchFlightServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        // Lấy tham số từ form
        String departure = request.getParameter("departure");
        String arrival = request.getParameter("arrival");
        String dateString = request.getParameter("flightDate");

        // Kiểm tra input hợp lệ
        if (departure == null || arrival == null || dateString == null ||
            departure.trim().isEmpty() || arrival.trim().isEmpty() || dateString.trim().isEmpty()) {
            request.setAttribute("error", "Vui lòng nhập đầy đủ thông tin tìm kiếm.");
            request.getRequestDispatcher("home.jsp").forward(request, response);
            return;
        }

        // Chuyển đổi ngày bay từ chuỗi sang Date
        Date date = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            request.setAttribute("error", "Định dạng ngày không hợp lệ.");
            request.getRequestDispatcher("home.jsp").forward(request, response);
            return;
        }

        // Gọi EJB để tìm kiếm chuyến bay
        List<Flights> flights = flightsFacade.searchFlights(departure, arrival, date);

        // Gửi danh sách chuyến bay đến flight.jsp
        request.setAttribute("flights", flights);
        request.getRequestDispatcher("flight.jsp").forward(request, response);
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
