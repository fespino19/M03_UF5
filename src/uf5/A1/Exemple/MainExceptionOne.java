package uf5.A1.Exemple;

import uf5.A1.Control.PersonNotBornException;
import uf5.A1.Model.Person;

import java.time.LocalDate;

public class MainExceptionOne {

    public static void main(String[] args)  {
        Person p = null;
        try {
            p = new Person("Pep", Person.Genere.HOME, LocalDate.of(2022,10,10));
            System.out.println(p);
        } catch (PersonNotBornException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Finally");
        }
        System.out.println("Fi");

    }
}
