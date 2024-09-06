package br.com.lux.model;

// LEMBRANDO, CLIENTE NÃO DEVE HERDAR USER


public class Usuario {

    private int idUsuario;
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private boolean status;
    private String grupo;


    public Usuario() {
        super();
    }

    public Usuario(int idUsuario, String username, String senha, String cpf, String email, String grupo, boolean status) {
        this.idUsuario = idUsuario;
        this.nome = username;
        this.senha = senha;
        this.cpf = cpf;
        this.email = email;
        this.grupo = grupo;
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
