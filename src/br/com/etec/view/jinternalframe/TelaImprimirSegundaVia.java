/*
 * Tela para imprimir 2º via do titulo de eleitor.
 */
package br.com.etec.view.jinternalframe;

import br.com.etec.log.Log;
import br.com.etec.utils.DbUtils;
import br.com.etec.view.jframe.TelaDesktop;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author jose
 */
public class TelaImprimirSegundaVia extends JInternalFrame {

    public TelaImprimirSegundaVia() {
        iniciandoCompomentes();

    }

    private void iniciandoCompomentes() {
        setTitle("Imprimir 2º via do título de Eleitor");
        setSize(800, 500);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            @Override
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }

            @Override
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }

            @Override
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }

            @Override
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }

            @Override
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }

            @Override
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }

            @Override
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        //Panel
        Container container = getContentPane();
        container.setLayout(null);

        lblCamposObri = new JLabel("(*)Campos Obrigatórios");
        lblCamposObri.setBounds(600, 20, 200, 20);
        lblCamposObri.setForeground(Color.red);

        //Número do Eleitor
        lblNumeroEleitor = new JLabel("*Nome do eleitor");
        lblNumeroEleitor.setBounds(210, 30, 120, 25);

        //Número do eleitor caixa de texto
        txtNumeroEleitor = new JFormattedTextField();
        txtNumeroEleitor.setBounds(160, 60, 200, 25);
        txtNumeroEleitor.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                pesqEleitor();
                tblEleitor.setVisible(true);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                tblEleitor.setVisible(true);
            }
        });

        //Imagem de pesquisa
        imgPesquisar = new ImageIcon(getClass().getResource("/br/com/etec/imgs/seo.png"));
        lblImagemPesquisa = new JLabel(imgPesquisar);
        lblImagemPesquisa.setBounds(110, 50, 50, 50);

        //Tabel Eleitor
        jScrollPane = new JScrollPane();
        jScrollPane.setBounds(10, 120, 770, 150);
        jScrollPane.setVisible(true);
        tblEleitor = new JTable();
        tblEleitor.setVisible(true);
        tblEleitor.setModel(new DefaultTableModel(
                new Object[][]{
                    {null, null, null},
                    {null, null, null},
                    {null, null, null},
                    {null, null, null},
                    {null, null, null},
                    {null, null, null},
                    {null, null, null},
                    {null, null, null}
                },
                new String[]{
                    "Nº Eleitor", "Nome", "Dat. Nasc.", "Dat. Emissão", "Zona", "Seção", "Cidade", "UF"
                }
        ));
        //Evento de selecionar um determinado eleitor retornado pelo banco de dados
        tblEleitor.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnImprimir.setEnabled(true);
            }
        });
        jScrollPane.setViewportView(tblEleitor);

        //Buttons
        ImageIcon imgImprimir = new ImageIcon(getClass().getResource("/br/com/etec/imgs/print.png"));
        btnImprimir = new JButton(imgImprimir);
        btnImprimir.setEnabled(false);
        btnImprimir.setToolTipText("Imprimir 2° via");
        btnImprimir.setBounds(370, 280, 60, 60);
        btnImprimir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int rowLine = tblEleitor.getSelectedRow();

                if (rowLine > -1) {
                    int confirmar = JOptionPane.showConfirmDialog(null, "Confirma a impressão da 2° via do título de eleitor", "Atenção", JOptionPane.YES_NO_OPTION);

                    long idEleitor = Long.parseLong(tblEleitor.getModel().getValueAt(rowLine, 0).toString());

                    Log.d("idEleitor", idEleitor  +" ");

                    ImageIcon imgTitulo = new ImageIcon(getClass().getResource("/br/com/etec/imgs/imgTitulo.jpg"));
                    ImageIcon imgTituloVerso = new ImageIcon(getClass().getResource("/br/com/etec/imgs/imgTituloVerso.jpg"));
                    final Map<String, Object> paramEleitor = new HashMap<>();
                    paramEleitor.put("paramsID", idEleitor);
                    paramEleitor.put("paramsImgTitulo", imgTitulo.getImage());
                    paramEleitor.put("paramsImgTituloVerso", imgTituloVerso.getImage());

                    if (confirmar == JOptionPane.YES_NO_OPTION) {
                        new Thread() {
                            @Override
                            public void run() {
                                try {

                                    Connection connection = DbUtils.getConnection();
                                    JasperPrint viewer = JasperFillManager.fillReport("src/br/com/etec/ireport/projectTitulo.jasper", paramEleitor, connection);

                                    JasperViewer.viewReport(viewer, false);
                                } catch (JRException | ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                                    Logger.getLogger(TelaDesktop.class.getName()).log(Level.SEVERE, null, ex);
                                    JOptionPane.showMessageDialog(null, "Erro ao imprimir título de eleitor " + ex.getMessage(), "Erro impressão", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }.start();
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Selecione um eleitor");
                }

            }
        });

        //ADD
        container.add(lblCamposObri);
        container.add(lblNumeroEleitor);
        container.add(txtNumeroEleitor);
        container.add(lblImagemPesquisa);
        container.add(jScrollPane);
        container.add(btnImprimir);
        //container.add(btnPesquisar);

        //container.add(conteudoRetorno);
        //Configurações
        setClosable(true);
        setIconifiable(true);
        setLocation(100, 50);
    }

    /* Método responsável pela query no banco de dados
    e pelo preenchimento da tabela. */
    private void pesqEleitor() {
        ResultSet resultSet;
        try {
            connection = DbUtils.getConnection();

            String sql = "select eleitor.id_eleitor as ID, eleitor.nome as Nome,"
                    + " DATE_FORMAT(eleitor.data_nascimento, '%d/%m/%Y') as Nascimento,"
                    + " DATE_FORMAT(eleitor.data_cadastro, '%d/%m/%Y') as Emissao,"
                    + " eleitor.zona as Zona, eleitor.secao as Secao, cidade.nome as Cidade, estado.uf as UF \n"
                    + "from eleitor left join cidade \n"
                    + "on eleitor.id_cidade = cidade.id_cidade \n"
                    + "left join estado \n"
                    + "on cidade.id_estado = estado.id_estado \n"
                    + "where eleitor.nome like ?";
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);

            statement.setString(1, txtNumeroEleitor.getText() + "%");

            resultSet = statement.executeQuery();

            tblEleitor.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(resultSet));

            if (connection != null) {
                connection.close();
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
        }
    }

    /* Evento que verifica se o campo de pesquisa
    de eleitor está vazio, se sim a tabela e desabilitada. */
    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
        if (txtNumeroEleitor.getText().isEmpty()) {
            tblEleitor.setVisible(false);
        }
    }

    // Atributos da Classe
    private JLabel lblCamposObri;
    private JLabel lblNumeroEleitor;
    private JLabel lblImagemPesquisa;
    private JFormattedTextField txtNumeroEleitor;
    private JButton btnImprimir;
    private ImageIcon imgPesquisar;
    private JTable tblEleitor;
    private JScrollPane jScrollPane;
    private Connection connection;

}
