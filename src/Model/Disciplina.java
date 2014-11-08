package Model;

/**
 *
 * @author Lucas
 */
public class Disciplina {
    
    private int codigo;
    private String nome;
    private String descricao;

    public Disciplina(){
        
    }

    public Disciplina(int codigo, String nome, String descricao) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
    }



    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int id) {
        this.codigo = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    
    
    
}
