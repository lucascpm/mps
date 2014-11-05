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
public class Professor extends Usuario{
    
    private int codigo;
    private final Calendar dataAdmissao;
    
    public Professor(int codigo, Calendar dataAdmissao, String nome, String endereceo, String telefone, String sexo, String email, String senha, String login) {
        super(nome, endereceo, telefone, sexo, email, senha, login);
        this.codigo = codigo;
        this.dataAdmissao = dataAdmissao;
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
    
}
