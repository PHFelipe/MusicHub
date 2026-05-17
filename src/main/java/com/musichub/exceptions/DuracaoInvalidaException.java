package com.musichub.exceptions;

public class DuracaoInvalidaException extends Exception{

    @Override
    public String getMessage(){
        return "A Duração Da Midia Não Pode Ser Negativa";
    }
}
