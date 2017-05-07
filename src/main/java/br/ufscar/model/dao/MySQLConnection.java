package br.ufscar.model.dao;

import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;

public class MySQLConnection {    
    
    public Connection getConnection() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver()); 
            return DriverManager.getConnection("jdbc:mysql://mysql:3306/mydb?autoReconnect=true","root","root");
        } catch(SQLException e) {
            throw new RuntimeException(e);            
        }
    }
    
    public void closeConnection(){
        try {
            if (getConnection() != null) {
                if (!DriverManager.getConnection("jdbc:mysql://mysql:3306/mydb?autoReconnect=true","root","root").isClosed()) {
                    getConnection().close();
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Timestamp toTimestamp(Date date) {
        return new Timestamp(date.getTime());
    }
}