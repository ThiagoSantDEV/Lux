package br.com.lux.servlet;

import br.com.lux.dao.CreateUsuarioDao;
import br.com.lux.model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet("/lista-usuario ")
public class ListarUsuarioServlet extends HttpServlet {
    private CreateUsuarioDao usuarioDao = new CreateUsuarioDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void usuarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Usuario> lista = usuarioDao.listarUsuarios();
    }
}