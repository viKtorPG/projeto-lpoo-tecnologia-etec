/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etec.model;

/**
 *
 * @author jose
 */
public class Eleitor {

    private int idCod;
    private String nome;
    private String dataNascimento;
    private String cidade;
    private String estado;
    private String zona;
    private String secao;
    private String dataEmissao;

    public Eleitor() {
    }

    
    
    public Eleitor(int idCod, String nome, String dataNascimento, String cidade, String estado, String zona, String secao, String dataEmissao) {
        this.idCod = idCod;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cidade = cidade;
        this.estado = estado;
        this.zona = zona;
        this.secao = secao;
        this.dataEmissao = dataEmissao;
    }
    
    public int getIdCod() {
        return idCod;
    }

    public void setIdCod(int idCod) {
        this.idCod = idCod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getSecao() {
        return secao;
    }

    public void setSecao(String secao) {
        this.secao = secao;
    }

    public String getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(String dataEmissao) {
        this.dataEmissao = dataEmissao;
    }
    
    
}
