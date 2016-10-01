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
                partido.setIdNumero(resultSet.getInt(1));
                partido.setNome(resultSet.getString(2));
                partido.setSigla(resultSet.getString(3));
                partido.setLogo(resultSet.getBytes(4));
                partido.setSlogan(resultSet.getString(5));
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
            String sql = "insert into partido (id_partido, nome, sigla, logo, sloga) values (?, ?, ?, ?, ?)";

            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            statement.setInt(1, entidade.getIdNumero());
            System.err.println("Erro: " + entidade.getIdNumero());
            statement.setString(2, entidade.getNome());
            statement.setString(3, entidade.getSigla());
            statement.setBytes(4, entidade.getLogo());
            statement.setString(5, entidade.getSlogan());

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
                String sql = "update partido set nome=?, sigla=?, logo=?, sloga=? where id_partido=?";
                statement = DbUtils.getPreparedStatement(connection, sql);
                statement.setString(1, entidade.getNome());
                statement.setString(2, entidade.getSigla());
                statement.setBytes(3, entidade.getLogo());
                statement.setString(4, entidade.getSlogan());
                statement.setInt(5, entidade.getIdNumero());
            } else {
                String sql = "update partido set nome=?, sigla=?, sloga=? where id_partido=?";
                statement = DbUtils.getPreparedStatement(connection, sql);
                statement.setString(1, entidade.getNome());
                statement.setString(2, entidade.getSigla());
                statement.setString(3, entidade.getSlogan());
                statement.setInt(4, entidade.getIdNumero());
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
            statement.setInt(1, entidade.getIdNumero());

            statement.execute();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

}
