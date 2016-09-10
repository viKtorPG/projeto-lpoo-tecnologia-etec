/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etec.view.jinternalframe;

import java.awt.Container;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

/**
 *
 * @author jose
 */
public class TelaCadastroUsuario extends JInternalFrame{
    
    public TelaCadastroUsuario(){
        iniciandoCompomentes();
    }
    
    private void iniciandoCompomentes(){
        setTitle("Usuários");
        setSize(800, 500);
        
        //Panel
        Container container = getContentPane();
        container.setLayout(null);
        
        JLabel lblCamposObri = new JLabel("(*Campos Obrigatórios)");
        
        
        //ADD
        
        setClosable(true);
        setIconifiable(true);
        setLocation(100, 50);
        
    }
}
