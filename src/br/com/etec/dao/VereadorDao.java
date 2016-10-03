/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etec.dao;

import br.com.etec.interfaces.dao.IAbstractDaoCandidato;
import br.com.etec.model.Prefeito;
import br.com.etec.model.Vereador;
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
public class VereadorDao implements IAbstractDaoCandidato<Vereador> {

    Connection connection;

    @Override
    public Vereador findById(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        ResultSet resultSet;
        try {
            connection = DbUtils.getConnection();
            String sql = "select id_vereador, id_partido, id_prefeito, nome, DATE_FORMAT(data_nascimento, '%d/%m/%Y'), numero, foto from vereador where numero=?";
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                JOptionPane.showMessageDialog(null, "Não há candidato com esse id");
                return null;
            } else {

                Vereador vereador = new Vereador();
                vereador.setIdVereador(resultSet.getInt(1));
                vereador.setIdPartido(resultSet.getInt(2));
                vereador.setIdPrefeito(resultSet.getInt(3));
                vereador.setNome(resultSet.getString(4));
                vereador.setDataNascimento(resultSet.getString(5));
                vereador.setNumero(resultSet.getInt(6));
                vereador.setFoto(resultSet.getBytes(7));

                // Blob blob = resultSet.getBlob(5);
                // ImageIcon imagem = new ImageIcon(blob.getBytes(1, (int)blob.length()));
                // candidato.setFoto(new File(""));
                return vereador;
            }

        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void delete(Vereador entidade) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        try {
            connection = DbUtils.getConnection();
            String sql = "delete from vereador where vereador.numero = ?";
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
    public void insert(Vereador entidade) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        try {
            connection = DbUtils.getConnection();
            String sql = "insert into vereador(id_partido, id_prefeito, nome, data_nascimento, numero, foto) values(?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            statement.setInt(1, entidade.getIdPartido());
            statement.setInt(2, entidade.getIdPrefeito());
            statement.setString(3, entidade.getNome());
            statement.setString(4, entidade.getDataNascimento());
            statement.setInt(5, entidade.getNumero());
            statement.setBytes(6, entidade.getFoto());

            statement.execute();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void update(Vereador entidade) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        try {
            connection = DbUtils.getConnection();

            PreparedStatement statement = null;

            if (entidade.getFoto() != null) {
                String sql = "update vereador set id_partido=?, id_prefeito=?, nome=?, data_nascimento=?, numero=?, foto=? where id_vereador = ?";
                statement = DbUtils.getPreparedStatement(connection, sql);
                statement.setInt(1, entidade.getIdPartido());
                statement.setInt(2, entidade.getIdPrefeito());
                statement.setString(3, entidade.getNome());
                statement.setString(4, entidade.getDataNascimento());
                statement.setInt(5, entidade.getNumero());
                statement.setBytes(6, entidade.getFoto());
                statement.setInt(7, entidade.getIdVereador());

            } else {
                String sql = "update vereador set id_partido=?, id_prefeito=?, nome=?, data_nascimento=?, numero=? where id_vereador = ?";
                statement = DbUtils.getPreparedStatement(connection, sql);
                statement.setInt(1, entidade.getIdPartido());
                statement.setInt(2, entidade.getIdPrefeito());
                statement.setString(3, entidade.getNome());
                statement.setString(4, entidade.getDataNascimento());
                statement.setInt(5, entidade.getNumero());
                statement.setInt(6, entidade.getIdVereador());
            }

            statement.execute();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void updateVotos(Vereador entidade) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
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
