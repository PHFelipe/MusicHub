package com.musichub.tests;

import com.musichub.Catalogo;
import com.musichub.enums.GeneroMusica;
import com.musichub.exceptions.DuracaoInvalidaException;
import com.musichub.midia.Midia;
import com.musichub.midia.Musica;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CatalogoTest {

    @Test
    void adicionaMusica() throws DuracaoInvalidaException {
        Catalogo catalogo = new Catalogo();
        Midia musica = new Musica("IN THE END", "LINKIN PARK", "3:36", GeneroMusica.ROCK,"SEM ALBUM");

        catalogo.adicionarMidia(musica);
        assertEquals(1, catalogo.getQuantidadeMidias());
    }
}
