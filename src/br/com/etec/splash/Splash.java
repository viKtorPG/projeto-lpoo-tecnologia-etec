/*
 * Tela de splash do sistema. */
package br.com.etec.splash;

import br.com.etec.view.jframe.TelaLogin;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;


/**
 *
 * @author jose
 */
public class Splash extends JWindow {

    private final long mTempo;
    private final int mWidth;
    private final int mHeight;
    private final ImageIcon icon;

    public Splash(long mTempo) {
        this.mTempo = mTempo;
        icon = new ImageIcon(getClass().getResource("/br/com/etec/imgs/Logo_Eleicoes_2016.png"));
        mWidth = icon.getIconWidth();
        mHeight = icon.getIconHeight();
    }

    /* Construindo a splash e esperando o termino para chamar a tela login.*/
    public void showSplash(){
        
        // Container para os elementos
        JPanel container = (JPanel) getContentPane();
        container.setLayout(null);
        
        // Atributos do JWindow
        setSize(mWidth, mHeight);
        setLocationRelativeTo(null);
        setBackground(new Color(0f, 0f, 0f, 0f));
        
        // Elementos do splash
        
        JLabel lblImg = new JLabel(icon);
        lblImg.setBounds(0, 0, mWidth + 2, mHeight + 2);
        
        container.add(lblImg);
        
        // Torna visivel antes do tempo, depois do tempo setado na thread.
        setVisible(true);
        try {
            Thread.sleep(mTempo);
        } catch (Exception e) {
        }
        
        // Inicia a tela login
        new TelaLogin().execute();
        
        setVisible(false);
    }

    //Método para exibir a splash e depois sair do sistema
    public void showExit() {
        showSplash();
        System.exit(0);
    }
    
    //Método para da um dispose e sair para outra tela.
    public void showDispose() {
        showSplash();
        dispose();
    }
}

