package uf5.A1;

import uf5.A1.Control.ClientAccountException;
import uf5.A1.Model.Menu;

import java.util.Scanner;

import static uf5.A1.Control.ClientAccountException.WRONG_DNI;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.show();
    }
}
