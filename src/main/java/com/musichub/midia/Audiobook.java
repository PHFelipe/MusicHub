package com.musichub.midia;

import com.musichub.enums.Cores;
import com.musichub.enums.GeneroAudiobook;

import static com.musichub.ui.Menu.spaces;
import static com.musichub.ui.Menu.truncar;

public class Audiobook extends Midia{
    private String narrador;
    private GeneroAudiobook genero;
    public Audiobook(String titulo, String artista, String duracao, GeneroAudiobook genero, String narrador) {
        super(titulo, artista, duracao);
        this.narrador =  narrador;
        this.genero = genero;
    }

    @Override
    public void reproduzir() {

        System.out.println(String.format("%s  │  %8s%s%-19s%s│%s", cor(Cores.PURPLE), "", cor(Cores.YELLOW), "AUDIOBOOK", cor(Cores.PURPLE), cor(Cores.RESET)));

        System.out.println(cor(Cores.PURPLE) + "  ├─────────────────────────────┤" + cor(Cores.RESET));

        System.out.println(cor(Cores.PURPLE) + "  │  " + cor(Cores.MAGENTA) + cor(Cores.BOLD) + truncar(this.getTitulo(), 27) + cor(Cores.RESET) + cor(Cores.PURPLE) + "│" + cor(Cores.RESET));

        System.out.println(cor(Cores.PURPLE) + "  │  " + cor(Cores.CYAN) + truncar(this.getArtista(), 27) + cor(Cores.RESET) + cor(Cores.PURPLE) + "│" + cor(Cores.RESET));

        System.out.println(cor(Cores.PURPLE) + "  │  " + cor(Cores.YELLOW) +"Narrado por: "+ truncar(this.getNarrador(), 14) + cor(Cores.RESET) + cor(Cores.PURPLE) + "│" + cor(Cores.RESET));

        System.out.println(cor(Cores.PURPLE) + "  │  " + cor(Cores.GRAY) + "Duração: " + this.getDuracao() + spaces(18 - this.getDuracao().length()) + cor(Cores.RESET) + cor(Cores.PURPLE) + "│" + cor(Cores.RESET));
    }

    private String cor(Cores cor){
        return cor.getCodigo();
    }

    public String getNarrador() {
        return narrador;
    }

    public void setNarrador(String narrador) {
        this.narrador = narrador;
    }

    public GeneroAudiobook getGenero() {
        return genero;
    }

    public void setGenero(GeneroAudiobook genero) {
        this.genero = genero;
    }
}
