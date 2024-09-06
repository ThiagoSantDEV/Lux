package br.com.lux.servlet;

import br.com.lux.dao.CreateUsuarioDao;
import br.com.lux.model.Usuario;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CadastroAdminServlet")
public class CadastroAdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CreateUsuarioDao userDao = new CreateUsuarioDao();
        if (userDao.buscarUsuarioPorEmail("admin@gmail.com") == null) {
            Usuario admin = new Usuario();

            admin.setNome("admin");
            admin.setCpf("49916687862");
            admin.setEmail("admin@gmail.com");
            admin.setSenha(BCrypt.hashpw("admin", BCrypt.gensalt()));
            admin.setGrupo("admin");
            admin.setStatus(true);
            userDao.criarUsuario(admin);
            response.getWriter().println("Admin cadastrado com sucesso!");
        } else {
            response.getWriter().println("Admin já está cadastrado.");
        }
    }
}
