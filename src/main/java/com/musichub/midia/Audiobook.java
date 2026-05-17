package com.musichub.midia;

import com.musichub.enums.GeneroAudiobook;

public class Audiobook extends Midia{
    private String narrador;
    private GeneroAudiobook genero;
    public Audiobook(String titulo, String artista, String duracao, GeneroAudiobook genero, String narrador) {
        super(titulo, artista, duracao);
        this.narrador =  narrador;
        this.genero = genero;
    }

    @Override
    public void reproduzir(){
        System.out.println("Reproduzindo o Audiobook " + getTitulo() + " Narrado por " + getNarrador());
        System.out.println("Duração: " + getDuracao());
        System.out.println("Genero: " + getGenero());
        System.out.println("Autor: "+ getArtista());
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
