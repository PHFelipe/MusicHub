package com.musichub.ui;

import com.musichub.Catalogo;
import com.musichub.Playlist;
import com.musichub.enums.GeneroAudiobook;
import com.musichub.enums.GeneroMusica;
import com.musichub.enums.GeneroPodcast;
import com.musichub.exceptions.DuracaoInvalidaException;
import com.musichub.midia.Audiobook;
import com.musichub.midia.Midia;
import com.musichub.midia.Musica;
import com.musichub.midia.Podcast;
import com.musichub.player.Player;

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

    private final Player player;
    public Menu() {
        this.scanner = new Scanner(System.in);
        this.catalogo = new Catalogo();
        this.player = new Player();
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
                case "2" -> menuPlayer();
                case "3" -> menuCatalogo();
                case "4" -> menuPlaylists();
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

    private void menuPlaylists() {
        limparTela();
        boolean voltar = false;
        while (!voltar) {
            cabecalho("PLAYLISTS");
            List<Playlist> playlists = catalogo.getPlaylists();
            if (playlists.isEmpty()) {
                println(GRAY + "  Nenhuma playlist. Crie uma!" + RESET);
            } else {
                for (int i = 0; i < playlists.size(); i++) {
                    Playlist p = playlists.get(i);
                    println(CYAN + "  [" + (i + 1) + "] " + WHITE + p.getNomePlaylist()
                            + GRAY + "  — " + p.getTotalFaixas() + " faixas" + RESET);
                }
            }
            println();
            println(CYAN + "  [N] " + WHITE + "Nova playlist   "
                    + CYAN + "[R] " + WHITE + "Remover   "
                    + CYAN + "[V] " + WHITE + "Ver playlist   "
                    + CYAN + "[0] " + WHITE + "Voltar" + RESET);
            prompt("Ação");
            switch (lerEntrada().toUpperCase()) {
                case "N" -> criarPlaylist();
                case "R" -> removerPlaylist();
                case "V" -> verPlaylist();
                case "0" -> voltar = true;
                default  -> erro("Opção inválida.");
            }
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
        if ("1".equals(op)) reproduzirDaBiblioteca();
    }

    private void menuPlayer() {
        limparTela();
        boolean voltar = false;
        boolean toggle = false;
        while (!voltar) {

            if(toggle) {
                toggle = false;
            }else{
                cabecalho("PLAYER");
                exibirNowPlaying();
            }
            println(GRAY + "  ───────────────────────────────" + RESET);
            opcaoMenu("1",  "PLAY / PAUSE");
            opcaoMenu("2",  "PRÓXIMA FAIXA");
            opcaoMenu("3",  "FAIXA ANTERIOR");
            opcaoMenu("4",  "AJUSTAR VOLUME");
            opcaoMenu("5",  "VER FILA");
            opcaoMenu("0",  "VOLTAR");
            println(PURPLE + "  └─────────────────────────────┘" + RESET);
            prompt("Ação");
            switch (lerEntrada()) {
                case "1" -> {cabecalho("PLAYER"); togglePlay(); toggle = true;}
                case "2" -> { player.proximo();   sucesso("▶ Próxima faixa."); }
                case "3" -> { player.anterior();  sucesso("◀ Faixa anterior."); }
                case "4" -> ajustarVolume();
                case "5" -> exibirFila();
                case "0" -> voltar = true;
                default  -> erro("Opção inválida.");
            }
        }
    }

    private void exibirNowPlaying() {
        Midia m = player.getMidiaAtual();
        println(PURPLE + "  ┌─────────────────────────────┐" + RESET);
        if (m == null) {
            println(PURPLE + "  │  " + GRAY + "Nenhuma mídia carregada.   "+ PURPLE + "│" + RESET);
        } else {
            String status = player.isTocando() ? GREEN + "     ► REPRODUZINDO        " : YELLOW + "        ⏸ PAUSADO          ";
            println(PURPLE + "  │  " + status + RESET + PURPLE + "│" + RESET);
            m.reproduzir();
            println(PURPLE + "  │  " + WHITE + String.format("%-27s", "Volume: " + player.getVolume() + "%") + RESET + PURPLE + "│" + RESET);
        }
    }
    private void togglePlay() {
        if (player.getMidiaAtual() == null) { erro("Nenhuma mídia na fila."); return; }
        if (player.isTocando()) { player.pause();}
        else                    { player.play();}
    }

    private void ajustarVolume() {
        prompt("Volume (0-100)");
        try {
            int v = Integer.parseInt(lerEntrada());
            player.setVolume(v);
            sucesso("Volume definido para " + player.getVolume() + "%");
        } catch (NumberFormatException e) {
            erro("Valor inválido.");
        }
    }

    private void exibirFila() {
        cabecalho("FILA DE REPRODUÇÃO");
        List<Midia> fila = player.getFila();
        if (fila.isEmpty()) { println(GRAY + "  Fila vazia." + RESET); }
        else {
            for (int i = 0; i < fila.size(); i++) {
                Midia m = fila.get(i);
                String cur = (i == player.getIndiceAtual()) ? MAGENTA + "► " : GRAY + "  ";
                println(cur + CYAN + (i + 1) + ". " + WHITE + m.getTitulo()
                        + GRAY + "  —  " + m.getArtista() + RESET);
            }
        }
        aguardar();
    }
    private void criarPlaylist() {
        prompt("Nome da nova playlist");
        String nome = lerEntrada();
        if (nome.isBlank()) { erro("Nome não pode ser vazio."); return; }
        catalogo.adicionarPlaylist(new Playlist(nome));
        sucesso("Playlist '" + nome + "' criada!");
    }

    private void removerPlaylist() {
        List<Playlist> playlists = catalogo.getPlaylists();
        if (playlists.isEmpty()) {
            erro("Nenhuma playlist para remover.");
            return;
        }

        prompt("Número da playlist para remover");
        try {
            int indice = Integer.parseInt(lerEntrada()) - 1;
            if (indice < 0 || indice >= playlists.size()) {
                erro("Número de playlist inválido.");
                return;
            }

            String nome = playlists.get(indice).getNomePlaylist();
            if (catalogo.removerPlaylist(indice)) {
                sucesso("Playlist '" + nome + "' removida!");
            } else {
                erro("Não foi possível remover a playlist.");
            }
        } catch (NumberFormatException e) {
            erro("Entrada inválida. Digite apenas o número da playlist.");
        }
    }


    private void verPlaylist() {
        Playlist playlist = selecionarPlaylist();
        if (playlist == null) return;

        boolean voltar = false;
        while (!voltar) {
            limparTela();
            cabecalho("PLAYLIST: " + playlist.getNomePlaylist());
            listarMidiasPlaylist(playlist);

            println();
            println(CYAN + "  [A] " + WHITE + "Adicionar mídia   "
                    + CYAN + "[R] " + WHITE + "Remover mídia   "
                    + CYAN + "[0] " + WHITE + "Voltar" + RESET);
            prompt("Ação");

            switch (lerEntrada().toUpperCase()) {
                case "A" -> adicionarMidiaNaPlaylist(playlist);
                case "R" -> removerMidiaDaPlaylist(playlist);
                case "0" -> voltar = true;
                default -> erro("Opção inválida.");
            }
        }
    }

    private Playlist selecionarPlaylist() {
        List<Playlist> playlists = catalogo.getPlaylists();
        if (playlists.isEmpty()) {
            erro("Nenhuma playlist cadastrada.");
            return null;
        }

        prompt("Número da playlist");
        try {
            int indice = Integer.parseInt(lerEntrada()) - 1;
            if (indice < 0 || indice >= playlists.size()) {
                erro("Número de playlist inválido.");
                return null;
            }
            return playlists.get(indice);
        } catch (NumberFormatException e) {
            erro("Entrada inválida. Digite apenas o número da playlist.");
            return null;
        }
    }

    private void listarMidiasPlaylist(Playlist playlist) {
        List<Midia> midias = playlist.getMidias();
        if (midias.isEmpty()) {
            println(GRAY + "  Playlist vazia." + RESET);
        } else {
            println(GRAY + String.format("  %-3s %-28s %-20s %-16s %s", "#", "TÍTULO", "ARTISTA", "DURAÇÃO", "TIPO") + RESET);
            println(GRAY + "  " + "─".repeat(82) + RESET);

            for (int i = 0; i < midias.size(); i++) {
                Midia m = midias.get(i);
                println(CYAN + String.format("  %-3d", i + 1)
                        + WHITE + String.format(" %-28s", truncar(m.getTitulo(), 27))
                        + GRAY + String.format(" %-20s", truncar(m.getArtista(), 19))
                        + PURPLE + String.format(" %-17s", m.getDuracao())
                        + YELLOW + tipoMidia(m) + RESET);
            }
        }

        println(GRAY + "\n  Total de faixas: " + playlist.getTotalFaixas()
                + " | Duração total: " + playlist.getDuracaoPlaylist() + RESET);
    }

    private void adicionarMidiaNaPlaylist(Playlist playlist) {
        List<Midia> midiasCatalogo = catalogo.getCatalogoMidias();
        if (midiasCatalogo.isEmpty()) {
            erro("Catálogo vazio. Adicione mídias antes de montar playlists.");
            return;
        }

        cabecalho("MÍDIAS DO CATÁLOGO");
        for (int i = 0; i < midiasCatalogo.size(); i++) {
            Midia m = midiasCatalogo.get(i);
            println(CYAN + "  [" + (i + 1) + "] " + WHITE + m.getTitulo()
                    + GRAY + " — " + m.getArtista()
                    + " — " + m.getDuracao() + RESET);
        }

        prompt("Número da mídia para adicionar");
        try {
            int indice = Integer.parseInt(lerEntrada()) - 1;
            if (indice < 0 || indice >= midiasCatalogo.size()) {
                erro("Número de mídia inválido.");
                return;
            }

            Midia midia = midiasCatalogo.get(indice);
            playlist.adicionarMidia(midia);
            sucesso("Mídia '" + midia.getTitulo() + "' adicionada à playlist!");
        } catch (NumberFormatException e) {
            erro("Entrada inválida. Digite apenas o número da mídia.");
        } catch (DuracaoInvalidaException e) {
            erro(e.getMessage());
        }
    }

    private void removerMidiaDaPlaylist(Playlist playlist) {
        List<Midia> midias = playlist.getMidias();
        if (midias.isEmpty()) {
            erro("Playlist vazia. Não há mídia para remover.");
            return;
        }

        prompt("Número da mídia para remover");
        try {
            int indice = Integer.parseInt(lerEntrada()) - 1;
            if (indice < 0 || indice >= midias.size()) {
                erro("Número de mídia inválido.");
                return;
            }

            String titulo = midias.get(indice).getTitulo();
            if (playlist.removerMidia(titulo)) {
                sucesso("Mídia '" + titulo + "' removida da playlist!");
            } else {
                erro("Não foi possível remover a mídia.");
            }
        } catch (NumberFormatException e) {
            erro("Entrada inválida. Digite apenas o número da mídia.");
        }
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

    public static String truncar(String s, int max) {
        return s.length() <= max ? s + spaces(max - s.length()) : s.substring(0, max - 1) + "…";
    }

    private String listEnum(Enum<?>[] values) {
        StringBuilder sb = new StringBuilder();
        for (Enum<?> v : values) sb.append(v.name()).append("  ");
        return sb.toString();
    }

    private void reproduzirDaBiblioteca() {
        prompt("Número da mídia");
        try {
            int idx = Integer.parseInt(lerEntrada()) - 1;
            List<Midia> todas = catalogo.getCatalogoMidias();
            if (idx < 0 || idx >= todas.size()) { erro("Número inválido."); return; }
            Midia m = todas.get(idx);
            player.adicionarNaFila(m);
            player.play();
            sucesso("Adicionado à fila e reproduzindo: " + m.getTitulo());
        } catch (NumberFormatException e) {
            erro("Entrada inválida.");
        }
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
        opcaoMenu("2", "MIDIA PLAYER");
        opcaoMenu("3", "VER CATALOGO");
        opcaoMenu("4", "VER PLAYLISTS");
        opcaoMenu("0","SAIR");
        println(PURPLE + "  └─────────────────────────────┘" + RESET);
        prompt("Escolha");
    }

    private void aguardar() {
        prompt("Pressione ENTER para continuar");
        scanner.nextLine();
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

    public static String spaces(int n) {
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
