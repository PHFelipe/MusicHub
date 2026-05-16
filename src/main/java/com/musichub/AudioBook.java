package com.musichub;

import com.musichub.midia.Midia;

public class AudioBook extends Midia {
    private String narrador;

    public AudioBook(String titulo, String artista, String duracao, String genero, String narrador) {
        super(titulo, artista, duracao, genero);
        this.narrador = narrador;
    }

    public String getNarrador() {
        return this.narrador;
    }

    public void setNarrador(String narrador) {
        this.narrador = narrador;
    }
}