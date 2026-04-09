# IDATT2105 Full-Stack Project

This project is a full-stack web application developed as part of the IDATT2105 course project.  
The system is designed as a digital internal control solution for restaurants, with functionality related to task management, deviation handling, employee management, restaurant membership, and courses. 

Made by Petter Tjelde Vaagen

## Tech Stack

### Frontend
- Vue 3
- TypeScript
- Vite
- Vue Router
- Pinia

### Backend
- Java 21
- Spring Boot
- Spring Security
- Spring JDBC / JPA
- JWT authentication

### Test Credentials
<br>
    Normal employee
<br>
    Email - ola@test.no
<br>
    Password - pass123
<br>
<br>
    Manager
<br>
    Email - kari@test.no
<br>
    Password - pass123

### Documentation
- [Javadoc](https://blinkendelys.com/IDATT2105-FullStackProsjekt/javadoc/index.html)
- [Tests](https://blinkendelys.com/IDATT2105-FullStackProsjekt/jacoco/index.html)
- [Endpoints](https://blinkendelys.com/IDATT2105-FullStackProsjekt/swagger/index.html)
- [Diagram](https://blinkendelys.com/IDATT2105-FullStackProsjekt/diagram/index.html)

### Database
- H2 (currently configured for local development and testing)

---

## How to Run the Project

### Recommended (Windows)

From the project root, run:

```bat
run.bat
```

### alternative
if the run.bat file did not work you can open a terminal from the backend folder and run:
```bat
mvn spring-boot:run
```
and then open a new terminal from the frontend folder and run:
```bat
npm install
npm run dev
```
then you can acess the app at http://localhost:5173/