package br.com.etec.dao;

import br.com.etec.interfaces.dao.IAbstractDao;
import br.com.etec.model.Eleitor;
import br.com.etec.utils.DbUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author jose
 */
public class EleitorDao implements IAbstractDao<Eleitor> {

    private Connection connection = null;

    //Os eleitores são carregados em um jtable
    @Override
    public List<Eleitor> all() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        return null;

    }

    @Override
    public Eleitor findById(long id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        ResultSet resultSet;

        try {
            connection = DbUtils.getConnection();
            String sql = "select nome, DATE_FORMAT(eleitor.data_cadastro, '%d/%m/%Y'), zona, secao from eleitor where id_eleitor = ?";
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);

            statement.setLong(1, id);

            resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                JOptionPane.showMessageDialog(null, "Eleitor não encontrado!");
                return null;
            } else {
                Eleitor eleitor = new Eleitor();
                eleitor.setNome(resultSet.getString(1));
                eleitor.setDataEmissao(resultSet.getString(2));
                eleitor.setZona(resultSet.getString(3));
                eleitor.setSecao(resultSet.getString(4));

                return eleitor;
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
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
