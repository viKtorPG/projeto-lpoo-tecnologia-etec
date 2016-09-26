/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etec.view.jinternalframe;

import br.com.etec.dao.ViceDao;
import br.com.etec.model.Vice;
import br.com.etec.utils.ManipularImagem;
import br.com.etec.utils.PartidosNumeros;
import java.awt.Color;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Aluno
 */
public class TelaCadastroVice extends JInternalFrame {

    public TelaCadastroVice() throws ParseException {
        iniciandoCompomentes();
    }

    private void iniciandoCompomentes() throws ParseException {
        setTitle("Vice");
        setSize(800, 500);

        //Panel
        Container container = getContentPane();
        container.setLayout(null);

        // Obrigatorio
        JLabel lblCamposObri = new JLabel("(*)Campos Obrigatórios");
        lblCamposObri.setBounds(600, 20, 200, 20);
        lblCamposObri.setForeground(Color.red);
        //Id
        lblId = new JLabel("*ID");
        lblId.setBounds(200, 120, 80, 25);
        lblId.setForeground(Color.black);

        txtId = new JTextField();
        txtId.setBounds(255, 120, 100, 25);
        txtId.setEditable(false);

        // Nome do Candidato
        lblNome = new JLabel("*Nome");
        lblNome.setBounds(200, 150, 200, 25);
        lblNome.setForeground(Color.black);

        txtNome = new JTextField(10);
        txtNome.setBounds(255, 150, 200, 25);

        // Partido
        lblPartido = new JLabel("*Partido");
        lblPartido.setBounds(200, 180, 80, 25);
        lblPartido.setForeground(Color.black);

        jcPartido = new JComboBox<>();
        jcPartido.setModel(new javax.swing.DefaultComboBoxModel<>(PartidosNumeros.partidos()));
        jcPartido.setBounds(255, 180, 285, 25);
        jcPartido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                lblValorNumero.setText(PartidosNumeros.numero(jcPartido.getSelectedItem().toString().substring(jcPartido.getSelectedItem().toString().indexOf("-") + 2)));
            }
        });

        // Número
        lblNumero = new JLabel("*Número");
        lblNumero.setBounds(550, 180, 80, 25);
        lblNumero.setForeground(Color.black);

        lblValorNumero = new JLabel(PartidosNumeros.numero(jcPartido.getSelectedItem().toString().substring(jcPartido.getSelectedItem().toString().indexOf("-") + 2)));
        lblValorNumero.setBounds(605, 180, 80, 25);
        lblValorNumero.setForeground(Color.black);

        //Imagem
        lblFoto = new JLabel(" Foto");
        lblFoto.setBounds(200, 210, 80, 25);
        lblFoto.setForeground(Color.black);

        txtFoto = new JTextField(10);
        txtFoto.setBounds(255, 210, 200, 25);
        txtFoto.setEnabled(false);

        lblImagem = new JLabel();
        lblImagem.setBounds(550, 210, 200, 200);

        btnEnviar = new JButton("Enviar");
        btnEnviar.setBounds(460, 210, 80, 25);
        // Buttons
        ImageIcon imgAdicionar = new ImageIcon(getClass().getResource("/br/com/etec/imgs/add.png"));
        btnAdicionar = new JButton(imgAdicionar);
        btnAdicionar.setToolTipText("Adicionar");
        btnAdicionar.setBounds(260, 300, 60, 60);
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vice addVice = new Vice();
                if ((txtNome.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Todos os campos (*) obrigatórios");
                } else {
                    addVice.setId(1);
                    addVice.setNome(txtNome.getText());
                    addVice.setPartido(jcPartido.getSelectedItem().toString());
                    addVice.setNumero(Integer.valueOf(lblValorNumero.getText()));

                    if (imagem != null) {
                        addVice.setImagem(ManipularImagem.getImgBytes(imagem));
                    }

                    try {
                        new ViceDao().insert(addVice);
                        clearCampos();
                        desabilita();
                        JOptionPane.showMessageDialog(null, "Vice Candidato cadastrado com sucesso!");

                    } catch (HeadlessException ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao cadastrar vice candidato" + ex.getMessage());
                    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(TelaCadastroVice.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        );

        ImageIcon imgAtualizar = new ImageIcon(getClass().getResource("/br/com/etec/imgs/update.png"));
        btnAtualizar = new JButton(imgAtualizar);

        btnAtualizar.setToolTipText(
                "Atualizar");
        btnAtualizar.setBounds(
                330, 300, 60, 60);
        btnAtualizar.setEnabled(
                false);
        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                Vice addVice = new Vice();
                if ((txtNome.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Todos os campos (*) obrigatórios");
                } else {
                    addVice.setId(Integer.parseInt(txtId.getText()));
                    addVice.setNome(txtNome.getText());
                    addVice.setPartido(jcPartido.getSelectedItem().toString());
                    addVice.setNumero(Integer.valueOf(lblValorNumero.getText()));

                    if (imagem != null) {
                        addVice.setImagem(ManipularImagem.getImgBytes(imagem));
                    }

                    try {
                        new ViceDao().update(addVice);
                        clearCampos();
                        desabilita();
                        JOptionPane.showMessageDialog(null, "Vice atualizado com sucesso!");

                    } catch (HeadlessException ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao atualizar vice " + ex.getMessage());
                    } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
                        Logger.getLogger(TelaCadastroVice.class.getName()).log(Level.SEVERE, null, ex);
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
        btnExcluir.setEnabled(
                false);
        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {

                int numero;
                Vice excVice = new Vice();

                try {
                    numero = Integer.parseInt(JOptionPane.showInputDialog("Número do Partido"));
                    excVice.setNumero(numero);
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Apenas números");
                }

                try {
                    new ViceDao().delete(excVice);
                    desabilita();
                    clearCampos();
                    JOptionPane.showMessageDialog(null, "Candidato excluído com sucesso!");
                } catch (IllegalAccessException | ClassNotFoundException | SQLException | InstantiationException ex) {
                    Logger.getLogger(TelaCadastroVice.class.getName()).log(Level.SEVERE, null, ex);
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
        btnPesquisar.setToolTipText(
                "Pesquisar");
        btnPesquisar.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                try {
                    int numero = 0;
                    try {
                        numero = Integer.parseInt(JOptionPane.showInputDialog("Número do Partido"));
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(null, "Apenas números");
                    }
                    vice = new ViceDao().findById(numero);

                    txtId.setText(String.valueOf(vice.getId()));
                    txtNome.setText(vice.getNome());
                    jcPartido.setSelectedItem(vice.getPartido());
                    lblValorNumero.setText("" + vice.getNumero());
                    ManipularImagem.exibirImagemLabel(vice.getImagem(), lblImagem);

                    habilita();

                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException | HeadlessException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao pesquisar" + ex.getMessage());
                }
            }
        }
        );

        btnEnviar.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae
            ) {
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fc.setAcceptAllFileFilterUsed(false);
                fc.addChoosableFileFilter(new br.com.etec.components.ImageFilter());
                int res = fc.showOpenDialog(null);

                if (res == JFileChooser.APPROVE_OPTION) {
                    File arquivo = fc.getSelectedFile();

                    try {
                        imagem = ManipularImagem.setImagemDimensao(arquivo.getAbsolutePath(), 200, 200);

                        lblImagem.setIcon(new ImageIcon(imagem));

                    } catch (Exception ex) {
                        // System.out.println(ex.printStackTrace().toString());
                    }

                    txtFoto.setText(arquivo.getPath());
                } else {
                    JOptionPane.showMessageDialog(null, "Voce nao selecionou nenhum arquivo.");
                }
            }
        }
        );

        //Add
        container.add(lblCamposObri);

        container.add(lblNome);

        container.add(txtNome);

        container.add(jcPartido);

        container.add(lblNumero);

        container.add(lblPartido);

        container.add(lblFoto);

        container.add(txtFoto);

        container.add(btnEnviar);

        container.add(lblId);

        container.add(txtId);

        container.add(lblValorNumero);

        container.add(btnAdicionar);

        container.add(btnAtualizar);

        container.add(btnExcluir);

        container.add(btnPesquisar);

        container.add(lblImagem);

        setClosable(
                true);
        setIconifiable(
                true);
        setLocation(
                100, 50);
    }

    public void clearCampos() {
        txtId.setText(null);
        txtNome.setText(null);
        txtFoto.setText(null);
        lblImagem.setIcon(null);
        jcPartido.setSelectedIndex(0);
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

    private JComboBox<String> jcPartido;
    private JButton btnAdicionar;
    private JButton btnAtualizar;
    private JButton btnExcluir;
    private JButton btnPesquisar;
    private JLabel lblNome;
    private JTextField txtNome;
    private JLabel lblNumero;
    private JLabel lblPartido;
    private JLabel lblFoto;
    BufferedImage imagem;
    private JButton btnEnviar;
    private JLabel lblId;
    private JTextField txtId;
    private JLabel lblImagem;
    private JLabel lblValorNumero;
    private Vice vice;
    private JTextField txtFoto;
    Connection connection;

}
