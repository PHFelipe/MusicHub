package com.musichub;

import com.musichub.exceptions.DuracaoInvalidaException;
import com.musichub.exceptions.MidiaNaoEncontradaException;
import com.musichub.midia.Midia;
import com.musichub.midia.Musica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Catalogo implements IServicoStreaming {

    private List<Midia> catalogoMidias;

    private List<Playlist> playlists;

    public Catalogo() {
        this.catalogoMidias = new ArrayList<>();
        this.playlists = new ArrayList<>();
    }

    @Override
    public void adicionarMidia(Midia midia) throws DuracaoInvalidaException {
        DuracaoValidator.validarDuracao(midia);
        catalogoMidias.add(midia);
    }

    public void adicionarPlaylist(Playlist playlist){
        playlists.add(playlist);
    }
    @Override
    public boolean removerMidia(String titulo) throws MidiaNaoEncontradaException {
        boolean removeu = catalogoMidias.removeIf(m -> m.getTitulo().equalsIgnoreCase(titulo));

        if (!removeu) {
            throw new MidiaNaoEncontradaException();
        }

        return true;
    }

    // Permite a busca por artista ou por título
    @Override
    public List<Midia> buscarMidia(String termo) throws MidiaNaoEncontradaException {
        String busca = termo.toLowerCase();

        List<Midia> resultado = catalogoMidias.stream()
                .filter(midia -> midia.getTitulo().toLowerCase().contains(busca)
                        || midia.getArtista().toLowerCase().contains(busca))
                .collect(Collectors.toList());

        if (resultado.isEmpty()) {
            throw new MidiaNaoEncontradaException();
        }

        return resultado;
    }

    public int getQuantidadeMidias() {
        return catalogoMidias.size();
    }

    public List<Midia> getCatalogoMidias() {
        return Collections.unmodifiableList(catalogoMidias);
    }

    public List<Playlist> getPlaylists(){
        return this.playlists;
    }

}
