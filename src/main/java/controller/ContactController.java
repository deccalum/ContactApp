package controller;

import data.ContactDAO;
import data.FileContactDAOImpl;
import exception.ContactStorageException;
import exception.ExceptionHandler;
import view.ContactView;

/**
 * Controller class for managing contacts in the application.
 * Handles user interactions, delegates data operations, and manages the application flow.
 */
public class ContactController {

    private final ContactView view;
    private final ContactDAO contactDAO;

    /**
     * Constructs a ContactController with a new ContactView and FileContactDAOImpl.
     */
    public ContactController() {
        this.view = new ContactView();
        this.contactDAO = new FileContactDAOImpl();
    }

    /**
     * Runs the main application loop for the application.
     * Handles user input, displays menus, and processes contact operations.
     */
    public static void run() {
        ContactController controller = new ContactController();
        boolean running = true;

        while (running) {
            controller.view.displayMenu();
            String choice = controller.view.getUserInput("Select an option");

            try {
                switch (choice) {
                    case "1" -> controller.view.displayContacts(controller.contactDAO.findAll());
                    case "2" -> {
                        String name = controller.view.getUserInput("Enter contact name");
                        String phone = controller.view.getUserInput("Enter contact phone number");
                        controller.contactDAO.save(new model.Contact(name, phone));
                        controller.view.displayMessage("Contact added successfully.");
                    }
                    case "3" -> {
                        String searchName = controller.view.getUserInput("Enter name to search");
                        model.Contact contact = controller.contactDAO.findByName(searchName);
                        controller.view.displayMessage("Found: " + contact.getName() + " - " + contact.getPhoneNumber());
                    }
                    case "4" -> running = false;
                    default -> controller.view.displayError("Invalid option. Please try again.");
                }
            } catch (IllegalArgumentException | ContactStorageException e) {
                ExceptionHandler.handle(e); // centralized logging/reporting
                controller.view.displayError(e.getMessage());
            }
        }

        controller.view.close();
    }
}