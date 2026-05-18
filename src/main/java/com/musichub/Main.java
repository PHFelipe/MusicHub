package com.musichub;

import com.musichub.exceptions.DuracaoInvalidaException;
import com.musichub.ui.Menu;

public class Main {
    public static void main(String[] args) throws DuracaoInvalidaException {
        Menu menu = new Menu();
        menu.iniciar();
    }
}