/*
 * Tela para imprimir 2º via do titulo de eleitor.
 */
package br.com.etec.view.jinternalframe;

import br.com.etec.utils.DbUtils;
import br.com.etec.utils.PartidosNumeros;
import br.com.etec.view.jframe.TelaDesktop;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.text.MaskFormatter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author jose
 */
public class TelaGerarRelatorioPartido extends JInternalFrame {

    public TelaGerarRelatorioPartido() {
        iniciandoCompomentes();

    }

    private void iniciandoCompomentes() {
        setTitle("Gerar relatório partido");
        setSize(800, 500);

        //Panel
        Container container = getContentPane();
        container.setLayout(null);

        //Formatando o campo NumeroEleitor
        try {
            formatTxtNumeroEleitor = new MaskFormatter("##");
            formatTxtNumeroEleitor.setPlaceholderCharacter('_');
        } catch (ParseException ex) {
            Logger.getLogger(TelaGerarRelatorioPartido.class.getName()).log(Level.SEVERE, null, ex);
        }

        lblCamposObri = new JLabel("(*)Campos Obrigatórios");
        lblCamposObri.setBounds(600, 20, 200, 20);
        lblCamposObri.setForeground(Color.red);

        //Número do Eleitor
        lblNumeroEleitor = new JLabel("*Número do partido");
        lblNumeroEleitor.setBounds(205, 150, 130, 25);

        //Número do eleitor caixa de texto
        txtNumeroEleitor = new JFormattedTextField(formatTxtNumeroEleitor);
        txtNumeroEleitor.setBounds(160, 180, 200, 25);

        //Imagem de pesquisa
        imgPesquisar = new ImageIcon(getClass().getResource("/br/com/etec/imgs/seo.png"));
        lblImagemPesquisa = new JLabel(imgPesquisar);
        lblImagemPesquisa.setBounds(110, 160, 50, 50);

        //Buttons
        ImageIcon imgImprimir = new ImageIcon(getClass().getResource("/br/com/etec/imgs/print.png"));
        btnImprimir = new JButton(imgImprimir);
        btnImprimir.setToolTipText("Imprimir 2° via");
        btnImprimir.setBounds(190, 250, 60, 60);
        btnImprimir.setEnabled(false);
        btnImprimir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                final Map<String, Object> paramEleitor = new HashMap<>();
                paramEleitor.put("paramsNumeroPartido", Integer.parseInt(txtNumeroEleitor.getText()));

                if (prefeito) {
                    int confirmar = JOptionPane.showConfirmDialog(null, "Conrfima a impressão desse relatorio", "Atenção", JOptionPane.YES_NO_OPTION);
                    if (confirmar == JOptionPane.YES_NO_OPTION) {
                        new Thread() {
                            @Override
                            public void run() {
                                try {

                                    Connection connection = DbUtils.getConnection();
                                    JasperPrint viewer = JasperFillManager.fillReport("src/br/com/etec/ireport/projectPartidoPrefeito.jasper", paramEleitor, connection);

                                    JasperViewer.viewReport(viewer, false);
                                } catch (JRException | ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                                    Logger.getLogger(TelaDesktop.class.getName()).log(Level.SEVERE, null, ex);
                                        JOptionPane.showMessageDialog(null, "Erro ao imprimir relatório " + ex.getMessage(), "Erro impressão", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }.start();
                    }
                } else {
                    int confirmar = JOptionPane.showConfirmDialog(null, "Conrfima a impressão desse relatorio", "Atenção", JOptionPane.YES_NO_OPTION);
                    if (confirmar == JOptionPane.YES_NO_OPTION) {
                        new Thread() {
                            @Override
                            public void run() {
                                try {

                                    Connection connection = DbUtils.getConnection();
                                    JasperPrint viewer = JasperFillManager.fillReport("src/br/com/etec/ireport/projectPartidoVereador.jasper", paramEleitor, connection);

                                    JasperViewer.viewReport(viewer, false);
                                } catch (JRException | ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                                    Logger.getLogger(TelaDesktop.class.getName()).log(Level.SEVERE, null, ex);
                                    JOptionPane.showMessageDialog(null, "Erro ao imprimir relatório " + ex.getMessage(), "Erro impressão", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }.start();
                    }
                }

                clearCampos();
                btnImprimir.setEnabled(false);
            }
        });

        ImageIcon imgPesquisar = new ImageIcon(getClass().getResource("/br/com/etec/imgs/search.png"));
        btnPesquisar = new JButton(imgPesquisar);
        btnPesquisar.setToolTipText("Pesquisar");
        btnPesquisar.setBounds(260, 250, 60, 60);
        btnPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (txtNumeroEleitor.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Campo de pesquisa obrigatorio");
                } else {
                    int result = PartidosNumeros.getIDPartido(Integer.parseInt(txtNumeroEleitor.getText()));
                    if (result > 0) {
                        btnImprimir.setEnabled(true);
                        JOptionPane.showMessageDialog(null, "Relatório pronto pra impressão");

                    } else {
                        JOptionPane.showMessageDialog(null, "Esse número não conrresponde a nenhum partido");
                        clearCampos();
                    }
                }
            }
        });

        //Panel de retorno
        JPanel conteudoRetorno = new JPanel();
        conteudoRetorno.setBorder(javax.swing.BorderFactory.createTitledBorder("Escolha uma opção"));
        conteudoRetorno.setLayout(null);
        //conteudoRetorno.setBackground(Color.red);
        conteudoRetorno.setBounds(450, 120, 250, 100);

        ButtonGroup group = new ButtonGroup();
        handler = new RadioButtonHandler();

        // RadioPrefeito
        jrdPrefeito = new JRadioButton("Prefeito", true);
        jrdPrefeito.setBounds(16, 30, 80, 20);

        // RadioVereador
        jrdVereador = new JRadioButton("Vereador", false);
        jrdVereador.setBounds(100, 30, 80, 20);

        //ADD elementos ao grupo
        group.add(jrdPrefeito);
        group.add(jrdVereador);

        //Handler
        jrdPrefeito.addItemListener(handler);
        jrdVereador.addItemListener(handler);

        // ADD elementos ao conteudoRetorno
        conteudoRetorno.add(jrdPrefeito);
        conteudoRetorno.add(jrdVereador);

        //ADD
        container.add(lblCamposObri);
        container.add(lblNumeroEleitor);
        container.add(txtNumeroEleitor);
        container.add(lblImagemPesquisa);

        container.add(btnImprimir);
        container.add(btnPesquisar);

        container.add(conteudoRetorno);

        //Configurações
        setClosable(true);
        setIconifiable(true);
        setLocation(100, 50);
    }

    private class RadioButtonHandler implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent event) {
            if (jrdPrefeito.isSelected()) {
                prefeito = true;
            }
            if (jrdVereador.isSelected()) {
                prefeito = false;
            }
        }

    }

    private void clearCampos() {
        txtNumeroEleitor.setText("");
    }

    // Atributos da Classe
    private JLabel lblCamposObri;
    private JLabel lblNumeroEleitor;
    private JLabel lblImagemPesquisa;
    private JRadioButton jrdPrefeito;
    private JRadioButton jrdVereador;
    private JFormattedTextField txtNumeroEleitor;
    private JButton btnImprimir;
    private JButton btnPesquisar;
    private ImageIcon imgPesquisar;
    private MaskFormatter formatTxtNumeroEleitor;
    private RadioButtonHandler handler;
    private boolean prefeito = true;
}
