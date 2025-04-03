/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package flightbooking.servlet;

import flightbooking.ejb.UsersFacadeLocal;
import flightbooking.entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author BAC
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
    
    @EJB
    private UsersFacadeLocal usersFacade;

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
            out.println("<title>Servlet RegisterServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterServlet at " + request.getContextPath() + "</h1>");
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
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        
        HttpSession session = request.getSession();
        
        try {
            // Validation checks
            if (username == null || username.trim().isEmpty()) {
                session.setAttribute("errorMessage", "Tên đăng nhập không được để trống");
                response.sendRedirect("register");
                return;
            }

            if (email == null || email.trim().isEmpty() || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                session.setAttribute("errorMessage", "Email không hợp lệ");
                response.sendRedirect("register");
                return;
            }

            if (password == null || password.length() < 3) {
                session.setAttribute("errorMessage", "Mật khẩu phải có ít nhất 6 ký tự");
                response.sendRedirect("register");
                return;
            }

            if (!password.equals(confirmPassword)) {
                session.setAttribute("errorMessage", "Mật khẩu xác nhận không khớp");
                response.sendRedirect("register");
                return;
            }

            // Check if username already exists
            if (usersFacade.findByUsername(username) != null) {
                session.setAttribute("errorMessage", "Tên đăng nhập đã tồn tại");
                response.sendRedirect("register");
                return;
            }

            // Create new user
            Users newUser = new Users();
            newUser.setUsername(username);
            newUser.setEmail(email);
            newUser.setPassword(password); 

            // Save to database
            usersFacade.create(newUser);

            // Registration successful
            session.setAttribute("successMessage", "Đăng ký thành công! Vui lòng đăng nhập.");
            response.sendRedirect("login.jsp");

        } catch (Exception e) {
            session.setAttribute("errorMessage", "Đã xảy ra lỗi trong quá trình đăng ký: " + e.getMessage());
            response.sendRedirect("register.jsp");
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
