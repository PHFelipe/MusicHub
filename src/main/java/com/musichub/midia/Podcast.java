package com.musichub.midia;

import com.musichub.enums.Cores;
import com.musichub.enums.GeneroPodcast;

import static com.musichub.ui.Menu.spaces;
import static com.musichub.ui.Menu.truncar;

public class Podcast extends Midia {
    private String host;
    private String numeroEpisodio;
    private GeneroPodcast genero;

    public Podcast(String titulo, String artista, String duracao, GeneroPodcast genero, String host, String numeroEpisodio) {
        super(titulo, artista, duracao);
        this.host = host;
        this.numeroEpisodio = numeroEpisodio;
        this.genero = genero;
    }

    @Override
    public void reproduzir() {

        System.out.println(String.format("%s  │  %9s%s%-18s%s│%s", cor(Cores.PURPLE), "", cor(Cores.YELLOW), "PODCAST", cor(Cores.PURPLE), cor(Cores.RESET)));

        System.out.println(cor(Cores.PURPLE) + "  ├─────────────────────────────┤" + cor(Cores.RESET));

        System.out.println(cor(Cores.PURPLE) + "  │  " + cor(Cores.MAGENTA) + cor(Cores.BOLD) + truncar(this.getTitulo(), 27) + cor(Cores.RESET) + cor(Cores.PURPLE) + "│" + cor(Cores.RESET));

        System.out.println(cor(Cores.PURPLE) + "  │  " + cor(Cores.YELLOW) + "EP: " + truncar(this.getNumeroEpisodio(),23) + cor(Cores.RESET) + cor(Cores.PURPLE) + "│" + cor(Cores.RESET));

        System.out.println(cor(Cores.PURPLE) + "  │  " + cor(Cores.CYAN) + truncar(this.getArtista(), 27) + cor(Cores.RESET) + cor(Cores.PURPLE) + "│" + cor(Cores.RESET));

        System.out.println(cor(Cores.PURPLE) + "  │  " + cor(Cores.CYAN) + truncar(this.getHost(), 27) + cor(Cores.RESET) + cor(Cores.PURPLE) + "│" + cor(Cores.RESET));

        System.out.println(cor(Cores.PURPLE) + "  │  " + cor(Cores.GRAY) + "Duração: " + this.getDuracao() + spaces(18 - this.getDuracao().length()) + cor(Cores.RESET) + cor(Cores.PURPLE) + "│" + cor(Cores.RESET));
    }

    private String cor(Cores cor){
        return cor.getCodigo();
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getNumeroEpisodio() {
        return this.numeroEpisodio;
    }

    public void setNumeroEpisodio(String numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    public GeneroPodcast getGenero() {
        return genero;
    }

    public void setGenero(GeneroPodcast genero) {
        this.genero = genero;
    }
}