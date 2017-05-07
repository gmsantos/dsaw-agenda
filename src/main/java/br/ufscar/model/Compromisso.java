package br.ufscar.model;

import java.util.Date;

public class Compromisso {
    
    private int id;
    private String titulo;
    private String tipo;
    private Date data;
    private String local;
    private Double duracao;
    private String observacao;
        
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
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
    
    public String getObservacao(){
        return observacao;
    }
    
    public void setObservacao(String observacao){
        this.observacao = observacao;
    }

    public boolean isValid(){
        return !titulo.isEmpty() 
            && !tipo.isEmpty()
            && !local.isEmpty() 
            && !duracao.isNaN() 
            && !observacao.isEmpty();
    }
}
