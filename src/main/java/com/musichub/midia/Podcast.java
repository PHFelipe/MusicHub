package com.musichub.midia;

import com.musichub.enums.GeneroPodcast;

public class Podcast extends Midia {
    private String host;
    private String numeroEpisodio;
    private GeneroPodcast genero;

    public Podcast(String titulo, String artista, String duracao, GeneroPodcast genero, String host, String numeroEpisodio) {
        super(titulo, artista, duracao);
        this.host = host;
        this.numeroEpisodio = numeroEpisodio;
        this.genero = genero;
    }

    @Override
    public void reproduzir() {
        System.out.println("Reproduzindo o Podcast " + getTitulo() + " apresentado por " + getHost());
        System.out.println("Episódio: " + getNumeroEpisodio());
        System.out.println("Duração: " + getDuracao());
        System.out.println("Genero: " + getGenero());
        System.out.println("Criador: " + getArtista());
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

    public GeneroPodcast getGenero() {
        return genero;
    }

    public void setGenero(GeneroPodcast genero) {
        this.genero = genero;
    }
}