
package Model;

/**
 *
 * @author Lucas
 */
public class Curso {
    
    private String nome;
    private int codigo;
    private Professor professores[];
    private Disciplina disciplinas[];
    
    public Curso(){
        
    }

    public Curso(String nome, int codigo) {
        this.nome = nome;
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getCodigo() {
        return codigo;
    }
    
    public Disciplina[] getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(Disciplina[] disciplinas) {
        this.disciplinas = disciplinas;
    }
    
    
    
    
    
    
    
    
    
}
