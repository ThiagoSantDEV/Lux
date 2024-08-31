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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    };

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        UserDao userDao = new UserDao();

        if (email == null || senha == null) {
            request.getSession().setAttribute("message", "Email or password is missing");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        User user = new User(); // O nome de usuário pode ser nulo aqui, já que não é usado

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
