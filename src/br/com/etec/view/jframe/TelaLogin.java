/*
 * Tela de Login de Usuário
 */
package br.com.etec.view.jframe;

import br.com.etec.persist.Login;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author jose
 */
public class TelaLogin{
    
    public static void execute(String title){
         // Criação da Tela
        final JFrame jf = new JFrame(title);
        jf.setSize(400, 330);

        // Panel que será responsavel por add todos os elementos
        JPanel panel = new JPanel();
        panel.setLayout(null);
        
        //Panel Top
        JPanel panelTop = new JPanel();
        panelTop.setBounds(0, 0, 400, 60);
        panelTop.setLayout(null);
        panelTop.setBackground(new Color(0, 51, 204));
        
        JLabel lblPanelTopeEtec = new JLabel("Etec - ");
        lblPanelTopeEtec.setFont(new Font("Comic San", Font.BOLD, 20));
        lblPanelTopeEtec.setForeground(Color.WHITE);
        lblPanelTopeEtec.setBounds(70, 20, 65, 20);
        
        JLabel lblPanelTopCadastro = new JLabel("Cadastro de eleitores");
        lblPanelTopCadastro.setFont(new Font("Comic San", Font.BOLD, 20));
        lblPanelTopCadastro.setForeground(Color.ORANGE);
        lblPanelTopCadastro.setBounds(130, 20, 300, 20);
        
        // Panel Center
        JPanel panelCenter = new JPanel();
        panelCenter.setBounds(0, 60, 393, 205);
        panelCenter.setLayout(null);
        panelCenter.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        JLabel lblUsuario = new JLabel("Usuário");
        lblUsuario.setBounds(90, 20, 100, 20);
        
        final JTextField txtUsuario = new JTextField();
        txtUsuario.setBounds(90, 45, 200, 30);
        
        
        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setBounds(90, 80, 100, 20);
        
        final JPasswordField txtSenha = new JPasswordField();
        txtSenha.setBounds(90, 105, 200, 30);
        
        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(30, 150, 80, 30);
        jf.getRootPane().setDefaultButton(btnLogin);
        final Login login = new Login();
        
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(login.logar(txtUsuario.getText(), txtSenha.getText()) == true){
                    jf.dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "******************");
                }
                
            }
        });
        
        // Panel Below
        JPanel panelBelow = new JPanel();
        panelBelow.setBounds(0, 265, 393, 40);
        panelBelow.setLayout(null);
        panelBelow.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelBelow.setBackground(new Color(0, 51, 204));
        
        JLabel lblCopri = new JLabel("Sistem de cadastro de eleitores 2° IIA - 2016");
        lblCopri.setBounds(70, 5, 300, 20);
        lblCopri.setForeground(Color.WHITE);
        
        //Add elementos ao Panel
        panel.add(panelTop);
        panel.add(panelCenter);
        panel.add(panelBelow);
        
        //Add elementos PanelTop
        panelTop.add(lblPanelTopeEtec);
        panelTop.add(lblPanelTopCadastro);
        
        //Add elementos PanelCenter
        panelCenter.add(lblUsuario);
        panelCenter.add(txtUsuario);
        panelCenter.add(lblSenha);
        panelCenter.add(txtSenha);
        panelCenter.add(btnLogin);
        
        //Add elementos PanelBelow
        panelBelow.add(lblCopri);
        
        // Add elementos ao JFrame
        jf.add(panel);
        jf.setVisible(true);
        jf.setResizable(false);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
