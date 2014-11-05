/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.util.Calendar;

/**
 *
 * @author Lucas
 */
public class Administrador extends Usuario{
    
    private int codigo;
    private final Calendar dataAdmissao;
    private final Calendar dataDemissao;

    public Administrador(String nome, String endereceo, String telefone, String sexo, String email, String senha, String login, Calendar dataAdmissao, Calendar dataDemissao) {
        super(nome, endereceo, telefone, sexo, email, senha, login);
        this.dataAdmissao = dataAdmissao;
        this.dataDemissao = dataDemissao;
    }

    
    //Métodos Exclusivos dos Administradores
    public boolean cadastrarAluno(){
        return true;
    }
    
    public boolean cadastrarProfessor(){
        return true;
    }
    
   public boolean cadastrarDisciplina(){
        return true;
    }
    
    public boolean cadastrarCurso(){
        return true;
    }
    
    
    
    
    
    
    
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Calendar getDataAdmissao() {
        return dataAdmissao;
    }

    public Calendar getDataDemissao() {
        return dataDemissao;
    }
    
}
