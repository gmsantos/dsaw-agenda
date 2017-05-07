/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Daniel
 */
public class Compromisso {
    
    private String titulo;
    private String tipo;
    private String data;
    private String hora;
    private String local;
    private Double duracao;
    private String observacao;
    
    public Compromisso(){
        titulo     = "";
        tipo       = "";
        data       = "";
        hora       = "";
        local      = "";
        duracao    =  0.0;
        observacao = "";
    }
    
    public String getTitulo(){
        return titulo;
    }
    
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    
    public String getTipo(){
        return tipo;
    }
    
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    
    public String getData(){
        return data;
    }
    
    public void setData(String data){
        this.data = data;
    }
    
    public String getHora(){
        return hora;
    }
    
    public void setHora(String hora){
        this.hora = hora;
    }
    
    public String getLocal(){
        return local;
    }
    
    public void setLocal(String local){
        this.local = local;
    }
    
    public Double getDuracao(){
        return duracao;
    }
    
    public void setDuracao(Double duracao){
        this.duracao = duracao;
    }
    
    public String getObsevacao(){
        return observacao;
    }
    
    public void setObservacao(String observacao){
        this.observacao = observacao;
    }
    
    public boolean save(){
        boolean retorno = false;
        ConexaoDB conexao = new ConexaoDB();
        Connection conn = conexao.getConexao();
        
        if(conn != null){
            PreparedStatement p;
            try {
                p = conn.prepareStatement(
                    "INSERT INTO compromissos VALUES (?,?,?,?,?,?,?)"
                );
                p.setString(1, titulo);
                p.setString(2, tipo);
                p.setString(3, data);
                p.setString(4, hora);
                p.setString(5, local);
                p.setDouble(6, duracao);
                p.setString(7, observacao);
                p.executeUpdate();
                retorno = true;
            }
            catch(SQLException ex)
            {
                System.err.println("Falha no cadastro: " + ex.getMessage());
            }
            finally
            {
                conexao.fecharConexao();
            }
        }
        return retorno;
    }
    
    public boolean select(){
        boolean retorno = false;
        ConexaoDB conexao = new ConexaoDB();
        Connection conn = conexao.getConexao();
        
        if(conn != null){
            PreparedStatement p;
            try {
                p = conn.prepareStatement(
                    "SELECT * FROM compromissos WHERE com_data = ? AND com_hora = ? AND com_local = ?");
                p.setString(1, data);
                p.setString(2, hora);
                p.setString(3, local);
                
                ResultSet r = p.executeQuery();
                
                if(r.next()){
                    titulo      = r.getString(1);
                    tipo        = r.getString(2);
                    data        = r.getString(3);
                    hora        = r.getString(4);
                    local       = r.getString(5);
                    duracao     = r.getDouble(6);
                    observacao  = r.getString(7);
                    
                    retorno = true;
                }
            } catch(SQLException ex) {
                System.err.println("Falha ao buscar pelos compromissos: " + ex.getMessage());
            } finally {
                conexao.fecharConexao();
            }
        }
      return retorno;      
    }
}
