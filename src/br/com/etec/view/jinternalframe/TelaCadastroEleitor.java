/*
 * Tela responsavel por cadastra eleitores
 */
package br.com.etec.view.jinternalframe;

import br.com.etec.dao.EleitorDao;
import br.com.etec.model.Eleitor;
import br.com.etec.utils.CidadeEstado;
import br.com.etec.utils.Data;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
        txtCodEleitor.setEditable(false);

        // Zona
        lblZona = new JLabel("Zona");
        lblZona.setBounds(485, 120, 80, 25);
        lblZona.setForeground(Color.black);

        jcZona = new JComboBox<>();
        jcZona.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"301", "302", "303", "304", "305", "306", "307", "308", "309", "310"}));
        jcZona.setBounds(460, 150, 80, 25);

        // Seção
        lblSecao = new JLabel("Seção");
        lblSecao.setBounds(565, 120, 80, 25);
        lblSecao.setForeground(Color.black);

        jcSecao = new JComboBox<>();
        jcSecao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"0160", "0161", "0162", "0163", "0164", "0165", "0166", "0167", "0168", "0169", "0170"}));
        jcSecao.setBounds(545, 150, 80, 25);

        // Nome
        lblNome = new JLabel("*Nome");
        lblNome.setBounds(200, 180, 80, 25);
        lblNome.setForeground(Color.black);

        txtNome = new JTextField(10);
        txtNome.setBounds(255, 180, 200, 25);

        /*
        // Data Emissão
        lblDataEmissao = new JLabel("Data de Emissão");
        lblDataEmissao.setBounds(490, 180, 150, 25);
        lblDataEmissao.setForeground(Color.black);

        txtDataEmissao = new JTextField(10);
        txtDataEmissao.setBounds(460, 210, 165, 25);
         */
        // Data Nascimento
        lblDataNascimento = new JLabel("Data de Nascimento");
        lblDataNascimento.setBounds(130, 210, 150, 25);
        lblDataNascimento.setForeground(Color.black);

        jdNascimento = new JDateChooser();
        jdNascimento.setBounds(255, 210, 150, 25);

        // Cidade/Estado
        lblCidadeMuni = new JLabel("*Cidade/Estado");
        lblCidadeMuni.setBounds(140, 240, 150, 25);
        lblCidadeMuni.setForeground(Color.black);

        try {
            jcEstado = new JComboBox<>();
            jcEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new CidadeEstado().allEstado()));
            jcEstado.setBounds(400, 240, 55, 25);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar estados");
            System.err.println(ex.getMessage());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(TelaCadastroEleitor.class.getName()).log(Level.SEVERE, null, ex);
        }

        jcCidadeMuni = new JComboBox<>();
        jcEstado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jcCidadeMuni.setModel(new javax.swing.DefaultComboBoxModel<>(new CidadeEstado().allCidade(jcEstado.getSelectedIndex() + 1)));
                } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                    Logger.getLogger(TelaCadastroEleitor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        jcCidadeMuni.setBounds(255, 240, 140, 25);

        // Buttons
        ImageIcon imgAdicionar = new ImageIcon(getClass().getResource("/br/com/etec/imgs/add.png"));
        btnAdicionar = new JButton(imgAdicionar);
        btnAdicionar.setToolTipText("Adicionar");
        btnAdicionar.setBounds(260, 300, 60, 60);
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Eleitor addEleitor = new Eleitor();

                addEleitor.setNome(txtNome.getText());
                //Date date = jdNascimento.getDate();
                addEleitor.setDataNascimento(Data.convertSql(jdNascimento.getDate()));
                addEleitor.setZona(jcZona.getSelectedItem().toString());
                addEleitor.setSecao(jcSecao.getSelectedItem().toString());
                try {
                    addEleitor.setIdCidade(new CidadeEstado().retornaIdCidade(jcCidadeMuni.getSelectedItem().toString(), new CidadeEstado().retornaIdEstado((String) jcEstado.getSelectedItem())));
                } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                    Logger.getLogger(TelaCadastroEleitor.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    new EleitorDao().insert(addEleitor);
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException | HeadlessException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário " + ex.getMessage());
                }
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
        container.add(jcZona);

        container.add(lblSecao);
        container.add(jcSecao);

        container.add(lblNome);
        container.add(txtNome);

        container.add(lblDataNascimento);
        container.add(jdNascimento);

        container.add(lblCidadeMuni);
        container.add(jcCidadeMuni);
        container.add(jcEstado);

        /*container.add(lblDataEmissao);
        container.add(txtDataEmissao);*/
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
    //private JLabel lblDataEmissao;
    private JTextField txtCodEleitor;
    private JTextField txtNome;
    private JDateChooser jdNascimento;
    private JComboBox<String> jcCidadeMuni;
    private JComboBox<String> jcZona;
    private JComboBox<String> jcSecao;
    //private JTextField txtDataEmissao;
    private JComboBox<String> jcEstado;
    private JButton btnAdicionar;
    private JButton btnAtualizar;
    private JButton btnExcluir;
    private JButton btnPesquisar;
}
