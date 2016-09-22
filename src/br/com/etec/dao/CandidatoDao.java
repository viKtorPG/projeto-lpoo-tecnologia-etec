/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etec.dao;

import br.com.etec.model.Candidato;
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
public class CandidatoDao implements IAbstractDaoCandidato<Candidato> {

    Connection connection;

    @Override
    public Candidato findById(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        ResultSet resultSet;
        try {
            connection = DbUtils.getConnection();
            String sql = "select * from candidato where numero=?";
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                JOptionPane.showMessageDialog(null, "Não há candidato com esse id");
                return null;
            } else {

                Candidato candidato = new Candidato();
                candidato.setId(resultSet.getInt(1));
                candidato.setNome(resultSet.getString(2));
                candidato.setNumero(resultSet.getInt(3));
                candidato.setPartido(resultSet.getString(5));
                candidato.setCargo(resultSet.getString(4));
                candidato.setImagem(resultSet.getBytes(6));
                candidato.setVotos(resultSet.getInt(7));

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
    public void delete(Candidato entidade) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        try {
            connection = DbUtils.getConnection();
            String sql = "delete from candidato where numero=?";
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            statement.setInt(1, entidade.getNumero());

            statement.execute();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void insert(Candidato entidade) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        try {
            connection = DbUtils.getConnection();
            String sql = "insert into candidato (id, nome, numero, partido, cargo, foto, votos) values(null,?,?,?,?,?,?)";
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            statement.setString(1, entidade.getNome());
            statement.setInt(2, entidade.getNumero());
            statement.setString(3, entidade.getPartido());
            statement.setString(4, entidade.getCargo());
            statement.setBytes(5, entidade.getImagem());
            statement.setInt(6, 0);
            statement.execute();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void update(Candidato entidade) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        try {
            connection = DbUtils.getConnection();
            String sql = "update candidato set nome=?, numero=?, cargo=?, partido=?,foto=? where id=?";
            String sql2 = "update candidato set nome=?, numero=?, cargo=?, partido=? where id=?";
            PreparedStatement statement = null;
            if (entidade.getImagem() != null) {
                statement = DbUtils.getPreparedStatement(connection, sql);
                statement.setString(1, entidade.getNome());
                statement.setInt(2, entidade.getNumero());
                statement.setString(3, entidade.getCargo());
                statement.setString(4, entidade.getPartido());
                statement.setBytes(5, entidade.getImagem());
                statement.setInt(6, entidade.getId());
            } else {
                statement = DbUtils.getPreparedStatement(connection, sql2);
                statement.setString(1, entidade.getNome());
                statement.setInt(2, entidade.getNumero());
                statement.setString(3, entidade.getCargo());
                statement.setString(4, entidade.getPartido());
                statement.setInt(5, entidade.getId());
            }

            statement.execute();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void updateVotos(Candidato entidade) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        try {
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
        }
    }

}
