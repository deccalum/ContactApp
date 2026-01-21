package exception;

public class ExceptionHandler {

    static void handle(Exception e) {
        System.err.println("An error occurred: " + e.getMessage());
    }
}

