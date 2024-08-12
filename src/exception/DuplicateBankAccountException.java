package exception;

public class DuplicateBankAccountException  extends Exception{
    public DuplicateBankAccountException(String message) {
        super(message);
    }
}
