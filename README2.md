# Contact App

## Overview
A JavaFX desktop application for managing contacts with a SQLite database backend.

## Features
- View all contacts in a database table
- Add new contacts with a form
- Update existing contact information
- Delete contacts from the database
- Data persistence with SQLite

## Requirements
- Java 17 or higher
- Maven 3.6 or higher

## Building and Running
```bash
mvn clean compile
mvn test
mvn javafx:run
```

## Project Structure
All Java files organized by package (db, model, controller, util)
Resources in src/main/resources

## Team Members
- Alpatson Cobbina Siaw: Main Window & Integration
- Kelvin AWUDJA: Database & DAO Layer
- Kwabena ANOKYE: Model & Utilities
- Josephine Ama Gyanewah GYASI: Forms & Controllers
```

**File 8: .gitignore**
```
# IDE
.idea/
.vscode/
*.swp
*.swo
*.iml
nbproject/
nb-configuration.xml

# Maven
target/
*.class
*.jar
*.war

# Database
*.db
*.sqlite
data/

# OS
.DS_Store
Thumbs.db