/*
 * Tela de Desktop do projeto.
 */
package br.com.etec.view.jframe;

import br.com.etec.components.BackgroundedDesktopPane;
import br.com.etec.utils.DbUtils;
import br.com.etec.view.jinternalframe.TelaCadastroPrefeito;
import br.com.etec.view.jinternalframe.TelaCadastroEleitor;
import br.com.etec.view.jinternalframe.TelaCadastroPartido;
import br.com.etec.view.jinternalframe.TelaCadastroUsuario;
import br.com.etec.view.jinternalframe.TelaCadastroVereador;
import br.com.etec.view.jinternalframe.TelaGerarRelatorioCandidato;
import br.com.etec.view.jinternalframe.TelaGerarRelatorioPartido;
import br.com.etec.view.jinternalframe.TelaImprimirSegundaVia;
import br.com.etec.view.jinternalframe.TelaValidarVoto;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author jose
 */
public class TelaDesktop {

    public static JMenu jmCadastado;
    public static JMenu jmRelatorio;

    public TelaDesktop() {

    }

    public static void execute() {

        // Criação da Tela
        final JFrame jf = new JFrame("Desktop");
        jf.setSize(1010, 700);

        // Panel que será responsavel por add todos os elementos
        JPanel panel = new JPanel();
        panel.setLayout(null);

        //Menu
        JMenuBar jMenu = new JMenuBar();
        jMenu.setBounds(0, 0, 1010, 25);

        //Desktop
        final JDesktopPane desktopPane = new BackgroundedDesktopPane();
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
                TelaCadastroPartido partido = null;
                try {
                    partido = new TelaCadastroPartido();
                } catch (ParseException ex) {
                    Logger.getLogger(TelaDesktop.class.getName()).log(Level.SEVERE, null, ex);
                }
                partido.setVisible(true);
                desktopPane.add(partido);
            }
        });
        jmCadastado.add(jmCadastadoPartido);

        JMenuItem jmCadastadoCandidato = new JMenuItem("Prefeito");
        jmCadastadoCandidato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaCadastroPrefeito candidato = null;
                try {
                    candidato = new TelaCadastroPrefeito();
                    candidato.setVisible(true);
                    desktopPane.add(candidato);
                } catch (ParseException ex) {
                    Logger.getLogger(TelaDesktop.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        );
        jmCadastado.add(jmCadastadoCandidato);

        JMenuItem jmCadastadoVice = new JMenuItem("Vereador");
        jmCadastadoVice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaCadastroVereador vice = null;
                try {
                    vice = new TelaCadastroVereador();
                } catch (ParseException ex) {
                    Logger.getLogger(TelaDesktop.class.getName()).log(Level.SEVERE, null, ex);
                }
                vice.setVisible(true);
                desktopPane.add(vice);
            }
        }
        );
        jmCadastado.add(jmCadastadoVice);

        JMenuItem jmCadastadoUsuario = new JMenuItem("Usuário");

        jmCadastadoUsuario.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                TelaCadastroUsuario usuario = new TelaCadastroUsuario();
                usuario.setVisible(true);
                desktopPane.add(usuario);
            }
        }
        );
        jmCadastado.add(jmCadastadoUsuario);

        //Item de Menu (Relatório)
        jmRelatorio = new JMenu("Relatório");

        jmRelatorio.setVisible(
                false);

        JMenuItem jmRelatorioPartido = new JMenuItem("Partido");

        jmRelatorioPartido.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                TelaGerarRelatorioPartido partido = new TelaGerarRelatorioPartido();
                partido.setVisible(true);
                desktopPane.add(partido);
            }
        }
        );
        jmRelatorio.add(jmRelatorioPartido);

        JMenuItem jmRelatorioCandidato = new JMenuItem("Candidato");

        jmRelatorioCandidato.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                TelaGerarRelatorioCandidato candidato = new TelaGerarRelatorioCandidato();
                candidato.setVisible(true);
                desktopPane.add(candidato);
            }
        }
        );
        jmRelatorio.add(jmRelatorioCandidato);

        JMenuItem jmRelatorioGeral = new JMenuItem("Geral");

        jmRelatorioGeral.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {

            }
        });
        jmRelatorio.add(jmRelatorioGeral);

        JMenuItem jmRelatorioUsuarios = new JMenuItem("Usuários");

        jmRelatorioUsuarios.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                int confirmar = JOptionPane.showConfirmDialog(null, "Conrfima a impressão desse relatorio", "Atenção", JOptionPane.YES_NO_OPTION);

                
                
                if (confirmar == JOptionPane.YES_NO_OPTION) {
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                Connection connection = DbUtils.getConnection();
                                JasperPrint viewer = JasperFillManager.fillReport("src/br/com/etec/ireport/projectUser.jasper", null, connection);

                                JasperViewer.viewReport(viewer, false);
                            } catch (JRException | ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                                Logger.getLogger(TelaDesktop.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }.start();
                }
            }
        }
        );
        jmRelatorio.add(jmRelatorioUsuarios);

        //Item de Menu (Imprimir)
        JMenu jmImprimir = new JMenu("Imprimir");

        JMenuItem jmRelatorioSTitulo = new JMenuItem("Título 2° Via");

        jmRelatorioSTitulo.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                TelaImprimirSegundaVia segundaVia = new TelaImprimirSegundaVia();
                segundaVia.setVisible(true);
                desktopPane.add(segundaVia);
            }
        }
        );
        jmImprimir.add(jmRelatorioSTitulo);

        //Item de Menu (Válidar voto)
        JMenu jmValidar = new JMenu("Válidar");
        JMenuItem jmValidarValor = new JMenuItem("Voto");

        jmValidarValor.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                TelaValidarVoto voto = new TelaValidarVoto();
                voto.setVisible(true);
                desktopPane.add(voto);
            }
        }
        );
        jmValidar.add(jmValidarValor);

        //Item de Menu (Opções)
        JMenu jmOpcoes = new JMenu("Opções");

        JMenuItem jmOpcoesSairLogin = new JMenuItem("Sair para tela de login");

        jmOpcoesSairLogin.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                TelaLogin login = new TelaLogin();
                login.execute();
                jf.dispose();
            }
        }
        );
        jmOpcoes.add(jmOpcoesSairLogin);

        JMenuItem jmOpcoesSair = new JMenuItem("Sair");

        jmOpcoesSair.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                System.exit(0);
            }
        }
        );
        jmOpcoes.add(jmOpcoesSair);

        //Item de Menu (Ajuda)
        JMenu jmAjuda = new JMenu("Ajuda");

        JMenuItem jmAjudaSobre = new JMenuItem("Sobre");

        jmAjudaSobre.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {

            }
        });
        jmAjuda.add(jmAjudaSobre);

        //Add elementos no menu
        jMenu.add(jmCadastado);

        jMenu.add(jmRelatorio);

        jMenu.add(jmImprimir);

        jMenu.add(jmValidar);

        jMenu.add(jmOpcoes);

        jMenu.add(jmAjuda);

        //Add elementos ao Panel
        panel.add(jMenu);

        panel.add(desktopPane, BorderLayout.CENTER);

        panel.add(desktopPane);

        // Add elementos ao JFrame
        jf.add(panel);

        jf.setVisible(
                true);
        jf.setResizable(
                false);
        jf.setLocationRelativeTo(
                null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
