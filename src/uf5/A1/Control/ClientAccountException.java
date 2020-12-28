package uf5.A1.Control;

public class ClientAccountException extends  Exception{

    public static final String WRONG_DNI = "DNI incorrecte";

    public ClientAccountException(String message) {
        super(message);
    }

}
