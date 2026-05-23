package com.musichub;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DuracaoValidatorTest {

    @Test
    @DisplayName("Deve aceitar duração no formato HH:MM:SS")
    void deveAceitarDuracaoValida() {

        String duracao = "00:03:45";


        boolean resultado = DuracaoValidator.duracaoValida(duracao);


        assertTrue(resultado);
    }

    @Test
    @DisplayName("Deve rejeitar duração zerada")
    void deveRejeitarDuracaoZerada() {

        String duracao = "00:00:00";


        boolean resultado = DuracaoValidator.duracaoValida(duracao);


        assertFalse(resultado);
    }

    @Test
    @DisplayName("Deve rejeitar duração fora do formato esperado")
    void deveRejeitarDuracaoForaDoFormato() {

        String duracao = "3:45";


        boolean resultado = DuracaoValidator.duracaoValida(duracao);


        assertFalse(resultado);
    }

    @Test
    @DisplayName("Deve rejeitar minutos ou segundos maiores que 59")
    void deveRejeitarMinutosOuSegundosInvalidos() {

        assertFalse(DuracaoValidator.duracaoValida("00:60:00"));
        assertFalse(DuracaoValidator.duracaoValida("00:10:60"));
    }

    @Test
    @DisplayName("Deve rejeitar duração nula")
    void deveRejeitarDuracaoNula() {

        assertFalse(DuracaoValidator.duracaoValida(null));
    }
}
