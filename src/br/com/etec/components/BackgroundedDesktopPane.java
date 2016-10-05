/*
 * Classe responsável pelo carregamento da tela de fundo do jdesktop.
 */
package br.com.etec.components;

import br.com.etec.log.Log;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JDesktopPane;

/**
 *
 * @author jose
 */
public class BackgroundedDesktopPane extends JDesktopPane{
    
    private Image img;
    private static final String TAG = "BackgroundedDesktopPane";
    
    // Carrega a imagem na variável do tipo image. 
    public BackgroundedDesktopPane(){
        try{
            URL imgCaminho = getClass().getResource("/br/com/etec/imgs/Logo_Eleicoes_2016.png");
            img = ImageIO.read(imgCaminho);
        } catch (IOException ex) {
            Log.e(TAG, ex.getMessage());
        }
    }
    
    //
    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        if(img != null){
            graphics.drawImage(img, 200, 180, 585, 299, this);
        }else{
            graphics.drawString("Imagem não encontrada", 50, 50);
        }
    }
}
