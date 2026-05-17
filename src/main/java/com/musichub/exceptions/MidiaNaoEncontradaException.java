package com.musichub.exceptions;

public class MidiaNaoEncontradaException extends Exception{
    @Override
    public String getMessage(){
        return "A Midia Buscada Não Pode Ser Encontrada Em Nosso Sistema";
    }
}
