package uf5.A1.Model;



import uf5.A1.Control.BankAccountException;

import java.util.ArrayList;
import java.util.List;

import static uf5.A1.Control.BankAccountException.ACCOUNT_OVERDRAFT;
import static uf5.A1.Control.BankAccountException.ACCOUNT_ZERO_USER;

public class CompteEstalvi {
    private String numCompte;
    private double saldo;
    private List<Client> llista_usuaris = new ArrayList<>();

    public CompteEstalvi(String numCompte) {
        this.numCompte = numCompte;
        saldo = 0;
    }

    /**
        Afegeix un usuari d'aquest compte
        @param client
        @return quantitat d'usuaris que té el compte

     **/
    public int addUser(Client client) {
        llista_usuaris.add(client);
        return llista_usuaris.size();
    }

    /**
     Elimina un usuari d'aquest compte,
     Com que no pot quedar un compte sense usuari, si és l'ùltim és llança una excepció
     @param dni
     @return quantitat d'usuaris que té el compte
     @throws BankAccountException
     **/
    public int removeUser(String dni) throws BankAccountException {
        if (llista_usuaris.size() >2){
            llista_usuaris.removeIf(u -> dni.equals(u.getDNI()));
        }else {
            throw new BankAccountException(ACCOUNT_ZERO_USER);
        }
        return llista_usuaris.size();
    }

    /**
     * Afegeix m diners al saldo
     * @param m
     */
    public void ingressar(double m) {
        saldo += m;
    }

    /**
     * Treu m diners del compte si n'hi han suficient sinó es llança l'excepció
     * @param m
     * @throws BankAccountException
     */
    public void treure(double m) throws BankAccountException {
        if (saldo >= m){
            saldo -= m;
        }else {
            throw new BankAccountException(ACCOUNT_OVERDRAFT);
        }
    }

    public String getNumCompte() {
        return numCompte;
    }

    public double getSaldo() {
        return saldo;
    }

    public List<Client> getLlista_usuaris() {
        return llista_usuaris;
    }

    public void setNumCompte(String numCompte) {
        this.numCompte = numCompte;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
