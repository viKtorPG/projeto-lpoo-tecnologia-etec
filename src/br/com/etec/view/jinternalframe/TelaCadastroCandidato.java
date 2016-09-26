/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etec.view.jinternalframe;

import br.com.etec.dao.CandidatoDao;
import br.com.etec.model.Candidato;
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
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Aluno
 */
public class TelaCadastroCandidato extends JInternalFrame {

    public TelaCadastroCandidato() throws ParseException {
        iniciandoCompomentes();
    }

    private void iniciandoCompomentes() throws ParseException{
        setTitle("Candidato");
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
        lblPartido.setBounds(200, 210, 80, 25);
        lblPartido.setForeground(Color.black);

        jcPartido = new JComboBox<>();
        jcPartido.setModel(new javax.swing.DefaultComboBoxModel<>(PartidosNumeros.partidos()));
        jcPartido.setBounds(255, 210, 285, 25);
        jcPartido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                lblValorNumero.setText(PartidosNumeros.numero(jcPartido.getSelectedItem().toString().substring(jcPartido.getSelectedItem().toString().indexOf("-") + 2)));
            }
        });
        
        // Número
        lblNumero = new JLabel("*Número");
        lblNumero.setBounds(460, 180, 80, 25);
        lblNumero.setForeground(Color.black);

        MaskFormatter mask = new MaskFormatter();
        mask.setMask("###");
        mask.setPlaceholderCharacter('_');
        
        lblValorNumero = new JLabel(PartidosNumeros.numero(jcPartido.getSelectedItem().toString().substring(jcPartido.getSelectedItem().toString().indexOf("-") + 2)));
        lblValorNumero.setBounds(515, 180, 80, 25);
        lblValorNumero.setForeground(Color.black);

        txtNumero = new JFormattedTextField(mask);
        txtNumero.setBounds(530, 180, 30, 25);
        txtNumero.setVisible(false);


        //Cargo
        lblCargo = new JLabel("*Cargo");
        lblCargo.setBounds(200, 180, 80, 25);
        lblCargo.setForeground(Color.black);

        jcCargo = new JComboBox<>();
        jcCargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"PREFEITO", "VEREADOR"}));
        jcCargo.setBounds(255, 180, 200, 25);
        jcCargo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (jcCargo.getSelectedIndex() == 0) {
                    txtNumero.setVisible(false);
                } else {
                    txtNumero.setVisible(true);
                }
            }
        });

        //Imagem
        lblFoto = new JLabel(" Foto");
        lblFoto.setBounds(200, 240, 80, 25);
        lblFoto.setForeground(Color.black);

        txtFoto = new JTextField(10);
        txtFoto.setBounds(255, 240, 200, 25);
        txtFoto.setEnabled(false);

        lblImagem = new JLabel();
        lblImagem.setBounds(550, 240, 200, 200);

        btnEnviar = new JButton("Enviar");
        btnEnviar.setBounds(460, 240, 80, 25);
        // Buttons
        ImageIcon imgAdicionar = new ImageIcon(getClass().getResource("/br/com/etec/imgs/add.png"));
        btnAdicionar = new JButton(imgAdicionar);
        btnAdicionar.setToolTipText("Adicionar");
        btnAdicionar.setBounds(260, 300, 60, 60);
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Candidato addCandidato = new Candidato();
                if ((txtNome.getText().isEmpty() || txtNumero.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Todos os campos (*) obrigatórios");
                } else if ((txtNumero.getText().length() < 3) && (jcCargo.getSelectedItem().toString().equalsIgnoreCase("Vereador"))) {
                    JOptionPane.showMessageDialog(null, "Faltam números para vereador");
                } else {
                    try {
                        addCandidato.setId(1);
                        addCandidato.setNome(txtNome.getText());

                        if (txtNumero.getText().isEmpty()) {
                            if (jcCargo.getSelectedItem().toString().equalsIgnoreCase("vereador")) {
                                JOptionPane.showMessageDialog(null, "Preencha o número corretamente");
                            }
                        } else if (jcCargo.getSelectedItem().toString().equalsIgnoreCase("vereador")) {
                            addCandidato.setNumero(Integer.parseInt(lblValorNumero.getText().concat(txtNumero.getText())));
                        } else if (jcCargo.getSelectedItem().toString().equalsIgnoreCase("prefeito")) {

                            addCandidato.setNumero(Integer.parseInt(lblValorNumero.getText()));
                        }
                        addCandidato.setPartido(jcPartido.getSelectedItem().toString());
                        addCandidato.setCargo(jcCargo.getSelectedItem().toString());
                        if (imagem != null) {
                            addCandidato.setImagem(ManipularImagem.getImgBytes(imagem));
                        }
                        new CandidatoDao().insert(addCandidato);
                        clearCampos();
                        desabilita();
                        JOptionPane.showMessageDialog(null, "Candidato cadastrado com sucesso!");

                    } catch (HeadlessException ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao cadastrar candidato" + ex.getMessage());
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
                Candidato addCandidato = new Candidato();
                if ((txtNome.getText().isEmpty() || txtNumero.getText().isEmpty() || jcPartido.getSelectedItem().toString().toLowerCase().equals("sigla do Partido"))) {
                    JOptionPane.showMessageDialog(null, "Todos os campos (*) obrigatórios");
                } else {
                    addCandidato.setId(Integer.parseInt(txtId.getText()));
                    addCandidato.setNome(txtNome.getText());

                    if (txtNumero.getText().isEmpty()) {
                        if (jcCargo.getSelectedItem().toString().equalsIgnoreCase("vereador")) {
                            JOptionPane.showMessageDialog(null, "Preencha o número corretamente");
                        }
                    } else if (jcCargo.getSelectedItem().toString().equalsIgnoreCase("vereador")) {
                        addCandidato.setNumero(Integer.parseInt(lblValorNumero.getText().concat(txtNumero.getText())));
                    } else if (jcCargo.getSelectedItem().toString().equalsIgnoreCase("prefeito")) {

                        addCandidato.setNumero(Integer.parseInt(lblValorNumero.getText()));
                    }
                    addCandidato.setPartido(jcPartido.getSelectedItem().toString());
                    addCandidato.setCargo(jcCargo.getSelectedItem().toString());
                    if (imagem != null) {
                        addCandidato.setImagem(ManipularImagem.getImgBytes(imagem));
                    }

                    try {
                        new CandidatoDao().update(addCandidato);
                        clearCampos();
                        desabilita();
                        JOptionPane.showMessageDialog(null, "Candidato atualizado com sucesso!");

                    } catch (HeadlessException ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao atualizar candidado " + ex.getMessage());
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
                Candidato excCand = new Candidato();

                try {
                    numero = Integer.parseInt(JOptionPane.showInputDialog("Número do Candidato"));
                    excCand.setNumero(numero);
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Apenas números");
                }

                try {
                    new CandidatoDao().delete(excCand);
                    desabilita();
                    clearCampos();
                    JOptionPane.showMessageDialog(null, "Candidato excluído com sucesso!");
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
                        numero = Integer.parseInt(JOptionPane.showInputDialog("Número do Candidato"));
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(null, "Apenas números");
                    }
                    candidato = new CandidatoDao().findById(numero);

                    txtId.setText(String.valueOf(candidato.getId()));
                    txtNome.setText(candidato.getNome());
                    jcCargo.setSelectedItem(candidato.getCargo());
                    jcPartido.setSelectedItem(candidato.getPartido());
                    txtNumero.setValue(String.valueOf(candidato.getNumero()).substring(2));
                    ManipularImagem.exibirImagemLabel(candidato.getImagem(), lblImagem);

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

        //container.add(lblNumero);

        //container.add(txtNumero);

        container.add(lblPartido);

        container.add(lblFoto);

        container.add(txtFoto);

        container.add(btnEnviar);

        container.add(lblId);

        container.add(txtId);

        //container.add(lblValorNumero);

        container.add(btnAdicionar);

        container.add(btnAtualizar);

        container.add(btnExcluir);

        container.add(btnPesquisar);

        container.add(lblCargo);

        container.add(jcCargo);

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
        txtNumero.setValue(null);
        lblImagem.setIcon(null);
        jcPartido.setSelectedIndex(0);
        jcCargo.setSelectedIndex(0);
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
    private JFormattedTextField txtNumero;
    private JLabel lblPartido;
    private JLabel lblFoto;
    BufferedImage imagem;
    private JButton btnEnviar;
    private JLabel lblId;
    private JTextField txtId;
    public static File file;
    private JLabel lblCargo;
    private JComboBox<String> jcCargo;
    private JLabel lblImagem;
    private JLabel lblValorNumero;
    private Candidato candidato;
    private JTextField txtFoto;
    Connection connection;

}
