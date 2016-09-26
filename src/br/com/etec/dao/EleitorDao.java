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
public class EleitorDao implements IAbstractDao<Eleitor>{

    private Connection connection = null;

    @Override
    public List<Eleitor> all() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        try {
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
                eleitor.setEstado(resultSet.getString(8));
            }
            return list;
        }finally{
            if(connection != null){
                connection.close();
            }
        }

    }

    @Override
    public Eleitor findById(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        ResultSet resultSet;
        
        try{
            connection = DbUtils.getConnection();
            String sql = "select * from eleitor where id_eleitor = ?";
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            
            statement.setInt(1, id);
            
            resultSet = statement.executeQuery();
            
            if(!resultSet.next()){
                JOptionPane.showMessageDialog(null, "Eleitor não encontrado!");
                return null;
            }else{
                Eleitor eleitor = new Eleitor();
                eleitor.setIdCod(resultSet.getInt(1));
                eleitor.setNome(resultSet.getString(2));
                eleitor.setZona(resultSet.getString(3));
                eleitor.setSecao(resultSet.getString(4));
                eleitor.setDataNascimento(resultSet.getString(5));
                eleitor.setDataEmissao(resultSet.getString(6));
                eleitor.setIdCidade(resultSet.getInt(7));
                eleitor.setEstado(resultSet.getString(8));
                
                return eleitor;
            }
        }finally{
            if(connection != null){
                connection.close();
            }
        }
    }

    @Override
    public void insert(Eleitor entidade) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        try {
            connection = DbUtils.getConnection();
            
            String sql = "insert into eleitor(nome, data_nascimento, zona, secao, id_cidade) values(?, ?, ?, ?, ?)";
            
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            statement.setString(1, entidade.getNome());
            statement.setString(2, entidade.getDataNascimento());
            statement.setString(3, entidade.getZona());
            statement.setString(4, entidade.getSecao());
            statement.setInt(5, entidade.getIdCidade());
            
            statement.execute();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void update(Eleitor entidade) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {

    }

    @Override
    public void delete(Eleitor entidade) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

    }
}
