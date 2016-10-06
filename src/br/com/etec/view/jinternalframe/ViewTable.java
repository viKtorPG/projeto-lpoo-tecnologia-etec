/*
 * Tela responsavel por cadastra eleitores
 */
package br.com.etec.view.jinternalframe;

import br.com.etec.utils.DbUtils;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jose
 */
public class ViewTable {

    public ViewTable(String sql) {
        this.sql = sql;
    }

    public void execute() {

        // Criação da Tela
        final JFrame jf = new JFrame("Desktop");
        jf.setSize(800, 300);
        jf.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        // Panel que será responsavel por add todos os elementos
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Obrigatorio
        JLabel lblCamposObri = new JLabel("(*)Campos Obrigatórios");
        lblCamposObri.setBounds(600, 15, 200, 20);
        lblCamposObri.setForeground(Color.red);
        
        //Pesquisa Eleitor
        lblNomePes = new JLabel("*Pesquisa");
        lblNomePes.setBounds(10, 10, 80, 25);

        txtNomePes = new JTextField();
        txtNomePes.setBounds(90, 10, 200, 25);
        txtNomePes.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                pesqTable();
                tblPartido.setVisible(true);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                tblPartido.setVisible(true);
            }
        });

        //Tabel Eleitor
        jScrollPane = new JScrollPane();
        jScrollPane.setBounds(10, 40, 770, 200);
        tblPartido = new JTable();
        tblPartido.setModel(new DefaultTableModel(
                new Object[][]{
                    {null, null, null},
                    {null, null, null},
                    {null, null, null}
                },
                new String[]{
                    "ID", "Nome", "Sigla", "Slogan", "Numero", "Criação", "Votos"
                }
        ));
        //Evento de selecionar um determinado eleitor retornado pelo banco de dados
        tblPartido.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                
            }
        });
        jScrollPane.setViewportView(tblPartido);

        //Add elementos ao Panel
        panel.add(jScrollPane);
        panel.add(txtNomePes);
        panel.add(lblNomePes);


        // Add elementos ao JFrame
        jf.add(panel);
        jf.setVisible(true);  
        jf.setResizable(false);    
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    /* Método responsável pela query no banco de dados
    e pelo preenchimento da tabela. */
    private void pesqTable() {
        ResultSet resultSet;
        try {
            connection = DbUtils.getConnection();

            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            statement.setString(1,  txtNomePes.getText() + "%");
            
            resultSet = statement.executeQuery();

            tblPartido.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(resultSet));

            if (connection != null) {
                connection.close();
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void formWindowActivated(java.awt.event.WindowEvent evt) {                                     
        pesqTable();
    } 

    private JLabel lblNomePes;
    private JTextField txtNomePes;
    
    private JTable tblPartido;
    private JScrollPane jScrollPane;

    private String sql;
    
    private Connection connection;
}
