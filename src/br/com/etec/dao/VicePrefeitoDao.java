package br.com.etec.dao;

import br.com.etec.interfaces.dao.IAbstractDao;
import br.com.etec.model.VicePrefeito;
import br.com.etec.utils.DbUtils;
import java.sql.Blob;
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
public class VicePrefeitoDao implements IAbstractDao<VicePrefeito> {

    Connection connection;
    Blob blob;

    @Override
    public VicePrefeito findById(long id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        ResultSet resultSet;
        try {
            connection = DbUtils.getConnection();
            String sql = "select vice_prefeito.id_vice, vice_prefeito.id_partido, vice_prefeito.id_prefeito, vice_prefeito.nome, DATE_FORMAT(vice_prefeito.data_nascimento, '%d/%m/%Y'), vice_prefeito.foto from vice_prefeito, prefeito where (vice_prefeito.id_prefeito = prefeito.id_prefeito) and prefeito.numero = ?";
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            statement.setLong(1, id);

            resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                JOptionPane.showMessageDialog(null, "Não há candidato com esse número");
                return null;
            } else {

                VicePrefeito vice = new VicePrefeito();
                vice.setIdViceP(resultSet.getInt(1));
                vice.setIdPartidoViceP(resultSet.getInt(2));
                vice.setIdPrefeito(resultSet.getInt(3));
                vice.setNome(resultSet.getString(4));
                vice.setDataNascimento(resultSet.getString(5));
                vice.setFotoViceP(resultSet.getBytes(6));
                return vice;
            }

        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    //A exclusão já acontece por parte do prefeito.
    @Override
    public void delete(VicePrefeito entidade) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
    }

    @Override
    public void insert(VicePrefeito entidade) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        try {
            connection = DbUtils.getConnection();
            String sql = "insert into vice_prefeito(id_partido, id_prefeito, nome, data_nascimento, foto) values(?, ?, ?, ?, ?)";
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            statement.setInt(1, entidade.getIdPartidoViceP());
            statement.setInt(2, entidade.getIdPrefeito());
            statement.setString(3, entidade.getNome());
            statement.setString(4, entidade.getDataNascimento());
            statement.setBytes(5, entidade.getFotoViceP());
            statement.execute();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void update(VicePrefeito entidade) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        try {
            connection = DbUtils.getConnection();

            PreparedStatement statement = null;
            if (entidade.getFotoViceP() != null) {
                String sql = "update vice_prefeito set id_partido=?, nome=?, data_nascimento=?, foto=? where id_vice=?";
                statement = DbUtils.getPreparedStatement(connection, sql);
                statement.setInt(1, entidade.getIdPartidoViceP());
                statement.setString(2, entidade.getNome());
                statement.setString(3, entidade.getDataNascimento());
                statement.setBytes(4, entidade.getFotoViceP());
                statement.setInt(5, entidade.getIdViceP());
            } else {
                String sql = "update vice_prefeito set id_partido=?,  nome=?, data_nascimento=? where id_vice=?";
                statement = DbUtils.getPreparedStatement(connection, sql);
                statement.setInt(1, entidade.getIdPartidoViceP());
                statement.setString(2, entidade.getNome());
                statement.setString(3, entidade.getDataNascimento());
                statement.setInt(4, entidade.getIdViceP());
            }

            statement.execute();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public List<VicePrefeito> all() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        return null;
    }
}
