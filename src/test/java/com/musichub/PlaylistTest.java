package com.musichub;

import com.musichub.enums.GeneroMusica;
import com.musichub.exceptions.DuracaoInvalidaException;
import com.musichub.exceptions.MidiaNaoEncontradaException;
import com.musichub.midia.Midia;
import com.musichub.midia.Musica;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlaylistTest {

    @Test
    @DisplayName("Deve adicionar mídia na playlist")
    void deveAdicionarMidiaNaPlaylist() throws DuracaoInvalidaException {

        Playlist playlist = new Playlist("Favoritas");
        Midia musica = criarMusica("Tempo Perdido", "Legião Urbana", "00:05:02");


        playlist.adicionarMidia(musica);


        assertEquals(1, playlist.getTotalFaixas());
        assertTrue(playlist.getMidias().contains(musica));
    }

    @Test
    @DisplayName("Deve calcular a duração total da playlist")
    void deveCalcularDuracaoTotalDaPlaylist() throws DuracaoInvalidaException {

        Playlist playlist = new Playlist("Treino");
        playlist.adicionarMidia(criarMusica("Faixa 1", "Artista A", "00:03:00"));
        playlist.adicionarMidia(criarMusica("Faixa 2", "Artista B", "00:02:30"));


        String duracaoTotal = playlist.getDuracaoPlaylist();


        assertEquals("00:05:30", duracaoTotal);
    }

    @Test
    @DisplayName("Deve somar duração passando de minutos e horas")
    void deveSomarDuracaoComViradaDeMinutosEHoras() throws DuracaoInvalidaException {

        Playlist playlist = new Playlist("Longas");
        playlist.adicionarMidia(criarMusica("Faixa longa 1", "Artista A", "01:40:40"));
        playlist.adicionarMidia(criarMusica("Faixa longa 2", "Artista B", "00:30:30"));


        String duracaoTotal = playlist.getDuracaoPlaylist();


        assertEquals("02:11:10", duracaoTotal);
    }

    @Test
    @DisplayName("Deve buscar mídia dentro da playlist")
    void deveBuscarMidiaDentroDaPlaylist() throws DuracaoInvalidaException, MidiaNaoEncontradaException {

        Playlist playlist = new Playlist("Rock");
        playlist.adicionarMidia(criarMusica("Smells Like Teen Spirit", "Nirvana", "00:05:01"));
        playlist.adicionarMidia(criarMusica("Wonderwall", "Oasis", "00:04:18"));


        List<Midia> resultado = playlist.buscarMidia("nirvana");


        assertEquals(1, resultado.size());
        assertEquals("Smells Like Teen Spirit", resultado.get(0).getTitulo());
    }

    @Test
    @DisplayName("Deve remover mídia da playlist")
    void deveRemoverMidiaDaPlaylist() throws DuracaoInvalidaException {

        Playlist playlist = new Playlist("Remover teste");
        playlist.adicionarMidia(criarMusica("Pais e Filhos", "Legião Urbana", "00:05:08"));


        boolean removeu = playlist.removerMidia("pais e filhos");

        assertTrue(removeu);
        assertEquals(0, playlist.getTotalFaixas());
    }

    @Test
    @DisplayName("Deve lançar exceção ao adicionar mídia inválida na playlist")
    void deveLancarExcecaoAoAdicionarMidiaInvalidaNaPlaylist() {

        Playlist playlist = new Playlist("Inválidas");
        Midia musica = criarMusica("Sem duração", "Artista", "00:00:00");


        assertThrows(DuracaoInvalidaException.class, () -> playlist.adicionarMidia(musica));
    }

    private Musica criarMusica(String titulo, String artista, String duracao) {
        return new Musica(titulo, artista, duracao, GeneroMusica.ROCK, "Álbum teste");
    }
}
