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

    public List<Compromisso> getAll(){
        List<Compromisso> collection = new ArrayList<Compromisso>();

        try {
            PreparedStatement stmn = connection.getConnection().prepareStatement(
                "SELECT id, titulo, tipo, data, local, duracao, observacao FROM compromissos;"
            );
            
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

    public List<Compromisso> getAllByUser(int userId){
        List<Compromisso> collection = new ArrayList<Compromisso>();

        try {
            PreparedStatement stmn = connection.getConnection().prepareStatement(
                "SELECT id, titulo, tipo, data, local, duracao, observacao FROM compromissos WHERE usuario_id = ?;"
            );

            stmn.setInt(1, userId);
            
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

    public Compromisso getOne(int id) throws RuntimeException{
        try {
            PreparedStatement stmn = connection.getConnection().prepareStatement(
                "SELECT id, titulo, tipo, data, local, duracao, observacao FROM compromissos WHERE id = ?;"
            );            
            stmn.setInt(1, id);
            
            ResultSet rs = stmn.executeQuery();
            
            if (rs.next()) {
                Compromisso compromisso = new Compromisso();

                compromisso.setId(rs.getInt("id"));
                compromisso.setTitulo(rs.getString("titulo"));
                compromisso.setTipo(rs.getString("tipo"));
                compromisso.setData(rs.getTimestamp("data"));
                compromisso.setLocal(rs.getString("local"));
                compromisso.setDuracao(rs.getDouble("duracao"));
                compromisso.setObservacao(rs.getString("observacao"));

                return compromisso;
            }

            connection.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new RuntimeException("Compromisso not found");
    }
    
    public boolean insert(Compromisso compromisso) {
        try {
            PreparedStatement stmn = connection.getConnection().prepareStatement(
                "INSERT INTO compromissos (titulo, tipo, data, local, duracao, observacao, usuario_id) VALUES (?,?,?,?,?,?,?)"
            );
            stmn.setString(1, compromisso.getTitulo());
            stmn.setString(2, compromisso.getTipo());
            stmn.setTimestamp(3, connection.toTimestamp(compromisso.getData()));
            stmn.setString(4, compromisso.getLocal());
            stmn.setDouble(5, compromisso.getDuracao());
            stmn.setString(6, compromisso.getObservacao());
            stmn.setInt(7, compromisso.getUserId());
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

    public boolean update(Compromisso compromisso) {
        try {
            PreparedStatement stmn = connection.getConnection().prepareStatement(
                "UPDATE compromissos SET titulo = ?, tipo = ?, data = ?, local = ?, duracao = ?, observacao = ? WHERE id = ?;"
            );
            stmn.setString(1, compromisso.getTitulo());
            stmn.setString(2, compromisso.getTipo());
            stmn.setTimestamp(3, connection.toTimestamp(compromisso.getData()));
            stmn.setString(4, compromisso.getLocal());
            stmn.setDouble(5, compromisso.getDuracao());
            stmn.setString(6, compromisso.getObservacao());
            stmn.setInt(7, compromisso.getId());
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

    public boolean delete(int id) {
        try {
            PreparedStatement stmn = connection.getConnection().prepareStatement(
                "DELETE FROM compromissos WHERE id = ?;"
            );
            stmn.setInt(1, id);
            stmn.executeUpdate();

            connection.closeConnection();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean ownedByUser(int id, int userId) {
        try {
            PreparedStatement stmn = connection.getConnection().prepareStatement(
                "SELECT * FROM compromissos WHERE id = ? AND usuario_id = ?"
            );
            stmn.setInt(1, id);
            stmn.setInt(2, userId);
            
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

    public boolean find(Compromisso compromisso) {
        try {
            PreparedStatement stmn = connection.getConnection().prepareStatement(
                "SELECT titulo, tipo, data, local, duracao, observacao FROM compromissos WHERE data = ? AND local = ?"
            );
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
