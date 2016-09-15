/*
 * Tela para imprimir 2º via do titulo de eleitor.
 */
package br.com.etec.view.jinternalframe;

import java.awt.Color;
import java.awt.Container;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

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

        //Panel
        Container container = getContentPane();
        container.setLayout(null);
        
        //Formatando o campo NumeroEleitor
        try{
            formatTxtNumeroEleitor = new MaskFormatter("#### #### #### ####");
        } catch (ParseException ex) {
            Logger.getLogger(TelaImprimirSegundaVia.class.getName()).log(Level.SEVERE, null, ex);
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
        ImageIcon imgImprimir = new ImageIcon(getClass().getResource("/br/com/etec/imgs/print.png"));
        btnImprimir = new JButton(imgImprimir);
        btnImprimir.setToolTipText("Imprimir 2° via");
        btnImprimir.setBounds(190, 250, 60, 60);
        
        ImageIcon imgPesquisar = new ImageIcon(getClass().getResource("/br/com/etec/imgs/search.png"));
        btnPesquisar = new JButton(imgPesquisar);
        btnPesquisar.setToolTipText("Pesquisar");
        btnPesquisar.setBounds(260, 250, 60, 60);
        
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
        setClosable( true);
        setIconifiable(true); 
        setLocation(100, 50);
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
