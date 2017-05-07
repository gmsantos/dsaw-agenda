package br.ufscar.servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.sql.Timestamp;

public class Compromisso {
    
    private String titulo;
    private String tipo;
    private Date data;
    private String local;
    private Double duracao;
    private String observacao;
    
    public Compromisso(){
        titulo = "";
        tipo = "";
        data = new Date();
        local = "";
        duracao =  0.0;
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
    
    public Date getData(){
        return data;
    }
    
    public void setData(Date data){
        this.data = data;
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
    
    public boolean save() {
        boolean retorno = false;
        ConexaoDB conexao = new ConexaoDB();
        Connection conn = conexao.getConexao();
        
        if(conn != null){
            PreparedStatement p;
            try {
                p = conn.prepareStatement(
                    "INSERT INTO compromissos (titulo, tipo, data, local, duracao, observacao) VALUES (?,?,?,?,?,?)"
                );
                p.setString(1, titulo);
                p.setString(2, tipo);
                p.setTimestamp(3, new Timestamp(data.getTime()));
                p.setString(4, local);
                p.setDouble(5, duracao);
                p.setString(6, observacao);
                p.executeUpdate();
                retorno = true;
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
            }
            finally
            {
                conexao.fecharConexao();
            }
        }
        return retorno;
    }
    
    public boolean select() {
        boolean retorno = false;
        ConexaoDB conexao = new ConexaoDB();
        Connection conn = conexao.getConexao();
        
        if(conn != null){
            PreparedStatement p;
            try {
                p = conn.prepareStatement(
                    "SELECT titulo, tipo, data, local, duracao, observacao FROM compromissos WHERE data = ? AND local = ?");
                p.setTimestamp(1, new Timestamp(data.getTime()));
                p.setString(2, local);
                
                ResultSet r = p.executeQuery();
                
                if(r.next()){
                    titulo      = r.getString(1);
                    tipo        = r.getString(2);
                    data        = r.getTimestamp(3);
                    local       = r.getString(4);
                    duracao     = r.getDouble(5);
                    observacao  = r.getString(6);
                    
                    retorno = true;
                }
            } catch(SQLException ex) {
                ex.printStackTrace();
            } finally {
                conexao.fecharConexao();
            }
        }
      return retorno;      
    }
}
