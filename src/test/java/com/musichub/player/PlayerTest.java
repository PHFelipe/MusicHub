package com.musichub.player;

import com.musichub.Player;
import com.musichub.Playlist;
import com.musichub.enums.GeneroMusica;
import com.musichub.exceptions.DuracaoInvalidaException;
import com.musichub.midia.Midia;
import com.musichub.midia.Musica;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    @DisplayName("Deve adicionar mídia na fila e definir mídia atual")
    void deveAdicionarMidiaNaFilaEDefinirMidiaAtual() {

        Player player = new Player();
        Midia musica = criarMusica("Sultans of Swing", "Dire Straits", "00:05:48");


        player.adicionarNaFila(musica);


        assertEquals(1, player.getFila().size());
        assertEquals(musica, player.getMidiaAtual());
        assertEquals(0, player.getIndiceAtual());
    }

    @Test
    @DisplayName("Deve carregar playlist no player")
    void deveCarregarPlaylistNoPlayer() throws DuracaoInvalidaException {

        Player player = new Player();
        Playlist playlist = new Playlist("Fila teste");
        Midia primeira = criarMusica("Primeira", "Artista A", "00:02:00");
        Midia segunda = criarMusica("Segunda", "Artista B", "00:03:00");
        playlist.adicionarMidia(primeira);
        playlist.adicionarMidia(segunda);


        player.carregarPlaylist(playlist);


        assertEquals(2, player.getFila().size());
        assertEquals(primeira, player.getMidiaAtual());
        assertEquals(0, player.getIndiceAtual());
    }

    @Test
    @DisplayName("Deve avançar e voltar faixas")
    void deveAvancarEVoltarFaixas() {

        Player player = new Player();
        Midia primeira = criarMusica("Primeira", "Artista A", "00:02:00");
        Midia segunda = criarMusica("Segunda", "Artista B", "00:03:00");
        player.adicionarNaFila(primeira);
        player.adicionarNaFila(segunda);


        player.proximo();


        assertEquals(segunda, player.getMidiaAtual());
        assertEquals(1, player.getIndiceAtual());


        player.anterior();


        assertEquals(primeira, player.getMidiaAtual());
        assertEquals(0, player.getIndiceAtual());
    }

    @Test
    @DisplayName("Não deve ultrapassar os limites da fila")
    void naoDeveUltrapassarLimitesDaFila() {

        Player player = new Player();
        Midia unica = criarMusica("Única", "Artista", "00:03:00");
        player.adicionarNaFila(unica);


        player.proximo();
        player.anterior();


        assertEquals(unica, player.getMidiaAtual());
        assertEquals(0, player.getIndiceAtual());
    }

    @Test
    @DisplayName("Deve alternar entre play e pause")
    void deveAlternarPlayEPause() {

        Player player = new Player();
        player.adicionarNaFila(criarMusica("Teste", "Artista", "00:03:00"));


        player.play();


        assertTrue(player.isTocando());


        player.pause();


        assertFalse(player.isTocando());
    }

    @Test
    @DisplayName("Deve limitar volume entre zero e cem")
    void deveLimitarVolumeEntreZeroECem() {

        Player player = new Player();


        player.setVolume(120);
        assertEquals(100, player.getVolume());

        player.setVolume(-10);
        assertEquals(0, player.getVolume());

        player.setVolume(55);
        assertEquals(55, player.getVolume());
    }

    private Musica criarMusica(String titulo, String artista, String duracao) {
        return new Musica(titulo, artista, duracao, GeneroMusica.ROCK, "Álbum teste");
    }
}
