package br.com.etec.view.jframe;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author jose
 */
public class TelaSobre extends JDialog{

    public TelaSobre(){
        super.setSize(400, 330);
    }
    
    public void execute() {
        /*// Criação da Tela
        final JFrame jf = new JFrame("Sobre");
        jf.setSize(400, 330);*/

        // Panel que será responsavel por add todos os elementos
        JPanel panel = new JPanel();
        panel.setLayout(null);
        
        
        //
        JLabel lblNomeS = new JLabel("<html><body><center><h2>Sistema de cadastro e controle eleitoral.</h2></center></body></html>");
        lblNomeS.setBounds(10, 20, 380, 80);
        
        JLabel lblInfo = new JLabel("<html><body><center><h4>2016 - 2° IIA Informática (Turma B)</h4></center></body></html>");
        lblInfo.setBounds(10, 100, 380, 80);
        
        JLabel lblProf = new JLabel("<html><body><center><h5>Professor Turma B - Carlos</h5></center></body></html>");
        lblProf.setBounds(10, 180, 380, 80);
        
        //Logo
        ImageIcon logo = new ImageIcon(getClass().getResource("/br/com/etec/imgs/logo_cps.png"));
        JLabel lblLogo = new JLabel(logo);
        lblLogo.setBounds(0, 175, 400, 200);
        
        panel.add(lblNomeS);
        panel.add(lblInfo);
        panel.add(lblProf);
        panel.add(lblLogo);
        
         // Add elementos ao JFrame
        super.add(panel);
        super.setVisible(true);
        super.setResizable(false);
        super.setModal(true);
        super.setLocationRelativeTo(new TelaDesktop());
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
