/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etec.view.jinternalframe;

import br.com.etec.dao.PrefeitoDao;
import br.com.etec.model.Prefeito;
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
import javax.swing.JPanel;
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
        panelPrefeito.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações do Prefeito"));
        panelPrefeito.setLayout(null);
        panelPrefeito.setBounds(20, 50, 370, 230);

        // ID do Prefeito
        lblIdPrefeito = new JLabel("N° Pref.");
        lblIdPrefeito.setBounds(10, 40, 50, 25);
        lblIdPrefeito.setForeground(Color.black);

        txtIdPrefeito = new JTextField(10);
        txtIdPrefeito.setBounds(80, 40, 175, 25);
        
        // Nome do Prefeito
        lblNomePrefeito = new JLabel("*Nome");
        lblNomePrefeito.setBounds(10, 70, 50, 25);
        lblNomePrefeito.setForeground(Color.black);

        txtNomePrefeito = new JTextField(10);
        txtNomePrefeito.setBounds(80, 70, 175, 25);

        //Data de Nasciment
        lblDatadeNascimentPrefeito = new JLabel("*Data de Nascimento");
        lblDatadeNascimentPrefeito.setBounds(10, 100, 120, 25);
        lblDatadeNascimentPrefeito.setForeground(Color.black);

        jdNascimentoPrefeito = new JDateChooser();
        jdNascimentoPrefeito.setBounds(135, 100, 120, 25);
        jdNascimentoPrefeito.setDate(new Date());
        jdNascimentoPrefeito.setDateFormatString("dd/MM/yyyy");

        //Imagem
        lblFotoPrefeito = new JLabel("*Foto");
        lblFotoPrefeito.setBounds(10, 130, 50, 25);
        lblFotoPrefeito.setForeground(Color.black);

        txtFotoPrefeito = new JTextField(10);
        txtFotoPrefeito.setBounds(80, 130, 100, 25);
        txtFotoPrefeito.setEnabled(false);

        ImageIcon lblImgPrefeito = new ImageIcon(getClass().getResource("/br/com/etec/imgs/logoUserBD.png"));
        lblImagemPrefeito = new JLabel(lblImgPrefeito);
        lblImagemPrefeito.setBounds(265, 40, 85, 105);

        btnEnviarPrefeito = new JButton("Enviar");
        btnEnviarPrefeito.setBounds(185, 130, 70, 25);

        // Partido
        lblPartidoPrefeito = new JLabel("*Partido");
        lblPartidoPrefeito.setBounds(10, 160, 50, 25);
        lblPartidoPrefeito.setForeground(Color.black);

        jcPartidoPrefeito = new JComboBox<>();
        jcPartidoPrefeito.setModel(new javax.swing.DefaultComboBoxModel<>(PartidosNumeros.partidos()));
        jcPartidoPrefeito.setBounds(80, 160, 275, 25);
        jcPartidoPrefeito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.err.println(jcPartidoPrefeito.getSelectedItem().toString().substring(jcPartidoPrefeito.getSelectedItem().toString().indexOf("-") + 2));
                txtNumeroPartido.setText(PartidosNumeros.numero(jcPartidoPrefeito.getSelectedItem().toString().substring(jcPartidoPrefeito.getSelectedItem().toString().indexOf("-") + 2)));
            }
        });

        // Número
        lblNumeroPrefeito = new JLabel("*Número");
        lblNumeroPrefeito.setBounds(10, 190, 50, 25);
        lblNumeroPrefeito.setForeground(Color.black);

        MaskFormatter mask = new MaskFormatter();
        mask.setMask("###");
        mask.setPlaceholderCharacter('_');

        txtNumeroPartido = new JTextField();
        txtNumeroPartido.setBounds(80, 190, 30, 25);
        txtNumeroPartido.setForeground(Color.black);
        txtNumeroPartido.setEditable(false);

        /*txtNumeroPrefeito = new JFormattedTextField(mask);
        txtNumeroPrefeito.setBounds(115, 190, 65, 25);
        txtNumeroPrefeito.setVisible(true);*/

        //ADD ELEMENTOS PREFEITO
        panelPrefeito.add(lblIdPrefeito);
        panelPrefeito.add(txtIdPrefeito);
        
        //panelPrefeito.add(txtNumeroPrefeito);
        panelPrefeito.add(lblNumeroPrefeito);

        panelPrefeito.add(lblNomePrefeito);
        panelPrefeito.add(txtNomePrefeito);

        panelPrefeito.add(jdNascimentoPrefeito);
        panelPrefeito.add(lblDatadeNascimentPrefeito);

        panelPrefeito.add(lblFotoPrefeito);
        panelPrefeito.add(txtFotoPrefeito);
        panelPrefeito.add(lblImagemPrefeito);

        panelPrefeito.add(lblPartidoPrefeito);
        panelPrefeito.add(jcPartidoPrefeito);
        panelPrefeito.add(txtNumeroPartido);

        panelPrefeito.add(btnEnviarPrefeito);

        /* <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< * >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */
        // Panel Prefeito
        JPanel panelVicePrefeito = new JPanel();
        panelVicePrefeito.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações do Vice-prefeito"));
        panelVicePrefeito.setLayout(null);
        panelVicePrefeito.setBounds(400, 50, 370, 230);

        // ID do Vice_Prefeito
        lblIdViceP = new JLabel("N° V.Pref.");
        lblIdViceP.setBounds(10, 40, 80, 25);
        lblIdViceP.setForeground(Color.black);

        txtIdViceP = new JTextField(10);
        txtIdViceP.setBounds(80, 40, 175, 25);
        
        // Nome do Vice_Prefeito
        lblNomeViceP = new JLabel("*Nome");
        lblNomeViceP.setBounds(10, 70, 50, 25);
        lblNomeViceP.setForeground(Color.black);

        txtNomeViceP = new JTextField(10);
        txtNomeViceP.setBounds(80, 70, 175, 25);

        //Data de Nasciment
        lblDatadeNascimentViceP = new JLabel("*Data de Nascimento");
        lblDatadeNascimentViceP.setBounds(10, 100, 120, 25);
        lblDatadeNascimentViceP.setForeground(Color.black);

        jdNascimentoViceP = new JDateChooser();
        jdNascimentoViceP.setBounds(135, 100, 120, 25);
        jdNascimentoViceP.setDate(new Date());
        jdNascimentoViceP.setDateFormatString("dd/MM/yyyy");

        //Imagem
        lblFotoViceP = new JLabel("*Foto");
        lblFotoViceP.setBounds(10, 130, 50, 25);
        lblFotoViceP.setForeground(Color.black);

        txtFotoViceP = new JTextField(10);
        txtFotoViceP.setBounds(80, 130, 100, 25);
        txtFotoViceP.setEnabled(false);

        ImageIcon lblImgVicePrefeito = new ImageIcon(getClass().getResource("/br/com/etec/imgs/logoUserBD.png"));
        lblImagemViceP = new JLabel(lblImgVicePrefeito);
        lblImagemViceP.setBounds(265, 40, 85, 105);

        btnEnviarViceP = new JButton("Enviar");
        btnEnviarViceP.setBounds(185, 130, 70, 25);

        // Partido
        lblPartidoViceP = new JLabel("*Partido");
        lblPartidoViceP.setBounds(10, 160, 50, 25);
        lblPartidoViceP.setForeground(Color.black);

        jcPartidoVicePartido = new JComboBox<>();
        jcPartidoVicePartido.setModel(new javax.swing.DefaultComboBoxModel<>(PartidosNumeros.partidos()));
        jcPartidoVicePartido.setBounds(80, 160, 275, 25);
        jcPartidoVicePartido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.err.println(jcPartidoPrefeito.getSelectedItem().toString().substring(jcPartidoPrefeito.getSelectedItem().toString().indexOf("-") + 2));
                lblNumeroVicePartido.setText(PartidosNumeros.numero(jcPartidoVicePartido.getSelectedItem().toString().substring(jcPartidoVicePartido.getSelectedItem().toString().indexOf("-") + 2)));
            }
        });

        // Número
        lblNumeroViceP = new JLabel("*Número");
        lblNumeroViceP.setBounds(10, 190, 50, 25);
        lblNumeroViceP.setForeground(Color.black);

        /*MaskFormatter maskVice = new MaskFormatter();
        mask.setMask("###");
        mask.setPlaceholderCharacter('_');*/
        lblNumeroVicePartido = new JTextField();
        lblNumeroVicePartido.setBounds(80, 190, 30, 25);
        lblNumeroVicePartido.setForeground(Color.black);
        lblNumeroVicePartido.setEditable(false);

        /*txtNumeroViceP = new JFormattedTextField(maskVice);
        txtNumeroViceP.setBounds(100, 160, 65, 25);
        txtNumeroViceP.setVisible(true);*/
        
        //ADD ELEMENTOS VICE_PREFEITO
        panelVicePrefeito.add(lblIdViceP);
        panelVicePrefeito.add(txtIdViceP);
        panelVicePrefeito.add(lblNumeroViceP);

        panelVicePrefeito.add(lblNomeViceP);
        panelVicePrefeito.add(txtNomeViceP);

        panelVicePrefeito.add(jdNascimentoViceP);
        panelVicePrefeito.add(lblDatadeNascimentViceP);

        panelVicePrefeito.add(lblFotoViceP);
        panelVicePrefeito.add(txtFotoViceP);
        panelVicePrefeito.add(lblImagemViceP);

        panelVicePrefeito.add(lblPartidoViceP);
        panelVicePrefeito.add(jcPartidoVicePartido);
        panelVicePrefeito.add(lblNumeroVicePartido);

        panelVicePrefeito.add(btnEnviarViceP);

        // Buttons
        ImageIcon imgAdicionar = new ImageIcon(getClass().getResource("/br/com/etec/imgs/add.png"));
        btnAdicionar = new JButton(imgAdicionar);
        btnAdicionar.setToolTipText("Adicionar");
        btnAdicionar.setBounds(260, 300, 60, 60);
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Prefeito addPrefeito = new Prefeito();
                if ((txtNomePrefeito.getText().isEmpty() || txtFotoPrefeito.getText().isEmpty()) || txtNomePrefeito.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos os campos (*) obrigatórios");
                }
                try {

                    addPrefeito.setIdPartido(PartidosNumeros.getID(Integer.parseInt(txtNumeroPartido.getText())));
                    addPrefeito.setNome(txtNomePrefeito.getText());
                    addPrefeito.setDataNascimento(Data.convertSql(jdNascimentoPrefeito.getDate()));
                    addPrefeito.setNumero(Integer.parseInt(txtNumeroPartido.getText()));
                    addPrefeito.setFoto(ManipularImagem.getImgBytes(imgPrefeito));
                    
                    new PrefeitoDao().insert(addPrefeito);
                    JOptionPane.showMessageDialog(null, "Prefeito cadastradado com sucesso!");
                    
                } catch (HeadlessException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar candidato" + ex.getMessage());
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(TelaCadastroCandidato.class.getName()).log(Level.SEVERE, null, ex);
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
                Prefeito updatePrefeito = new Prefeito();
                if ((txtNomePrefeito.getText().isEmpty() || txtFotoPrefeito.getText().isEmpty()) || txtNomePrefeito.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos os campos (*) obrigatórios");
                }
                try {

                    updatePrefeito.setIdPartido(PartidosNumeros.getID(Integer.parseInt(txtNumeroPartido.getText())));
                    updatePrefeito.setNome(txtNomePrefeito.getText());
                    updatePrefeito.setDataNascimento(Data.convertSql(jdNascimentoPrefeito.getDate()));
                    updatePrefeito.setNumero(Integer.parseInt(txtNumeroPartido.getText()));
                    updatePrefeito.setFoto(ManipularImagem.getImgBytes(imgPrefeito));
                    updatePrefeito.setIdPrefeito(Integer.parseInt(txtIdPrefeito.getText()));
                    
                    new PrefeitoDao().update(updatePrefeito);
                    JOptionPane.showMessageDialog(null, "Prefeito atualizado com sucesso!");
                    
                } catch (HeadlessException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar candidato" + ex.getMessage());
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(TelaCadastroCandidato.class.getName()).log(Level.SEVERE, null, ex);
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
                Prefeito deletePrefeito = new Prefeito();

                try {
                    numero = Integer.parseInt(JOptionPane.showInputDialog("Número do Candidato"));
                    deletePrefeito.setNumero(numero);
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Apenas números");
                }

                try {
                    new PrefeitoDao().delete(deletePrefeito);
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
                    Prefeito prefeito = new PrefeitoDao().findById(numero);

                    txtIdPrefeito.setText(String.valueOf(prefeito.getIdPrefeito()));
                    jcPartidoPrefeito.setSelectedItem(PartidosNumeros.partido(prefeito.getNumero()));
                    //txtNumeroPrefeito.setText(String.valueOf(prefeito.getNumero()).substring(2, 5));
                    txtNomePrefeito.setText(prefeito.getNome());
                    txtNumeroPartido.setText(PartidosNumeros.numero(jcPartidoVicePartido.getSelectedItem().toString().substring(jcPartidoVicePartido.getSelectedItem().toString().indexOf("-") + 2)));
                    jdNascimentoPrefeito.setDate(Data.convertDate(prefeito.getDataNascimento()));
                    ManipularImagem.exibirImagemLabel(prefeito.getFoto(), lblImagemPrefeito);

                    habilita();

                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException | HeadlessException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao pesquisar" + ex.getMessage());
                }
            }
        }
        );

        btnEnviarPrefeito.addActionListener(
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
                        imgPrefeito = ManipularImagem.setImagemDimensao(arquivo.getAbsolutePath(), 200, 200);

                        lblImagemPrefeito.setIcon(new ImageIcon(imgPrefeito));

                    } catch (Exception ex) {
                        // System.out.println(ex.printStackTrace().toString());
                    }

                    txtFotoPrefeito.setText(arquivo.getPath());
                } else {
                    JOptionPane.showMessageDialog(null, "Voce nao selecionou nenhum arquivo.");
                }
            }
        }
        );

        btnEnviarViceP.addActionListener(
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
                        imgViceP = ManipularImagem.setImagemDimensao(arquivo.getAbsolutePath(), 200, 200);

                        lblImagemViceP.setIcon(new ImageIcon(imgViceP));

                    } catch (Exception ex) {
                        // System.out.println(ex.printStackTrace().toString());
                    }

                    txtFotoViceP.setText(arquivo.getPath());
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

        //Panel Vice_Prefeito
        container.add(panelVicePrefeito);

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
    private JLabel lblIdPrefeito;
    private JTextField txtIdPrefeito;
    
    private JLabel lblNumeroPrefeito;
    //private JFormattedTextField txtNumeroPrefeito;*/

    private JLabel lblNomePrefeito;
    private JTextField txtNomePrefeito;

    private JLabel lblPartidoPrefeito;
    private JLabel lblFotoPrefeito;

    private JLabel lblDatadeNascimentPrefeito;
    private JDateChooser jdNascimentoPrefeito;

    private JTextField txtFotoPrefeito;
    private BufferedImage imgPrefeito;
    private JButton btnEnviarPrefeito;
    private JLabel lblImagemPrefeito;

    private JTextField txtNumeroPartido;
    private JComboBox<String> jcPartidoPrefeito;

    public static File filePrefeito;

    //Atributos prefeito_vice
    private JLabel lblIdViceP;
    private JTextField txtIdViceP;
    
    private JLabel lblNumeroViceP;
    //private JFormattedTextField txtNumeroViceP;

    private JLabel lblNomeViceP;
    private JTextField txtNomeViceP;

    private JLabel lblPartidoViceP;
    private JLabel lblFotoViceP;

    private JLabel lblDatadeNascimentViceP;
    private JDateChooser jdNascimentoViceP;

    private JTextField txtFotoViceP;
    private BufferedImage imgViceP;
    private JButton btnEnviarViceP;
    private JLabel lblImagemViceP;

    private JTextField lblNumeroVicePartido;
    private JComboBox<String> jcPartidoVicePartido;

    public static File filePrefeitoVicePrefeito;

    /**/
    private Prefeito candidato;

    Connection connection;

}
