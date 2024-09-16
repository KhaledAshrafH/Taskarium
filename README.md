# Taskarium - Task and Note Management System

Taskarium is a powerful and user-friendly task and note management application developed using Spring Boot, Spring MVC, and other modern technologies. This project allows users to efficiently manage their tasks and notes while providing a secure environment with tailored access controls. Built with clean architecture principles in mind, Taskarium offers great maintainability and scalability for future development.

## Table of Contents
- [Features](#features)
- [Technologies Used](#technologies-used)
- [System Requirements](#system-requirements)
- [Usage](#usage)
- [Security](#security)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)

### Features

* User registration and login with secure password storage.
* Role-based access control (RBAC) for user permissions.
* Task creation, editing, and deletion with customizable descriptions, colors, and statuses.
* Note creation, editing, and deletion associated with specific tasks.
* Users can only view and manage their own notes and tasks, ensuring data privacy.
* Responsive front-end design using Thymeleaf templates.

## Technologies Used

- **Spring Boot**: Framework for building production-ready applications.
- **Spring MVC**: For creating web applications in a clean and modular way.
- **Spring Security**: For handling authentication and authorization.
- **Spring Data JPA**: For database interactions using JPA (Java Persistence API) with **H2 database**.
- **Thymeleaf**: For rendering dynamic web pages and defining server-side templates.
- **MapStruct**: For mapping between DTOs and entities.
- **Lombok**: To minimize boilerplate code.

## System Requirements

- **Java Development Kit (JDK)**: Version 17 or later
- **Maven**: For dependency management
- **H2 Database**: Bundled with the project

## Usage
1. Clone the project repository.
2. Install the required dependencies (rebuild the pom.xml file).
3. Configure the database connection (if using a database other than H2).
4. Run the application using your preferred Java IDE or command line:
    ```bash
    mvn spring-boot:run
    ```
5. Access the application in your browser at `http://localhost:8080` (default port).

## Security
Taskarium implements robust security measures:
- Each user has their own credentials, allowing them to log in securely.
- Users can only access and manage tasks and notes they own.
- Role-based access control is enabled, ensuring that an admin can perform operations while preventing unauthorized access for regular users.

## Project Structure
The project follows a clean architecture approach with clear separation of concerns:

* **Controller**: Handles user requests and interacts with services.
* **Service**: Business logic layer that interacts with repositories.
* **Repository**: Data access layer.
* **Model**: Contains entity classes representing database tables.
* **DTO**: Data Transfer Objects used for communication between layers.
* **Exception**: Custom exception classes for handling application errors.
* **Filter**: Spring Security filters for implementing sepcific constraints.
* **Security**: Configuration for user roles, permissions, and authentication mechanisms.
* **Advice**: Aspect-oriented programming aspects for cross-cutting concerns.
* **Layout**: Thymeleaf templates for building the user interface.

## Contributing
Contributions are welcome! Please fork the repository and create a pull request with your changes.

## License
This project is licensed under the [MIT License](LICENSE).
