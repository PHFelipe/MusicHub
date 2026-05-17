package com.musichub.midia;

import com.musichub.enums.GeneroMusica;

public class Musica extends Midia {
    private String album;
    private GeneroMusica genero;
    public Musica(String titulo, String artista, String duracao,GeneroMusica genero, String album) {
        super(titulo, artista, duracao);
        this.album = album;
        this.genero = genero;
    }

    @Override
    public void reproduzir(){
        System.out.println("Reproduzindo a Musica " + getTitulo() + " do album " + getAlbum() + " de " + getArtista());
        System.out.println("Duração: " + getDuracao());
        System.out.println("Genero: " + getGenero());
    }
    public String getAlbum() {
        return this.album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public GeneroMusica getGenero() {
        return genero;
    }

    public void setGenero(GeneroMusica genero) {
        this.genero = genero;
    }
}