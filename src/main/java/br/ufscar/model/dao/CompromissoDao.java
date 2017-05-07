package br.ufscar.model.dao;

import br.ufscar.model.Compromisso;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompromissoDao {

    private MySQLConnection connection;
    
    public CompromissoDao(){
        this.connection = new MySQLConnection();
    }

    public boolean insert(Compromisso compromisso) {
        try {
            PreparedStatement stmn = connection.getConnection().prepareStatement(
                "INSERT INTO compromissos (titulo, tipo, data, local, duracao, observacao) VALUES (?,?,?,?,?,?)"
            );
            stmn.setString(1, compromisso.getTitulo());
            stmn.setString(2, compromisso.getTipo());
            stmn.setTimestamp(3, connection.toTimestamp(compromisso.getData()));
            stmn.setString(4, compromisso.getLocal());
            stmn.setDouble(5, compromisso.getDuracao());
            stmn.setString(6, compromisso.getObsevacao());
            stmn.executeUpdate();

            connection.closeConnection();

            return true;            
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }

        connection.closeConnection();
        return false;
    }

    public List<Compromisso> getAll(){
        List<Compromisso> collection = new ArrayList<Compromisso>();

        try {
            PreparedStatement stmn = connection.getConnection().prepareStatement(
                "SELECT id, titulo, tipo, data, local, duracao, observacao FROM compromissos;");
            
            ResultSet rs = stmn.executeQuery();
            
            while (rs.next()){
                Compromisso compromisso = new Compromisso();

                compromisso.setId(rs.getInt("id"));
                compromisso.setTitulo(rs.getString("titulo"));
                compromisso.setTipo(rs.getString("tipo"));
                compromisso.setData(rs.getTimestamp("data"));
                compromisso.setLocal(rs.getString("local"));
                compromisso.setDuracao(rs.getDouble("duracao"));
                compromisso.setObservacao(rs.getString("observacao"));

                collection.add(compromisso);
            }

            connection.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return collection;
    }
    
    public boolean find(Compromisso compromisso) {
        try {
            PreparedStatement stmn = connection.getConnection().prepareStatement(
                "SELECT titulo, tipo, data, local, duracao, observacao FROM compromissos WHERE data = ? AND local = ?");
            stmn.setTimestamp(1, connection.toTimestamp(compromisso.getData()));
            stmn.setString(2, compromisso.getLocal());
            
            ResultSet rs = stmn.executeQuery();
            
            if(rs.next()){
                connection.closeConnection();
                return true;
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }

        connection.closeConnection();
        return false;
    }
}
