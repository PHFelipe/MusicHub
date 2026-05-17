package com.musichub;

import com.musichub.midia.Midia;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private int IDUsuario;
    private String nomePlaylist;
    private List<Midia> midias;

    public Playlist(int IDUsuario, String nomePlaylist) {
        this.IDUsuario = IDUsuario;
        this.midias = new ArrayList<>();
        this.nomePlaylist = nomePlaylist;
    }

    public String getNomePlaylist() { return nomePlaylist; }

    public void setNomePlaylist(String nomePlaylist) { this.nomePlaylist = nomePlaylist; }

    public int getIDUsuario() { return IDUsuario; }

    public void setIDUsuario(int IDUsuario) { this.IDUsuario = IDUsuario; }

    public boolean removerMidia (String titulo){
        return midias.removeIf(m -> m.getTitulo().equalsIgnoreCase(titulo));
    }

    public String getDuracaoPlaylist() {

        int totalHoras = 0;
        int totalMinutos = 0;
        int totalSegundos = 0;

        for (Midia midia : midias) {

            String duracao = midia.getDuracao();
            String[] partes = duracao.split(":");

            int horas = Integer.parseInt(partes[0]);
            int minutos = Integer.parseInt(partes[1]);
            int segundos = Integer.parseInt(partes[2]);

            totalHoras += horas;
            totalMinutos += minutos;
            totalSegundos += segundos;
        }

        totalMinutos += totalSegundos / 60;
        totalSegundos = totalSegundos % 60;
        totalHoras += totalMinutos / 60;
        totalMinutos = totalMinutos % 60;

        return String.format(
                "%02d:%02d:%02d",
                totalHoras,
                totalMinutos,
                totalSegundos
        );
    }
    public int getTotalFaixas() { return midias.size(); }
    public List<Midia> getMidias() { return midias; }

    public void insertMidia(Midia midia){ this.midias.add(midia); }
}
