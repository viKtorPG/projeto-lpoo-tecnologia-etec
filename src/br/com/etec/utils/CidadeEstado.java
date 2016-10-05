/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etec.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jose
 */
public class CidadeEstado {
    
    private Connection connection;

    public String[] allEstado() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        List<String> list = new ArrayList<>();
        String sql = "select id_estado, uf from estado;";
        ResultSet resultSet = null;
        
        try{
            connection = DbUtils.getConnection();
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                list.add(resultSet.getString(2));
            }
        }finally{
            if(connection != null){
                connection.close();
            }
        }
        
        String[] listEstadoUF = new String[list.size()];
        listEstadoUF = list.toArray(listEstadoUF);
        return listEstadoUF;
    }
    
    
    public String[] allCidade(int id) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        String sql = "select cidade.nome from cidade inner join estado on cidade.id_estado = estado.id_estado where estado.id_estado = ?";
        List<String> list = new ArrayList<>();
        ResultSet resultSet = null;
        
        try{
            connection = DbUtils.getConnection();
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            statement.setInt(1, id);
            
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                list.add(resultSet.getString(1));
            }
        }finally{
            if(connection != null){
                connection.close();
            }
        }
        
        String[] listCidades = new String[list.size()];
        listCidades = list.toArray(listCidades);
        
        return listCidades;
    }
    
    public int retornaIdCidade(String cidade, int estado) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        int idCidade = 0;
        String sql = "select cidade.id_cidade from cidade where cidade.nome = ? and cidade.id_estado = ?";
        ResultSet resultSet = null;
        
        try{
            connection = DbUtils.getConnection();
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            statement.setString(1, cidade);
            statement.setInt(2, estado);
            
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                idCidade = resultSet.getInt(1);
            }
        }finally{
            if(connection != null){
                connection.close();
            }
        }
        return idCidade;
    }
    
    public int retornaIdEstado(String estado) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        int idCidade = 0;
        String sql = "select estado.id_estado from estado where estado.uf = ?";
        ResultSet resultSet = null;
        
        try{
            connection = DbUtils.getConnection();
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            statement.setString(1, estado);
            
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                idCidade = resultSet.getInt(1);
            }
        }finally{
            if(connection != null){
                connection.close();
            }
        }
        return idCidade;
    }
}
