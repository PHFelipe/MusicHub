package com.musichub;

import com.musichub.exceptions.DuracaoInvalidaException;
import com.musichub.exceptions.MidiaNaoEncontradaException;
import com.musichub.midia.Midia;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Playlist implements IServicoStreaming{
    private String nomePlaylist;
    private List<Midia> midias;

    public Playlist(String nomePlaylist) {
        this.midias = new ArrayList<>();
        this.nomePlaylist = nomePlaylist;
    }

    public String getNomePlaylist() { return nomePlaylist; }

    public void setNomePlaylist(String nomePlaylist) { this.nomePlaylist = nomePlaylist; }

    @Override
    public void adicionarMidia(Midia midia) throws DuracaoInvalidaException{
        DuracaoValidator.validarDuracao(midia);
        midias.add(midia);
    }
    @Override
    public List<Midia> buscarMidia(String termo) throws MidiaNaoEncontradaException {
        String busca = termo.toLowerCase();

        List<Midia> resultado = midias.stream()
                .filter(midia -> midia.getTitulo().toLowerCase().contains(busca)
                        || midia.getArtista().toLowerCase().contains(busca))
                .collect(Collectors.toList());

        if (resultado.isEmpty()) {
            throw new MidiaNaoEncontradaException();
        }

        return resultado;
    }

    @Override
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
