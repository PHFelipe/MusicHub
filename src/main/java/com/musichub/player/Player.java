package com.musichub.player;

import com.musichub.midia.Midia;
import com.musichub.Playlist;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private Midia midiaAtual;
    private List<Midia> fila;
    private int indiceAtual;
    private boolean tocando;
    private int volume; // 0-100

    public Player() {
        this.fila = new ArrayList<>();
        this.indiceAtual = 0;
        this.tocando = false;
        this.volume = 80;
    }

    public void carregarPlaylist(Playlist playlist) {
        fila.clear();
        fila.addAll(playlist.getMidias());
        indiceAtual = 0;
        if (!fila.isEmpty()) {
            midiaAtual = fila.get(0);
        }
    }

    public void play() {
        if (midiaAtual != null) {
            tocando = true;
            midiaAtual.reproduzir();
        }
    }

    public void pause() {
        tocando = false;
    }

    public void proximo() {
        if (indiceAtual < fila.size() - 1) {
            indiceAtual++;
            midiaAtual = fila.get(indiceAtual);
            if (tocando) midiaAtual.reproduzir();
        }
    }

    public void anterior() {
        if (indiceAtual > 0) {
            indiceAtual--;
            midiaAtual = fila.get(indiceAtual);
            if (tocando) midiaAtual.reproduzir();
        }
    }

    public void setVolume(int volume) {
        this.volume = Math.max(0, Math.min(100, volume));
    }

    public void adicionarNaFila(Midia midia) {
        fila.add(midia);
        if (midiaAtual == null) {
            midiaAtual = midia;
            indiceAtual = 0;
        }
    }

    public Midia getMidiaAtual() { return midiaAtual; }
    public List<Midia> getFila() { return fila; }
    public boolean isTocando() { return tocando; }
    public int getVolume() { return volume; }
    public int getIndiceAtual() { return indiceAtual; }
}
