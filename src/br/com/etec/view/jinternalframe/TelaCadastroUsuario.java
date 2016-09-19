/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etec.view.jinternalframe;

import br.com.etec.dal.UsuarioDao;
import br.com.etec.model.Usuario;
import br.com.etec.view.jframe.TelaTableUsuario;
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
        jcPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"admin", "user"}));
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
                addUser.setPerfil(jcPerfil.getSelectedItem().toString().toLowerCase());
                if ((txtNome.getText().isEmpty() || txtLogin.getText().isEmpty() || txtSenha.getText().isEmpty() || txtSenhaCof.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Todos os campos (*) obrigatórios");
                } else if (!(txtSenha.getText().equals(txtSenhaCof.getText()))) {
                    JOptionPane.showMessageDialog(null, "Senha não conferem");
                } else {
                    try {
                        new UsuarioDao().insert(addUser);
                        JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
                        clearCampos();
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
        btnAtualizar.setEnabled(false);
        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario addUser = new Usuario();
                if ((txtNome.getText().isEmpty() || txtLogin.getText().isEmpty() || txtSenha.getText().isEmpty() || txtSenhaCof.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Todos os campos (*) obrigatórios");
                } else if (!(txtSenha.getText().equals(txtSenhaCof.getText()))) {
                    JOptionPane.showMessageDialog(null, "Senha não conferem");
                } else {
                    try {
                        addUser.setId(Integer.parseInt(txtId.getText()));
                        addUser.setNome(txtNome.getText());
                        addUser.setLogin(txtLogin.getText());
                        addUser.setSenha(txtSenha.getText());
                        addUser.setPerfil(jcPerfil.getSelectedItem().toString().toLowerCase());
                        new UsuarioDao().update(addUser);
                        JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!");
                        clearCampos();
                        desabilita();

                    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException | HeadlessException ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário" + ex.getMessage());
                    }
                }
            }
        }
        );

        ImageIcon imgExcluir = new ImageIcon(getClass().getResource("/br/com/etec/imgs/delete.png"));
        btnExcluir = new JButton(imgExcluir);

        btnExcluir.setToolTipText(
                "Excluir");
        btnExcluir.setBounds(
                400, 300, 60, 60);
        btnExcluir.setEnabled(false);
        btnExcluir.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                Usuario removeUser = new Usuario();
                removeUser.setId(Integer.parseInt(txtId.getText()));
                try {
                    int confir = JOptionPane.showConfirmDialog(null, "Deseja excluir", "Atenção", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (confir == JOptionPane.YES_OPTION) {
                        new UsuarioDao().delete(removeUser);
                        JOptionPane.showMessageDialog(null, "Usuário deletado com sucesso!");
                        clearCampos();
                        desabilita();
                    }
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException | HeadlessException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário");
                }
            }
        }
        );

        ImageIcon imgPesquisar = new ImageIcon(getClass().getResource("/br/com/etec/imgs/search.png"));
        btnPesquisar = new JButton(imgPesquisar);

        btnPesquisar.setToolTipText(
                "Pesquisar");
        btnPesquisar.setBounds(
                470, 300, 60, 60);
        btnPesquisar.setToolTipText("Pesquisar");
        btnPesquisar.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                try {
                    int id = 0;
                    try {
                        id = Integer.parseInt(JOptionPane.showInputDialog(""));
                        user = new UsuarioDao().findById(id);
                        txtId.setText(String.valueOf(user.getId()));
                        txtNome.setText(user.getNome());
                        txtLogin.setText(user.getLogin());
                        txtSenha.setText(user.getSenha());
                        jcPerfil.setSelectedItem(user.getPerfil());
                        System.err.println(user.getPerfil());
                        habilita();
                    } catch (NumberFormatException numberFormatException) {
                        JOptionPane.showMessageDialog(null, "Apenas números");
                    }
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException | HeadlessException | NullPointerException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário" + ex.getMessage());
                }
            }
        }
        );

        ImageIcon imgLista = new ImageIcon(getClass().getResource("/br/com/etec/imgs/list.png"));
        btnLista = new JButton(imgLista);

        btnLista.setBounds(
                100, 350, 60, 60);
        btnLista.setToolTipText("Lista de usuários cadastrados");
        btnLista.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                new TelaTableUsuario().execute("Lista");
            }
        }
        );

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

        container.add(btnLista);

        setClosable(
                true);
        setIconifiable(
                true);
        setLocation(
                100, 50);

    }

    public void clearCampos() {
        txtId.setText("");
        txtNome.setText("");
        txtLogin.setText("");
        txtSenha.setText("");
        txtSenhaCof.setText("");
    }

    // Habilita os botões de Excluir e atualizar
    public void habilita() {
        btnAtualizar.setEnabled(true);
        btnExcluir.setEnabled(true);
    }

    // Desabilita os botões de Excluir e atualizar
    public void desabilita() {
        btnAtualizar.setEnabled(false);
        btnExcluir.setEnabled(false);
    }

    // Atributos da Classe
    private JLabel lblCamposObri;
    private JLabel lblId;
    private JLabel lblNome;
    private JLabel lblLogin;
    private JLabel lblPerfil;
    private JLabel lblSenha;
    private JLabel lblSenhaConf;
    private JTextField txtId;
    private JTextField txtNome;
    private JTextField txtLogin;
    private JTextField txtSenha;
    private JTextField txtSenhaCof;
    private JButton btnAdicionar;
    private JButton btnAtualizar;
    private JButton btnExcluir;
    private JButton btnPesquisar;
    private JComboBox<String> jcPerfil;
    private JButton btnLista;
    private Usuario user;
}
