package com.musichub.enums;

public enum Cores {

    RESET("\033[0m"),
    BOLD("\033[1m"),
    PURPLE("\033[35m"),
    MAGENTA("\033[95m"),
    CYAN("\033[96m"),
    GRAY("\033[90m"),
    WHITE("\033[97m"),
    GREEN("\033[92m"),
    YELLOW("\033[93m"),
    RED("\033[91m");

    private final String codigo;

    Cores(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}