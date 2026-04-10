# Patient Management System

A backend REST API built with **Spring Boot**, **MySQL**, and **JWT** for managing patients, doctors, and appointments in a healthcare setting.

**GitHub:** [github.com/abdullahRiyadh/patient-management](https://github.com/abdullahRiyadh/patient-management)

---

## Tech Stack

- Java 17
- Spring Boot 3.5
- Spring Security + JWT
- Spring Data JPA / Hibernate
- MySQL 8
- Lombok
- Swagger / OpenAPI 3
- Maven

---

## Architecture
Controller → Service → Repository → MySQL
↑
Spring Security Filter Chain (JWT)
- Global exception handling
- Input validation with Bean Validation
- Role-based access control (ADMIN, DOCTOR, RECEPTIONIST)
- Stateless JWT authentication

---

## Getting Started

### Prerequisites
- Java 17+
- MySQL 8+
- Maven 3.8+

### Setup

```bash
git clone https://github.com/abdullahRiyadh/patient-management.git
cd patient-management
```

Edit `src/main/resources/application.properties`:
```properties
rename application-example.properties to application.properties
spring.datasource.username=root
spring.datasource.password=your_password
app.jwt.secret=your_jwt_secret
```

Run:
```bash
mvn spring-boot:run
```

---

## API Documentation

Swagger UI: `http://localhost:8080/swagger-ui.html`

---

## Endpoints

### Auth
| Method | Endpoint | Auth |
|--------|----------|------|
| POST | `/api/auth/register` | No |
| POST | `/api/auth/login` | No |

### Patients
| Method | Endpoint |
|--------|----------|
| POST | `/api/patients` |
| GET | `/api/patients` |
| GET | `/api/patients/{id}` |
| PUT | `/api/patients/{id}` |
| DELETE | `/api/patients/{id}` |
| GET | `/api/patients/search?keyword=` |

### Doctors
| Method | Endpoint |
|--------|----------|
| POST | `/api/doctors` |
| GET | `/api/doctors` |
| GET | `/api/doctors/{id}` |
| PUT | `/api/doctors/{id}` |
| DELETE | `/api/doctors/{id}` |
| GET | `/api/doctors/available` |
| GET | `/api/doctors/specialization/{spec}` |

### Appointments
| Method | Endpoint |
|--------|----------|
| POST | `/api/appointments` |
| GET | `/api/appointments` |
| GET | `/api/appointments/{id}` |
| GET | `/api/appointments/patient/{id}` |
| GET | `/api/appointments/doctor/{id}` |
| GET | `/api/appointments/date/{date}` |
| PATCH | `/api/appointments/{id}/status` |
| DELETE | `/api/appointments/{id}/cancel` |

---

## Usage Example

### 1. Register a user
```bash
POST /api/auth/register
{
  "username": "admin",
  "email": "admin@hospital.com",
  "password": "admin123",
  "role": "ADMIN"
}
```

### 2. Login and copy the token
```bash
POST /api/auth/login
{
  "username": "admin",
  "password": "admin123"
}
```

### 3. Use the token in all subsequent requests
```
Authorization: <your_jwt_token>
```

### 4. Create a patient
```bash
POST /api/patients
{
  "firstName": "Rahim",
  "lastName": "Uddin",
  "email": "rahim@example.com",
  "phone": "01712345678",
  "dateOfBirth": "1990-05-15",
  "gender": "MALE",
  "address": "Dhaka, Bangladesh",
  "medicalHistory": "Chest Pain"
}
```

### 5. Add a doctor
```bash
POST /api/doctors
{
  "firstName": "Farida",
  "lastName": "Hossain",
  "email": "dr.farida@hospital.com",
  "phone": "01712345679",
  "specialization": "Cardiology",
  "qualification": "MBBS, MD",
  "licenseNumber": "BMDC-12345",
  "available": true
}
```

### 6. Book an appointment
```bash
POST /api/appointments
{
  "patientId": 1,
  "doctorId": 1,
  "appointmentDate": "2026-04-20",
  "appointmentTime": "10:00",
  "reason": "Chest pain checkup",
  "notes": "Urgent"
}
```

---

## Roles & Permissions

| Role | Patients | Doctors | Appointments |
|------|----------|---------|--------------|
| ADMIN | Full access | Full access | Full access |
| DOCTOR | Read/Write | Read only | Full access |
| RECEPTIONIST | Full access | Read/Write | Full access |

---

## Key Features

- **JWT Authentication** — Stateless, token-based security
- **Role-Based Access Control** — Three roles with distinct permissions
- **Input Validation** — All request fields validated with meaningful error messages
- **Global Exception Handling** — Consistent error response structure across all endpoints
- **Conflict Detection** — Prevents double-booking a doctor at the same time slot
- **Patient Search** — Full-text search across name, email, and phone
- **Optimized Queries** — Custom JPQL queries for search and slot-checking
- **Swagger UI** — Interactive API documentation

---



## Author

**Abdullah Riyadh** — Software Engineer (Backend – Java)
- Email: abdullahriyadhcse@gmail.com
- GitHub: [abdullahRiyadh](https://github.com/abdullahRiyadh)
- LinkedIn: [Abdullah Riyadh](https://www.linkedin.com/in/abdullah-riyadh-02a655353/)
