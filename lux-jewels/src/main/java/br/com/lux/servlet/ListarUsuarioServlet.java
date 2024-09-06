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

@WebServlet("/lista-usuario")
public class ListarUsuarioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Usuario> listaUsuarios = new  CreateUsuarioDao().listarUsuarios();
        request.setAttribute("users", listaUsuarios);

        System.out.println("Número de usuários enviados para o JSP: " + listaUsuarios.size());

        request.getRequestDispatcher("listarUsuario.jsp").forward(request, response);
    }
}


