package exception;

/**
 * Checked exception for persistence or IO failures when storing contacts.
 */
public class ContactStorageException extends Exception {

    /**
     * Constructs a new ContactStorageException with the specified detail message.
     *
     * @param message the detail message
     */
    public ContactStorageException(String message) {
        super(message);
    }

    /**
     * Constructs a new ContactStorageException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public ContactStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}

