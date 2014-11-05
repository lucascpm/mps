package Model;

/**
 *
 * @author Lucas
 */
public class Usuario {
    
    private String nome;
    private String endereceo;
    private String telefone;
    private String email;
    private String senha;
    private String login;     
    private String sexo;

    public Usuario(){
        
    }
    
    public Usuario(String nome, String endereceo, String telefone, String sexo, String email, String senha, String login) {
        this.nome = nome;
        this.endereceo = endereceo;
        this.telefone = telefone;
        this.sexo = sexo;
        this.email = email;
        this.senha = senha;
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereceo() {
        return endereceo;
    }

    public void setEndereceo(String endereceo) {
        this.endereceo = endereceo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    
}
