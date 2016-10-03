/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etec.dao;

import br.com.etec.interfaces.dao.IAbstractDao;
import br.com.etec.model.Partido;
import br.com.etec.utils.DbUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Victor
 */
public class PartidoDao implements IAbstractDao<Partido> {

    Connection connection;

    @Override
    public List<Partido> all() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public Partido findById(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        ResultSet resultSet;
        try {
            connection = DbUtils.getConnection();
            String sql = "select * from partido where id_partido=?";
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                JOptionPane.showMessageDialog(null, "Não há partido com esse número");
                return null;
            } else {
                Partido partido = new Partido();
                partido.setIdPartido(resultSet.getInt(1));
                partido.setNome(resultSet.getString(2));
                partido.setSigla(resultSet.getString(3));
                partido.setSlogan(resultSet.getString(4));
                partido.setNumero(resultSet.getInt(5));
                partido.setLogo(resultSet.getBytes(6));
                partido.setDataCriacao(resultSet.getString(7));

                return partido;
            }

        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void insert(Partido entidade) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        try {
            connection = DbUtils.getConnection();
            String sql = "insert into partido (nome_partido, sigla, slogan, numero, logo) values (?, ?, ?, ?, ?)";

            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            statement.setString(1, entidade.getNome());
            statement.setString(2, entidade.getSigla());
            statement.setString(3, entidade.getSlogan());
            statement.setInt(4, entidade.getNumero());
            statement.setBytes(5, entidade.getLogo());

            statement.execute();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void update(Partido entidade) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        try {
            connection = DbUtils.getConnection();
            PreparedStatement statement = null;
            if (entidade.getLogo() != null) {
                String sql = "update partido set nome_partido=?, sigla=?, slogan=?, numero=?, logo=? where id_partido=?";
                statement = DbUtils.getPreparedStatement(connection, sql);
                statement.setString(1, entidade.getNome());
                statement.setString(2, entidade.getSigla());
                statement.setString(3, entidade.getSlogan());
                statement.setInt(4, entidade.getNumero());
                statement.setBytes(5, entidade.getLogo());
                statement.setInt(6, entidade.getIdPartido());
            } else {
                String sql = "update partido set nome_partido=?, sigla=?, slogan=?, numero=? where id_partido=?";
                statement = DbUtils.getPreparedStatement(connection, sql);
                statement.setString(1, entidade.getNome());
                statement.setString(2, entidade.getSigla());
                statement.setString(3, entidade.getSlogan());
                statement.setInt(4, entidade.getNumero());
                statement.setInt(5, entidade.getIdPartido());
            }

            statement.execute();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void delete(Partido entidade) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        try {
            connection = DbUtils.getConnection();
            String sql = "delete from partido where id_partido=?";
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            statement.setInt(1, entidade.getIdPartido());

            statement.execute();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

}
