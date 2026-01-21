package exception;

/**
 * Checked exception for duplicates detected before saving a contact.
 */
public class DuplicateContactException extends Exception {
    /**
     * Constructs a new DuplicateContactException with the specified detail message.
     *
     * @param message the detail message
     */
    public DuplicateContactException(String message) {
        super(message);
    }
    
}