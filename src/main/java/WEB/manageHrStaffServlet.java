/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package WEB;

import DAO.StaffDAO;
import MODEL.staff;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author edrag
 */
@WebServlet("/manageHrStaffServlet")
public class manageHrStaffServlet extends HttpServlet {

    private StaffDAO staffDAO;

    public void init() {
        staffDAO = new StaffDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();

        try {
            switch (action) {

                case "/manageHrStaffServlet":

                case "/new":
                    showNewForm(request, response);
                    break;

                case "/insert":
                    insertStaff(request, response);
                    break;

                case "/delete":
                    deleteStaff(request, response);
                    break;

                case "/edit":
                    showEditForm(request, response);
                    break;

                case "/update":
                    updateStaff(request, response);
                    break;

                case "/logout":
                    logout(request, response);
                    break;

                default:
                    listStaff(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    //showInsertForm
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("addNewHrStaff.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String staffID = request.getParameter("staffID");
        staff existingStaff = staffDAO.selectStaff(staffID);
        RequestDispatcher dispatcher = request.getRequestDispatcher("addNewHrStaff.jsp");
        request.setAttribute("staff", existingStaff);
        dispatcher.forward(request, response);

    }

    //insert staff
    private void insertStaff(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String staffID = request.getParameter("staffID");
        String fullname = request.getParameter("fullname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        staff newStaff = new staff(staffID, fullname, username, password);
        staffDAO.insertStaff(newStaff);
        response.sendRedirect("listStaff");
    }

    //listStaff
    private void listStaff(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<staff> listStaff = staffDAO.selectAllStaff();
        request.setAttribute("listStaff", listStaff);
        RequestDispatcher dispatcher = request.getRequestDispatcher("viewListStaff.jsp");
        dispatcher.forward(request, response);
    }

    //update staff
    private void updateStaff(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String staffID = request.getParameter("staffID");
        String fullname = request.getParameter("fullname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        staff updateStaff = new staff(staffID, fullname, username, password);
        staffDAO.updateStaff(updateStaff);
        response.sendRedirect("listStaff");
    }

    //delete staff
    private void deleteStaff(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String staffID = request.getParameter("staffID");
        staffDAO.deleteStaff(staffID);
        response.sendRedirect("listStaff");
    }

    //logout staff
    private void logout(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("username") == null) {
            // The session is likely invalidated
            request.setAttribute("sessionDestroyed", true);
        }

        // Redirect to the login page or any other page after logout
        request.getRequestDispatcher("index.jsp").forward(request, response);
//        response.sendRedirect("index.jsp");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
