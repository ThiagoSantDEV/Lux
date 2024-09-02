package br.com.lux.servlet;

import br.com.lux.dao.CreateUsuarioDao;
import br.com.lux.model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AlterarStatusServlet")
public class AlterarStatusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        CreateUsuarioDao userDao = new CreateUsuarioDao();
        Usuario usuario = userDao.buscarUsuarioPorId(id);
        if (usuario != null) {
            usuario.setStatus(!usuario.getStatus()); // Alterna o status
            userDao.updateUsuario(usuario);
        }
        response.sendRedirect("listarUsuarios");
    }
}
