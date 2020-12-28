package uf5.A1.Model;

import jdk.swing.interop.SwingInterOpUtils;
import uf5.A1.Control.BankAccountException;
import uf5.A1.Control.ClientAccountException;
import uf5.A1.Control.OperacionsBanc;

import java.util.Scanner;

public class Menu {
    OperacionsBanc opB = new OperacionsBanc();
    Scanner sc = new Scanner(System.in);
    int opcion=0;
    private String  numeroCuentaL;

    public void show() {

        while (!(opcion == 3)) {
            System.out.println("Bienvenido al Banco Elpuig");
            opciones();
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    crearCuenta();
                    break;

                case 2:
                    accederCuenta();
                    break;

                case 3:
                    System.out.println("Saliendo de la APP...");
                    break;

                default:
                    System.out.println("Las opciones posibles son 1,2,3");
                    break;
            }
        }
    }

    private void accederCuenta() {
        System.out.println("Para acceder a la cuenta introduzca los siguientes datos:");
        sc.nextLine();
        System.out.println("Numero de cuenta:");
        numeroCuentaL = sc.nextLine();
        System.out.println("DNI Asociado a la cuenta:");
        String  dniCuenta= sc.nextLine();
        try{
            opB.loginCuenta(numeroCuentaL,dniCuenta);
            menuCuenta();
        } catch (BankAccountException e) {
            System.out.println(BankAccountException.ACCOUNT_LOGIN_FAIL);
        }
    }

    private void menuCuenta() {
        int opcionMC = 0;
        while (opcionMC != 6){
            System.out.println("1.Consultar Saldo");
            System.out.println("2.Transfer");
            System.out.println("3.Ingresar dinero");
            System.out.println("4.Sacar dinero");
            System.out.println("5.Opciones de la cuenta");
            System.out.println("6.Salir");
            opcionMC = sc.nextInt();
            switch (opcionMC){
                case 1:
                    for (CompteEstalvi compteEstalvi: opB.listaDeComptes){
                        if (compteEstalvi.getNumCompte().equals(numeroCuentaL)){
                            System.out.println(compteEstalvi.getSaldo());
                        }
                    }
                    break;

                case 2:
                    System.out.println("Introduzca el numero de cuenta al que quiere hacer la transferencia y la cantida.");
                    sc.nextLine();
                    System.out.println("Numero de cuenta:");
                    String numCT = sc.nextLine();
                    System.out.println("Importe:");
                    double dinero = sc.nextDouble();
                    try {
                        opB.transfer(numeroCuentaL,numCT,dinero);
                    } catch (BankAccountException ex) {
                        System.out.println(ex);
                    }
                    break;

                case 3:
                    System.out.println("Ingrese la cantidad a ingresar");
                    System.out.println("Cantidad:");
                    double dineroIngreso = sc.nextDouble();
                    for (CompteEstalvi compteEstalvi: opB.listaDeComptes){
                        if (compteEstalvi.getNumCompte().equals(numeroCuentaL)){
                            compteEstalvi.ingressar(dineroIngreso);
                            System.out.println("El saldo actual de la cuenta es: "+compteEstalvi.getSaldo());
                        }
                    }
                    break;

                case 4:
                    System.out.println("Ingrese la cantidad que quiere sacar");
                    System.out.println("Cantidad:");
                    double dineroSacar = sc.nextDouble();
                    for (CompteEstalvi compteEstalvi: opB.listaDeComptes){
                        if (compteEstalvi.getNumCompte().equals(numeroCuentaL)){
                            try {
                                compteEstalvi.treure(dineroSacar);
                            } catch (BankAccountException e) {
                                System.out.println(e);
                            }
                            System.out.println("El saldo actual de la cuenta es: "+compteEstalvi.getSaldo());
                        }
                    }
                    break;

                case 5:
                    opcioneCuenta();
                    break;

                case 6:
                    break;
            }
        }
    }

    private void opcioneCuenta() {
                int opcionMC = 0;
                while (opcionMC != 4) {
                    System.out.println("1.Añadir usuario");
                    System.out.println("2.Eliminar usuario");
                    System.out.println("3.Lista usuarios");
                    System.out.println("4.Salir");
                    opcionMC = sc.nextInt();
                    switch (opcionMC){
                        case 1:
                            System.out.println("Introduzca los datos del nuevo usuario");
                            sc.nextLine();
                            System.out.println("Nom:");
                            String nom = sc.nextLine();
                            System.out.println("Cognom:");
                            String cognom = sc.nextLine();
                            System.out.println("DNI:");
                            String dni = sc.nextLine();
                            Client client = null;
                            try {
                                client = new Client(nom,cognom,dni);
                            } catch (ClientAccountException e) {
                                System.out.println(e);
                            }
                            for (CompteEstalvi compteEstalvi: opB.listaDeComptes){
                                if (compteEstalvi.getNumCompte().equals(numeroCuentaL)){
                                    System.out.println(compteEstalvi.addUser(client));
                                }
                            }
                            break;

                        case 2:
                            System.out.println("Lista de usuarios:");
                            for (CompteEstalvi compteEstalvi: opB.listaDeComptes){
                                if (compteEstalvi.getNumCompte().equals(numeroCuentaL)) {
                                    compteEstalvi.getLlista_usuaris().forEach(System.out::println);
                                }
                            }

                            System.out.println("Introduzca el DNI del usuarios que desea eliminar");
                            sc.nextLine();
                            System.out.println("DNI:");
                            String dniDelete = sc.nextLine();
                            try {
                                for (CompteEstalvi compteEstalvi: opB.listaDeComptes){
                                    if (compteEstalvi.getNumCompte().equals(numeroCuentaL)) {
                                        System.out.println(compteEstalvi.removeUser(dniDelete));
                                        System.out.println("Usuario eliminado correctamente");
                                    }
                                }

                            } catch (BankAccountException e) {
                                System.out.println(e);
                            }
                            break;

                        case 3:
                            for (CompteEstalvi compteEstalvi: opB.listaDeComptes){
                                if (compteEstalvi.getNumCompte().equals(numeroCuentaL)) {
                                    compteEstalvi.getLlista_usuaris().forEach(System.out::println);
                                }
                            }
                            break;

                        case 4:
                            break;
                    }
                }
            }

    private void crearCuenta() {
            System.out.println("Para crear una cuenta primero debe introducir los datos del titular:");
            sc.nextLine();
            System.out.println("Nom:");
            String nom = sc.nextLine();
            System.out.println("Cognom:");
            String cognom = sc.nextLine();
            System.out.println("DNI");
            String DNI = sc.nextLine();
            System.out.println("El titular que quiere añadir es:");
            System.out.println(nom+" "+cognom+" "+DNI);
            System.out.println("1. Si");
            System.out.println("2. No");
            int opcionTitular;
            opcionTitular = sc.nextInt();
            switch (opcionTitular){
                case 1:

                    try{
                        opB.agregarTitular(nom,cognom,DNI);
                        System.out.println("Creando titular de la cuenta...");
                        System.out.println("Titular generado correctamente...");
                        System.out.println("Generando cuenta corriente...");
                        opB.agregarCuenta(opB.getNumeroCuenta());
                        System.out.println("Cuenta corriente generada con exito!");
                        System.out.println();
                        System.out.println(" Datos para el login: ");
                        System.out.println("Numero de cuenta: "+opB.getNumeroCuenta());
                        System.out.println("DNI del titular o titulares: "+DNI);
                        System.out.println();
                        opB.cambioDeNumeroCuenta();
                    }catch (Exception e){
                        System.out.println(ClientAccountException.WRONG_DNI);
                        break;
                    }
                    break;

                case 2:
                    System.out.println("El titular no ha sido creado...");
                    System.out.println("La cuanta corriente no ha sido creada...");
                    break;
            }
        }

    public void opciones() {
        System.out.println("1.Crear una cuenta");
        System.out.println("2.Acceder a la cuenta");
        System.out.println("3.Salir");
    }
}
