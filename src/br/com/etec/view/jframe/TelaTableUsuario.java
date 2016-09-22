/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etec.view.jframe;

import br.com.etec.components.UsuarioJTable;
import br.com.etec.dao.UsuarioDao;
import br.com.etec.view.jinternalframe.TelaCadastroUsuario;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author jose
 */
public class TelaTableUsuario {

    public void execute(String title) {
        // Criação da Tela
        final JFrame jf = new JFrame(title);
        jf.setSize(425, 400);

        // Panel que será responsavel por add todos os elementos
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblId = new JLabel("Id");
        lblId.setBounds(55, 10, 50, 20);
        
        JLabel lblNome = new JLabel("Nome");
        lblNome.setBounds(145, 10, 50, 20);
        
        JLabel lblLogin = new JLabel("Login");
        lblLogin.setBounds(245, 10, 50, 20);
                
        JLabel lblSenha = new JLabel("Perfil");
        lblSenha.setBounds(340, 10, 50, 20);
        
        UsuarioJTable jTable = new UsuarioJTable();
        jTable.setBounds(10, 35, 400, 310);
        jTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        carregarTabela(jTable);

        // ADD
        panel.add(jTable);
        panel.add(lblId);
        panel.add(lblNome);
        panel.add(lblLogin);
        panel.add(lblSenha);

        // Add elementos ao JFrame
        jf.add(panel);
        jf.setVisible(true);
        jf.setResizable(false);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void carregarTabela(UsuarioJTable tabela) {
        try {
            tabela.load(new UsuarioDao().all());
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(null, "Houve um erro ao carregar dados do bando de dados.");
        }

    }
}
