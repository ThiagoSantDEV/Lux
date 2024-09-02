package br.com.lux.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import br.com.lux.dao.CreateUsuarioDao;
import br.com.lux.model.Usuario;

@WebServlet("/lista-usuario")
public class ListarUsuarioServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CreateUsuarioDao userDao = new CreateUsuarioDao();
        List<Usuario> usuarios = userDao.listarUsuarios();

        req.setAttribute("usuarios", usuarios);

        req.getRequestDispatcher("listarUsuario.jsp").forward(req, resp);
    };
}
