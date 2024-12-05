/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package WEB;

import DAO.guardDAO;
import MODEL.guard;
import MODEL.staff;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author edrag
 */
@WebServlet("/guardServlet")
public class guardServlet extends HttpServlet {

    private guardDAO GuardDAO;

    public void init() {
        GuardDAO = new guardDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String action = request.getServletPath();

            switch (action) {
                case "/guardServlet":

                case "/newGuard":
                    showNewFormGuard(request, response);
                    break;

                case "/insertGuard":
                    insertGuard(request, response);
                    break;

                case "/deleteGuard":
                    deleteGuard(request, response);
                    break;

                case "/editGuard":
                    showEditFormGuard(request, response);
                    break;

                case "/updateGuard":
                    updateGuard(request, response);
                    break;

                case "/listGuardMenu":
                    listGuardMenu(request, response);
                    break;

                default:
                    listGuard(request, response);
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(guardServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void showNewFormGuard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("addGuard.jsp");
        dispatcher.forward(request, response);
    }

    private void insertGuard(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String guardID = request.getParameter("guardID");
        String guardName = request.getParameter("guardName");
        int guardAge = Integer.parseInt(request.getParameter("guardAge"));
        String guardGender = request.getParameter("guardGender");
        String guardPhoneNo = request.getParameter("guardPhoneNo");
        String guardEmail = request.getParameter("guardEmail");
        String nationality = request.getParameter("nationality");
        String guardRace = request.getParameter("guardRace");
        double guardSalary = Double.parseDouble(request.getParameter("guardSalary"));
        String guardDOB = request.getParameter("guardDOB");
        String branchID = request.getParameter("branchID");
        String siteID = request.getParameter("siteID");

        guard newGuard = new guard(guardID, guardName, guardAge, guardGender, guardPhoneNo, guardEmail, nationality, guardRace, guardSalary, guardDOB, branchID, siteID);
        GuardDAO.insertGuard(newGuard);
        response.sendRedirect("listGuard");
    }

    private void deleteGuard(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String guardID = request.getParameter("guardID");
        GuardDAO.deleteGuard(guardID);
        response.sendRedirect("listGuard");
    }

    private void showEditFormGuard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String guardID = request.getParameter("guardID");
        guard existingGuard = GuardDAO.selectGuard(guardID);
        RequestDispatcher dispatcher = request.getRequestDispatcher("addGuard.jsp");
        request.setAttribute("guard", existingGuard);
        dispatcher.forward(request, response);
    }

    private void updateGuard(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String guardID = request.getParameter("guardID");
        String guardName = request.getParameter("guardName");
        int guardAge = Integer.parseInt(request.getParameter("guardAge"));
        String guardGender = request.getParameter("guardGender");
        String guardPhoneNo = request.getParameter("guardPhoneNo");
        String guardEmail = request.getParameter("guardEmail");
        String nationality = request.getParameter("nationality");
        String guardRace = request.getParameter("guardRace");
        double guardSalary = Double.parseDouble(request.getParameter("guardSalary"));
        String guardDOB = request.getParameter("guardDOB");
        String branchID = request.getParameter("branchID");
        String siteID = request.getParameter("siteID");

        guard updateGuard = new guard(guardID, guardName, guardAge, guardGender, guardPhoneNo, guardEmail, nationality, guardRace, guardSalary, guardDOB, branchID, siteID);

        GuardDAO.updateGuard(updateGuard);
        response.sendRedirect("listGuard");
    }

    private void listGuard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<guard> listGuard = GuardDAO.selectAllGuard();
        request.setAttribute("listGuard", listGuard);
        RequestDispatcher dispatcher = request.getRequestDispatcher("viewGuardList.jsp");
        dispatcher.forward(request, response);
    }

    private void listGuardMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<guard> listGuard = GuardDAO.selectAllGuard();
        request.setAttribute("listGuard", listGuard);
        RequestDispatcher dispatcher = request.getRequestDispatcher("adminMenu.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
