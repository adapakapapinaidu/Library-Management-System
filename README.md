Here’s the **updated backend README.md** including your live demo link:

---

# Library Management System - Backend

The backend of the **Library Management System** is built with **Java Spring Boot** and **MySQL**, providing secure REST APIs to manage library members and books efficiently.

## Features

* Full **CRUD operations** for members and books
* Search members by **name** and **complaints** (equal, greater than, smaller than)
* User **authentication and authorization**
* RESTful API endpoints for seamless frontend integration

## Technologies

* Java
* Spring Boot
* Maven
* MySQL (or any relational database)

## Setup & Run

1. **Clone the repository:**

```bash
git clone https://github.com/adapakapapinaidu/Library-Management-System.git
cd Library-Management-System
```

2. **Configure the database:**

* Update `application.properties` with your MySQL credentials and database name.

3. **Run the application:**

* Using your IDE (IntelliJ/Eclipse) **or** via terminal:

```bash
mvn spring-boot:run
```

4. **API Access:**

* Backend runs on: `http://localhost:8080`
* Test endpoints using Postman or your preferred API client

5. **Live Demo:**

* Check the live backend at: [https://library-management-system-fixa.onrender.com/](https://library-management-system-fixa.onrender.com/)
