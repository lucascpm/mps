package Model;

import java.util.Calendar;

/**
 *
 * @author Lucas
 */
public class Aluno extends Usuario {

    private int matricula;
    private final Calendar dataMatricula;
    
    public Aluno(String nome, String endereceo, String telefone, String sexo, String email, String senha, String login, int matricula, Calendar dataMatricula) {
        super(nome, endereceo, telefone, sexo, email, senha, login);
        this.dataMatricula = dataMatricula;
        this.matricula = matricula;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public Calendar getDataMatricula() {
        return dataMatricula;
    }
    
}
