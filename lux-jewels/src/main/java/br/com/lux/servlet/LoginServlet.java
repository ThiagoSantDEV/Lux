package br.com.lux.servlet;

import br.com.lux.dao.CreateUsuarioDao;
import br.com.lux.model.Usuario;
import br.com.lux.servlet.LoginServlet;
import org.mindrot.jbcrypt.BCrypt;

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
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        if (email == null || senha == null) {
            request.getSession().setAttribute("message", "Email or password is missing");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        CreateUsuarioDao userDao = new CreateUsuarioDao();

        try {
            Usuario user = new Usuario();
            user.setEmail(email);
            user.setSenha(senha);

            boolean isValidUser = userDao.verifyCredentials(user);

            if (isValidUser) {
                request.getSession().setAttribute("loggedUser", email);
                response.sendRedirect("links.jsp");
            } else {
                request.getSession().setAttribute("message", "E-mail e/ou senha incorretos. Por favor, tente novamente.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Erro ao verificar as credenciais.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
