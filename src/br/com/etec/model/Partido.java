/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etec.model;

/**
 *
 * @author Victor
 */
public class Partido {

    private int id, numero;
    private String nome, sigla, data_criacao, slogan;
    private byte[] logo;    

    public String getData() {
        return data_criacao;
    }

    public int getId() {
        return id;
    }

    public byte[] getLogo() {
        return logo;
    }

    public String getNome() {
        return nome;
    }

    public int getNumero() {
        return numero;
    }

    public String getSigla() {
        return sigla;
    }

    public void setData(String data_criacao) {
        this.data_criacao = data_criacao;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getSlogan() {
        return slogan;
    }      
}
