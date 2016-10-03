/*
 * Tela para imprimir 2º via do titulo de eleitor.
 */
package br.com.etec.view.jinternalframe;

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
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.input.KeyCode.T;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
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
        setTitle("Imprimir 2º via do Eleitor");
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

        /*//Formatando o campo NumeroEleitor
        try{
            formatTxtNumeroEleitor = new MaskFormatter("#### #### #### ####");
        } catch (ParseException ex) {
            Logger.getLogger(TelaImprimirSegundaVia.class.getName()).log(Level.SEVERE, null, ex);
        }*/
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

                int confirmar = JOptionPane.showConfirmDialog(null, "Conrfima a impressão desse relatorio", "Atenção", JOptionPane.YES_NO_OPTION);

                int rowLine = tblEleitor.getSelectedRow();
                long idEleitor = Long.parseLong(tblEleitor.getModel().getValueAt(rowLine, 0).toString());
                
                System.err.println("Long: " + idEleitor);

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
                            }
                        }
                    }.start();
                }
            }
        });

        /*ImageIcon imgPesquisar = new ImageIcon(getClass().getResource("/br/com/etec/imgs/search.png"));
        btnPesquisar = new JButton(imgPesquisar);
        btnPesquisar.setToolTipText("Pesquisar");
        btnPesquisar.setBounds(260, 280, 60, 60);*/
 /*
        //Panel de retorno
        JPanel conteudoRetorno = new JPanel();
        conteudoRetorno.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações do eleitor"));
        conteudoRetorno.setLayout(null);
        //conteudoRetorno.setBackground(Color.red);
        conteudoRetorno.setBounds(450, 120, 250, 200);

        //Nome
        lblNome = new JLabel("Nome:");
        lblNome.setBounds(16, 30, 50, 20);
        
        lblNomeRetorno = new JLabel("**************");
        lblNomeRetorno.setBounds(16, 50, 200, 20);
        
        // Data de cadastramento
        lblDataCadastramento = new JLabel("Data de cadastramento:");
        lblDataCadastramento.setBounds(16, 80, 200, 20);
        
        lblDataCadastramentoRetorno = new JLabel("**************");
        lblDataCadastramentoRetorno.setBounds(16, 100, 200, 20);
        
        // Zona
        lblZona = new JLabel("Zona:");
        lblZona.setBounds(16, 130, 200, 20);
        
        lblZonaRetorno = new JLabel("**************");
        lblZonaRetorno.setBounds(16, 150, 200, 20);
        
        // Seção
        lblSecao = new JLabel("Seção:");
        lblSecao.setBounds(150, 130, 200, 20);
        
        lblSecaoRetorno = new JLabel("**************");
        lblSecaoRetorno.setBounds(150, 150, 200, 20);
        
        // ADD elementos ao conteudoRetorno
        conteudoRetorno.add(lblNome);
        conteudoRetorno.add(lblNomeRetorno);
        
        conteudoRetorno.add(lblDataCadastramento);
        conteudoRetorno.add(lblDataCadastramentoRetorno);
        
        conteudoRetorno.add(lblZona);
        conteudoRetorno.add(lblZonaRetorno);
        
        conteudoRetorno.add(lblSecao);
        conteudoRetorno.add(lblSecaoRetorno);
         */
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
    private JLabel lblNome;
    private JLabel lblNomeRetorno;
    private JLabel lblDataCadastramento;
    private JLabel lblDataCadastramentoRetorno;
    private JLabel lblZona;
    private JLabel lblZonaRetorno;
    private JLabel lblSecao;
    private JLabel lblSecaoRetorno;
    private JFormattedTextField txtNumeroEleitor;
    private MaskFormatter formatTxtNumeroEleitor;
    private JButton btnImprimir;
    private JButton btnPesquisar;
    private ImageIcon imgPesquisar;
    private JTable tblEleitor;
    private JScrollPane jScrollPane;
    private Connection connection;

}
