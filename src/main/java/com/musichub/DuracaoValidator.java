package com.musichub;

import com.musichub.exceptions.DuracaoInvalidaException;
import com.musichub.midia.Midia;

public class DuracaoValidator {

    private DuracaoValidator(){}

    public static void validarDuracao(Midia midia) throws DuracaoInvalidaException {
        if (midia == null || !duracaoValida(midia.getDuracao())) {
            throw new DuracaoInvalidaException();
        }
    }

    public static boolean duracaoValida(String duracao) {
        if (duracao == null || !duracao.matches("\\d{2}:\\d{2}:\\d{2}")) {
            return false;
        }

        String[] partes = duracao.split(":");
        int horas = Integer.parseInt(partes[0]);
        int minutos = Integer.parseInt(partes[1]);
        int segundos = Integer.parseInt(partes[2]);

        if (minutos > 59 || segundos > 59) {
            return false;
        }

        return horas > 0 || minutos > 0 || segundos > 0;
    }
}
