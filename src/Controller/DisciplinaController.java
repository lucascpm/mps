
package Controller;

import java.sql.SQLException; 
import java.util.ArrayList; 
import java.util.List; 
import java.util.Scanner; 
import Model.Aluno; 
import Model.Disciplina; 

/**
 *
 * @author Lucas
 */
public class DisciplinaController {
    private Scanner in = new Scanner(System.in);
    
    public void menuGeral() throws SQLException { 
        String resposta; 
        do {
            System.out .println("****************************Disciplinas 0.0.1****************************"); 
            System.out.println("1-Listar");
            System.out.println("2-Inserir");
            System.out.println("3-Remover");
            System.out.println("4-Pesquisar");
            System.out.println(":"); 
            resposta = in.next(); 
            
            if (resposta.trim().equals("2")) 
                inserirDisciplinas(); 
            else if (resposta.trim().equals("1")) 
                listarDisciplinas();
            else if (resposta.trim().equals("3")) 
                removerDisciplina();
            else if (resposta.trim().equals("4")) 
                pesquisarDisciplina();
        } 
        
        while (!resposta.trim().equals("0"));
    }

    private void listarDisciplinas() throws SQLException {
        List<Disciplina> listaDisciplina = new ArrayList<Disciplina>();
        BancoDadosController consultaBancoDados = new BancoDadosController();
        listaDisciplina = consultaBancoDados.selectDisciplina(null);
        System.out .println("______________________________________________________________________");
        System.out.println("CODIGO--NOME--PROFESSOR--LIMITE--ALUNOS MATRICULADOS");
        
        for (int i = 0; i < listaDisciplina.size(); i++)
            System.out.println(listaDisciplina.get(i).getCodigo()+ " - " + listaDisciplina.get(i).getNome() + "   - " + listaDisciplina.get(i).getProfessor()
            + " - " + listaDisciplina.get(i).getLimiteAlunos() + " - " + listaDisciplina.get(i).getQtde_alunos_matriculados());
        
        System.out .println("______________________________________________________________________");
    }
    
    private void inserirDisciplinas() throws SQLException {
        String codigo_in;
        String limite_alunos_in;
        String professor;
        String nome;

        System.out .println("______________________________________________________________________");
        System.out.println("Codigo: ");
        codigo_in = in.next(); 
        System.out.println("Nome: ");
        nome = in.next(); 
        System.out.println("Professor: ");
        professor = in.next(); 
        System.out.println("Limite: ");
        limite_alunos_in = in.next();

        
        System.out .println("______________________________________________________________________");
        int codigo = Integer.parseInt(codigo_in);
        int limite_alunos = Integer.parseInt(limite_alunos_in);
        
        Disciplina novaDisciplina = new Disciplina(codigo, nome, professor, limite_alunos, 0);
        BancoDadosController insercaoBancoDados = new BancoDadosController();
        insercaoBancoDados.insertDisciplina(novaDisciplina);
    }
    
    private void removerDisciplina() throws SQLException {
        System.out .println("______________________________________________________________________");
        System.out.println("Codigo: ");
        String codigo_in = in.next();
        
        BancoDadosController remocaoBancoDados = new BancoDadosController();
        remocaoBancoDados.removeDisciplina(Integer.parseInt(codigo_in));
//        if(remocaoBancoDados.removeDisciplina(Integer.parseInt(codigo_in))){
//            System.out.println("Disciplina de codigo "+ codigo_in +" removida com sucesso.");
//        }else{
//            System.out.println("Disciplina de codigo "+ codigo_in +" não encontrada.");
//                    
//        }

        
    }
    
    private void pesquisarDisciplina() throws SQLException {
        System.out .println("______________________________________________________________________");
        System.out.println("Codigo: ");
        String codigo_in = in.next();
        
        BancoDadosController pesquisaBancoDados = new BancoDadosController();
        Disciplina disciplina = new Disciplina();
        disciplina = pesquisaBancoDados.pesquisaDisciplina(Integer.parseInt(codigo_in));
        
        System.out.println("CODIGO---------NOME---------PROFESSOR-------LIMITE");
        System.out.println(disciplina.getCodigo()+ " -   " + disciplina.getNome() + "   - " + disciplina.getProfessor()
                    + " - " + disciplina.getLimiteAlunos());
    }
    
    
    
}
