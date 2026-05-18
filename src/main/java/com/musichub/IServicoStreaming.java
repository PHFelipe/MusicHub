package com.musichub;

import com.musichub.exceptions.DuracaoInvalidaException;
import com.musichub.exceptions.MidiaNaoEncontradaException;
import com.musichub.midia.Midia;

import java.util.List;

public interface IServicoStreaming {
    void adicionarMidia(Midia midia) throws DuracaoInvalidaException;

    boolean removerMidia(String titulo) throws MidiaNaoEncontradaException;

    // Permite a busca por artista ou por título
    List<Midia> buscarMidia(String termo) throws MidiaNaoEncontradaException;
}
