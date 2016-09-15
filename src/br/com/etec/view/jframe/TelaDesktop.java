/*
 * Tela de Desktop do projeto.
 */
package br.com.etec.view.jframe;

import br.com.etec.view.jinternalframe.TelaCadastroEleitor;
import br.com.etec.view.jinternalframe.TelaCadastroUsuario;
import br.com.etec.view.jinternalframe.TelaImprimirSegundaVia;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author jose
 */
public class TelaDesktop {

    public static JMenu jmCadastado;
    public static JMenu jmRelatorio;

    public TelaDesktop() {

    }

    public static void exibir(String title) {

        // Criação da Tela
        final JFrame jf = new JFrame(title);
        jf.setSize(1010, 700);

        // Panel que será responsavel por add todos os elementos
        JPanel panel = new JPanel();
        panel.setLayout(null);

        //Menu
        JMenuBar jMenu = new JMenuBar();
        jMenu.setBounds(0, 0, 1010, 25);

        //Desktop
        final JDesktopPane desktopPane = new JDesktopPane();
        desktopPane.setBounds(5, 30, 995, 635);

        //Item de Menu (Cadastro)
        jmCadastado = new JMenu("Cadastro");
        jmCadastado.setVisible(false);

        JMenuItem jmCadastadoEleitor = new JMenuItem("Eleitor");
        jmCadastadoEleitor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaCadastroEleitor leitor = new TelaCadastroEleitor();
                leitor.setVisible(true);
                desktopPane.add(leitor);
            }
        });
        jmCadastado.add(jmCadastadoEleitor);

        JMenuItem jmCadastadoPartido = new JMenuItem("Partido");
        jmCadastadoPartido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        jmCadastado.add(jmCadastadoPartido);

        JMenuItem jmCadastadoCandidato = new JMenuItem("Candidato");
        jmCadastadoCandidato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        jmCadastado.add(jmCadastadoCandidato);

        JMenuItem jmCadastadoUsuario = new JMenuItem("Usuário");
        jmCadastadoUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaCadastroUsuario usuario = new TelaCadastroUsuario();
                usuario.setVisible(true);
                desktopPane.add(usuario);
            }
        });
        jmCadastado.add(jmCadastadoUsuario);

        //Item de Menu (Relatório)
        jmRelatorio = new JMenu("Relatório");
        jmRelatorio.setVisible(false);

        JMenuItem jmRelatorioPartido = new JMenuItem("Eleitor");
        jmRelatorioPartido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        jmRelatorio.add(jmRelatorioPartido);

        JMenuItem jmRelatorioCandidato = new JMenuItem("Candidato");
        jmRelatorioCandidato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        jmRelatorio.add(jmRelatorioCandidato);

        JMenuItem jmRelatorioGeral = new JMenuItem("Geral");
        jmRelatorioGeral.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        jmRelatorio.add(jmRelatorioGeral);

        //Item de Menu (Imprimir)
        JMenu jmImprimir = new JMenu("Imprimir");

        JMenuItem jmRelatorioSTitulo = new JMenuItem("Título 2° Via");
        jmRelatorioSTitulo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaImprimirSegundaVia segundaVia = new TelaImprimirSegundaVia();
                segundaVia.setVisible(true);
                desktopPane.add(segundaVia);
            }
        });
        jmImprimir.add(jmRelatorioSTitulo);

        //Item de Menu (Válidar voto)
        JMenu jmValidarValor = new JMenu("Válidar Voto");
        jmValidarValor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        //Item de Menu (Opções)
        JMenu jmOpcoes = new JMenu("Opções");

        JMenuItem jmOpcoesSairLogin = new JMenuItem("Sair para tela de login");
        jmOpcoesSairLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaLogin login = new TelaLogin();
                login.execute();
                jf.dispose();
            }
        });
        jmOpcoes.add(jmOpcoesSairLogin);

        JMenuItem jmOpcoesSair = new JMenuItem("Sair");
        jmOpcoesSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jmOpcoes.add(jmOpcoesSair);

        //Item de Menu (Ajuda)
        JMenu jmAjuda = new JMenu("Ajuda");

        JMenuItem jmAjudaSobre = new JMenuItem("Sobre");
        jmAjudaSobre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        jmAjuda.add(jmAjudaSobre);

        //Add elementos no menu
        jMenu.add(jmCadastado);
        jMenu.add(jmRelatorio);
        jMenu.add(jmImprimir);
        jMenu.add(jmValidarValor);
        jMenu.add(jmOpcoes);
        jMenu.add(jmAjuda);

        //Add elementos ao Panel
        panel.add(jMenu);
        panel.add(desktopPane);

        // Add elementos ao JFrame
        jf.add(panel);
        jf.setVisible(true);
        jf.setResizable(false);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
