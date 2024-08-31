package br.com.lux.model;

// LEMBRANDO, CLIENTE NÃO DEVE HERDAR USER


public class User   {

    private int idUsuario;
    private String username;
    private String email;
    private String senha;
    private String status;
    private String grupo;


    public User() {
        super();
    }

    public User(int idUsuario, String username, String senha, String email, String grupo, String status) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.senha = senha;
        this.email = email;
        this.grupo = grupo;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
