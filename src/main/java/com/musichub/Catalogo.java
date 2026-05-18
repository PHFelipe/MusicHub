package com.musichub;

import com.musichub.exceptions.DuracaoInvalidaException;
import com.musichub.exceptions.MidiaNaoEncontradaException;
import com.musichub.midia.Midia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Catalogo implements IServicoStreaming {

    private final List<Midia> catalogoMidias;

    public Catalogo() {
        this.catalogoMidias = new ArrayList<>();
    }

    @Override
    public void adicionarMidia(Midia midia) throws DuracaoInvalidaException {
        validarDuracao(midia);
        catalogoMidias.add(midia);
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

    private void validarDuracao(Midia midia) throws DuracaoInvalidaException {
        if (midia == null || !duracaoValida(midia.getDuracao())) {
            throw new DuracaoInvalidaException();
        }
    }

    private boolean duracaoValida(String duracao) {
        if (duracao == null || !duracao.matches("\\d{2}:\\d{2}:\\d{2}")) {
            return false;
        }

        String[] partes = duracao.split(":");
        int horas = Integer.parseInt(partes[0]);
        int minutos = Integer.parseInt(partes[1]);
        int segundos = Integer.parseInt(partes[2]);

        if (minutos > 59 || segundos > 59) {
            return false;
        }

        return horas > 0 || minutos > 0 || segundos > 0;
    }
}
