package br.com.lux.dao;

import br.com.lux.config.ConnectionPoolConfig;
import br.com.lux.model.Usuario;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class CreateUsuarioDao {

    public Usuario criarUsuario(Usuario usuario) {
        String SQL = "INSERT INTO usuarios (nome, cpf, email, senha, grupo, ativo) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getSenha());
            stmt.setString(5, usuario.getGrupo());
            stmt.setBoolean(6, true);  // sempre ativo

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 1) {
                ResultSet chavesGeradas = stmt.getGeneratedKeys();
                if (chavesGeradas.next()) {
                    usuario.setIdUsuario(chavesGeradas.getInt(1));
                    System.out.println("Usuário criado com sucesso!");
                } else {
                    System.out.println("Falha ao obter o ID do usuário.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao criar o usuário: " + e.getMessage());
        }
        return usuario;
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        String SQL = "SELECT * FROM usuarios WHERE email = ?";
        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQL)) {

            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("id"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setGrupo(rs.getString("grupo"));
                    usuario.setStatus(rs.getBoolean("ativo"));
                    return usuario;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuário por email: " + e.getMessage());
        }
        return null;
    }

    public boolean verifyCredentials(Usuario usuario) {
        String SQL = "SELECT senha FROM usuarios WHERE email = ?";
        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQL)) {

            stmt.setString(1, usuario.getEmail());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String passwordHash = rs.getString("senha");
                    return BCrypt.checkpw(usuario.getSenha(), passwordHash);
                } else {
                    System.out.println("Nenhum usuário encontrado com este e-mail.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar credenciais: " + e.getMessage());
        }
        return false;
    }

    public Usuario updateUsuario (Usuario usuario) {

        String SQL = "UPDATE usuarios SET nome = ?, email = ?, senha = ?, cpf = ?, status = ?, grupo = ? WHERE idUsuario = ?";

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getCpf());
            stmt.setBoolean(5, usuario.getStatus());
            stmt.setString(6, usuario.getGrupo());
            stmt.setInt(7, usuario.getIdUsuario());

            System.out.println("Usuário alterado com sucesso!");

        } catch (SQLException e) {

            System.out.println("Erro ao alterar o usuário: " + e.getMessage());

        }
        return usuario;
    }

}
