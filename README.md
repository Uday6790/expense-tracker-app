# 💰 Smart Expense & Budget Tracker

A full-stack **Smart Expense & Budget Tracker** web application developed using Java and Spring Boot. The application enables users to securely manage their personal expenses, organize spending by category, and monitor their financial activities through an interactive dashboard.

🌐 **Live Application:** [https://expense-tracker.udaykiran.is-a.dev](https://expense-tracker.udaykiran.is-a.dev)

📂 **GitHub Repository:** [Expense Tracker App](https://github.com/Uday6790/expense-tracker-app)

---

## 🚀 Features

- 🔐 User Registration and Login
- 👤 User Authentication and Session Management
- ➕ Add New Expenses
- ✏️ Edit Existing Expenses
- 🗑️ Delete Expenses
- 📋 View and Manage Expense Records
- 💵 Track Expense Amounts
- 📅 Record Expense Dates
- 🏷️ Categorize Expenses
- 📊 Interactive Expense Charts and Visualization
- 📥 Export Expense Data
- 📱 Responsive Web Interface
- 🗄️ Database-Backed Expense Management
- ☁️ Cloud Deployment

---

## 🛠️ Technologies Used

### Backend
- Java
- Spring Boot
- Spring Data JPA
- Spring Security
- Maven
- REST APIs

### Frontend
- HTML5
- CSS3
- JavaScript
- Chart.js

### Database
- MySQL
- PostgreSQL

### Testing
- JUnit
- Selenium

### Deployment
- Render
- Custom Domain: is-a.dev

---

## 📁 Project Structure

```text
expense-tracker-app/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── expensetracker/
│   │   │
│   │   └── resources/
│   │       ├── static/
│   │       │   ├── login.html
│   │       │   ├── register.html
│   │       │   └── index.html
│   │       │
│   │       ├── templates/
│   │       └── application.properties
│   │
│   └── test/
│       └── java/
│
├── pom.xml
├── README.md
└── .gitignore
```

---

## 🌐 Live Application

Access the deployed application using the following link:

### [🚀 Launch Expense Tracker](https://expense-tracker.udaykiran.is-a.dev/login.html)

The application is hosted on Render and accessed through a custom is-a.dev domain.

---

## ⚙️ Getting Started

Follow these steps to run the project locally.

### 1. Clone the Repository

```bash
git clone https://github.com/Uday6790/expense-tracker-app.git
```

### 2. Navigate to the Project Directory

```bash
cd expense-tracker-app
```

### 3. Configure the Database

Create a database using MySQL or PostgreSQL.

Configure your database connection in `application.properties`.

Example PostgreSQL configuration:

```properties
spring.datasource.url=${SPRING_DATASOURCE_URL}
spring.datasource.username=${SPRING_DATASOURCE_USERNAME}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

**Security Note:** Never commit database credentials, passwords, or secret keys to GitHub.

### 4. Build the Project

```bash
mvn clean install
```

### 5. Run the Application

```bash
mvn spring-boot:run
```

The application will normally be available at:

```text
http://localhost:8080
```

---

## 🔌 REST API

The application provides REST APIs for user authentication and expense management.

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/users/register` | Register a new user |
| POST | `/api/users/login` | Authenticate a user |
| GET | `/api/expenses` | Retrieve expenses |
| POST | `/api/expenses` | Create a new expense |
| PUT | `/api/expenses/{id}` | Update an expense |
| DELETE | `/api/expenses/{id}` | Delete an expense |

*Note: API access may require an authenticated user session.*

---

## 🧾 Expense Data

Each expense record can include:

- Expense ID
- Description
- Amount
- Category
- Date

---

## 🧪 Testing

Run automated tests using Maven:

```bash
mvn test
```

JUnit is used for backend testing, while Selenium can be used for browser-based application testing.

---

## 🔐 Security

The application incorporates user authentication and security practices to protect user access.

Sensitive configuration should be managed through environment variables.

Example:

```properties
spring.datasource.url=${SPRING_DATASOURCE_URL}
spring.datasource.username=${SPRING_DATASOURCE_USERNAME}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}
```

---

## 📸 Screenshots

Screenshots of the application can be added to demonstrate its functionality.

```text
screenshots/
├── dashboard.png
├── login.png
├── register.png
├── add-expense.png
└── expense-list.png
```

---

## 🎯 Future Enhancements

- 📊 Advanced Expense Analytics
- 📈 Monthly and Yearly Spending Reports
- 🔎 Expense Search and Filtering
- 📥 PDF and CSV Export
- 🔔 Budget Notifications
- 📱 Progressive Web App Support
- ☁️ Enhanced Cloud Deployment

---

## 👨‍💻 Author

**Uday Kiran**

MCA Student | Java Full Stack Developer | Software Developer

- GitHub: [Uday6790](https://github.com/Uday6790)
- Portfolio: [https://udaykiran.is-a.dev](https://udaykiran.is-a.dev)
- Expense Tracker: [Live Application](https://expense-tracker.udaykiran.is-a.dev)

---

## 📄 License

This project is developed for educational, learning, and software development portfolio purposes.
