package br.com.lux.servlet;

import br.com.lux.dao.CreateUsuarioDao;
import br.com.lux.model.Usuario;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CadastroUsuarioServlet")
public class CadastroUsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idUsuario = request.getParameter("id");
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf").replaceAll("[^\\d]", "");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String confirmaSenha = request.getParameter("confirmaSenha");
        String grupo = request.getParameter("grupo");

        if (!senha.equals(confirmaSenha)) {
            request.setAttribute("mensagem", "As senhas não coincidem!");
            request.getRequestDispatcher("cadastrarUsuario.jsp").forward(request, response);
            return;
        }

        try {
            CreateUsuarioDao userDao = new CreateUsuarioDao();

            if (userDao.buscarUsuarioPorEmail(email) != null) {
                request.setAttribute("mensagem", "O email já está cadastrado!");
                request.getRequestDispatcher("cadastrarUsuario.jsp").forward(request, response);
                return;
            }
            if (userDao.buscarUsuarioPorCPF(cpf) != null) {
                request.setAttribute("mensagem", "Esse CPF já está cadastrado!");
                request.getRequestDispatcher("cadastrarUsuario.jsp").forward(request, response);
                return;
            }

            String senhaEncriptada = BCrypt.hashpw(senha, BCrypt.gensalt());

            Usuario usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setCpf(cpf);
            usuario.setEmail(email);
            usuario.setSenha(senhaEncriptada);
            usuario.setGrupo(grupo);
            usuario.setStatus(true);

            if (idUsuario == null || idUsuario.isBlank()) {
                userDao.criarUsuario(usuario);
                response.sendRedirect("login.jsp");
            } else {
                usuario.setIdUsuario(Integer.parseInt(idUsuario));
                userDao.updateUsuario(usuario);
                response.sendRedirect("/lista-usuario");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", "Ocorreu um erro ao cadastrar o usuário.");
            request.getRequestDispatcher("cadastrarUsuario.jsp").forward(request, response);
        }
    }
}
