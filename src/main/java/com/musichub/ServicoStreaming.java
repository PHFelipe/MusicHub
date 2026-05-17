package com.musichub;

import com.musichub.midia.Midia;

import java.util.List;

public interface ServicoStreaming {
    void adicionarMidia(Midia midia);

    boolean removerMidia(String titulo);


    //Permite a busca por Artista ou por Titulo
    List<Midia> BuscarMidia(String termo);
}