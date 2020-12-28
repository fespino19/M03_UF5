package uf5.A1.Exemple;

import uf5.A1.Control.PersonNotBornException;
import uf5.A1.Model.Person;

import java.time.LocalDate;

public class MainExcepcions {

    public static void main(String[] args) {


        try {
            addFamily();
            System.out.println("codi després de l'error");
        } catch (PersonNotBornException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        System.out.println("codi després del try-catch");

    }


    public static void addPerson() throws PersonNotBornException {
            Person p = new Person("Pep", Person.Genere.HOME, LocalDate.of(2020,10,10));
    }

    public static void addFamily() throws PersonNotBornException {
        addPerson();
    }
}
