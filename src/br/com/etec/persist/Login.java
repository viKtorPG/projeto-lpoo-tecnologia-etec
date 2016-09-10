/*
 * Retorna o usuário e senha correspondente
 */
package br.com.etec.persist;

import br.com.etec.dal.Conexao;
import br.com.etec.view.jframe.TelaDesktop;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author jose
 */
public class Login{

    public boolean logar(String usuario, String senha) {
        boolean result = false;
        
        String sql = "select * from db_usuarios where login=? and senha=?";
        
        Connection conexao = Conexao.conector();
        ResultSet resultSet;
        
        try {
            PreparedStatement statement = conexao.prepareStatement(sql);
            
            statement.setString(1, usuario);
            statement.setString(2, senha);
            
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                result = true;
                
                String perfil = resultSet.getString(6); 
                TelaDesktop.exibir("Tela");
                
                if(perfil.equals("admin")){
                    TelaDesktop.jmCadastado.setVisible(true);
                    TelaDesktop.jmRelatorio.setVisible(true);
                }
            }
            conexao.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro BD");
        }
        return result;
    }
}
