/*
 * Tela responsavel por cadastra eleitores
 */
package br.com.etec.view.jinternalframe;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author jose
 */
public class TelaCadastroEleitor extends JInternalFrame {

    public TelaCadastroEleitor() {
        iniciandoCompomentes();
    }

    private void iniciandoCompomentes() {
        setTitle("Eleitor");
        setSize(800, 500);

        //Panel
        Container container = getContentPane();
        container.setLayout(null);

        // Obrigatorio
        JLabel lblCamposObri = new JLabel("(*)Campos Obrigatórios");
        lblCamposObri.setBounds(600, 20, 200, 20);
        lblCamposObri.setForeground(Color.red);
        
        // Cod Eleitor
        lblCodEleitor = new JLabel("Código do Eleitor");
        lblCodEleitor.setBounds(150, 150, 200, 25);
        lblCodEleitor.setForeground(Color.black);

        txtCodEleitor = new JTextField(10);
        txtCodEleitor.setBounds(255, 150, 200, 25);

        // Zona
        lblZona = new JLabel("Zona");
        lblZona.setBounds(485, 120, 80, 25);
        lblZona.setForeground(Color.black);

        txtZona = new JTextField(10);
        txtZona.setBounds(460, 150, 80, 25);

        // Seção
        lblSecao = new JLabel("Seção");
        lblSecao.setBounds(565, 120, 80, 25);
        lblSecao.setForeground(Color.black);

        txtSecao = new JTextField(10);
        txtSecao.setBounds(545, 150, 80, 25);

        // Nome
        lblNome = new JLabel("*Nome");
        lblNome.setBounds(200, 180, 80, 25);
        lblNome.setForeground(Color.black);

        txtNome = new JTextField(10);
        txtNome.setBounds(255, 180, 200, 25);

        // Data Emissão
        lblDataEmissao = new JLabel("Data de Emissão");
        lblDataEmissao.setBounds(490, 180, 150, 25);
        lblDataEmissao.setForeground(Color.black);

        txtDataEmissao = new JTextField(10);
        txtDataEmissao.setBounds(460, 210, 165, 25);

        // Data Nascimento
        lblDataNascimento = new JLabel("Data de Nascimento");
        lblDataNascimento.setBounds(130, 210, 150, 25);
        lblDataNascimento.setForeground(Color.black);

        jdNascimento = new JDateChooser();
        jdNascimento.setBounds(255, 210, 150, 25);

        // Cidade/Estado
        lblCidadeMuni = new JLabel("*Cidade/Municipio");
        lblCidadeMuni.setBounds(140, 240, 150, 25);
        lblCidadeMuni.setForeground(Color.black);

        txtCidadeMuni = new JTextField(10);
        txtCidadeMuni.setBounds(255, 240, 140, 25);

        jcEstado = new JComboBox<>();
        jcEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"SP", "RJ"}));
        jcEstado.setBounds(400, 240, 55, 25);

        // Buttons
        ImageIcon imgAdicionar = new ImageIcon(getClass().getResource("/br/com/etec/imgs/add.png"));
        btnAdicionar = new JButton(imgAdicionar);
        btnAdicionar.setToolTipText("Adicionar");
        btnAdicionar.setBounds(260, 300, 60, 60);
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        ImageIcon imgAtualizar = new ImageIcon(getClass().getResource("/br/com/etec/imgs/update.png"));
        btnAtualizar = new JButton(imgAtualizar);
        btnAtualizar.setToolTipText("Atualizar");
        btnAtualizar.setBounds(330, 300, 60, 60);
        btnAtualizar.setEnabled(false);
        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        }
        );

        ImageIcon imgExcluir = new ImageIcon(getClass().getResource("/br/com/etec/imgs/delete.png"));
        btnExcluir = new JButton(imgExcluir);
        btnExcluir.setToolTipText("Excluir");
        btnExcluir.setBounds(400, 300, 60, 60);
        btnExcluir.setEnabled(false);
        btnExcluir.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {

            }
        }
        );

        ImageIcon imgPesquisar = new ImageIcon(getClass().getResource("/br/com/etec/imgs/search.png"));
        btnPesquisar = new JButton(imgPesquisar);
        btnPesquisar.setToolTipText("Pesquisar");
        btnPesquisar.setBounds(470, 300, 60, 60);
        btnPesquisar.setToolTipText("Pesquisar");
        btnPesquisar.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {

            }
        }
        );

        //Add
        container.add(lblCamposObri);
        
        container.add(lblCodEleitor);
        container.add(txtCodEleitor);

        container.add(lblZona);
        container.add(txtZona);

        container.add(lblSecao);
        container.add(txtSecao);

        container.add(lblNome);
        container.add(txtNome);

        container.add(lblDataNascimento);
        container.add(jdNascimento);

        container.add(lblCidadeMuni);
        container.add(txtCidadeMuni);
        container.add(jcEstado);

        container.add(lblDataEmissao);
        container.add(txtDataEmissao);

        container.add(btnAdicionar);
        container.add(btnAtualizar);
        container.add(btnExcluir);
        container.add(btnPesquisar);

        setClosable(true);
        setIconifiable(true);
        setLocation(100, 50);

    }

    private JLabel lblCodEleitor;
    private JLabel lblNome;
    private JLabel lblDataNascimento;
    private JLabel lblCidadeMuni;
    private JLabel lblZona;
    private JLabel lblSecao;
    private JLabel lblDataEmissao;
    private JTextField txtCodEleitor;
    private JTextField txtNome;
    private JDateChooser jdNascimento;
    private JTextField txtCidadeMuni;
    private JTextField txtZona;
    private JTextField txtSecao;
    private JTextField txtDataEmissao;
    private JComboBox<String> jcEstado;
    private JButton btnAdicionar;
    private JButton btnAtualizar;
    private JButton btnExcluir;
    private JButton btnPesquisar;
}
