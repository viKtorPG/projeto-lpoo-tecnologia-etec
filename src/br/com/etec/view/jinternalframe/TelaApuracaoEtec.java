/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etec.view.jinternalframe;

/**
 *
 * @author jose
 */
import br.com.etec.dao.ResultadosDao;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Victor
 */
public class TelaApuracaoEtec extends JInternalFrame {

    int quocienteEleitoral, quocientePartidario[], i = 0, linhas = 0, somatorioQP = 0, partido;
    Integer partidos[];
    final int VAGAS = 55;
    double media[];
    int vagasRemanescentes[];
    ResultadosDao rd;
    JPanel painel;
    JLabel lblNumero;
    private final JLabel lblPartidos;
    private final JLabel lblVereadores;
    private final JLabel lblQP;
    private final JLabel lblQE;
    private final JLabel lblQtdEleitos;
    private final JTable tblQP;
    private final JLabel lblPrefeitoEleito;
    private final JLabel lblVotosNulosPrefeito;
    private final JLabel lblVotosBrancosPrefeito;
    private final JLabel lblVotosValidosPrefeito;
    private final JTable tblVereadoresEleitos;
    private final JLabel lblVotosValidosVereador;
    private final JLabel lblVotosBrancosVereador;
    private final JLabel lblVotosNulosVereador;
    private final JComboBox<String> jcListaPartidos;
    private final JScrollPane barra_rolagem;

