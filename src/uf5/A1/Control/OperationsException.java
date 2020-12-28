package uf5.A1.Control;

public class OperationsException extends Exception{
    public static final String TRANSFER_ERROR = "Error en la transferència";

    public OperationsException(String message) {
        super(message);
    }
}
