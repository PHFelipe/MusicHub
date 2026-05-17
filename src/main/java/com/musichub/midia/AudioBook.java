package com.musichub.midia;

import com.musichub.enums.GeneroAudiobook;

public class AudioBook extends Midia{
    private String narrador;
    private GeneroAudiobook genero;
    public AudioBook(String titulo, String artista, String duracao, GeneroAudiobook genero,String narrador) {
        super(titulo, artista, duracao);
        this.narrador =  narrador;
        this.genero = genero;
    }

    public String getNarrador() {
        return narrador;
    }

    public void setNarrador(String narrador) {
        this.narrador = narrador;
    }

    public GeneroAudiobook getGenero() {
        return genero;
    }

    public void setGenero(GeneroAudiobook genero) {
        this.genero = genero;
    }
}
