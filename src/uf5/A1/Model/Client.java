package uf5.A1.Model;

import uf5.A1.Control.ClientAccountException;
import uf5.A1.Control.OperacionsBanc;

import static uf5.A1.Control.ClientAccountException.WRONG_DNI;

public class Client {
    private String Nom;
    private String Cognoms;
    private String DNI;

    public Client(String nom, String cognoms, String DNI) throws ClientAccountException {
        Nom = nom;
        Cognoms = cognoms;
        if(OperacionsBanc.verifyDNI(DNI)) this.DNI = DNI;
        else throw new ClientAccountException(WRONG_DNI);
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getCognoms() {
        return Cognoms;
    }

    public void setCognoms(String cognoms) {
        Cognoms = cognoms;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    @Override
    public String toString() {
        return "Client{" + "Nom='" + Nom + '\'' + ", Cognoms='" + Cognoms + '\'' + ", DNI='" + DNI + '\'' + '}';
    }
}
