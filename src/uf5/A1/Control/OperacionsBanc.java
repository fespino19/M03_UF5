package uf5.A1.Control;

import jdk.swing.interop.SwingInterOpUtils;
import uf5.A1.Model.Client;
import uf5.A1.Model.CompteEstalvi;

import java.util.ArrayList;
import java.util.List;

import static uf5.A1.Control.BankAccountException.ACCOUNT_NOT_FOUND;
import static uf5.A1.Control.BankAccountException.ACCOUNT_OVERDRAFT;
import static uf5.A1.Control.ClientAccountException.WRONG_DNI;

public class OperacionsBanc {

    CompteEstalvi compteEstalvi;
    Client client;
    public List<CompteEstalvi> listaDeComptes = new ArrayList<>();
    int id=1;
    private String numeroCuenta;
    public static boolean verifyDNI(String dni) throws ClientAccountException {
        //TODO implementar fòrnula de verificació del DNI

        int DNI_Numeros = Integer.parseInt(dni.substring(0,8));
        String DNI_Letra = dni.substring(8,9);
        boolean correcto=false;
        String[] Letras= {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};

        int numero= DNI_Numeros %23;

        for (int i = 0; i < Letras.length; i++) {
            if (numero == i && Letras[i].equals(DNI_Letra)){
                correcto=true;
                break;
            }
        }

        if (!correcto){
            throw new ClientAccountException(WRONG_DNI);
        }

        return correcto;
    }

    public String getNumeroCuenta() {
        numeroCuenta = ("CU210" + id );
        return numeroCuenta;
    }



    public  void agregarTitular(String nom, String cognom, String DNI) throws ClientAccountException {
            client = new Client(nom,cognom,DNI);
    }

    public void agregarCuenta(String compte){
        compteEstalvi=new CompteEstalvi(compte);
        compteEstalvi.addUser(client);
        listaDeComptes.add(compteEstalvi);
    }

    public void cambioDeNumeroCuenta(){
        id++;
    }

    public void transfer(String compteOrigen,String compteDestino, double dinero) throws BankAccountException{
        boolean conseguido = false;
        boolean encontrada = false;

        for (CompteEstalvi compteEstalvi: listaDeComptes){
            if (compteEstalvi.getNumCompte().equals(compteOrigen)){
                try{
                    compteEstalvi.treure(dinero);
                    conseguido=true;
                } catch (BankAccountException e) {
                    throw new BankAccountException(ACCOUNT_OVERDRAFT);
                }
            }
        }

        for (CompteEstalvi compteEstalvi1: listaDeComptes){
            if (compteEstalvi1.getNumCompte().equals(compteDestino)){
                encontrada=true;
                if (conseguido){
                    compteEstalvi1.ingressar(dinero);
                    System.out.println("Transferencia realizada correctamente!");
                }else break;
            }
        }
        if (!encontrada){
            throw new BankAccountException(ACCOUNT_NOT_FOUND);
        }

    }

    public boolean loginCuenta(String numeroCuenta, String dniCuenta) throws BankAccountException {
        boolean login = false;
        for (CompteEstalvi compteEstalvi1 : listaDeComptes){
            if (compteEstalvi1.getNumCompte() == numeroCuenta){
                for (Client client1: compteEstalvi1.getLlista_usuaris()){
                    if (client1.getDNI() == dniCuenta){
                        login = true;
                        break;
                    }
                }
            }
        }

        return login;
    }
}
