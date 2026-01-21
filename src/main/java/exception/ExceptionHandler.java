package exception;

/**
 * Centralized helper for logging/reporting exceptions in the application.
 */
public class ExceptionHandler {

    /**
     * Logs the given exception to standard error. Extend here if you later add
     * file logging or user-friendly messages.
     *
     * @param e exception to handle
     */
    public static void handle(Exception e) {
        System.err.println("An error occurred: " + e.getMessage());
    }
}