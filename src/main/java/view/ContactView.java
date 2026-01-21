package view;

import java.util.List;
import java.util.Scanner;

import model.Contact;

/** Handles user interaction via console (View in MVC). */
public class ContactView {

    private final Scanner scanner = new Scanner(System.in);

    public String getUserInput(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }

    public void displayMenu() {
        System.out.println("\n--- Contact Menu ---");
        System.out.println("1. View all contacts");
        System.out.println("2. Add contact");
        System.out.println("3. Find contact");
        System.out.println("4. Exit");
    }

    public void displayContacts(List<Contact> contacts) {
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
        } else {
            for (Contact contact : contacts) {
                System.out.println(contact.getName() + " - " + contact.getPhoneNumber());
            }
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayError(String message) {
        System.err.println("Error: " + message);
    }

    public void close() {
        scanner.close();
    }
}