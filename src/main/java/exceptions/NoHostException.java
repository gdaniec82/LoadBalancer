package exceptions;

public class NoHostException extends RuntimeException{

    public NoHostException() {
        super("No host available");
    }
}
