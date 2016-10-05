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
        super.setModel(this.model);
    }
    
    public void load(List<Usuario> list){
        this.model.load(list);
    }
    
    public Usuario getPessoaSelecionada(){
        int index = getSelectedRow();
        return this.model.getPessoaAt(index);
    }
    
}
