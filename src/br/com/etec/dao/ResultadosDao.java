/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etec.dao;

import br.com.etec.log.Log;
import br.com.etec.utils.DbUtils;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Victor
 */
public class ResultadosDao {

    Connection conn;

    public int quociente_eleitoral(int vagas) {
        CallableStatement stmt;
        try {
            conn = DbUtils.getConnection();
            stmt = conn.prepareCall("{? = call quociente_eleitoral(?)}");
            stmt.registerOutParameter(1, Types.INTEGER);
            stmt.setInt(2, vagas);
            stmt.execute();
            return stmt.getInt(1);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }
        return 0;
    }

    public int quocioente_partidario(int numero, int quociente_eleitoral) {
        try {
            conn = DbUtils.getConnection();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
        }
        try {
            CallableStatement stmt = conn.prepareCall("{? = call quociente_partidario(?, ?)}");
            stmt.registerOutParameter(1, Types.INTEGER);
            stmt.setInt(2, numero);
            stmt.setInt(3, quociente_eleitoral);
            stmt.execute();
            return stmt.getInt(1);
        } catch (SQLException ex) {
            return 0;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }
    }

    public String[] selectPartidos() {
        ResultSet rs;
        ArrayList<String> lista = new ArrayList<>();
        String sql = "select nome_partido, sigla from partido order by votos desc";
        try {
            conn = DbUtils.getConnection();
            PreparedStatement ps;

            ps = DbUtils.getPreparedStatement(conn, sql);

            rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(rs.getString("nome_partido") + " - " + rs.getString("sigla"));
            }
        } catch (SQLException ex) {
            return null;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }

        String[] partidos = new String[lista.size()];
        partidos = lista.toArray(partidos);

