package Model;

/**
 *
 * @author Lucas
 */
public class Disciplina {
    
    private int codigo;
    private String nome;
    private String professor;
    private int limiteAlunos;
    private int qtde_alunos_matriculados;

    public Disciplina(){
        
    }

    public Disciplina(int codigo, String nome, String professor, int limiteAlunos, int qtde_alunos_matriculados) {
        this.codigo = codigo;
        this.nome = nome;
        this.professor = professor;
        this.limiteAlunos = limiteAlunos;
        this.qtde_alunos_matriculados = qtde_alunos_matriculados;
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

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public int getLimiteAlunos() {
        return limiteAlunos;
    }

    public void setLimiteAlunos(int limiteAlunos) {
        this.limiteAlunos = limiteAlunos;
    }

    public int getQtde_alunos_matriculados() {
        return qtde_alunos_matriculados;
    }

    public void setQtde_alunos_matriculados(int qtde_alunos_matriculados) {
        this.qtde_alunos_matriculados = qtde_alunos_matriculados;
    }
    
    
    
    
}
