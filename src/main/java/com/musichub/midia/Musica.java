package com.musichub.midia;

import com.musichub.enums.Cores;
import com.musichub.enums.GeneroMusica;

import static com.musichub.ui.Menu.spaces;
import static com.musichub.ui.Menu.truncar;

public class Musica extends Midia {
    private String album;
    private GeneroMusica genero;
    public Musica(String titulo, String artista, String duracao,GeneroMusica genero, String album) {
        super(titulo, artista, duracao);
        this.album = album;
        this.genero = genero;
    }

    @Override
    public void reproduzir() {

        System.out.println(String.format("%s  │  %9s%s%-18s%s│%s", cor(Cores.PURPLE), "", cor(Cores.YELLOW), "MUSICA", cor(Cores.PURPLE), cor(Cores.RESET)));

        System.out.println(cor(Cores.PURPLE) + "  ├─────────────────────────────┤" + cor(Cores.RESET));

        System.out.println(cor(Cores.PURPLE) + "  │  " + cor(Cores.MAGENTA) + cor(Cores.BOLD) + truncar(this.getTitulo(), 27) + cor(Cores.RESET) + cor(Cores.PURPLE) + "│" + cor(Cores.RESET));

        System.out.println(cor(Cores.PURPLE) + "  │  " + cor(Cores.CYAN) + truncar(this.getArtista(), 27) + cor(Cores.RESET) + cor(Cores.PURPLE) + "│" + cor(Cores.RESET));

        System.out.println(cor(Cores.PURPLE) + "  │  " + cor(Cores.GRAY) + "Duração: " + this.getDuracao() + spaces(18 - this.getDuracao().length()) + cor(Cores.RESET) + cor(Cores.PURPLE) + "│" + cor(Cores.RESET));
    }

    private String cor(Cores cor){
        return cor.getCodigo();
    }
    public String getAlbum() {
        return this.album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public GeneroMusica getGenero() {
        return genero;
    }

    public void setGenero(GeneroMusica genero) {
        this.genero = genero;
    }
}