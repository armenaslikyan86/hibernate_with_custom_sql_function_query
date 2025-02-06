# Spring Boot Hibernate Demo

A simple Spring Boot application demonstrating the use of Hibernate with H2 database. The application manages a collection of books and provides REST endpoints to interact with the data.

## Features

- Spring Boot 3.2.3
- Hibernate 6.4.4
- H2 In-memory Database
- RESTful API endpoints
- Custom SQL function (LPAD) implementation

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## Getting Started

1. Clone the repository:
```bash
git clone [your-repository-url]
cd hibernate-demo
```

2. Build the project:
```bash
mvn clean install
```

3. Run the application:
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Endpoints

- `GET /api/books` - List all books
- `GET /api/books/padded-titles` - Get books with padded titles

## H2 Console

Access the H2 database console at `http://localhost:8080/h2-console` with these settings:
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (leave empty)

## Sample Data

The application automatically creates these sample books on startup:
- The Great Gatsby by F. Scott Fitzgerald
- 1984 by George Orwell
- To Kill a Mockingbird by Harper Lee 