    public TelaApuracaoEtec() {
        super("Apuração da Votação");
        super.setSize(990, 630);
        super.setLayout(null);

        painel = (JPanel) getContentPane();
        painel.setBackground(Color.WHITE);

        JLabel fundo = new JLabel(new ImageIcon(getClass().getResource("/br/com/etec/imgs/Logo_Eleicoes_2016.png")));
        fundo.setBounds(440, 280, fundo.getIcon().getIconWidth(), fundo.getIcon().getIconHeight());
        painel.add(fundo);

        rd = new ResultadosDao();
        partidos = rd.selectNumeros();
        vagasRemanescentes = new int[partidos.length];
        quocientePartidario = new int[partidos.length];
        quocienteEleitoral = rd.quociente_eleitoral(VAGAS);
        calculosProporcionais();

        lblPartidos = new JLabel("Partido");
        lblPartidos.setForeground(new Color(0, 78, 112));
        lblPartidos.setBounds(20, 20, 350, 50);
        lblPartidos.setFont(new Font("Arial", Font.BOLD, 20));
        painel.add(lblPartidos);

        UIManager.put("ComboBox.selectionBackground", new ColorUIResource(new Color(0, 78, 112)));
        UIManager.put("ComboBox.selectionForeground", new ColorUIResource(new Color(223, 146, 0)));
        jcListaPartidos = new JComboBox<>();
        jcListaPartidos.setModel(new DefaultComboBoxModel<>(rd.selectPartidos()));
        jcListaPartidos.setBackground(new Color(255, 255, 255));
        jcListaPartidos.setFont(new Font("Arial", Font.BOLD, 13));
        jcListaPartidos.setForeground(new Color(66, 138, 52));
        jcListaPartidos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                lblNumero.setText("Número: " + rd.selectNumero(jcListaPartidos.getSelectedItem().toString().substring(jcListaPartidos.getSelectedItem().toString().indexOf("-") + 2)));
                partido = Integer.valueOf(lblNumero.getText().replace("Número: ", ""));
                try {
                    lblVotosValidosVereador.setText("Votos Válidos (Vereador): " + rd.selectVotosValidos(partido, "vereador"));
                    lblVotosValidosPrefeito.setText("Votos Válidos (Prefeito):   " + rd.selectVotosValidos(partido, "prefeito"));
                    tblVereadoresEleitos.setModel(rd.eleitos(quocienteEleitoral, partido));
                    numeroLinhasTabela();
                    lblVereadores.setText("Vereadores Eleitos - " + jcListaPartidos.getSelectedItem());
                    maiorQueQP();
                } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                    Logger.getLogger(TelaApuracaoEtec.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        jcListaPartidos.setBounds(110, 20, 250, 40);
        painel.add(jcListaPartidos);

        lblQP = new JLabel("Quociente Partidário");
        lblQP.setBounds(700, 40, 200, 20);
        lblQP.setForeground(new Color(0, 78, 112));
        lblQP.setFont(new Font("Arial", Font.BOLD, 20));
        painel.add(lblQP);

        tblQP = new JTable(dadosQP(), colunasQP());
        tblQP.setRowHeight(20);
        tblQP.setForeground(new Color(66, 138, 52));
        tblQP.setSelectionForeground(new Color(243, 155, 0));
        tblQP.setSelectionBackground(new Color(0, 78, 112));
        JScrollPane barra = new JScrollPane(tblQP);
        barra.setBounds(650, 60, 320, 120);
        painel.add(barra);

        lblQE = new JLabel("Quociente Eleitoral " + quocienteEleitoral);
        lblQE.setBounds(450, 30, 800, 20);
        lblQE.setFont(new Font("Arial", Font.BOLD, 20));
        lblQE.setForeground(new Color(0, 78, 112));
        painel.add(lblQE);

        lblNumero = new JLabel("Número: " + rd.selectNumero(jcListaPartidos.getSelectedItem().toString().substring(jcListaPartidos.getSelectedItem().toString().indexOf("-") + 2)));
        partido = Integer.valueOf(lblNumero.getText().replace("Número: ", ""));
        lblNumero.setForeground(new Color(0, 78, 112));
        lblNumero.setBounds(365, 30, 200, 20);
        painel.add(lblNumero);

        lblVotosValidosVereador = new JLabel();
        try {
            lblVotosValidosVereador.setText("Votos Válidos (Vereador): " + rd.selectVotosValidos(partido, "partido"));
            lblVotosValidosVereador.setBounds(20, 70, 200, 40);
            lblVotosValidosVereador.setFont(new Font("Arial", Font.BOLD, 14));
            lblVotosValidosVereador.setForeground(new Color(223, 146, 0));
            painel.add(lblVotosValidosVereador);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(TelaApuracaoEtec.class.getName()).log(Level.SEVERE, null, ex);
        }

        lblVotosBrancosVereador = new JLabel("Votos Brancos (Vereador): " + rd.selectVotosBrancos("vereador"));
        lblVotosBrancosVereador.setBounds(230, 70, 200, 40);
        lblVotosBrancosVereador.setForeground(new Color(223, 146, 0));
        lblVotosBrancosVereador.setFont(lblVotosValidosVereador.getFont());
        painel.add(lblVotosBrancosVereador);

        lblVotosNulosVereador = new JLabel("Votos Nulos (Vereador): " + rd.selectVotosNulos("vereador"));
        lblVotosNulosVereador.setBounds(440, 70, 500, 40);
        lblVotosNulosVereador.setForeground(new Color(223, 146, 0));
        lblVotosNulosVereador.setFont(lblVotosValidosVereador.getFont());
        painel.add(lblVotosNulosVereador);

        lblVotosValidosPrefeito = new JLabel();
        try {
            lblVotosValidosPrefeito.setText("Votos Válidos (Prefeito):   " + rd.selectVotosValidos(partido, "prefeito"));
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(TelaApuracaoEtec.class.getName()).log(Level.SEVERE, null, ex);
        }
        lblVotosValidosPrefeito.setBounds(20, 110, 500, 40);
        lblVotosValidosPrefeito.setForeground(new Color(223, 146, 0));
        lblVotosValidosPrefeito.setFont(lblVotosValidosVereador.getFont());
        painel.add(lblVotosValidosPrefeito);

        lblVotosBrancosPrefeito = new JLabel("Votos Brancos (Prefeito):    " + rd.selectVotosBrancos("prefeito"));
        lblVotosBrancosPrefeito.setBounds(230, 110, 500, 40);
        lblVotosBrancosPrefeito.setForeground(new Color(223, 146, 0));
        lblVotosBrancosPrefeito.setFont(lblVotosValidosVereador.getFont());
        painel.add(lblVotosBrancosPrefeito);

        lblVotosNulosPrefeito = new JLabel("Votos Nulos (Prefeito):    " + rd.selectVotosNulos("prefeito"));
        lblVotosNulosPrefeito.setBounds(440, 110, 500, 40);
        lblVotosNulosPrefeito.setForeground(new Color(223, 146, 0));
        lblVotosNulosPrefeito.setFont(lblVotosValidosVereador.getFont());
        painel.add(lblVotosNulosPrefeito);

        lblVereadores = new JLabel("Vereadores Eleitos - " + jcListaPartidos.getSelectedItem());
        lblVereadores.setForeground(new Color(0, 78, 112));
        lblVereadores.setFont(new Font("Arial", Font.BOLD, 23));
        lblVereadores.setBounds(20, 170, 600, 30);
        painel.add(lblVereadores);

        tblVereadoresEleitos = new JTable();
        tblVereadoresEleitos.setModel(rd.eleitos(quocienteEleitoral, partido));
        maiorQueQP();
        tblVereadoresEleitos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tblVereadoresEleitos.setRowHeight(20);

        tblVereadoresEleitos.setForeground(new Color(66, 138, 52));
        tblVereadoresEleitos.setSelectionForeground(new Color(243, 155, 0));
        tblVereadoresEleitos.setSelectionBackground(new Color(0, 78, 112));
        barra_rolagem = new JScrollPane(tblVereadoresEleitos);
        numeroLinhasTabela();
        painel.add(barra_rolagem);

        lblQtdEleitos = new JLabel("fffffffffff");//"Total (Vereadores Eleitos): " + rd.selectVereadoresEleitos(quocienteEleitoral));
        calculaEleitos();
        lblQtdEleitos.setForeground(new Color(223, 146, 0));
        lblQtdEleitos.setFont(new Font("Arial", Font.BOLD, 23));
        lblQtdEleitos.setBounds(20, 400, 500, 40);
        painel.add(lblQtdEleitos);

        lblPrefeitoEleito = new JLabel("Prefeito(a): " + rd.selectPrefeitoEleito());
        lblPrefeitoEleito.setFont(new Font("Arial", Font.BOLD, 30));
        lblPrefeitoEleito.setForeground(new Color(0, 78, 112));
        lblPrefeitoEleito.setBounds(20, 480, 600, 40);
        painel.add(lblPrefeitoEleito);

        //super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setVisible(true);
        super.toFront();
        super.setClosable(true);
        super.setIconifiable(true);
    }

    private boolean temDireitoAVagas(int partido, int quociente_eleitoral) {
        try {
            return (rd.selectVotosValidos(partido, "partido") >= quociente_eleitoral);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(TelaApuracaoEtec.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    private int maiorMedia(double[] media) {
        double maior = 0;
        int maiorPos = 0;
        int cont = 0;
        while (cont < media.length) {
            if (media[cont] > maior) {
                maior = media[cont];
                maiorPos = cont;
            }
            cont++;
        }

        return maiorPos;
    }

    private void calculoSobras() {
        i = 0;
        media = new double[partidos.length];

        while (somatorioQP < VAGAS) {
            while (i < media.length) {
                int votos_validos;
                try {
                    votos_validos = rd.selectVotosValidos(partidos[i], "partido");
                    int QPAtual = quocientePartidario[i];
                    int vagas_remanescentes_atual = vagasRemanescentes[i];
                    if (votos_validos >= quocienteEleitoral) {
                        media[i] = votos_validos / (QPAtual + vagas_remanescentes_atual + 1);
                    }
                    i++;
                } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                    Logger.getLogger(TelaApuracaoEtec.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            int pos = maiorMedia(media);
            quocientePartidario[pos]++;
            vagasRemanescentes[pos]++;
            somatorioQP++;
            i = 0;
        }
    }

    private void maiorQueQP() {
        if (tblVereadoresEleitos.getRowCount() > quocientePartidario[jcListaPartidos.getSelectedIndex()]) {
            DefaultTableModel dtm = (DefaultTableModel) tblVereadoresEleitos.getModel();
            dtm.removeRow(tblVereadoresEleitos.getRowCount() - 1);
            tblVereadoresEleitos.setModel(dtm);
        }
    }

    private void calculosProporcionais() {
        while (i < partidos.length) {
            if (somatorioQP <= VAGAS) {
                quocientePartidario[i] = rd.quocioente_partidario(partidos[i], quocienteEleitoral);
                somatorioQP += quocientePartidario[i];
                linhas++;
            }

            vagasRemanescentes[i] = 0;
            i++;
        }

        if (somatorioQP < VAGAS) {
            calculoSobras();
        }
    }

    private Object[][] dadosQP() {
        Object[][] dados = new Object[linhas + 20][2];

        int k = 0;
        for (int j = 0; j < partidos.length; j++) {
            if (temDireitoAVagas(partidos[j], quocienteEleitoral)) {
                dados[k][0] = partidos[j];
                dados[k][1] = quocientePartidario[j];
                k++;
            }
        }

        return dados;
    }

    private Object[] colunasQP() {
        String[] colunas = {"Número do Partido", "Quociente Partidário"};
        return colunas;
    }

    private void calculaEleitos() {
        //"Total (Vereadores Eleitos): " + rd.selectVereadoresEleitos(quocienteEleitoral));
        int contador = 0;
        int somaEleitos = 0;
        while (contador < partidos.length) {
            if (quocientePartidario[contador] != 0) {
                somaEleitos += rd.selectVereadoresEleitos(quocienteEleitoral, partidos[contador]);
            }
            contador++;
        }

        lblQtdEleitos.setText("Total (Vereadores Eleitos): " + somaEleitos);
    }

    private void numeroLinhasTabela() {
        if (tblVereadoresEleitos.getRowCount() < 5) {
            barra_rolagem.setBounds(20, 200, 950, (tblVereadoresEleitos.getRowCount() + 1) * 20);
        } else {
            barra_rolagem.setBounds(20, 200, 950, 120);
        }
    }
}
