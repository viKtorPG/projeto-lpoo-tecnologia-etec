/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etec.dao;

import br.com.etec.interfaces.dao.IAbstractDao;
import br.com.etec.model.Eleitor;
import br.com.etec.utils.DbUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author jose
 */
public class EleitorDao implements IAbstractDao<Eleitor> {

    private Connection connection = null;

    @Override
    public List<Eleitor> all() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        /*try {
            connection = DbUtils.getConnection();
            ResultSet resultSet = DbUtils.getResultSet(connection, "select * from eleitor");
            List<Eleitor> list = new ArrayList<>();
            while (resultSet.next()) {
                Eleitor eleitor = new Eleitor();
                eleitor.setIdCod(resultSet.getInt(1));
                eleitor.setNome(resultSet.getString(2));
                eleitor.setZona(resultSet.getString(3));
                eleitor.setSecao(resultSet.getString(4));
                eleitor.setDataNascimento(resultSet.getString(5));
                eleitor.setDataEmissao(resultSet.getString(6));
                eleitor.setIdCidade(resultSet.getInt(7));

            }
            return list;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }*/
        
        return null;

    }

    @Override
    public Eleitor findById(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        /*ResultSet resultSet;

        try {
            connection = DbUtils.getConnection();
            String sql = "select eleitor.id_eleitor, eleitor.nome, DATE_FORMAT(eleitor.data_nascimento, '%d/%m/%y'), DATE_FORMAT(eleitor.data_emissao, '%d/%m/%Y'), eleitor.zona, eleitor.secao, cidade.nome, estado.uf  \n"
                    + "from eleitor left join cidade \n"
                    + "on eleitor.id_cidade = cidade.id_cidade \n"
                    + "left join estado \n"
                    + "on cidade.id_estado = estado.id_estado \n"
                    + "where id_eleitor = ?";
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);

            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                JOptionPane.showMessageDialog(null, "Eleitor não encontrado!");
                return null;
            } else {
                Eleitor eleitor = new Eleitor();
                eleitor.setIdCod(resultSet.getInt(1));
                eleitor.setNome(resultSet.getString(2));
                eleitor.setDataNascimento(resultSet.getString(3));
                eleitor.setDataEmissao(resultSet.getString(4));
                eleitor.setZona(resultSet.getString(5));
                eleitor.setSecao(resultSet.getString(6));
                eleitor.setNomeCidade(resultSet.getString(7));
                eleitor.setNomeUF(resultSet.getString(8));

                return eleitor;
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }*/
        
        return null;
    }

    @Override
    public void insert(Eleitor entidade) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        try {
            connection = DbUtils.getConnection();

            String sql = "insert into eleitor(id_cidade, nome, zona, secao, data_nascimento) values(?, ?, ?, ?, ?)";

            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            statement.setInt(1, entidade.getIdCidade());
            statement.setString(2, entidade.getNome());
            statement.setString(3, entidade.getZona());
            statement.setString(4, entidade.getSecao());
            statement.setString(5, entidade.getDataNascimento());

            statement.execute();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void update(Eleitor entidade) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        try {
            connection = DbUtils.getConnection();

            String sql = "update eleitor set nome=?, data_nascimento=?, zona=?, secao=?, id_cidade=? where id_eleitor=?";

            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            statement.setString(1, entidade.getNome());
            statement.setString(2, entidade.getDataNascimento());
            statement.setString(3, entidade.getZona());
            statement.setString(4, entidade.getSecao());
            statement.setInt(5, entidade.getIdCidade());
            statement.setLong(6, entidade.getIdCod());

            statement.execute();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void delete(Eleitor entidade) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        try {
            connection = DbUtils.getConnection();
            String sql = "delete from eleitor where id_eleitor=?";
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            statement.setLong(1, entidade.getIdCod());

            statement.execute();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
