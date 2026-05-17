package com.musichub;

import com.musichub.midia.Midia;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Catalogo implements ServicoStreaming{

    private List<Midia> catalogoMidias;

    public Catalogo() {
        this.catalogoMidias = new ArrayList<>();
    }

    @Override
    public void adicionarMidia(Midia midia) {
        catalogoMidias.add(midia);
    }

    @Override
    public boolean removerMidia(String titulo) {
        return catalogoMidias.removeIf(p -> p.getTitulo().equalsIgnoreCase(titulo));
    }

    //Permite a busca por Artista ou por Titulo
    @Override
    public List<Midia> BuscarMidia(String termo) {
        String t = termo.toLowerCase();
        return catalogoMidias.stream().filter(
                midia -> midia.getTitulo().toLowerCase().contains(t)
                || midia.getArtista().toLowerCase().contains(t)
        ).collect(Collectors.toList());
    }
}
