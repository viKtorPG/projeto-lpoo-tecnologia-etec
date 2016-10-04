/*
 * Tela para imprimir 2º via do titulo de eleitor.
 */
package br.com.etec.view.jinternalframe;

import br.com.etec.dao.VereadorDao;
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
public class TelaGerarRelatorioCandidato extends JInternalFrame {

    public TelaGerarRelatorioCandidato() {
        iniciandoCompomentes();

    }

    private void iniciandoCompomentes() {
        setTitle("Gerar relatório vereador");
        setSize(800, 500);

        //Panel
        Container container = getContentPane();
        container.setLayout(null);

        //Formatando o campo NumeroEleitor
        try {
            formatTxtNumeroEleitor = new MaskFormatter("#####");
            formatTxtNumeroEleitor.setPlaceholderCharacter('_');
        } catch (ParseException ex) {
            Logger.getLogger(TelaGerarRelatorioCandidato.class.getName()).log(Level.SEVERE, null, ex);
        }

        lblCamposObri = new JLabel("(*)Campos Obrigatórios");
        lblCamposObri.setBounds(600, 20, 200, 20);
        lblCamposObri.setForeground(Color.red);

        //Número do Eleitor
        lblNumeroEleitor = new JLabel("*Número do candidato");
        lblNumeroEleitor.setBounds(200, 150, 130, 25);

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
                paramEleitor.put("paramsNumeroVereador", Integer.parseInt(lblNumeroCandidatoRetorno.getText()));
                
                int confirmar = JOptionPane.showConfirmDialog(null, "Conrfima a impressão desse relatorio", "Atenção", JOptionPane.YES_NO_OPTION);
                if (confirmar == JOptionPane.YES_NO_OPTION) {
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                Connection connection = DbUtils.getConnection();
                                JasperPrint viewer = JasperFillManager.fillReport("src/br/com/etec/ireport/projectVereador.jasper", paramEleitor, connection);

                                JasperViewer.viewReport(viewer, false);
                            } catch (JRException | ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                                Logger.getLogger(TelaDesktop.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }.start();
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

                } else {
                    try {
                        Vereador vereador = new VereadorDao().findById(Integer.parseInt(txtNumeroEleitor.getText()));
                        lblNomeRetorno.setText(vereador.getNome());
                        lblNumeroCandidatoRetorno.setText(String.valueOf(vereador.getNumero()));
                        lblDataCadastramentoRetorno.setText(vereador.getDataNascimento());

                        btnImprimir.setEnabled(true);
                        
                        txtNumeroEleitor.setText(null);
                    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Não existe vereador com esse número");
                        clearCampos();
                    }catch (java.lang.NullPointerException npe){
                        JOptionPane.showMessageDialog(null, "Não existe vereador com esse número");
                        clearCampos();
                    }
                }
            }
        });

        //Panel de retorno
        JPanel conteudoRetorno = new JPanel();
        conteudoRetorno.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações do candidato"));
        conteudoRetorno.setLayout(null);
        //conteudoRetorno.setBackground(Color.red);
        conteudoRetorno.setBounds(450, 120, 250, 230);

        //Nome
        lblNome = new JLabel("Nome:");
        lblNome.setBounds(16, 30, 50, 20);

        lblNomeRetorno = new JLabel("**************");
        lblNomeRetorno.setBounds(16, 50, 200, 20);

        // Número do candidato
        lblNumeroCandidato = new JLabel("Número do candidato");
        lblNumeroCandidato.setBounds(16, 80, 200, 20);

        lblNumeroCandidatoRetorno = new JLabel("**************");
        lblNumeroCandidatoRetorno.setBounds(16, 100, 200, 20);

        // Data de cadastramento
        lblDataCadastramento = new JLabel("Data de cadastramento");
        lblDataCadastramento.setBounds(16, 130, 200, 20);

        lblDataCadastramentoRetorno = new JLabel("**************");
        lblDataCadastramentoRetorno.setBounds(16, 150, 200, 20);

        // ADD elementos ao conteudoRetorno
        conteudoRetorno.add(lblNome);
        conteudoRetorno.add(lblNomeRetorno);

        conteudoRetorno.add(lblNumeroCandidato);
        conteudoRetorno.add(lblNumeroCandidatoRetorno);

        conteudoRetorno.add(lblDataCadastramento);
        conteudoRetorno.add(lblDataCadastramentoRetorno);

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
    
    private void clearCampos(){
        txtNumeroEleitor.setText(null);
        lblDataCadastramentoRetorno.setText("----------------");
        lblNomeRetorno.setText("----------------");
        lblNumeroCandidatoRetorno.setText("----------------");
    }
    
    // Atributos da Classe
    private JLabel lblCamposObri;
    private JLabel lblNumeroEleitor;
    private JLabel lblImagemPesquisa;
    private JLabel lblNome;
    private JLabel lblNomeRetorno;
    private JLabel lblNumeroCandidato;
    private JLabel lblNumeroCandidatoRetorno;
    private JLabel lblDataCadastramento;
    private JLabel lblDataCadastramentoRetorno;
    private JFormattedTextField txtNumeroEleitor;
    private JButton btnImprimir;
    private JButton btnPesquisar;
    private ImageIcon imgPesquisar;
    private MaskFormatter formatTxtNumeroEleitor;
}
