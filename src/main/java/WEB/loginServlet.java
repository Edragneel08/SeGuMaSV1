/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package WEB;

import DAO.loginDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author edrag
 */
@WebServlet("/")
public class loginServlet extends HttpServlet {

    private loginDAO LoginDAO;

    public void init() {
        LoginDAO = new loginDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Get the username and password from the request parameters
        String username = request.getParameter("username");
        String pass = request.getParameter("pass");

        // Get the session (if it exists)
        HttpSession session = request.getSession(false);

        // Check if the session has a username attribute
        if (session != null && session.getAttribute("username") != null) {
            // If the session already has a username, use that instead
            username = (String) session.getAttribute("username");
        } else {
            // Validate the username and password
            if (!LoginDAO.validate(username, pass)) {
                out.print("<script type='text/javascript'>");
                out.print("alert('Sorry, username or password error');");
                out.print("window.location='index.jsp';");
                out.print("</script>");
                out.close();
                return;  // Exit the method if validation fails
            }

            // Set the username in the session
            session = request.getSession();
            session.setAttribute("username", username);
        }

        System.out.println("Hello " + username);

        // Forward the request to the "listGuardMenu" servlet
        RequestDispatcher rd = request.getRequestDispatcher("listGuardMenu");
        rd.forward(request, response);

        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
