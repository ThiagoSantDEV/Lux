package br.com.lux.servlet;

import br.com.lux.dao.CreateUsuarioDao;
import br.com.lux.model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/alterarStatusUsuario")
public class AlterarStatusUsuarioServlet extends HttpServlet {
    private CreateUsuarioDao usuarioDao = new CreateUsuarioDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        List<Usuario> users = usuarioDao.getUsersByName(nome);
        if (users != null && !users.isEmpty()) {
            Usuario usuario = users.get(0);
            usuario.setStatus(!usuario.getStatus());
            usuarioDao.updateUsuario(usuario);
        }
        response.sendRedirect("lista-usuario");
    }
}