        return partidos;
    }

    public String selectNumero(String sigla) {
        String numero = "";
        try {
            conn = DbUtils.getConnection();

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
        }
        ResultSet rs;
        String sql = "select numero from partido where sigla='" + sigla + "' order by votos desc";
        //JOptionPane.showMessageDialog(null, selecionado);
        try {
            PreparedStatement statement = DbUtils.getPreparedStatement(conn, sql);
            rs = statement.executeQuery();
            if (rs.next()) {
                numero = "" + rs.getInt("numero");

            }
        } catch (SQLException ex) {
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }

        return numero;
    }

    public Integer[] selectNumeros() {
        ArrayList<Integer> lista = new ArrayList<>();
        try {
            conn = DbUtils.getConnection();

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
        }
        ResultSet rs;
        String sql = "select numero from partido order by votos desc";
        try {
            PreparedStatement statement = DbUtils.getPreparedStatement(conn, sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                lista.add(rs.getInt("numero"));
            }
            Integer[] partidos = new Integer[lista.size()];
            partidos = lista.toArray(partidos);
            return partidos;
        } catch (SQLException ex) {
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }

        return null;
    }

    public int selectVotosValidos(int numero, String flag) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ResultSet rs;
        String sql;
        PreparedStatement ps = null;
        int result = 0;
        if (flag.equalsIgnoreCase("prefeito")) {
            sql = "select votos from prefeito where numero=? order by votos desc";
        } else {
            sql = "select vereador.votos from vereador inner join partido on vereador.id_partido = partido.id_partido where partido.numero=? order by vereador.votos desc";
        }

        try {
            conn = DbUtils.getConnection();

            ps = DbUtils.getPreparedStatement(conn, sql);
            ps.setInt(1, numero);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                result += rs.getInt(1);
            }
            
            return result;
            
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }
    }

    public String selectVotosBrancos(String flag) {
        ResultSet rs;
        String sql;
        if (flag.equalsIgnoreCase("prefeito")) {
            sql = "select voto_branco_prefeito from voto_branco";
        } else {
            sql = "select voto_branco_vereador from voto_branco";
        }
        try {
            conn = DbUtils.getConnection();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
        }
        PreparedStatement ps;
        try {
            ps = DbUtils.getPreparedStatement(conn, sql);

            rs = ps.executeQuery();

            if (rs.next()) {
                if (flag.equalsIgnoreCase("prefeito")) {
                    return ("" + rs.getInt("voto_branco_prefeito"));
                } else {
                    return ("" + rs.getInt("voto_branco_vereador"));
                }
            } else {
                return "0";
            }
        } catch (SQLException ex) {
            return "0";
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }
    }

    public String selectVotosNulos(String flag) {
        ResultSet rs;
        String sql;
        if (flag.equalsIgnoreCase("prefeito")) {
            sql = "select voto_nulo_prefeito from voto_nulo";
        } else {
            sql = "select voto_nulo_vereador from voto_nulo";
        }
        try {
            conn = DbUtils.getConnection();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
        }
        PreparedStatement ps;
        try {
            ps = DbUtils.getPreparedStatement(conn, sql);

            rs = ps.executeQuery();

            if (rs.next()) {
                if (flag.equalsIgnoreCase("prefeito")) {
                    return ("" + rs.getInt("voto_nulo_prefeito"));
                } else {
                    return ("" + rs.getInt("voto_nulo_vereador"));
                }
            } else {
                return "0";
            }
        } catch (SQLException ex) {
            return "0";
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }
    }

    public String selectPrefeitoEleito() {
        ResultSet rs;
        //  String sql = "select foto from vice_prefeito where vice_prefeito.id_prefeito = (select prefeito.id_prefeito from prefeito where prefeito.numero=?)";
        String sql = "select prefeito.nome,( CURDATE() - prefeito.data_nascimento), prefeito.votos, partido.sigla from prefeito inner join partido on prefeito.id_partido = partido.id_partido where prefeito.votos = (select max(votos) from prefeito)";
        try {
            conn = DbUtils.getConnection();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
        }
        PreparedStatement ps;
        try {
            ps = DbUtils.getPreparedStatement(conn, sql);

            rs = ps.executeQuery();

            int i = 0;
            int idade[] = new int[100];
            String[] eleito = new String[100];
            while (rs.next()) {
                eleito[i] = (rs.getString("prefeito.nome") + " (" + rs.getString("partido.sigla") + ") " + " / " + rs.getInt("prefeito.votos") + " votos");
                idade[i] = rs.getInt(2);
                i++;
            }

            int maior = idade[0], pos = 0;
            i = 0;
            while (i < eleito.length) {
                if (idade[i] >= maior) {
                    maior = idade[i];
                    pos = i;
                }
                i++;
            }
            return eleito[pos];
        } catch (SQLException ex) {
            return "";
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }
    }

    public int selectVereadoresEleitos(int qE, int partido) {
        String sql = "SELECT COUNT(vereador.id_vereador) as eleitos, partido.* from vereador, partido where vereador.votos >= ROUND(" + (qE * 0.1) + ",0) and partido.numero = " + partido + " and vereador.id_partido = partido.id_partido ";
        ResultSet rs;

        try {
            conn = DbUtils.getConnection();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
        }
        PreparedStatement ps;
        try {
            ps = DbUtils.getPreparedStatement(conn, sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("eleitos");
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            return 0;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }
    }

    public int possiveisEleitos() {
        String sql = "SELECT COUNT(*) as registros from vereador where vereador.votos >= " + (0.1 * quociente_eleitoral(55));
        ResultSet rs;

        try {
            conn = DbUtils.getConnection();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
        }
        PreparedStatement ps;
        try {
            ps = DbUtils.getPreparedStatement(conn, sql);

            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("registros");
            }
        } catch (SQLException ex) {

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }
        return 0;
    }

    public DefaultTableModel eleitos(int qE, int partido) {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        String sql = "select vereador.*, partido.* from vereador, partido where vereador.votos >= ROUND(" + (qE * 0.1) + ",0) and partido.numero = " + partido + " and vereador.id_partido = partido.id_partido ";
        PreparedStatement ps;
        ResultSet rs;
        try {
            conn = DbUtils.getConnection();
            ps = DbUtils.getPreparedStatement(conn, sql);
//            ps.setInt(1, partido);
            rs = ps.executeQuery();

            dtm.addColumn("Nome");
            dtm.addColumn("Partido");
            dtm.addColumn("Número ");
            dtm.addColumn("Votos");
            while (rs.next()) {
                dtm.addRow(new Object[]{rs.getString("vereador.nome"), rs.getString("partido.nome_partido"), rs.getInt("vereador.numero"), rs.getInt("vereador.votos")});
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }
        return dtm;
    }
}

