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
public class VicePrefeito {

    private int idViceP;
    private int idPartidoViceP;
    private int idPrefeito;
    private String nome;
    private String dataNascimento;
    private byte[] fotoViceP;

    public int getIdViceP() {
        return idViceP;
    }

    public void setIdViceP(int idViceP) {
        this.idViceP = idViceP;
    }

    public int getIdPartidoViceP() {
        return idPartidoViceP;
    }

    public void setIdPartidoViceP(int idPartidoViceP) {
        this.idPartidoViceP = idPartidoViceP;
    }

    public int getIdPrefeito() {
        return idPrefeito;
    }

    public void setIdPrefeito(int idPrefeito) {
        this.idPrefeito = idPrefeito;
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

    public byte[] getFotoViceP() {
        return fotoViceP;
    }

    public void setFotoViceP(byte[] fotoViceP) {
        this.fotoViceP = fotoViceP;
    }

    
}
