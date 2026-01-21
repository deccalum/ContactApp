package model;

public class Contact {

    private String name;
    private String phoneNumber;

    public Contact(String name, String phoneNumber) {
        setName(name);
        setPhoneNumber(phoneNumber);
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty");
        }

        /** Regex Explanation
         *
         * ^    Start of string
         * \d  Any digit (0-9)
         * {10} Exactly 10 times
         * $    End of string
         *
         */
        if (!phoneNumber.matches("^\\d{10}$")) {
            throw new IllegalArgumentException("Phone number must contain exactly 10 digits");
        }

        this.phoneNumber = phoneNumber;
    }
}
