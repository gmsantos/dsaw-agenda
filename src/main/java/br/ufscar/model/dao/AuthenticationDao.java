package br.ufscar.model.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthenticationDao {

    private MySQLConnection connection;

    private int authUserId;

    private String authUserName;
    
    public AuthenticationDao(){
        this.connection = new MySQLConnection();
    }

    public boolean attempt(String username, String password){
        try {
            PreparedStatement stmn = connection.getConnection().prepareStatement(
                "SELECT id, name FROM usuarios WHERE login = ? AND password = ?;"
            );            
            stmn.setString(1, username);
            stmn.setString(2, getHash(password));
            
            ResultSet rs = stmn.executeQuery();
            
            if (rs.next()) {
                authUserId = rs.getInt("id");
                authUserName = rs.getString("name");

                return true;
            }

            connection.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        connection.closeConnection();
        return false;
    }

    public int getAuthUserId(){
        return authUserId;
    }

    public String getAuthUserName(){
        return authUserName;
    }

    /**
     * Esta função retorna um hash da senha fornecida pelo usuário
     * Este hash é produzido utilizando o algoritmo MD5
     */
    private String getHash(String senha) throws NoSuchAlgorithmException {
        MessageDigest algoritmo = MessageDigest.getInstance("MD5");
        algoritmo.reset();
        algoritmo.update(senha.getBytes());

        byte[] messageDigest = algoritmo.digest();
        StringBuilder strBuilder = new StringBuilder();

        for (int i = 0; i < messageDigest.length; i++) {
            int parteAlta = ((messageDigest[i] >> 4) & 0xf) << 4;
            int parteBaixa = messageDigest[i] & 0xf;
            
            if (parteAlta == 0) {
                strBuilder.append('0');
            }
            
            strBuilder.append(Integer.toHexString(parteAlta | parteBaixa));
        }

        return strBuilder.toString();
    }
}