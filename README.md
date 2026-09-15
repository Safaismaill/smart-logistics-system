# Smart Logistics & Delivery Management API

A backend REST API for managing delivery records, developed using Java, Spring Boot and PostgreSQL.

The project demonstrates RESTful API development, CRUD operations, database persistence, search and filtering, input validation, exception handling and automated unit testing.

## Technologies

- Java 21
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- JUnit 5
- Mockito

## Features

- Create new delivery records
- Retrieve all deliveries
- Retrieve an individual delivery by ID
- Update existing deliveries
- Delete deliveries
- Search deliveries by customer name
- Filter deliveries by postcode
- Filter deliveries by status
- Combine multiple search filters
- Validate required delivery information
- Return meaningful `400 Bad Request` responses for invalid input
- Return `404 Not Found` responses when a delivery does not exist
- Persistent PostgreSQL database storage
- Automated controller unit tests

## Architecture

The application follows a simple layered structure:

```text
Client
  ↓
REST Controller
  ↓
Spring Data Repository
  ↓
JPA / Hibernate
  ↓
PostgreSQL
```

The controller handles HTTP requests, the repository manages database operations, and JPA/Hibernate maps Java `Delivery` objects to PostgreSQL records.

## Delivery Model

Each delivery contains:

- `id`
- `customerName`
- `address`
- `postcode`
- `status`

Example response:

```json
{
  "id": 1,
  "customerName": "John Smith",
  "address": "15 King Street",
  "postcode": "M2 4AA",
  "status": "PENDING"
}
```

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/deliveries` | Retrieve all deliveries |
| GET | `/api/deliveries/{id}` | Retrieve a delivery by ID |
| POST | `/api/deliveries` | Create a new delivery |
| PUT | `/api/deliveries/{id}` | Update an existing delivery |
| DELETE | `/api/deliveries/{id}` | Delete a delivery |
| GET | `/api/health` | Check that the API is running |

## Search and Filtering

Deliveries can be filtered using query parameters.

### Filter by status

```text
GET /api/deliveries?status=PENDING
```

### Search by postcode

```text
GET /api/deliveries?postcode=M2
```

### Search by customer name

```text
GET /api/deliveries?customerName=John
```

### Combined filtering

```text
GET /api/deliveries?status=PENDING&postcode=M2&customerName=John
```

Searches are case-insensitive.

## Validation

Required fields are validated before a delivery is saved.

For example, sending a blank customer name returns:

```json
{
  "customerName": "Customer name is required"
}
```

with an HTTP status of:

```text
400 Bad Request
```

## Error Handling

If a delivery does not exist, the API returns a `404 Not Found` response.

Example:

```text
GET /api/deliveries/999
```

Response:

```json
{
  "error": "Delivery with ID 999 was not found"
}
```

## Database

The application uses PostgreSQL for persistent storage.

Spring Data JPA and Hibernate are used to map Java `Delivery` objects to records in the PostgreSQL `delivery` table.

Database credentials are supplied through environment variables rather than being stored directly in the source code.

## Configuration

The application requires the following environment variable:

```text
DB_PASSWORD
```

Optional environment variables:

```text
DB_URL
DB_USERNAME
```

Default configuration:

- Database: `smart_logistics`
- Port: `5432`
- Username: `postgres`

## Running the Application

### Requirements

- Java 21
- PostgreSQL
- Maven or the included Maven Wrapper

Create a PostgreSQL database named:

```text
smart_logistics
```

Set the `DB_PASSWORD` environment variable to your PostgreSQL password.

The application can then be started through IntelliJ by running:

```text
SmartLogisticsApiApplication
```

Alternatively, using the Maven Wrapper on Windows:

```text
mvnw.cmd spring-boot:run
```

The API will be available at:

```text
http://localhost:8080
```

The health endpoint can be used to check that the application is running:

```text
http://localhost:8080/api/health
```

## Testing

JUnit 5 and Mockito are used for automated testing.

Current controller tests cover:

- Retrieving an existing delivery
- Handling a missing delivery
- Creating a delivery
- Updating a delivery
- Deleting an existing delivery
- Handling deletion of a missing delivery

The project also contains a Spring Boot context test to confirm that the application starts successfully.

## Project Structure

```text
src/
├── main/
│   ├── java/com/safaismail/smartlogistics/
│   │   ├── controller/
│   │   ├── exception/
│   │   ├── model/
│   │   ├── repository/
│   │   └── SmartLogisticsApiApplication.java
│   │
│   └── resources/
│       └── application.properties
│
└── test/
    └── java/com/safaismail/smartlogistics/
        ├── DeliveryControllerTest.java
        └── SmartLogisticsApiApplicationTests.java
```

## What I Learned

This project provided practical experience with:

- Building REST APIs with Spring Boot
- HTTP methods and status codes
- JSON request and response handling
- Java object-oriented programming
- Dependency injection
- PostgreSQL database persistence
- Spring Data JPA
- Hibernate
- Input validation
- Custom exception handling
- Search and filtering using query parameters
- JUnit 5 testing
- Mockito mocking

## Future Improvements

Possible future improvements include:

- Delivery dates and timestamps
- Additional delivery statuses
- Paged results and sorting
- Authentication and user accounts
- Docker containerisation
- Cloud deployment
- A frontend dashboard

## Author

Safa Ismail  
BSc (Hons) Software Engineering