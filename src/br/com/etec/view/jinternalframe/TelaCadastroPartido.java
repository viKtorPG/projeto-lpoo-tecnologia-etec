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

        //ID Partido
        lblIdPartido = new JLabel("N° Partido");
        lblIdPartido.setBounds(130, 120, 80, 25);
        
        txtIdPartido = new JTextField();
        txtIdPartido.setBounds(205, 120, 80, 25);
        txtIdPartido.setEditable(false);
        
        // Nome do Partido
        lblNome = new JLabel("*Nome");
        lblNome.setBounds(130, 150, 200, 25);
        lblNome.setForeground(Color.black);

        txtNome = new JTextField(10);
        txtNome.setBounds(205, 150, 200, 25);

        // Partido
        lblPartido = new JLabel("*Sigla");
        lblPartido.setBounds(415, 120, 80, 25);
        lblPartido.setForeground(Color.black);

        txtPartido = new JTextField();
        txtPartido.setBounds(410, 150, 80, 25);

        // Número
        lblNumero = new JLabel("*Número");
        lblNumero.setBounds(130, 180, 80, 25);
        lblNumero.setForeground(Color.black);

        txtNumero = new JFormattedTextField(new MaskFormatter("##"));
        txtNumero.setBounds(205, 180, 30, 25);

        //Sloga
        lblSlogan = new JLabel("Slogan");
        lblSlogan.setBounds(130, 210, 80, 25);
        lblSlogan.setForeground(Color.black);

        txtSlogan = new JTextField();
        txtSlogan.setBounds(205, 210, 200, 25);

        //Imagem
        lblFoto = new JLabel("Logo");
        lblFoto.setBounds(130, 240, 80, 25);
        lblFoto.setForeground(Color.black);

        txtFoto = new JTextField(10);
        txtFoto.setBounds(205, 240, 200, 25);
        txtFoto.setEnabled(false);

        ImageIcon imgLogo = new ImageIcon(getClass().getResource("/br/com/etec/imgs/logoUserBD.png"));
        lblImagem = new JLabel(imgLogo);
        lblImagem.setToolTipText("Logo");
        lblImagem.setBounds(520, 100, 200, 200);

        btnEnviar = new JButton("Enviar");
        btnEnviar.setBounds(410, 240, 80, 25);

        // Buttons
        ImageIcon imgAdicionar = new ImageIcon(getClass().getResource("/br/com/etec/imgs/add.png"));
        btnAdicionar = new JButton(imgAdicionar);
        btnAdicionar.setToolTipText("Adicionar");
        btnAdicionar.setBounds(260, 300, 60, 60);
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Partido addPartido = new Partido();
                if ((txtNome.getText().isEmpty() || txtPartido.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Todos os campos (*) obrigatórios");
                } else if (txtNumero.getText().equals("  ")) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente");
                } else {
                    try {
                        System.err.println(">>"+txtNumero.getText()+"<<");
                        addPartido.setNome(txtNome.getText());
                        addPartido.setNumero(Integer.parseInt(txtNumero.getText()));
                        addPartido.setSigla(txtPartido.getText());
                        addPartido.setSlogan(txtSlogan.getText());
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
                        if (ex instanceof com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException) {
                            JOptionPane.showMessageDialog(null, "Partido já cadastrado");
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
                330, 300, 60, 60);
        btnAtualizar.setEnabled(
                false);
        btnAtualizar.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                Partido addPartido = new Partido();
                if ((txtNome.getText().isEmpty() || txtNumero.getText().isEmpty() || txtPartido.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Todos os campos (*) obrigatórios");
                } else if ((txtNumero.getText().length() < 2)) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente");
                } else {
                    try {
                        addPartido.setIdPartido(Integer.valueOf(txtIdPartido.getText()));
                        addPartido.setNome(txtNome.getText());
                        addPartido.setSigla(txtPartido.getText());
                        addPartido.setSlogan(txtSlogan.getText());
                        addPartido.setNumero(Integer.parseInt(txtNumero.getText()));
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
                        if (ex instanceof com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException) {
                            JOptionPane.showMessageDialog(null, "Partido já cadastrado");
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
                400, 300, 60, 60);
        btnExcluir.setEnabled(
                false);
        btnExcluir.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                Partido excPart = new Partido();
                excPart.setIdPartido(Integer.parseInt(txtIdPartido.getText()));
                try {
                    int confir = JOptionPane.showConfirmDialog(null, "Deseja excluir", "Atenção", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (confir == JOptionPane.YES_OPTION) {
                        new PartidoDao().delete(excPart);
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
                        partido = new PartidoDao().findById(numero);

                            System.err.println(partido);
                            txtIdPartido.setText("" +partido.getIdPartido());
                            txtNome.setText(partido.getNome());
                            txtNumero.setText("" + partido.getNumero());
                            txtPartido.setText(partido.getSigla());
                            txtSlogan.setText(partido.getSlogan());
                            ManipularImagem.exibirImagemLabel(partido.getLogo(), lblImagem);

                            habilita();
                        
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(null, "Apenas números");
                    }

                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException | HeadlessException | NullPointerException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao pesquisar" + ex.getMessage());
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
                ViewTable table = new ViewTable("select id_partido as ID, nome_partido as Nome, sigla as Sigla, slogan as Slogan, numero as Numero, data_criacao as Cricao, votos as Votos from partido where nome_partido like ?");
                table.execute();
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

        //container.add(lblDtCriacao);
        //container.add(txtDtCriacao);
        container.add(lblIdPartido);
        container.add(txtIdPartido);
        
        container.add(lblNome);
        container.add(txtNome);

        container.add(lblNumero);
        container.add(txtNumero);

        container.add(lblPartido);
        container.add(txtPartido);

        container.add(lblFoto);
        container.add(txtFoto);

        container.add(btnEnviar);
        container.add(btnAdicionar);
        container.add(btnAtualizar);
        container.add(btnExcluir);
        container.add(btnPesquisar);
        container.add(btnLista);

        container.add(lblImagem);

        container.add(lblSlogan);
        container.add(txtSlogan);

        setClosable(
                true);
        setIconifiable(
                true);
        setLocation(
                100, 50);
    }

    public void clearCampos() {
        txtIdPartido.setText(null);
        txtNome.setText(null);
        txtFoto.setText(null);
        txtNumero.setValue(null);
        lblImagem.setIcon(new ImageIcon(getClass().getResource("/br/com/etec/imgs/logoUserBD.png")));
        txtPartido.setText(null);
        txtSlogan.setText(null);
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
    private BufferedImage imagem;
    private JButton btnEnviar;
    private JLabel lblImagem;
    private JLabel lblIdPartido;
    private JTextField txtIdPartido;
    private Partido partido;
    private JTextField txtFoto;
    private Connection connection;
    private JButton btnLista;

}
