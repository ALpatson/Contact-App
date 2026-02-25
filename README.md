# Contact App

## Overview
A JavaFX desktop application for managing contacts with a SQLite database backend. Built as a team project with role-based development.

## Features
-  View all contacts in a table
-  Add new contacts with a form
-  Edit existing contact information
-  Delete contacts from the database
-  View detailed contact information
-  Data persistence with SQLite database
-  UI with JavaFX and CSS styling

## Requirements
- Java 25 (JDK)
- Maven 3.6 or higher
- SQLite JDBC Driver

## Building and Running

### Clean Build
```bash
mvn clean install
```

### Run Tests
```bash
mvn test
```

### Run the Application
```bash
# Using javafx-maven-plugin (RECOMMENDED)
set "JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-25.0.1.8-hotspot"
mvn clean javafx:run

# Or in Linux/Mac:
export JAVA_HOME=/path/to/jdk-25
mvn clean javafx:run
```

## Project Structure
```
Contact-App/
├── src/
│   ├── main/
│   │   ├── java/com/contactapp/
│   │   │   ├── Main.java                 # Application entry point
│   │   │   ├── controller/               # JavaFX Controllers
│   │   │   │   ├── MainController.java
│   │   │   │   ├── AddPersonController.java
│   │   │   │   └── UpdatePersonController.java
│   │   │   ├── model/                    # Data Models
│   │   │   │   └── Person.java
│   │   │   ├── db/                       # Database Layer
│   │   │   │   ├── DatabaseConnection.java
│   │   │   │   ├── DatabaseInitializer.java
│   │   │   │   └── PersonDAO.java
│   │   │   └── util/                     # Utilities
│   │   │       ├── DateUtil.java
│   │   │       └── PersonContext.java
│   │   └── resources/com/contactapp/view/
│   │       ├── MainWindow.fxml
│   │       ├── AddPersonForm.fxml
│   │       ├── UpdatePersonForm.fxml
│   │       ├── ViewPersonDetails.fxml
│   │       ├── styles.css
│   │       └── database.sql
│   └── test/
│       └── java/com/contactapp/
│           ├── db/
│           │   ├── DatabaseConnectionTest.java
│           │   └── PersonDAOTest.java
│           └── model/
│               └── PersonTest.java
├── pom.xml                               # Maven configuration
├── README.md                             # This file
└── .gitignore                            # Git ignore rules
```

## Database Schema
The application uses SQLite with the following table:
```sql
CREATE TABLE person (
    idperson INTEGER PRIMARY KEY AUTOINCREMENT,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    nickname VARCHAR(50),
    phone_number VARCHAR(20),
    email_address VARCHAR(100),
    address VARCHAR(255),
    birth_date DATE
);
```

## Team Members & Responsibilities

| Developer | Role | Components |
|-----------|------|------------|
| **Alpatson Cobbina SIAW** | Team Lead & UI Developer | Main Window, Form Integration, Controllers |
| **Kelvin AWUDJA** | Database Developer | Database Layer, PersonDAO, Connection Management |
| **Kwabena ANOKYE** | Model & Utility Developer | Person Model, DateUtil, Comprehensive Testing |
| **Josephine Ama Gyanewah GYASI** | Forms Developer | Add/Edit Forms, PersonContext, Form Controllers |

## Development Workflow

### Branch Strategy
- `main` - Production ready code
- `Development` - Integration branch for all features
- `dev-<name>` - Individual developer branches

### Getting Started
1. Clone the repository
2. Create your feature branch: `git checkout -b dev-yourname`
3. Make your changes and commit: `git commit -m "Your message"`
4. Push to GitHub: `git push origin dev-yourname`
5. Create a Pull Request to Development branch

## Testing
Run all tests with:
```bash
mvn test
```

Tests include:
- Unit tests for Person model
- Database connection tests
- PersonDAO CRUD operation tests
- JUnit 5 framework

## Known Issues & Future Improvements
- [ ] Add input validation for form fields
- [ ] Implement search/filter functionality
- [ ] Implement data backup functionality

## License
Open source project for educational purposes.

## Contact
For questions or issues, please contact the development team.