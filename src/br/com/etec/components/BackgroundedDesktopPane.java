/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etec.components;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JDesktopPane;

/**
 *
 * @author jose
 */
public class BackgroundedDesktopPane extends JDesktopPane{
    
    Image img;
    
    public BackgroundedDesktopPane(){
        try{
            URL imgCaminho = getClass().getResource("/br/com/etec/imgs/Logo_Eleicoes_2016.png");
            img = ImageIO.read(imgCaminho);
        } catch (IOException ex) {
            Logger.getLogger(BackgroundedDesktopPane.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
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
