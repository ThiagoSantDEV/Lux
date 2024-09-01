package br.com.lux.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LinksServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loggedUser = (String) request.getSession().getAttribute("loggedUser");

        if (loggedUser == null) {
            response.sendRedirect("login.jsp");
        } else {
            request.getRequestDispatcher("links.jsp").forward(request, response);
        }
    }
}
