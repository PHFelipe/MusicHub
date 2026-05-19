package com.musichub.ui;

import com.musichub.Catalogo;
import com.musichub.enums.GeneroAudiobook;
import com.musichub.enums.GeneroMusica;
import com.musichub.enums.GeneroPodcast;
import com.musichub.exceptions.DuracaoInvalidaException;
import com.musichub.midia.Audiobook;
import com.musichub.midia.Midia;
import com.musichub.midia.Musica;
import com.musichub.midia.Podcast;

import java.util.List;
import java.util.Scanner;


public class Menu {

    private static final String RESET   = "\033[0m";
    private static final String BOLD    = "\033[1m";
    private static final String PURPLE  = "\033[35m";
    private static final String MAGENTA = "\033[95m";
    private static final String CYAN    = "\033[96m";
    private static final String GRAY    = "\033[90m";
    private static final String WHITE   = "\033[97m";
    private static final String GREEN   = "\033[92m";
    private static final String YELLOW  = "\033[93m";
    private static final String RED     = "\033[91m";
    private final Scanner scanner;
    private final Catalogo catalogo;
    public Menu() {
        this.scanner = new Scanner(System.in);
        this.catalogo = new Catalogo();
    }



    public void iniciar() throws DuracaoInvalidaException {
        limparTela();
        exibirBanner();
        boolean rodando = true;
        while (rodando) {
            exibirMenuPrincipal();
            String opcao = lerEntrada();
            switch (opcao) {
                case "1" -> menuAdicionarMidia();
                case "2" -> menuCatalogo();
                case "0" -> { rodando = false; exibirSaida(); }
                default  -> erro("Opção inválida. Tente novamente.");
            }
        }

    }

    private void menuAdicionarMidia() throws DuracaoInvalidaException {
        limparTela();
        cabecalho("ADICIONAR MÍDIA");
        println(CYAN + "  [1] " + WHITE + "Música");
        println(CYAN + "  [2] " + WHITE + "Podcast");
        println(CYAN + "  [3] " + WHITE + "Audiobook");
        println(CYAN + "  [0] " + WHITE + "Voltar" + RESET);
        prompt("Tipo");

        switch (lerEntrada()) {
            case "1" -> adicionarMusica();
            case "2" -> adicionarPodcast();
            case "3" -> adicionarAudiobook();
            case "0" -> {}
            default  -> erro("Opção inválida.");
        }
    }

    private void adicionarMusica() throws DuracaoInvalidaException {
        prompt("Título");        String titulo = lerEntrada();
        prompt("Artista");       String artista = lerEntrada();
        prompt("Duração (ex: 00:03:45)"); String duracao = lerEntrada();
        prompt("Álbum");         String album = lerEntrada();
        println(GRAY + "  Gêneros: " + listEnum(GeneroMusica.values()) + RESET);
        prompt("Gênero");
        GeneroMusica genero = GeneroMusica.valueOf(lerEntrada().toUpperCase());

        try {
            catalogo.adicionarMidia(new Musica(titulo, artista, duracao, genero, album));
            sucesso("Música '" + titulo + "' adicionada!");
        }catch (DuracaoInvalidaException e){
            erro(e.getMessage());
        }


    }

    private void menuCatalogo() {
        limparTela();
        cabecalho("CATALOGO");
        List<Midia> todas = catalogo.getCatalogoMidias();
        if (todas.isEmpty()) {
            println(GRAY + "  Biblioteca vazia. Adicione mídias primeiro." + RESET);

            println();
            println(CYAN + "  [0] " + WHITE + "Voltar" + RESET);
        } else {
            println(GRAY + String.format("  %-3s %-28s %-20s %-16s %-16s %s", "#", "TÍTULO", "ARTISTA", "DURAÇÃO","GENERO", "TIPO") + RESET);
            println(GRAY + "  " + "─".repeat(98) + RESET);
            for (int i = 0; i < todas.size(); i++) {
                Midia m = todas.get(i);
                String tipo = tipoMidia(m);
                String genero = tipoGenero(m);
                println(CYAN + String.format("  %-3d", i + 1)
                        + WHITE + String.format(" %-28s", truncar(m.getTitulo(), 27))
                        + GRAY  + String.format(" %-20s", truncar(m.getArtista(), 19))
                        + PURPLE + String.format(" %-17s", m.getDuracao())
                        + GRAY + String.format( "%-17s",truncar(genero, 16))
                        + YELLOW + tipo + RESET
                );
            }

            println();
            println(CYAN + "  [1] " + WHITE + "Reproduzir uma mídia   " + CYAN + "[0] " + WHITE + "Voltar" + RESET);
        }
        prompt("Ação");
        String op = lerEntrada();
        //if ("1".equals(op)) reproduzirDaBiblioteca();
    }

