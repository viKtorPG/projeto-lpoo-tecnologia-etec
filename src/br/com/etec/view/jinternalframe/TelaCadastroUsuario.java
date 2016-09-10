/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etec.view.jinternalframe;

import br.com.etec.dal.UsuarioDao;
import br.com.etec.model.Usuario;
import java.awt.Color;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author jose
 */
public class TelaCadastroUsuario extends JInternalFrame {

    public TelaCadastroUsuario() {
        iniciandoCompomentes();
       
    }


    private void iniciandoCompomentes() {
        setTitle("Usuários");
        setSize(800, 500);

        //Panel
        Container container = getContentPane();
        container.setLayout(null);

        lblCamposObri = new JLabel("(*)Campos Obrigatórios");
        lblCamposObri.setBounds(600, 20, 200, 20);
        lblCamposObri.setForeground(Color.red);

        //Id
        lblId = new JLabel("*Id");
        lblId.setBounds(170, 80, 80, 25);
        txtId = new JTextField();
        txtId.setBounds(200, 80, 100, 25);
        txtId.setEditable(false);

        //Nome
        lblNome = new JLabel("*Nome");
        lblNome.setBounds(150, 110, 80, 25);
        txtNome = new JTextField();
        txtNome.setBounds(200, 110, 200, 25);

        //Login
        lblLogin = new JLabel("*Login");
        lblLogin.setBounds(150, 140, 80, 25);
        txtLogin = new JTextField();
        txtLogin.setBounds(200, 140, 200, 25);

        // Perfil
        lblPerfil = new JLabel("*Perfil");
        lblPerfil.setBounds(450, 140, 80, 25);
        jcPerfil = new JComboBox<>();
        jcPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "User" }));
        jcPerfil.setBounds(500, 140, 200, 25);

        //Senha
        lblSenha = new JLabel("*Senha");
        lblSenha.setBounds(150, 170, 80, 25);
        txtSenha = new JPasswordField();
        txtSenha.setBounds(200, 170, 200, 25);

        //Senha
        lblSenhaConf = new JLabel("*Repita a senha");
        lblSenhaConf.setBounds(100, 200, 100, 25);
        txtSenhaCof = new JPasswordField();
        txtSenhaCof.setBounds(200, 200, 200, 25);

        // Buttons
        ImageIcon imgAdicionar = new ImageIcon(getClass().getResource("/br/com/etec/imgs/add.png"));
        btnAdicionar = new JButton(imgAdicionar);
        btnAdicionar.setToolTipText("Adicionar");
        btnAdicionar.setBounds(260, 300, 60, 60);
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario addUser = new Usuario();
                addUser.setNome(txtNome.getText());
                addUser.setLogin(txtLogin.getText());
                addUser.setSenha(txtSenha.getText());
                addUser.setPerfil(jcPerfil.getSelectedItem().toString());
                if (txtNome.getText().isEmpty() || txtLogin.getText().isEmpty() || txtSenha.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos os campos (*) obrigatórios");
                } else {
                    try {
                        new UsuarioDao().insert(addUser);
                        JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
                    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException | HeadlessException ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário");
                    }
                }
            }
        });

        ImageIcon imgAtualizar = new ImageIcon(getClass().getResource("/br/com/etec/imgs/update.png"));
        btnAtualizar = new JButton(imgAtualizar);
        btnAtualizar.setToolTipText("Atualizar");
        btnAtualizar.setBounds(330, 300, 60, 60);
        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        ImageIcon imgExcluir = new ImageIcon(getClass().getResource("/br/com/etec/imgs/delete.png"));
        btnExcluir = new JButton(imgExcluir);
        btnExcluir.setToolTipText("Excluir");
        btnExcluir.setBounds(400, 300, 60, 60);
        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        ImageIcon imgPesquisar = new ImageIcon(getClass().getResource("/br/com/etec/imgs/search.png"));
        btnPesquisar = new JButton(imgPesquisar);
        btnPesquisar.setToolTipText("Pesquisar");
        btnPesquisar.setBounds(470, 300, 60, 60);
        btnPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
            }
        });

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
    
    // Atributos da Classe
    private JLabel lblCamposObri;
    private JLabel lblId;
    private JLabel lblNome;
    private JLabel lblLogin;
    private JLabel lblPerfil;
    private JLabel lblSenha;
    private JLabel lblSenhaConf;
    public static JTextField txtId;
    public static JTextField txtNome;
    public static JTextField txtLogin;
    public static JTextField txtSenha;
    private JTextField txtSenhaCof;
    private JButton btnAdicionar;
    private JButton btnAtualizar;
    private JButton btnExcluir;
    private JButton btnPesquisar;
    public static JComboBox<String> jcPerfil;
    private JButton btnLista;
}
