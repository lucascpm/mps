package Controller;

/**
 *
 * @author Lucas
 */
import Model.Professor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar; 
import java.util.List;
import java.util.Scanner;

public class ProfessorController {
    
    private Scanner in = new Scanner(System.in);
    public void menuGeral() throws SQLException { 
        String resposta; 
        do {
            System.out .println("****************************Professores 0.0.1****************************"); 
            System.out.println("1-Listar");
            System.out.println("2-Inserir");
            System.out.println("3-Remover");
            System.out.println("4-Pesquisar");
            System.out.println(":");
            resposta = in.next();
            
            if (resposta.trim().equals("1")) 
                listarProfessores(); 
            else if (resposta.trim().equals("2")) 
                inserirProfessor();
            else if (resposta.trim().equals("3")) 
                removerProfessor();
            else if (resposta.trim().equals("4")) 
                pesquisarProfessor();
                System.out.println("");

        } 
        
        while (!resposta.trim().equals("0"));
    }
    
    private void listarProfessores() throws SQLException {
        List<Professor> listaProfessor = new ArrayList<Professor>();
        BancoDadosController consultaBancoDados = new BancoDadosController();
        listaProfessor = consultaBancoDados.selectProfessor(null);
        System.out .println("______________________________________________________________________");
        System.out.println("CODIGO--NOME--EMAIL--SEXO--DATA ADMISSAO");
        
        for (int i = 0; i < listaProfessor.size(); i++)
            System.out.println(listaProfessor.get(i).getCodigo()+ " - " + listaProfessor.get(i).getNome() + "   - " + listaProfessor.get(i).getEmail()
            + " - " + listaProfessor.get(i).getSexo() + " - " + 
                    listaProfessor.get(i).getDataAdmissao().get(Calendar.DAY_OF_MONTH) +"/"+
                    listaProfessor.get(i).getDataAdmissao().get(Calendar.MONTH) +"/"+
                    listaProfessor.get(i).getDataAdmissao().get(Calendar.YEAR));
        
        System.out .println("______________________________________________________________________");
    }
    
    private void pesquisarProfessor() throws SQLException {
        System.out .println("______________________________________________________________________");
        System.out.println("Codigo: ");
        String codigo_in = in.next();
        
        BancoDadosController pesquisaBancoDados = new BancoDadosController();
        Professor professor = null;
        professor = pesquisaBancoDados.pesquisaProfessor(Integer.parseInt(codigo_in));
        
        System.out.println(professor.getCodigo()+ " - " + professor.getNome() + " - " + professor.getEmail() + " - " + professor.getSexo() + " - " + 
                    professor.getDataAdmissao().get(Calendar.DAY_OF_MONTH) +"/"+
                    professor.getDataAdmissao().get(Calendar.MONTH) +"/"+
                    professor.getDataAdmissao().get(Calendar.YEAR));
    }
    
    private void inserirProfessor() throws SQLException {
        String codigo_in;
        String nome;
        String endereco;
        String telefone;
        String sexo;
        String email;
        String senha;
        String login;

        System.out .println("______________________________________________________________________");
        System.out.println("Codigo: ");
        codigo_in = in.next();
        System.out.println("Nome: ");
        nome = in.next();
        System.out.println("Endereco: ");
        endereco = in.next();
        System.out.println("Telefone: ");
        telefone = in.next();
        System.out.println("Sexo: ");
        sexo = in.next();
        System.out.println("E-mail: ");
        email = in.next();
        System.out.println("Senha: ");
        senha = in.next();
        System.out.println("Login: ");
        login = in.next();
        

        
        System.out .println("______________________________________________________________________");
        int codigo = Integer.parseInt(codigo_in);
        Calendar dataAdmissao = Calendar.getInstance();
        Professor novoProfessor = new Professor(codigo, dataAdmissao,nome, endereco, telefone, sexo, email, senha, login);
        BancoDadosController insercaoBancoDados = new BancoDadosController();
        insercaoBancoDados.insertProfessor(novoProfessor);
    }
    
    private void removerProfessor() throws SQLException {
        System.out .println("______________________________________________________________________");
        System.out.println("Matricula: ");
        String codigo_in = in.next();
        
        BancoDadosController remocaoBancoDados = new BancoDadosController();
        remocaoBancoDados.removeProfessor(Integer.parseInt(codigo_in));
        System.out .println("______________________________________________________________________");
    }
    
    
}
