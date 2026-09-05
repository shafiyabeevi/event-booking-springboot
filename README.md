# Event Booking System

A backend application for managing events and ticket bookings using Spring Boot, MySQL, JWT authentication, and Docker.

## Features

- User registration and login
- JWT-based authentication
- Role-based authorization
- Event management
- Ticket booking
- MySQL database
- Docker containerization
- Persistent MySQL storage using Docker volumes
- Swagger API documentation

## Tech Stack

- Java
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA
- MySQL
- Maven
- Docker
- Docker Compose

## User Roles

### CUSTOMER

- Register and login
- View events
- Create bookings
- Access booking APIs

### EVENT_ORGANIZER

- Register and login
- View events
- Create events
- Update events
- Delete events

## API Endpoints

### Authentication

| Method | Endpoint | Description |
|---|---|---|
| POST | `/auth/register` | Register a new user |
| POST | `/auth/login` | Login and receive JWT |

### Events

| Method | Endpoint | Access |
|---|---|---|
| GET | `/events` | Public |
| GET | `/events/{id}` | Public |
| POST | `/events` | EVENT_ORGANIZER |
| PUT | `/events/{id}` | EVENT_ORGANIZER |
| DELETE | `/events/{id}` | EVENT_ORGANIZER |

### Bookings

| Method | Endpoint | Access |
|---|---|---|
| POST | `/bookings` | CUSTOMER |
| GET | `/bookings/{id}` | CUSTOMER |

## Authentication

The application uses JWT for authentication.

Passwords are encrypted using BCrypt.

After login, send the JWT token in the request header:

```text
Authorization: Bearer <JWT_TOKEN>
```

Protected APIs use the user's role to control access.

## Docker

The application uses Docker Compose to run:

- Spring Boot application
- MySQL database

MySQL data is persisted using a Docker volume.

### Docker Architecture

```text
              Docker Compose
                    |
          ┌─────────┴─────────┐
          |                   |
   Spring Boot App          MySQL
      Port 8080            Port 3306
          |                   |
          └─────────┬─────────┘
                    |
              mysql_data
                (volume)
```

## Running the Project

### 1. Clone the Repository

```bash
git clone https://github.com/shafiyabeevi/event-booking-springboot.git
cd event-booking-springboot
```

### 2. Configure Environment Variables

Create a `.env` file in the project root:

```env
MYSQL_ROOT_PASSWORD=your_password
JWT_SECRET=your_jwt_secret
```

> Do not commit the `.env` file to GitHub.

### 3. Build the Project

For Windows:

```powershell
.\mvnw.cmd clean package -DskipTests
```

### 4. Build the Docker Image

```bash
docker build -t event-booking .
```

### 5. Start the Application

```bash
docker compose up
```

The application will be available at:

```text
http://localhost:8080
```

## Swagger API Documentation

Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

## Database

MySQL runs inside Docker.

The MySQL database is exposed to the host machine on:

```text
localhost:3307
```

The Spring Boot application connects to MySQL inside Docker using:

```text
mysql:3306
```

## Project Structure

```text
event-booking-springboot/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── com/eventbooking/tickets/
│       │       ├── controller/
│       │       ├── dto/
│       │       ├── entity/
│       │       ├── repository/
│       │       ├── security/
│       │       └── service/
│       │
│       └── resources/
│           └── application.properties
│
├── Dockerfile
├── docker-compose.yml
├── pom.xml
├── .gitignore
└── README.md
```

## Security

- Passwords are encrypted using BCrypt.
- JWT tokens are used for authentication.
- Role-based authorization protects restricted APIs.
- Sensitive configuration is stored using environment variables.

## Author
**Shafiya Beevi**
GitHub: https://github.com/shafiyabeevi
