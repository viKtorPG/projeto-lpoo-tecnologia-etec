/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etec.dao;

import br.com.etec.interfaces.dao.IAbstractDaoCandidato;
import br.com.etec.model.Prefeito;
import br.com.etec.utils.DbUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Victor
 */
public class PrefeitoDao implements IAbstractDaoCandidato<Prefeito> {

    Connection connection;

    @Override
    public Prefeito findById(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        ResultSet resultSet;
        try {
            connection = DbUtils.getConnection();
            String sql = "select id_prefeito, id_partido, nome, DATE_FORMAT(data_nascimento, '%d/%m/%Y'), numero, foto from prefeito where numero=?";
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                JOptionPane.showMessageDialog(null, "Não há candidato com esse id");
                return null;
            } else {

                Prefeito candidato = new Prefeito();
                candidato.setIdPrefeito(resultSet.getInt(1));
                candidato.setIdPartido(resultSet.getInt(2));
                candidato.setNome(resultSet.getString(3));
                candidato.setDataNascimento(resultSet.getString(4));
                candidato.setNumero(resultSet.getInt(5));
                candidato.setFoto(resultSet.getBytes(6));

                // Blob blob = resultSet.getBlob(5);
                // ImageIcon imagem = new ImageIcon(blob.getBytes(1, (int)blob.length()));
                // candidato.setFoto(new File(""));
                return candidato;
            }

        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void delete(Prefeito entidade) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        try {
            connection = DbUtils.getConnection();
            String sql = "delete from prefeito where id_prefeito = ?";
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            statement.setInt(1, entidade.getIdPrefeito());

            statement.execute();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void insert(Prefeito entidade) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        try {
            connection = DbUtils.getConnection();
            String sql = "insert into prefeito(id_partido, nome, data_nascimento, numero, foto) values(?, ?, ?, ?, ?);";
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            statement.setInt(1, entidade.getIdPartido());
            statement.setString(2, entidade.getNome());
            statement.setString(3, entidade.getDataNascimento());
            statement.setInt(4, entidade.getNumero());
            statement.setBytes(5, entidade.getFoto());

            statement.execute();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void update(Prefeito entidade) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        try {
            connection = DbUtils.getConnection();

            PreparedStatement statement = null;

            if (entidade.getFoto()!= null) {
                String sql = "update prefeito set id_partido=?, nome=?, data_nascimento=?, numero=?, foto=? where id_prefeito = ?";
                statement = DbUtils.getPreparedStatement(connection, sql);
                statement.setInt(1, entidade.getIdPartido());
                statement.setString(2, entidade.getNome());
                statement.setString(3, entidade.getDataNascimento());
                statement.setInt(4, entidade.getNumero());
                statement.setBytes(5, entidade.getFoto());
                statement.setInt(6, entidade.getIdPrefeito());

            } else {
                String sql = "update prefeito set id_partido=?, nome=?, data_nascimento=?, numero=? where id_prefeito = ?";
                statement = DbUtils.getPreparedStatement(connection, sql);
                statement.setInt(1, entidade.getIdPartido());
                statement.setString(2, entidade.getNome());
                statement.setString(3, entidade.getDataNascimento());
                statement.setInt(4, entidade.getNumero());
                statement.setInt(5, entidade.getIdPrefeito());
            }

            statement.execute();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void updateVotos(Prefeito entidade) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        /*try {
            connection = DbUtils.getConnection();
            String sql = "update candidato votos=? where id=?";
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            statement.setInt(1, entidade.getVotos());
            statement.setInt(2, entidade.getId());
            statement.execute();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }*/
    }

}
