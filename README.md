# Contact App - Exception Handling

## Overview
This application follows the **MVC pattern** to separate concerns:
- **Model** (`Contact`): Represents the data and validates it using unchecked exceptions.
- **View** (`ContactView`): Handles all user interaction (input/output via console).
- **Controller** (`ContactController`): Coordinates between View and Model, catches exceptions, and delegates to the data layer.
- **Data Layer** (`ContactDAO` / `FileContactDAOImpl`): Manages persistence using custom checked exceptions.

MVC (Model–View–Controller) separates responsibilities: the Model stores/validates data, the View handles user input/output, and the Controller coordinates actions between them and manages the application flow.

### ContactDAO / FileContactDAOImpl Relations

`ContactDAO` is an **interface** that defines the contract for data operations:
- `findAll()`: Retrieve all contacts
- `save(Contact contact)`: Add a new contact
- `findByName(String name)`: Search for a contact by name

`FileContactDAOImpl` **implements** `ContactDAO` and provides the actual logic. It reads/writes contacts to a `.csv` file on the local filesystem. This design allows other implementations to swap in without changing the controller or view code.

### ContactView / ContactController Relations

`ContactView` is responsible for all user interactions:
- Displaying menus
- Collecting user input
- Showing results or error messages

This means `ContactView` can be reused with other UI systems. `ContactController` is the console UI interpreting all data from `ContactView` and coordinating with the data layer.

### Exception Handling Strategy

**Model Layer**: Uses unchecked exceptions (e.g., `IllegalArgumentException`) for validation errors. This keeps the model simple and focused on data integrity. 

**Controller Layer**: Catches exceptions from both the model and data layers. It handles user feedback and decides how to proceed (e.g., retrying input, logging errors).

**Data Layer**: Uses custom checked exceptions (e.g., `DataAccessException`) to signal issues with data persistence. This forces the controller to handle these exceptions explicitly, ensuring robust error management.