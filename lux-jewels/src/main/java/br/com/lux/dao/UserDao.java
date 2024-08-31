package br.com.lux.dao;

import br.com.lux.config.ConnectionPoolConfig;
import br.com.lux.model.User;

import java.sql.*;

public class UserDao {

    public User criarUsuario (User user) {
        try {
            String SQL = "INSERT INTO tb_usuarios (ds_username, ds_email, ds_senha, ds_grupo, ds_status) "
                    +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            Connection connection = ConnectionPoolConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getSenha());
            preparedStatement.setString(4, user.getGrupo());
            preparedStatement.setString(5, user.getStatus());

            int linhasAfetadas = preparedStatement.executeUpdate();

            if (linhasAfetadas == 1) {
                ResultSet chavesGeradas = preparedStatement.getGeneratedKeys();
                if (chavesGeradas.next()) {
                    int idUsuario = chavesGeradas.getInt(1);
                    user.setIdUsuario(idUsuario);

                    System.out.println("Usuário criado com sucesso!");
                } else {
                    System.out.println("Falha ao obter o ID do usuário.");
                }
            } else {
                System.out.println("Falha ao criar o usuário.");
            }

            connection.close();
        } catch (Exception e) {
            System.out.println("Erro ao criar o usuário: " + e.getMessage());
        }

        return user;
    }

    //ADICIONAR MÉTODO buscarUsuarioPorEmail PARA ARRUMAR LOGIN

    public User buscarUsuarioPorEmail(String email) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User user = null;

        try {
            Connection connection = ConnectionPoolConfig.getConnection();
            String query = "SELECT * FROM tb_usuarios WHERE ds_email = ?";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, email);
            rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setIdUsuario(rs.getInt("id_usuario"));
                user.setUsername(rs.getString("ds_username"));
                user.setEmail(rs.getString("ds_email"));
                user.setSenha(rs.getString("ds_senha"));
                user.setGrupo(rs.getString("ds_grupo"));
                user.setStatus(rs.getString("ds_status"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuário por email: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return user;
    }

    public boolean verifyCredentials(User user) {
        String SQL = "SELECT * FROM USERS WHERE EMAIL = ?";

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(1, user.getEmail());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String password = resultSet.getString("password");
                    if (password.equals(user.getSenha())) {
                        return true;
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return false;
    }

}
