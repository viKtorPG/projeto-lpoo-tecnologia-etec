/*
 * Tela responsavel por cadastra eleitores
 */
package br.com.etec.view.jinternalframe;

import br.com.etec.dao.EleitorDao;
import br.com.etec.model.Eleitor;
import br.com.etec.utils.CidadeEstado;
import br.com.etec.utils.Data;
import br.com.etec.utils.DbUtils;
import br.com.etec.utils.ValidaData;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

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

        // Obrigatorio
        JLabel lblCamposObri = new JLabel("(*)Campos Obrigatórios");
        lblCamposObri.setBounds(600, 15, 200, 20);
        lblCamposObri.setForeground(Color.red);

        //Formatando o campo NumeroEleitor
        try {
            formatTxtNumeroEleitor = new MaskFormatter("### ### ### ###");
        } catch (ParseException ex) {
            Logger.getLogger(TelaImprimirSegundaVia.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Pesquisa Eleitor
        lblNomePes = new JLabel("*Nome");
        lblNomePes.setBounds(10, 10, 50, 25);

        txtNomePes = new JTextField();
        txtNomePes.setBounds(65, 10, 200, 25);
        txtNomePes.addKeyListener(new KeyListener() {
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

        //Tabel Eleitor
        jScrollPane = new JScrollPane();
        jScrollPane.setBounds(10, 40, 770, 100);
        tblEleitor = new JTable();
        tblEleitor.setModel(new DefaultTableModel(
                new Object[][]{
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
                tblEleitorMouseClicked(evt);
            }
        });
        jScrollPane.setViewportView(tblEleitor);

        // Cod Eleitor
        lblCodEleitor = new JLabel("Código do Eleitor");
        lblCodEleitor.setBounds(150, 150, 200, 25);
        lblCodEleitor.setForeground(Color.black);

        txtCodEleitor = new JFormattedTextField(formatTxtNumeroEleitor);
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
        jcSecao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"160", "161", "162", "163", "164", "165", "166", "167", "168", "169", "170"}));
        jcSecao.setBounds(545, 150, 80, 25);

        // Nome
        lblNome = new JLabel("*Nome");
        lblNome.setBounds(200, 180, 80, 25);
        lblNome.setForeground(Color.black);

        txtNome = new JTextField(10);
        txtNome.setBounds(255, 180, 200, 25);

        // Data Nascimento
        lblDataNascimento = new JLabel("Data de Nascimento");
        lblDataNascimento.setBounds(130, 210, 150, 25);
        lblDataNascimento.setForeground(Color.black);

        jdNascimento = new JDateChooser();
        jdNascimento.setBounds(255, 210, 150, 25);
        jdNascimento.setDate(new Date());
        jdNascimento.setDateFormatString("dd/MM/yyyy");

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
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
        }

        jcCidadeMuni = new JComboBox<>();
        jcEstado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jcCidadeMuni.setModel(new javax.swing.DefaultComboBoxModel<>(new CidadeEstado().allCidade(jcEstado.getSelectedIndex() + 1)));
                } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                    JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
                }
            }
        });
        jcCidadeMuni.setBounds(255, 240, 140, 25);

        // Buttons + AddEleitor
        ImageIcon imgAdicionar = new ImageIcon(getClass().getResource("/br/com/etec/imgs/add.png"));
        btnAdicionar = new JButton(imgAdicionar);
        btnAdicionar.setToolTipText("Adicionar");
        btnAdicionar.setBounds(260, 300, 60, 60);
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Eleitor addEleitor = new Eleitor();

                try {
                    addEleitor.setNome(txtNome.getText());
                    addEleitor.setDataNascimento(Data.convertSql(jdNascimento.getDate()));
                    addEleitor.setZona(jcZona.getSelectedItem().toString());
                    addEleitor.setSecao(jcSecao.getSelectedItem().toString());

                    if (txtNome.getText().isEmpty() || jcCidadeMuni.getSelectedIndex() < 0) {
                        JOptionPane.showMessageDialog(null, "Todos os campos (*) obrigatórios");
                    } else if (!(ValidaData.validarData(Data.convertString(jdNascimento.getDate())))) {
                        JOptionPane.showMessageDialog(null, "Data inválida");
                    } else {
                        addEleitor.setIdCidade(new CidadeEstado().retornaIdCidade(jcCidadeMuni.getSelectedItem().toString(), new CidadeEstado().retornaIdEstado((String) jcEstado.getSelectedItem())));
                        new EleitorDao().insert(addEleitor);
                        JOptionPane.showMessageDialog(null, "Eleitor cadastradado com sucesso!");
                    }
                } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException | HeadlessException ex) {
                    JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
                } catch (NullPointerException npe) {
                    JOptionPane.showMessageDialog(null, "Data inválida");
                }
            }
        });

        //Button Atualizar
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

        //Button Excluir
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

        //Add elementos so panel
        container.add(lblCamposObri);

        container.add(lblNomePes);
        container.add(txtNomePes);
        container.add(jScrollPane);

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

        container.add(btnAdicionar);
        container.add(btnAtualizar);
        container.add(btnExcluir);

        setClosable(true);
        setIconifiable(true);
        setLocation(100, 50);

    }

    /* Método que seta os valores que serão retornados
    pela seleção de uma linha na tabela eleitor. */
    public void setCampos() {
        int rowLine = tblEleitor.getSelectedRow();
        txtCodEleitor.setText(tblEleitor.getModel().getValueAt(rowLine, 0).toString());
        txtNome.setText(tblEleitor.getModel().getValueAt(rowLine, 1).toString());
        jdNascimento.setDate(Data.convertDate(tblEleitor.getModel().getValueAt(rowLine, 2).toString()));
        jcZona.setSelectedItem(tblEleitor.getModel().getValueAt(rowLine, 4).toString());
        jcSecao.setSelectedItem(tblEleitor.getModel().getValueAt(rowLine, 5).toString());
        jcEstado.setSelectedItem(tblEleitor.getModel().getValueAt(rowLine, 7).toString());
        jcCidadeMuni.setSelectedItem(tblEleitor.getModel().getValueAt(rowLine, 6).toString());
    }

    /* Método responsável pela query no banco de dados
    e pelo preenchimento da tabela. */
    private void pesqEleitor() {
        ResultSet resultSet;
        try {
            connection = DbUtils.getConnection();

            String sql = "select eleitor.id_eleitor as ID, eleitor.nome as Nome,"
                    + " DATE_FORMAT(eleitor.data_nascimento, '%d/%m/%y') as Nascimento,"
                    + " DATE_FORMAT(eleitor.data_emissao, '%d/%m/%Y') as Emissao,"
                    + " eleitor.zona as Zona, eleitor.secao as Secao, cidade.nome as Cidade, estado.uf as UF \n"
                    + "from eleitor left join cidade \n"
                    + "on eleitor.id_cidade = cidade.id_cidade \n"
                    + "left join estado \n"
                    + "on cidade.id_estado = estado.id_estado \n"
                    + "where eleitor.nome like ?";
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);

            statement.setString(1, txtNomePes.getText() + "%");

            resultSet = statement.executeQuery();

            tblEleitor.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(resultSet));

            if (connection != null) {
                connection.close();
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
        }
    }

    /* Método que seleciona uma determinada 
    linha na tabela e chama o método responsável 
    pelo preenchimento dos campos. */
    private void tblEleitorMouseClicked(java.awt.event.MouseEvent evt) {
        setCampos();
    }

    /* Evento que verifica se o campo de pesquisa
    de eleitor está vazio, se sim a tabela e desabilitada. */
    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
        if (txtNomePes.getText().isEmpty()) {
            tblEleitor.setVisible(false);
        }
    }

    private JLabel lblCodEleitor;
    private JLabel lblNome;
    private JLabel lblDataNascimento;
    private JLabel lblCidadeMuni;
    private JLabel lblZona;
    private JLabel lblSecao;
    private JLabel lblNomePes;

    private JTextField txtNome;
    private JTextField txtNomePes;

    private JComboBox<String> jcCidadeMuni;
    private JComboBox<String> jcZona;
    private JComboBox<String> jcSecao;
    private JComboBox<String> jcEstado;

    private JButton btnAdicionar;
    private JButton btnAtualizar;
    private JButton btnExcluir;

    private JTable tblEleitor;
    private JScrollPane jScrollPane;
    private JDateChooser jdNascimento;

    private JFormattedTextField txtCodEleitor;
    private MaskFormatter formatTxtNumeroEleitor;

    private Connection connection;
}
