package com.musichub.midia;

public class Podcast extends Midia {
    private String host;
    private String numeroEpisodio;

    public Podcast(String titulo, String artista, String duracao, String genero, String host, String numeroEpisodio) {
        super(titulo, artista, duracao, genero);
        this.host = host;
        this.numeroEpisodio = numeroEpisodio;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getNumeroEpisodio() {
        return this.numeroEpisodio;
    }

    public void setNumeroEpisodio(String numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }
}