package br.com.etec.dao;

import br.com.etec.log.Log;
import br.com.etec.utils.DbUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jose
 */
public class Validar {

    public boolean verificaEleitor(long numero) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        connection = DbUtils.getConnection();
        ResultSet resultSet = null;

        try {
            String sql = "select votou from eleitor where id_eleitor = ?";

            int resultQuery = 0;

            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            statement.setLong(1, numero);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                resultQuery = resultSet.getInt("votou");
            }

            if (resultQuery == 1) {
                return true;
            } else {
                return false;
            }

        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void validaVoto(long numero) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        connection = DbUtils.getConnection();

        try {
            String sql = "update eleitor set votou=? where id_eleitor=?";

            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            statement.setLong(1, 1);
            statement.setLong(2, numero);
            statement.execute();
            
            Log.i("ValidarVoto", "OK");

        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    private Connection connection;
}
