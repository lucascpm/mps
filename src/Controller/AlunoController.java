package Controller;

/**
 *
 * @author Lucas
 */
import Model.Aluno;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar; 
import java.util.List;
import java.util.Scanner;

public class AlunoController {
    
    private Scanner in = new Scanner(System.in);
    public void menuGeral() throws SQLException { 
        String resposta; 
        do {
            System.out .println("****************************Alunos 0.0.1****************************"); 
            System.out.println("1-Listar");
            System.out.println("2-Inserir");
            System.out.println("3-Remover");
            System.out.println("4-Pesquisar:");
            resposta = in.next();
            
            if (resposta.trim().equals("1")) 
                listarAlunos(); 
            else if (resposta.trim().equals("2")) 
                inserirAluno();
            else if (resposta.trim().equals("3")) 
                removerAluno();
            else if (resposta.trim().equals("4")) 
                pesquisarAluno();
                System.out.println("");

        } 
        
        while (!resposta.trim().equals("0"));
    }
    
    private void listarAlunos() throws SQLException {
        List<Aluno> listaAluno = new ArrayList<Aluno>();
        BancoDadosController consultaBancoDados = new BancoDadosController();
        listaAluno = consultaBancoDados.selectAluno();
        System.out .println("______________________________________________________________________");
        System.out.println("MATRICULA--NOME--EMAIL--SEXO--DATA MATRICULA");
        
        for (int i = 0; i < listaAluno.size(); i++)
            System.out.println(listaAluno.get(i).getMatricula()+ " - " + listaAluno.get(i).getNome() + "   - " + listaAluno.get(i).getEmail()
            + " - " + listaAluno.get(i).getSexo() + " - " + 
                    listaAluno.get(i).getDataMatricula().get(Calendar.DAY_OF_MONTH) +"/"+
                    listaAluno.get(i).getDataMatricula().get(Calendar.MONTH) +"/"+
                    listaAluno.get(i).getDataMatricula().get(Calendar.YEAR));
        
        System.out .println("______________________________________________________________________");
    }
    
    private void pesquisarAluno() throws SQLException {
        System.out .println("______________________________________________________________________");
        System.out.println("Matricula: ");
        String codigo_in = in.next();
        
        BancoDadosController pesquisaBancoDados = new BancoDadosController();
        Aluno aluno = null;
        aluno = pesquisaBancoDados.pesquisaAluno(Integer.parseInt(codigo_in));
        
        System.out.println(aluno.getMatricula()+ " - " + aluno.getNome() + " - " + aluno.getEmail() + " - " + aluno.getSexo() + " - " + 
                    aluno.getDataMatricula().get(Calendar.DAY_OF_MONTH) +"/"+
                    aluno.getDataMatricula().get(Calendar.MONTH) +"/"+
                    aluno.getDataMatricula().get(Calendar.YEAR));
    }
    
    private void inserirAluno() throws SQLException {
        String matricula_in;
        String nome;
        String endereco;
        String telefone;
        String sexo;
        String email;
        String senha;
        String login;

        System.out .println("______________________________________________________________________");
        System.out.println("Matricula: ");
        matricula_in = in.next();
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
        int matricula = Integer.parseInt(matricula_in);
        Calendar dataMatricula = Calendar.getInstance();
        Aluno novoAluno = new Aluno(nome, endereco, telefone, sexo, email, senha, login, matricula, dataMatricula);
        BancoDadosController insercaoBancoDados = new BancoDadosController();
        insercaoBancoDados.insertAluno(novoAluno);
    }
    
    public void inserirAluno(Aluno novoAluno){
        BancoDadosController bd = new BancoDadosController();
        bd.insertAluno(novoAluno);
    }
    
    private void removerAluno() throws SQLException {
        System.out .println("______________________________________________________________________");
        System.out.println("Matricula: ");
        String matricula_in = in.next();
        
        BancoDadosController remocaoBancoDados = new BancoDadosController();
        remocaoBancoDados.removeAluno(Integer.parseInt(matricula_in));    
        System.out .println("______________________________________________________________________");
    }
    
    
}
