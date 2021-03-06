package View;

/**
 *
 * @author Lucas
 */
import Controller.AlunoController; 
import Controller.DisciplinaController;
import Controller.ProfessorController; 
import Controller.CursoController;
import java.sql.SQLException;
import java.util.Scanner;

public class TelaPrincipal { 
    private Scanner in = new Scanner(System.in); 
    public static void main(String args[]) throws SQLException { 
        TelaPrincipal telaPrincipal = new TelaPrincipal();
        telaPrincipal.menuInicial(); 
        System.out.println("");
    } 
    
    public void menuInicial() throws SQLException { 
        String resposta; 
        do { 
            System.out .println("****************************Gerenciamento de Cursos 0.01****************************"); 
            System.out.println("2-Alunos");
            System.out.println("0-Sair");
            
            System.out.print(":"); 
            resposta = in.next();
            if (resposta.trim().equals("1"))
                disciplinaController(); 
            else if (resposta.trim().equals("2"))
                alunoController();
            else if (resposta.trim().equals("3"))
                professorController();
            else if (resposta.trim().equals("4"))
                cursoController();
        } while (!resposta.trim().equals("0")); 
    } 
    
    private void disciplinaController() throws SQLException {
        DisciplinaController disciplinaController = new DisciplinaController(); 
        disciplinaController.menuGeral();
    } 
    
    private void alunoController() throws SQLException {
        AlunoController alunoController = new AlunoController(); 
        alunoController.menuGeral();
    }
    
    private void professorController() throws SQLException {
        ProfessorController professorController = new ProfessorController(); 
        professorController.menuGeral();
    } 
    
    private void cursoController() throws SQLException {
        CursoController cursoController = new CursoController(); 
        cursoController.menuGeral();
    } 
    
    
    
    
}

