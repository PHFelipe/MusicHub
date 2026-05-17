package com.musichub.ui;

import java.util.Scanner;

import static java.sql.DriverManager.println;

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
    public Menu() {
        this.scanner = new Scanner(System.in);
    }



    public void iniciar() {

        exibirBanner();
        exibirMenuPrincipal();

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
        opcaoMenu("1","PLAYER");
        opcaoMenu("2","BIBLIOTECA");
        opcaoMenu("3","PLAYLISTS");
        opcaoMenu("4","BUSCAR");
        opcaoMenu("5","ADICIONAR MÍDIA");
        opcaoMenu("0","SAIR");
        println(PURPLE + "  └─────────────────────────────┘" + RESET);
        prompt("Escolha");
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
    private void println(String s) { System.out.println(s); }
    private void println()         { System.out.println(); }
}
