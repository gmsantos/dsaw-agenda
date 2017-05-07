/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
    
    public Connection getConexao() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver()); 
            return DriverManager.getConnection("jdbc:mysql://mysql:3306/mydb?autoReconnect=true","root","root");
        } catch(SQLException e) {
            throw new RuntimeException(e);            
        }
    }
    
    public void fecharConexao(){
        try {
            if(getConexao()!= null){
                if(!DriverManager.getConnection("jdbc:mysql://mysql:3306/mydb?autoReconnect=true","root","root").isClosed()){
                    getConexao().close();
                }
            }
        }
        catch (SQLException excecao) {
            System.err.println("Erro ao fechar a conexao com o banco: " + excecao.getMessage());
        }
    }
    
}