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

class CatalogoTest {

    @Test
    @DisplayName("Deve adicionar uma mídia ao catálogo")
    void deveAdicionarMidiaAoCatalogo() throws DuracaoInvalidaException {
        Catalogo catalogo = new Catalogo();
        Midia musica = criarMusica("In The End", "Linkin Park", "00:03:36");


        catalogo.adicionarMidia(musica);


        assertEquals(1, catalogo.getQuantidadeMidias());
        assertTrue(catalogo.getCatalogoMidias().contains(musica));
    }

    @Test
    @DisplayName("Deve buscar mídia por artista")
    void deveBuscarMidiaPorArtista() throws DuracaoInvalidaException, MidiaNaoEncontradaException {

        Catalogo catalogo = new Catalogo();
        catalogo.adicionarMidia(criarMusica("Bohemian Rhapsody", "Queen", "00:05:55"));
        catalogo.adicionarMidia(criarMusica("Numb", "Linkin Park", "00:03:07"));


        List<Midia> resultado = catalogo.buscarMidia("queen");


        assertEquals(1, resultado.size());
        assertEquals("Bohemian Rhapsody", resultado.get(0).getTitulo());
    }

    @Test
    @DisplayName("Deve buscar mídia por título")
    void deveBuscarMidiaPorTitulo() throws DuracaoInvalidaException, MidiaNaoEncontradaException {

        Catalogo catalogo = new Catalogo();
        catalogo.adicionarMidia(criarMusica("Imagine", "John Lennon", "00:03:03"));


        List<Midia> resultado = catalogo.buscarMidia("imag");

        assertEquals(1, resultado.size());
        assertEquals("John Lennon", resultado.get(0).getArtista());
    }

    @Test
    @DisplayName("Deve remover mídia pelo título")
    void deveRemoverMidiaPeloTitulo() throws DuracaoInvalidaException, MidiaNaoEncontradaException {

        Catalogo catalogo = new Catalogo();
        catalogo.adicionarMidia(criarMusica("Yellow", "Coldplay", "00:04:29"));


        boolean removeu = catalogo.removerMidia("yellow");


        assertTrue(removeu);
        assertEquals(0, catalogo.getQuantidadeMidias());
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar mídia inexistente")
    void deveLancarExcecaoAoBuscarMidiaInexistente() {


        Catalogo catalogo = new Catalogo();


        assertThrows(MidiaNaoEncontradaException.class, () -> catalogo.buscarMidia("Mídia inexistente"));
    }

    @Test
    @DisplayName("Deve lançar exceção ao adicionar mídia com duração zerada")
    void deveLancarExcecaoAoAdicionarDuracaoZerada() {

        Catalogo catalogo = new Catalogo();
        Midia musica = criarMusica("Música inválida", "Artista", "00:00:00");

        assertThrows(DuracaoInvalidaException.class, () -> catalogo.adicionarMidia(musica));
    }

    private Musica criarMusica(String titulo, String artista, String duracao) {
        return new Musica(titulo, artista, duracao, GeneroMusica.ROCK, "Álbum teste");
    }
}