    private void adicionarPodcast() throws DuracaoInvalidaException {
        prompt("Título");             String titulo = lerEntrada();
        prompt("Criador/Autor");      String artista = lerEntrada();
        prompt("Duração (ex: 01:12:30)"); String duracao = lerEntrada();
        prompt("Host");               String host = lerEntrada();
        prompt("Nº do episódio");     String ep = lerEntrada();
        println(GRAY + "  Gêneros: " + listEnum(GeneroPodcast.values()) + RESET);
        prompt("Gênero");
        GeneroPodcast genero = GeneroPodcast.valueOf(lerEntrada().toUpperCase());

        try{
            catalogo.adicionarMidia(new Podcast(titulo, artista, duracao, genero, host, ep));
            sucesso("Podcast '" + titulo + "' adicionado!");
        }catch (DuracaoInvalidaException e){
            erro(e.getMessage());
        }


    }

    private void adicionarAudiobook() throws DuracaoInvalidaException {
        prompt("Título");        String titulo = lerEntrada();
        prompt("Autor");         String artista = lerEntrada();
        prompt("Duração (ex: 08:30:00)"); String duracao = lerEntrada();
        prompt("Narrador");      String narrador = lerEntrada();
        println(GRAY + "  Gêneros: " + listEnum(GeneroAudiobook.values()) + RESET);
        prompt("Gênero");
        GeneroAudiobook genero = GeneroAudiobook.valueOf(lerEntrada().toUpperCase());

        try{
            catalogo.adicionarMidia(new Audiobook(titulo, artista, duracao, genero, narrador));
            sucesso("Audiobook '" + titulo + "' adicionado!");
        }catch (DuracaoInvalidaException e){
            erro(e.getMessage());
        }


    }

    private String tipoGenero(Midia m){
        if (m instanceof Musica musica)    return musica.getGenero().toString();
        if (m instanceof Podcast podcast)   return podcast.getGenero().toString();
        if (m instanceof Audiobook audiobook) return audiobook.getGenero().toString();
        return "UNDEFINED";
    }

    private String tipoMidia(Midia m) {
        if (m instanceof Musica)    return "MÚSICA";
        if (m instanceof Podcast)   return "PODCAST";
        if (m instanceof Audiobook) return "AUDIOBOOK";
        return "MÍDIA";
    }

    private String truncar(String s, int max) {
        return s.length() <= max ? s + spaces(max - s.length()) : s.substring(0, max - 1) + "…";
    }

    private String listEnum(Enum<?>[] values) {
        StringBuilder sb = new StringBuilder();
        for (Enum<?> v : values) sb.append(v.name()).append("  ");
        return sb.toString();
    }
    private void cabecalho(String titulo) {
        println(PURPLE + "\n  ╔══════════════════════════════════════╗");
        println(PURPLE + "  ║    " + MAGENTA + BOLD + "// " + titulo + RESET + PURPLE
                + spaces(31 - titulo.length()) + "║");
        println(PURPLE + "  ╚══════════════════════════════════════╝\n" + RESET);
    }
    private void exibirBanner() {
        println(PURPLE + BOLD);
        println("  ╔══════════════════════════════════════════╗");
        println("  ║      ◈  M U S I C   H U B  v1.0  ◈       ║");
        println("  ║       Terminal Edition — Synthwave       ║");
        println("  ╚══════════════════════════════════════════╝" + RESET);
        println(GRAY + "  [music-hub]$ _" + RESET);
        println();
    }

    private void exibirMenuPrincipal() {
        println(PURPLE + "  ┌─────────────────────────────┐" + RESET);
        println(PURPLE + "  │  " + MAGENTA + BOLD + "  // MENU PRINCIPAL" + RESET + PURPLE + "        │" + RESET);
        println(PURPLE + "  ├─────────────────────────────┤" + RESET);
        opcaoMenu("1","ADICIONAR MÍDIA");
        opcaoMenu("2", "VER CATALOGO");
        opcaoMenu("0","SAIR");
        println(PURPLE + "  └─────────────────────────────┘" + RESET);
        prompt("Escolha");
    }

    private String lerEntrada() {
        return scanner.nextLine().trim();
    }
    private void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    private void opcaoMenu(String num, String label) {
        println(PURPLE + "  │  " + CYAN + "[" + num + "] " + WHITE + label
                + RESET + PURPLE + spaces(23 - label.length()) + "│" + RESET);
    }

    private void prompt(String msg) {
        System.out.print(PURPLE + "\n  [" + CYAN + "music-hub" + PURPLE + "]"
                + GRAY + "$ " + WHITE + msg + " › " + RESET);
    }

    private String spaces(int n) {
        return " ".repeat(Math.max(0, n));
    }
    private void erro(String msg) {
        println(RED + "\n  ✘ " + msg + RESET);
    }
    private void exibirSaida() {
        println(PURPLE + "\n  ◈ Até logo, Music Hub encerrado. ◈\n" + RESET);
    }

    private void sucesso(String msg) {
        println(GREEN + "\n  ✔ " + msg + RESET);
    }
    private void println(String s) { System.out.println(s); }
    private void println()         { System.out.println(); }
}
