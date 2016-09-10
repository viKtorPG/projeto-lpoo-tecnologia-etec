/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etec.components;

import br.com.etec.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jose
 */
public class UsuarioTableModel extends AbstractTableModel {

    private List<Usuario> usuarios = new ArrayList<>();
    private String[] columnNames = {"Id", "Nome", "Login", "Perfil"};
    private Class<?>[] columnTypes = {Integer.class, String.class, String.class, String.class};

    // Vai atualizar a list
    public void load(List<Usuario> usuarios) {
        this.usuarios = usuarios;
        fireTableDataChanged();
    }

    // Retorna a pessoa na posição index
    public Usuario getPessoaAt(int index) {
        if (this.usuarios.size() <= 0) {
            return null;
        }
        return this.usuarios.get(index);
    }

    @Override
    public int getRowCount() {
        return this.usuarios.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (this.usuarios.size() <= 0) {
            return null;
        }
        Usuario u = this.usuarios.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return u.getId();
            case 1:
                return u.getNome();
            case 2:
                return u.getLogin();
            case 3:
                return u.getPerfil();
        }

        return null;
    }

}
