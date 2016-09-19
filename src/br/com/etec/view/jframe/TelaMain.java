/*
 * Tela que chama a tela de login de usuário
 */
package br.com.etec.view.jframe;

import br.com.etec.splash.Splash;

/**
 *
 * @author jose
 */
public class TelaMain {
    public static void main(String args[]){
        Splash splash = new Splash(500);
        splash.showDispose();
    }
}
