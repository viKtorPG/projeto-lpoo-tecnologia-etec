/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etec.view.jinternalframe;

import java.awt.Color;
import java.awt.Container;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author jose
 */
public class TelaCadastroUsuario extends JInternalFrame{
    
    public TelaCadastroUsuario(){
        iniciandoCompomentes();
    }
    
    private JComboBox<String> jcPerfil;
    
    private void iniciandoCompomentes(){
        setTitle("Usuários");
        setSize(800, 500);
        
        //Panel
        Container container = getContentPane();
        container.setLayout(null);
        
        JLabel lblCamposObri = new JLabel("(*)Campos Obrigatórios");
        lblCamposObri.setBounds(600, 20, 200, 20);
        lblCamposObri.setForeground(Color.red);
        
        //Id
        JLabel lblId = new JLabel("*Id");
        lblId.setBounds(170, 80, 80, 25);
        JTextField txtId = new JTextField();
        txtId.setBounds(200, 80, 100, 25);
        txtId.setEditable(false);
        
        //Nome
        JLabel lblNome = new JLabel("*Nome");
        lblNome.setBounds(150, 110, 80, 25);
        JTextField txtNome = new JTextField();
        txtNome.setBounds(200, 110, 200, 25);
   
        //Nome
        JLabel lblLogin = new JLabel("*Login");
        lblLogin.setBounds(150, 140, 80, 25);
        JTextField txtLogin = new JTextField();
        txtLogin.setBounds(200, 140, 200, 25);
        
        // Perfil
        JLabel lblPerfil = new JLabel("*Perfil");
        lblPerfil.setBounds(450, 140, 80, 25);
        String perfil[] = {"Admin", "User"};
        jcPerfil = new JComboBox<>(perfil);
        jcPerfil.setBounds(500, 140, 200, 25);
        
        //Senha
        JLabel lblSenha = new JLabel("*Senha");
        lblSenha.setBounds(150, 170, 80, 25);
        JTextField txtSenha = new JPasswordField();
        txtSenha.setBounds(200, 170, 200, 25);
        
        //Senha
        JLabel lblSenhaConf = new JLabel("*Repita a senha");
        lblSenhaConf.setBounds(100, 200, 100, 25);
        JTextField txtSenhaCof = new JPasswordField();
        txtSenhaCof.setBounds(200, 200, 200, 25);
        
        // Buttons
        ImageIcon imgAdicionar = new ImageIcon(getClass().getResource("/br/com/etec/imgs/add.png"));
        JButton btnAdicionar = new JButton(imgAdicionar);
        btnAdicionar.setToolTipText("Adicionar");
        btnAdicionar.setBounds(260, 300, 60, 60);
        
        ImageIcon imgAtualizar = new ImageIcon(getClass().getResource("/br/com/etec/imgs/update.png"));
        JButton btnAtualizar = new JButton(imgAtualizar);
        btnAtualizar.setToolTipText("Atualizar");
        btnAtualizar.setBounds(330, 300, 60, 60);
        
        ImageIcon imgExcluir = new ImageIcon(getClass().getResource("/br/com/etec/imgs/delete.png"));
        JButton btnExcluir = new JButton(imgExcluir);
        btnExcluir.setToolTipText("Excluir");
        btnExcluir.setBounds(400, 300, 60, 60);
        
        ImageIcon imgPesquisar = new ImageIcon(getClass().getResource("/br/com/etec/imgs/search.png"));
        JButton btnPesquisar = new JButton(imgPesquisar);
        btnPesquisar.setToolTipText("Pesquisar");
        btnPesquisar.setBounds(470, 300, 60, 60);
        
        
        //ADD
        container.add(lblCamposObri);
        
        container.add(lblId);
        container.add(txtId);
        
        container.add(lblNome);
        container.add(txtNome);
        
        container.add(lblLogin);
        container.add(txtLogin);
        
        container.add(lblPerfil);
        container.add(jcPerfil);
        
        container.add(lblSenha);
        container.add(txtSenha);
        
        container.add(lblSenhaConf);
        container.add(txtSenhaCof);
        
        container.add(btnAdicionar);
        container.add(btnAtualizar);
        container.add(btnExcluir);
        container.add(btnPesquisar);
        
        setClosable(true);
        setIconifiable(true);
        setLocation(100, 50);
        
    }
}
