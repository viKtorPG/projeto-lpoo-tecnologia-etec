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
public class Vereador {
    private int idVereador;
    private int idPartido;
    private int idPrefeito;
    private String nome;
    private String dataNascimento;
    private int numero;
    private byte[] foto;
    private int voto;

    public int getIdVereador() {
        return idVereador;
    }

    public void setIdVereador(int idVereador) {
        this.idVereador = idVereador;
    }

    public int getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
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

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public int getVoto() {
        return voto;
    }

    public void setVoto(int voto) {
        this.voto = voto;
    }
    
    
}
