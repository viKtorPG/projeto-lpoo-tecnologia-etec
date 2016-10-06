/*
 * Tela para imprimir 2º via do titulo de eleitor.
 */
package br.com.etec.view.jinternalframe;

import br.com.etec.dao.EleitorDao;
import br.com.etec.dao.Validar;
import br.com.etec.dao.VereadorDao;
import br.com.etec.model.Eleitor;
import br.com.etec.model.Vereador;
import br.com.etec.utils.DbUtils;
import br.com.etec.view.jframe.TelaDesktop;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
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
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author jose
 */
public class TelaValidarVoto extends JInternalFrame {

    public TelaValidarVoto() {
        iniciandoCompomentes();

    }

    private void iniciandoCompomentes() {
        setTitle("Validar voto");
        setSize(800, 500);

        //Panel
        Container container = getContentPane();
        container.setLayout(null);

        //Formatando o campo NumeroEleitor
        try {
            formatTxtNumeroEleitor = new MaskFormatter("#### #### ####");
            formatTxtNumeroEleitor.setPlaceholderCharacter('_');
        } catch (ParseException ex) {
            Logger.getLogger(TelaValidarVoto.class.getName()).log(Level.SEVERE, null, ex);
        }

        lblCamposObri = new JLabel("(*)Campos Obrigatórios");
        lblCamposObri.setBounds(600, 20, 200, 20);
        lblCamposObri.setForeground(Color.red);

        //Número do Eleitor
        lblNumeroEleitor = new JLabel("*Número do eleitor");
        lblNumeroEleitor.setBounds(210, 150, 120, 25);

        //Número do eleitor caixa de texto
        txtNumeroEleitor = new JFormattedTextField(formatTxtNumeroEleitor);
        txtNumeroEleitor.setBounds(160, 180, 200, 25);

        //Imagem de pesquisa
        imgPesquisar = new ImageIcon(getClass().getResource("/br/com/etec/imgs/seo.png"));
        lblImagemPesquisa = new JLabel(imgPesquisar);
        lblImagemPesquisa.setBounds(110, 160, 50, 50);

        //Buttons
        ImageIcon imgImprimir = new ImageIcon(getClass().getResource("/br/com/etec/imgs/check.png"));
        btnImprimir = new JButton(imgImprimir);
        btnImprimir.setToolTipText("Validar");
        btnImprimir.setBounds(190, 250, 60, 60);
        btnImprimir.setEnabled(false);
        btnImprimir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = txtNumeroEleitor.getText().replaceAll(" ", "");
                try {
                    if (!new Validar().verificaEleitor(Long.parseLong(result))) {
                        new Validar().validaVoto(Long.parseLong(result));
                        JOptionPane.showMessageDialog(null, "Voto confirmado");
                        imprime(Long.parseLong(result));
                        clearCampos();
                        btnImprimir.setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Eleitor já votou");
                        imprime(Long.parseLong(result));
                        clearCampos();
                        btnImprimir.setEnabled(false);
                    }
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(TelaValidarVoto.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Erro de validação/ou impressão do comprovante" + ex.getMessage(), "Erro do sistema", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        ImageIcon imgPesquisar = new ImageIcon(getClass().getResource("/br/com/etec/imgs/search.png"));
        btnPesquisar = new JButton(imgPesquisar);
        btnPesquisar.setToolTipText("Pesquisar");
        btnPesquisar.setBounds(260, 250, 60, 60);
        btnPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtNumeroEleitor.getText().equals("____ ____ ____")) {
                    JOptionPane.showMessageDialog(null, "Campos (*) obrigatórios");
                } else {

                    String result = txtNumeroEleitor.getText().replaceAll(" ", "");
                    System.err.println(result);
                    try {
                        Eleitor eleitor = new EleitorDao().findById(Long.parseLong(result));

                        if (eleitor != null) {
                            lblNomeRetorno.setText(eleitor.getNome());
                            lblDataCadastramentoRetorno.setText(eleitor.getDataEmissao());
                            lblSecaoRetorno.setText(eleitor.getSecao());
                            lblZonaRetorno.setText(eleitor.getZona());

                            btnImprimir.setEnabled(true);
                        }else{
                            clearCampos();
                        }

                    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Não existe vereador com esse número");
                    }
                }
            }
        });

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

    private void imprime(long numero) {
        int confirmar = JOptionPane.showConfirmDialog(null, "Conrfima a impressão desse relatorio", "Atenção", JOptionPane.YES_NO_OPTION);

        final Map<String, Object> paramEleitor = new HashMap<>();
        paramEleitor.put("paramsNumero", numero);
        paramEleitor.put("paramasIdNumero", numero);

        if (confirmar == JOptionPane.YES_NO_OPTION) {
            new Thread() {
                @Override
                public void run() {
                    try {

                        Connection connection = DbUtils.getConnection();
                        JasperPrint viewer = JasperFillManager.fillReport("src/br/com/etec/ireport/projectValido.jasper", paramEleitor, connection);

                        JasperViewer.viewReport(viewer, false);
                    } catch (JRException | ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao imprimir comprovante " + ex.getMessage(), "Erro impressão", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }.start();
        }
    }

    private void clearCampos() {
        txtNumeroEleitor.setText(null);
        lblNomeRetorno.setText("-----------------");
        lblDataCadastramentoRetorno.setText("-------------");
        lblSecaoRetorno.setText("----");
        lblZonaRetorno.setText("----");
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
    private JButton btnImprimir;
    private JButton btnPesquisar;
    private ImageIcon imgPesquisar;
    private MaskFormatter formatTxtNumeroEleitor;
}
