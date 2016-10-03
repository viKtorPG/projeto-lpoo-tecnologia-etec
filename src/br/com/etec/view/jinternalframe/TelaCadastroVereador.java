/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etec.view.jinternalframe;

import br.com.etec.dao.VereadorDao;
import br.com.etec.model.Vereador;
import br.com.etec.utils.Data;
import br.com.etec.utils.ManipularImagem;
import br.com.etec.utils.PartidosNumeros;
import com.toedter.calendar.JDateChooser;
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
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Aluno
 */
public class TelaCadastroVereador extends JInternalFrame {

    public TelaCadastroVereador() throws ParseException {
        iniciandoCompomentes();
    }

    private void iniciandoCompomentes() throws ParseException {
        setTitle("Candidato");
        setSize(800, 500);

        //Panel
        Container container = getContentPane();
        container.setLayout(null);

        // Obrigatorio
        JLabel lblCamposObri = new JLabel("(*)Campos Obrigatórios");
        lblCamposObri.setBounds(600, 20, 200, 20);
        lblCamposObri.setForeground(Color.red);

        /* <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< * >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */
        // Panel Prefeito
        JPanel panelPrefeito = new JPanel();
        panelPrefeito.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações do Vereador"));
        panelPrefeito.setLayout(null);
        panelPrefeito.setBounds(210, 50, 370, 290);

        // ID do Prefeito
        lblIdVereador = new JLabel("N° Pref.");
        lblIdVereador.setBounds(10, 40, 50, 25);
        lblIdVereador.setForeground(Color.black);

        txtIdVereador = new JTextField(10);
        txtIdVereador.setBounds(80, 40, 175, 25);

        // Nome do Prefeito
        lblNomeVereador = new JLabel("*Nome");
        lblNomeVereador.setBounds(10, 70, 50, 25);
        lblNomeVereador.setForeground(Color.black);

        txtNomeVereador = new JTextField(10);
        txtNomeVereador.setBounds(80, 70, 175, 25);

        //Data de Nasciment
        lblDatadeNascimentVereador = new JLabel("*Data de Nascimento");
        lblDatadeNascimentVereador.setBounds(10, 100, 120, 25);
        lblDatadeNascimentVereador.setForeground(Color.black);

        jdNascimentoVereador = new JDateChooser();
        jdNascimentoVereador.setBounds(135, 100, 120, 25);
        jdNascimentoVereador.setDate(new Date());
        jdNascimentoVereador.setDateFormatString("dd/MM/yyyy");

        //Imagem
        lblFotoVereador = new JLabel("*Foto");
        lblFotoVereador.setBounds(10, 130, 50, 25);
        lblFotoVereador.setForeground(Color.black);

        txtFotoVereador = new JTextField(10);
        txtFotoVereador.setBounds(80, 130, 100, 25);
        txtFotoVereador.setEnabled(false);

        ImageIcon lblImgPrefeito = new ImageIcon(getClass().getResource("/br/com/etec/imgs/logoUserBD.png"));
        lblImagemVereador = new JLabel(lblImgPrefeito);
        lblImagemVereador.setBounds(265, 40, 85, 105);

        btnEnviarVereador = new JButton("Enviar");
        btnEnviarVereador.setBounds(185, 130, 70, 25);

        // Partido
        lblPartidoVereador = new JLabel("*Partido");
        lblPartidoVereador.setBounds(10, 160, 50, 25);
        lblPartidoVereador.setForeground(Color.black);

        jcPartidoVereador = new JComboBox<>();
        jcPartidoVereador.setModel(new javax.swing.DefaultComboBoxModel<>(PartidosNumeros.partidos()));
        jcPartidoVereador.setBounds(80, 160, 275, 25);
        jcPartidoVereador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.err.println(jcPartidoVereador.getSelectedItem().toString().substring(jcPartidoVereador.getSelectedItem().toString().indexOf("-") + 2));
                txtNumeroPartido.setText(PartidosNumeros.numero(jcPartidoVereador.getSelectedItem().toString().substring(jcPartidoVereador.getSelectedItem().toString().indexOf("-") + 2)));
            }
        });

        // Número
        lblNumeroVereador = new JLabel("*Número");
        lblNumeroVereador.setBounds(10, 190, 50, 25);
        lblNumeroVereador.setForeground(Color.black);

        MaskFormatter mask = new MaskFormatter();
        mask.setMask("###");
        mask.setPlaceholderCharacter('_');

        txtNumeroPartido = new JTextField();
        txtNumeroPartido.setBounds(80, 190, 30, 25);
        txtNumeroPartido.setForeground(Color.black);
        txtNumeroPartido.setEditable(false);

        txtNumeroVereador = new JFormattedTextField(mask);
        txtNumeroVereador.setBounds(115, 190, 65, 25);
        txtNumeroVereador.setVisible(true);

        // Numero Prefeito
        lblNumeroPrefeito = new JLabel("*Nº Prefeito");
        lblNumeroPrefeito.setBounds(10, 220, 100, 25);
        lblNumeroPrefeito.setForeground(Color.black);

        jcNumeroPrefeito = new JComboBox<>();
        jcNumeroPrefeito.setModel(new javax.swing.DefaultComboBoxModel<>(PartidosNumeros.getInfoPrefeito()));
        jcNumeroPrefeito.setBounds(10, 250, 300, 25);
        jcNumeroPrefeito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //System.err.println(jcPartidoVereador.getSelectedItem().toString().substring(jcPartidoVereador.getSelectedItem().toString().indexOf("-") + 2));
                txtNumeroPrefeito.setText(PartidosNumeros.numero(jcNumeroPrefeito.getSelectedItem().toString().substring(jcNumeroPrefeito.getSelectedItem().toString().indexOf("-") + 2)));
            }
        });

        txtNumeroPrefeito = new JTextField();
        txtNumeroPrefeito.setBounds(315, 250, 40, 25);
        txtNumeroPrefeito.setForeground(Color.black);
        txtNumeroPrefeito.setEditable(false);

        //ADD ELEMENTOS PREFEITO
        panelPrefeito.add(lblIdVereador);
        panelPrefeito.add(txtIdVereador);

        panelPrefeito.add(txtNumeroVereador);
        panelPrefeito.add(lblNumeroVereador);

        panelPrefeito.add(lblNomeVereador);
        panelPrefeito.add(txtNomeVereador);

        panelPrefeito.add(jdNascimentoVereador);
        panelPrefeito.add(lblDatadeNascimentVereador);

        panelPrefeito.add(lblFotoVereador);
        panelPrefeito.add(txtFotoVereador);
        panelPrefeito.add(lblImagemVereador);

        panelPrefeito.add(lblPartidoVereador);
        panelPrefeito.add(jcPartidoVereador);
        panelPrefeito.add(txtNumeroPartido);

        panelPrefeito.add(lblNumeroPrefeito);
        panelPrefeito.add(jcNumeroPrefeito);
        panelPrefeito.add(txtNumeroPrefeito);

        panelPrefeito.add(btnEnviarVereador);

        // Buttons
        ImageIcon imgAdicionar = new ImageIcon(getClass().getResource("/br/com/etec/imgs/add.png"));
        btnAdicionar = new JButton(imgAdicionar);
        btnAdicionar.setToolTipText("Adicionar");
        btnAdicionar.setBounds(260, 350, 60, 60);
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vereador addPrefeito = new Vereador();
                if ((txtNomeVereador.getText().isEmpty() || txtFotoVereador.getText().isEmpty()) || txtNumeroVereador.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos os campos (*) obrigatórios");
                } else if (txtNumeroPartido.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Selecione um partido");
                } else if (txtNumeroVereador.getText().equals("___")) {
                    JOptionPane.showMessageDialog(null, "Campo numero obrigatorio");
                } else if (txtNumeroPrefeito.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Um vereador deve associar-se a um prefeito");
                } else {
                    try {

                        addPrefeito.setIdPartido(PartidosNumeros.getIDPartido(Integer.parseInt(txtNumeroPartido.getText())));
                        addPrefeito.setIdPrefeito(PartidosNumeros.getIDPrefeito(Integer.parseInt(txtNumeroPartido.getText())));
                        addPrefeito.setNome(txtNomeVereador.getText());
                        addPrefeito.setDataNascimento(Data.convertSql(jdNascimentoVereador.getDate()));
                        addPrefeito.setNumero(Integer.parseInt(txtNumeroPartido.getText() + txtNumeroVereador.getText()));
                        addPrefeito.setFoto(ManipularImagem.getImgBytes(imgVereador));

                        new VereadorDao().insert(addPrefeito);
                        JOptionPane.showMessageDialog(null, "Vereador cadastradado com sucesso!");

                    } catch (HeadlessException ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao cadastrar candidato" + ex.getMessage());
                    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException ex) {
                        if (ex instanceof com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException) {
                            JOptionPane.showMessageDialog(null, "Vereador já cadastrado");
                        }
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
                330, 350, 60, 60);
        btnAtualizar.setEnabled(
                false);
        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                Vereador updateVereador = new Vereador();
                if ((txtNomeVereador.getText().isEmpty() || txtFotoVereador.getText().isEmpty()) || txtNumeroVereador.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos os campos (*) obrigatórios");
                } else if (txtNumeroPartido.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Selecione um partido");
                } else if (txtNumeroVereador.getText().equals("___")) {
                    JOptionPane.showMessageDialog(null, "Campo numero obrigatorio");
                } else if (txtNumeroPrefeito.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Um vereador deve associar-se a um prefeito");
                } else {
                    try {

                        updateVereador.setIdPartido(PartidosNumeros.getIDPartido(Integer.parseInt(txtNumeroPartido.getText())));
                        updateVereador.setIdPrefeito(PartidosNumeros.getIDPrefeito(Integer.parseInt(txtNumeroPartido.getText())));
                        updateVereador.setNome(txtNomeVereador.getText());
                        updateVereador.setDataNascimento(Data.convertSql(jdNascimentoVereador.getDate()));
                        updateVereador.setNumero(Integer.parseInt(txtNumeroPartido.getText() + txtNumeroVereador.getText()));
                        updateVereador.setFoto(ManipularImagem.getImgBytes(imgVereador));
                        updateVereador.setIdVereador(Integer.parseInt(txtIdVereador.getText()));

                        new VereadorDao().update(updateVereador);
                        JOptionPane.showMessageDialog(null, "Prefeito atualizado com sucesso!");

                    } catch (HeadlessException ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao cadastrar candidato" + ex.getMessage());
                    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException ex) {
                        if (ex instanceof com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException) {
                            JOptionPane.showMessageDialog(null, "Vereador já cadastrado");
                        }
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
                400, 350, 60, 60);
        btnExcluir.setEnabled(
                false);
        btnExcluir.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {

                int numero;
                Vereador deletePrefeito = new Vereador();

                try {
                    numero = Integer.parseInt(JOptionPane.showInputDialog("Número do Candidato"));
                    deletePrefeito.setNumero(numero);
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Apenas números");
                }

                try {
                    new VereadorDao().delete(deletePrefeito);
                    desabilita();
                    clearCampos();
                    JOptionPane.showMessageDialog(null, "Candidato excluído com sucesso!");
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException ex) {

                }
            }
        }
        );

        ImageIcon imgPesquisar = new ImageIcon(getClass().getResource("/br/com/etec/imgs/search.png"));
        btnPesquisar = new JButton(imgPesquisar);

        btnPesquisar.setToolTipText(
                "Pesquisar");
        btnPesquisar.setBounds(
                470, 350, 60, 60);
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

                        Vereador vereador = new VereadorDao().findById(numero);
                        txtIdVereador.setText(String.valueOf(vereador.getIdVereador()));
                        jcPartidoVereador.setSelectedItem(PartidosNumeros.partido(Integer.parseInt(String.valueOf(vereador.getNumero()).substring(0, 2))));
                        jcNumeroPrefeito.setSelectedItem(PartidosNumeros.getInfoPrefeito(Integer.parseInt(String.valueOf(vereador.getNumero()).substring(0, 2))));
                        txtNomeVereador.setText(vereador.getNome());
                        jdNascimentoVereador.setDate(Data.convertDate(vereador.getDataNascimento()));
                        txtNumeroVereador.setText(String.valueOf(vereador.getNumero()).substring(2, 5));
                        ManipularImagem.exibirImagemLabel(vereador.getFoto(), lblImagemVereador);

                        habilita();

                    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException | HeadlessException ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao pesquisar" + ex.getMessage());
                    }
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Apenas números");
                }
            }
        }
        );

        btnEnviarVereador.addActionListener(
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
                        imgVereador = ManipularImagem.setImagemDimensao(arquivo.getAbsolutePath(), 200, 200);

                        lblImagemVereador.setIcon(new ImageIcon(imgVereador));

                    } catch (Exception ex) {
                        // System.out.println(ex.printStackTrace().toString());
                    }

                    txtFotoVereador.setText(arquivo.getPath());
                } else {
                    JOptionPane.showMessageDialog(null, "Voce nao selecionou nenhum arquivo.");
                }
            }
        }
        );

        //Add
        container.add(lblCamposObri);

        //Panel Prefeito
        container.add(panelPrefeito);

        //container.add(lblValorNumero);
        container.add(btnAdicionar);
        container.add(btnAtualizar);
        container.add(btnExcluir);
        container.add(btnPesquisar);

        setClosable(true);
        setIconifiable(true);
        setLocation(100, 50);
    }

    public void clearCampos() {
        /* txtId.setText(null);
        txtNome.setText(null);
        txtFoto.setText(null);
        txtNumero.setValue(null);
        lblImagem.setIcon(null);
        jcPartido.setSelectedIndex(0);
        jcCargo.setSelectedIndex(0);*/
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

    //Atributos prefeito
    private JLabel lblIdVereador;
    private JTextField txtIdVereador;

    private JLabel lblNumeroVereador;
    private JFormattedTextField txtNumeroVereador;

    private JLabel lblNomeVereador;
    private JTextField txtNomeVereador;

    private JLabel lblFotoVereador;

    private JLabel lblDatadeNascimentVereador;
    private JDateChooser jdNascimentoVereador;

    private JTextField txtFotoVereador;
    private BufferedImage imgVereador;
    private JButton btnEnviarVereador;
    private JLabel lblImagemVereador;

    private JLabel lblPartidoVereador;
    private JTextField txtNumeroPartido;
    private JComboBox<String> jcPartidoVereador;

    private JLabel lblNumeroPrefeito;
    private JTextField txtNumeroPrefeito;
    private JComboBox<String> jcNumeroPrefeito;

    public static File filePrefeito;

    Connection connection;

}
