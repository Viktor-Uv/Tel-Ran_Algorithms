package lesson28.exceptions;

// Exception - нужно перехватывать и обрабатывать
// RuntimeException - не обязывает перехватывать и обрабатывать
public class DivisionByZeroException extends Exception {

    // Constructor:
    public DivisionByZeroException(String message) {
        super(message);
    }
}
