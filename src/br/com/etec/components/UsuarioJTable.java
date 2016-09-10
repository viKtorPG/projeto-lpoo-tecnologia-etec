/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etec.components;

import br.com.etec.model.Usuario;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author jose
 */
public class UsuarioJTable extends JTable{

    private UsuarioTableModel model;
    
    public UsuarioJTable(){
        this.model = new UsuarioTableModel();
        setModel(this.model);
    }
    
    public void load(List<Usuario> list){
        this.model.load(list);
    }
    
    public Usuario getPessoaSelecionada(){
        int index = getSelectedRow();
        return this.model.getPessoaAt(index);
    }
    
}
