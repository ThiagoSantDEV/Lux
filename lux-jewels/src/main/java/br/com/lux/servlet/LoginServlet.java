package br.com.lux.servlet;



import br.com.lux.dao.UserDao;
import br.com.lux.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    };

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || password == null) {
            request.getSession().setAttribute("message", "Email or password is missing");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        User user = new User(null, email, password); // O nome de usuário pode ser nulo aqui, já que não é usado

        boolean isValidUser = new UserDao().verifyCredentials(user);

        if (isValidUser) {
            request.getSession().setAttribute("loggedUser", email);
            response.sendRedirect("links");
        } else {
            request.getSession().setAttribute("message", "Invalid email or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
