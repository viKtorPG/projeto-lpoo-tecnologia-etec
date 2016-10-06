package br.com.etec.dao;

import br.com.etec.interfaces.dao.IAbstractDao;
import br.com.etec.model.Vereador;
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
public class VereadorDao implements IAbstractDao<Vereador> {

    Connection connection;

    @Override
    public Vereador findById(long id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        ResultSet resultSet;
        try {
            connection = DbUtils.getConnection();
            String sql = "select id_vereador, id_partido, id_prefeito, nome, DATE_FORMAT(data_nascimento, '%d/%m/%Y'), numero, foto from vereador where numero=?";
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            statement.setLong(1, id);

            resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                JOptionPane.showMessageDialog(null, "Não existe vereador com esse número");
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
    public List<Vereador> all() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        return null;
    }
}
