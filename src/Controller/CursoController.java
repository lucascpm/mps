
package Controller;

import java.sql.SQLException; 
import java.util.ArrayList; 
import java.util.List; 
import java.util.Scanner; 
import Model.Curso; 
import Model.Disciplina; 

/**
 *
 * @author Lucas
 */
public class CursoController {
    private Scanner in = new Scanner(System.in);
    
    public void menuGeral() throws SQLException { 
        String resposta; 
        do {
            System.out .println("****************************Cursos 0.0.1****************************"); 
            System.out.println("1-Listar");
            System.out.println("2-Inserir");
            System.out.println("3-Remover");
            System.out.println("4-Pesquisar");
            System.out.println("5-Disciplinas do Curso");
            System.out.println(":"); 
            resposta = in.next(); 
            
            if (resposta.trim().equals("2")) 
                inserirCursos(); 
            else if (resposta.trim().equals("1")) 
                listarCursos();
            else if (resposta.trim().equals("3")) 
                removerCurso();
            else if (resposta.trim().equals("4")) 
                pesquisarCursos();
            else if (resposta.trim().equals("5")) 
                disciplinasDoCurso();
        } 
        
        while (!resposta.trim().equals("0"));
    }

    private void listarCursos() throws SQLException {
        List<Curso> listaCurso = new ArrayList<Curso>();
        BancoDadosController consultaBancoDados = new BancoDadosController();
        listaCurso = consultaBancoDados.selectCurso(null);
        System.out .println("______________________________________________________________________");
        System.out.println("CODIGO-----NOME");
        
        for (int i = 0; i < listaCurso.size(); i++)
            System.out.println(listaCurso.get(i).getCodigo()+ "  -  " + listaCurso.get(i).getNome());
        
        System.out .println("______________________________________________________________________");
    }
    
    private void inserirCursos() throws SQLException {
        String codigo_in;
        String nome;

        System.out .println("______________________________________________________________________");
        System.out.println("Codigo: ");
        codigo_in = in.next(); 
        System.out.println("Nome: ");
        nome = in.next();

        
        System.out .println("______________________________________________________________________");
        int codigo = Integer.parseInt(codigo_in);
        
        Curso novoCurso = new Curso(nome, codigo);
        BancoDadosController insercaoBancoDados = new BancoDadosController();
        insercaoBancoDados.insertCurso(novoCurso);
    }
    
    private void removerCurso() throws SQLException {
        System.out .println("______________________________________________________________________");
        System.out.println("Codigo: ");
        String codigo_in = in.next();
        
        BancoDadosController remocaoBancoDados = new BancoDadosController();
        remocaoBancoDados.removeDisciplina(Integer.parseInt(codigo_in));

        
    }
    
    private void pesquisarCursos() throws SQLException {
        System.out .println("______________________________________________________________________");
        System.out.println("Codigo: ");
        String codigo_in = in.next();
        
        BancoDadosController pesquisaBancoDados = new BancoDadosController();
        Curso curso = new Curso();
        curso = pesquisaBancoDados.pesquisaCurso(Integer.parseInt(codigo_in));
        
        System.out.println("CODIGO-----NOME");
        System.out.println(curso.getCodigo()+ " -   " + curso.getNome());
    }
    
    private void disciplinasDoCurso() throws SQLException {
        System.out .println("______________________________________________________________________");
        System.out.println("Codigo do Curso: ");
        String codigo = in.next();
        
        List<Disciplina> listaDisciplina = new ArrayList<Disciplina>();
        BancoDadosController consultaBancoDados = new BancoDadosController();
        
        listaDisciplina = consultaBancoDados.disciplinasDoCurso(Integer.parseInt(codigo));
        
        System.out .println("______________________________________________________________________");
        System.out.println("CODIGO--NOME--PROFESSOR--LIMITE--ALUNOS MATRICULADOS");
        
        for (int i = 0; i < listaDisciplina.size(); i++)
            System.out.println(listaDisciplina.get(i).getCodigo()+ " - " + listaDisciplina.get(i).getNome() + " - " + listaDisciplina.get(i).getDescricao());
        
        System.out .println("______________________________________________________________________");
    }
    
}
