package com.musichub.midia;

public class Musica extends Midia {
    private String album;

    public Musica(String titulo, String artista, String duracao, String genero, String album) {
        super(titulo, artista, duracao, genero);
        this.album = album;
    }

    public String getAlbum() {
        return this.album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}