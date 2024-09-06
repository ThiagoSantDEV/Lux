
package br.com.lux.servlet;

import br.com.lux.dao.CreateUsuarioDao;
import br.com.lux.model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/alterarStatusUsuario")
public class AlterarStatusUsuarioServlet extends HttpServlet {
    private CreateUsuarioDao usuarioDao = new CreateUsuarioDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                Usuario usuario = usuarioDao.buscarUsuarioPorId(id);
                if (usuario != null) {
                    usuario.setStatus(!usuario.getStatus());
                    usuarioDao.updateUsuario(usuario);
                }
            } catch (NumberFormatException e) {
                System.out.println("ID inválido: " + e.getMessage());
            }
        }
        response.sendRedirect("/lista-usuario");
    }
}
