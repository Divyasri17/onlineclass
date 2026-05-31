# Online Class Scheduling System

## Project Overview

Online Class Scheduling System is a Spring Boot application that allows teachers to create courses, create offerings, schedule sessions, and allows parents to browse and enroll in available course offerings.

The application supports timezone conversion between teachers and parents, ensuring that session schedules are displayed correctly according to the user's timezone.

---

## Features

### Teacher Module

* Teacher Registration and Login
* Create Courses
* Create Course Offerings
* Create Course Sessions
* View and Manage Courses

### Parent Module

* Parent Registration and Login
* Browse Available Courses
* View Course Details
* View Session Schedules
* Enroll in Course Offerings

### System Features

* Timezone Conversion
* Course Scheduling
* Session Management
* Offering Management
* Enrollment Management

---

## Tech Stack

### Backend

* Java 17
* Spring Boot
* Spring MVC
* Spring Data JPA
* Hibernate

### Frontend

* HTML
* CSS
* Thymeleaf

### Database

* MySQL 8

### Build Tool

* Maven

---

## Project Structure

src/main/java

* Controller
* Service
* Repository
* Entity

src/main/resources

* templates
* static
* application.properties

---

## Database Configuration

Create the database:

```sql
CREATE DATABASE onlineclass;
```

Update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/onlineclass
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## Running the Application

### Clone Repository

```bash
git clone https://github.com/Divyasri17/onlineclass.git
```

### Open Project

Open the project in Spring Tool Suite (STS) or Eclipse.

### Run Application

Run:

```java
OnlineClassApplication.java
```

or

```bash
mvn spring-boot:run
```

---

## Application URL

```text
http://localhost:9090/
```

---

## Main Database Tables

* teacher
* parent
* course
* offering
* session
* booking

### Relationships

Teacher → Course

Course → Offering

Offering → Session

Parent → Booking

---

## Timezone Handling

Session timings are stored in UTC.

When a parent views a course, session timings are automatically converted from the teacher's timezone to the parent's timezone.

Example:

Teacher Timezone:
Asia/Kolkata

Parent Timezone:
America/New_York

The application displays the correct local time for each user.

---

## Assumptions

* One teacher can create multiple courses.
* One course can have multiple offerings.
* One offering can have multiple sessions.
* Parents can enroll in course offerings.
* Session timings are stored in UTC.

---

## Future Improvements

* Swagger API Documentation
* Docker Deployment
* Unit Testing
* Email Notifications
* Payment Integration

---

## Author

Divya Sri

GitHub:
https://github.com/Divyasri17/onlineclass
