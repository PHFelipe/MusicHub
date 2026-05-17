package com.musichub.midia;

public class AudioBook extends Midia{
    private String narrador;
    public AudioBook(String titulo, String artista, String duracao, String genero,String narrador) {
        super(titulo, artista, duracao, genero);
        this.narrador =  narrador;
    }
}
