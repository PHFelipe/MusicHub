package com.musichub;

import com.musichub.enums.*;
import com.musichub.midia.*;

public class Main {
    public static void main(String[] args) {
        // Instância de Música
        Musica musica = new Musica(
                "Bohemian Rhapsody",
                "Queen",
                "5:55",
                GeneroMusica.ROCK,
                "A Night at the Opera"
        );
        // Instância de Podcast
        Podcast podcast = new Podcast(
                "Ciência Sem Fim",
                "Sergião Sacani",
                "2:10:00",
                GeneroPodcast.TECNOLOGIA,
                "Sergião Sacani",
                "152"
        );
        // Instância de Audiobook
        Audiobook audiobook = new Audiobook(
                "Harry Potter e a Pedra Filosofal",
                "J.K. Rowling",
                "8:33:00",
                GeneroAudiobook.FANTASIA,
                "Ícaro Silva"
        );

        musica.reproduzir();
        System.out.println("\n");
        podcast.reproduzir();
        System.out.println("\n");
        audiobook.reproduzir();
    }
}