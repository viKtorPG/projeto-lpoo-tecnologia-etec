/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etec.model;

import java.io.File;
import java.io.FileInputStream;
import javax.swing.ImageIcon;

/**
 *
 * @author Victor
 */
public class Candidato {

    private String nome;
    private int numero;
    private String siglaPartido;
    private File foto;
    private int id;
    public static FileInputStream inputStream;
    private FileInputStream fileInputStream;
    private File file;
    private ImageIcon imageIcon;
    private String cargo;
    private String partido;
    private byte[] imagem;
    private int votos;

    public Candidato() {

    }
    
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getNumero() {
        return numero;
    }

    public String getSiglaPartido() {
        return siglaPartido;
    }

    public File getFoto() {
        return foto;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setSiglaPartido(String siglaPartido) {
        this.siglaPartido = siglaPartido;
    }

    public void setFoto(File foto) {
        this.foto = foto;
    }

    public void setFoto(FileInputStream fileInputStream, File file) {
       this.fileInputStream = fileInputStream;
       this.file = file;
    }
    
    public FileInputStream getFotoIS() {
       return fileInputStream;
    }
    
    public File getFotoF() {
       return file;
    }

    public void setFotoImg(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public String getCargo() {
        return cargo;
    }

    public String getPartido() {
        return partido;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public int getVotos() {
        return votos;
    }

    public void setVotos(int votos) {
        this.votos = votos;
    }
    
    
    
}
