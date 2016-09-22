/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etec.view.jinternalframe;

import br.com.etec.dao.PartidoDao;
import br.com.etec.model.Partido;
import br.com.etec.utils.ManipularImagem;
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
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Victor
 */
public class TelaCadastroPartido extends JInternalFrame {

    private JTextField txtPartido;
    private JLabel lblSlogan;
    private JTextField txtSlogan;
    private JLabel lblDtCriacao;
    private JFormattedTextField txtDtCriacao;

    public TelaCadastroPartido() throws ParseException {
        iniciandoCompomentes();
    }

    private void iniciandoCompomentes() throws ParseException {
        setTitle("Partido");
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
        lblPartido = new JLabel("*Sigla");
        lblPartido.setBounds(460, 150, 80, 25);
        lblPartido.setForeground(Color.black);

        txtPartido = new JTextField();
        txtPartido.setBounds(505, 150, 60, 25);

        // Número
        lblNumero = new JLabel("*Número");
        lblNumero.setBounds(200, 180, 80, 25);
        lblNumero.setForeground(Color.black);

        txtNumero = new JFormattedTextField(new MaskFormatter("##"));
        txtNumero.setBounds(255, 180, 30, 25);

        //Cargo
        lblSlogan = new JLabel(" Slogan");
        lblSlogan.setBounds(200, 210, 80, 25);
        lblSlogan.setForeground(Color.black);

        txtSlogan = new JTextField();
        txtSlogan.setBounds(255, 210, 200, 25);

        //Imagem
        lblFoto = new JLabel(" Logo");
        lblFoto.setBounds(200, 240, 80, 25);
        lblFoto.setForeground(Color.black);

        txtFoto = new JTextField(10);
        txtFoto.setBounds(255, 240, 200, 25);
        txtFoto.setEnabled(false);

        lblImagem = new JLabel();
        lblImagem.setBounds(550, 240, 200, 200);

        btnEnviar = new JButton("Enviar");
        btnEnviar.setBounds(460, 240, 80, 25);

        //
        lblDtCriacao = new JLabel(" Data de Criação");
        lblDtCriacao.setBounds(160, 270, 120, 25);
        lblDtCriacao.setForeground(Color.black);

        MaskFormatter mask = new MaskFormatter();
        mask.setMask("##/##/####");
        mask.setPlaceholderCharacter('_');

        txtDtCriacao = new JFormattedTextField(mask);
        txtDtCriacao.setBounds(255, 270, 200, 25);
        // Buttons
        ImageIcon imgAdicionar = new ImageIcon(getClass().getResource("/br/com/etec/imgs/add.png"));
        btnAdicionar = new JButton(imgAdicionar);
        btnAdicionar.setToolTipText("Adicionar");
        btnAdicionar.setBounds(260, 300, 60, 60);
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Partido addPartido = new Partido();
                if ((txtNome.getText().isEmpty() || txtNumero.getText().isEmpty() || txtDtCriacao.getText().isEmpty() || txtPartido.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Todos os campos (*) obrigatórios");
                } else if ((txtNumero.getText().length() < 2) || (txtDtCriacao.getText().length() < 10)) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente");
                } else {
                    try {
                        addPartido.setId(1);
                        addPartido.setNome(txtNome.getText());
                        addPartido.setNumero(Integer.parseInt(txtNumero.getText()));
                        addPartido.setSigla(txtPartido.getText());
                        addPartido.setSlogan(txtSlogan.getText());
                        addPartido.setData(txtDtCriacao.getText());
                        if (imagem != null) {
                            addPartido.setLogo(ManipularImagem.getImgBytes(imagem));
                        }

                        new PartidoDao().insert(addPartido);
                        clearCampos();
                        desabilita();
                        JOptionPane.showMessageDialog(null, "Partido cadastrado com sucesso!");

                    } catch (HeadlessException ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao criar partido" + ex.getMessage());
                    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(TelaCadastroCandidato.class.getName()).log(Level.SEVERE, null, ex);
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
        btnAtualizar.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                Partido addPartido = new Partido();
                if ((txtNome.getText().isEmpty() || txtNumero.getText().isEmpty() || txtDtCriacao.getText().isEmpty() || txtPartido.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Todos os campos (*) obrigatórios");
                } else if ((txtNumero.getText().length() < 2) || (txtDtCriacao.getText().length() < 10)) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente");
                } else {
                    try {
                        addPartido.setId(1);
                        addPartido.setNome(txtNome.getText());
                        addPartido.setNumero(Integer.parseInt(txtNumero.getText()));
                        addPartido.setSigla(txtPartido.getText());
                        addPartido.setSlogan(txtSlogan.getText());
                        addPartido.setData(txtDtCriacao.getText());
                        if (imagem != null) {
                            addPartido.setLogo(ManipularImagem.getImgBytes(imagem));
                        }

                        new PartidoDao().update(addPartido);
                        clearCampos();
                        desabilita();
                        JOptionPane.showMessageDialog(null, "Partido atualizado com sucesso!");

                    } catch (HeadlessException ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao atualizar partido " + ex.getMessage());
                    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(TelaCadastroCandidato.class.getName()).log(Level.SEVERE, null, ex);
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
        btnExcluir.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {

                int numero;
                Partido excPart = new Partido();

                try {
                    numero = Integer.parseInt(JOptionPane.showInputDialog("Número do Partido"));
                    excPart.setNumero(numero);
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Apenas números");
                }

                try {
                    new PartidoDao().delete(excPart);
                    desabilita();
                    clearCampos();
                    JOptionPane.showMessageDialog(null, "Partido excluído com sucesso!");
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(TelaCadastroCandidato.class.getName()).log(Level.SEVERE, null, ex);
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
                    partido = new PartidoDao().findById(numero);

                    txtId.setText(String.valueOf(partido.getId()));
                    txtNome.setText(partido.getNome());
                    txtNumero.setText("" + partido.getNumero());
                    txtPartido.setText(partido.getSigla());
                    txtSlogan.setText(partido.getSlogan());
                    txtDtCriacao.setText(partido.getData());
                    ManipularImagem.exibirImagemLabel(partido.getLogo(), lblImagem);

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

        container.add(lblDtCriacao);

        container.add(txtDtCriacao);

        container.add(lblNome);

        container.add(txtNome);

        container.add(lblNumero);

        container.add(txtNumero);

        container.add(lblPartido);

        container.add(lblFoto);

        container.add(txtFoto);

        container.add(btnEnviar);

        container.add(lblId);

        container.add(txtId);

        container.add(btnAdicionar);

        container.add(btnAtualizar);

        container.add(btnExcluir);

        container.add(btnPesquisar);

        container.add(lblImagem);

        container.add(txtPartido);

        container.add(lblSlogan);

        container.add(txtSlogan);

        container.add(txtNumero);

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
        txtNumero.setValue(null);
        lblImagem.setIcon(null);
        txtPartido.setText(null);
        txtSlogan.setText(null);
        txtDtCriacao.setText(null);
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

    private JButton btnAdicionar;
    private JButton btnAtualizar;
    private JButton btnExcluir;
    private JButton btnPesquisar;
    private JLabel lblNome;
    private JTextField txtNome;
    private JLabel lblNumero;
    private JFormattedTextField txtNumero;
    private JLabel lblPartido;
    private JLabel lblFoto;
    BufferedImage imagem;
    private JButton btnEnviar;
    private JLabel lblId;
    private JTextField txtId;
    private JLabel lblImagem;
    private Partido partido;
    private JTextField txtFoto;
    Connection connection;

}
