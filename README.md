# Cafe Management System - Setup & Installation Guide

Welcome to the Cafe Management System! This guide will walk you through setting up the project on a new computer. It covers Java installation, MySQL database setup, and compiling/running the application.

## 1. Prerequisites

Before starting, ensure you have the following installed on your new computer:

- **Java Development Kit (JDK 8 or higher)**: 
  - Download from [Oracle](https://www.oracle.com/java/technologies/downloads/) or install via OpenJDK.
  - Verify installation by running `java -version` and `javac -version` in your terminal.
- **MySQL Server (5.7 or 8.0+)**:
  - Download from [MySQL Official Website](https://dev.mysql.com/downloads/installer/).
  - Make sure the MySQL service is running.

---

## 2. Database Installation & Configuration

The application requires a MySQL database named `cafe_ms` to store users, categories, products, and bills.

### Step 2.1: Run the Database Script
We have provided a `database.sql` script in the root directory of this project.

1. Open your MySQL client (like MySQL Workbench, DBeaver, or terminal).
2. Copy the contents of `database.sql` and execute them. This will:
   - Create the `cafe_ms` database.
   - Create the `user`, `category`, `product`, and `bill` tables.
   - Insert the default admin user.

### Step 2.2: Verify Default Admin User
The script automatically inserts the following admin account:
- Email: **admin@gmail.com**
- Password: **admin**

---

## 3. Environment & Application Setup

Now that the database is ready, you need to configure the Java application to connect to it.

### Step 3.1: Update Database Credentials
Open the project folder and navigate to `src/com/java1234/util/DbUtil.java`. 

Update the `dbUserName` and `dbPassword` variables to match your local MySQL credentials:

```java
public class DbUtil {
    private String dbUrl = "jdbc:mysql://localhost:3306/cafe_ms?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
    
    // UPDATE THESE TO MATCH YOUR MYSQL SETUP:
    private String dbUserName = "root"; 
    private String dbPassword = "YOUR_MYSQL_PASSWORD_HERE"; 
    
    private String jdbcName = "com.mysql.cj.jdbc.Driver";
    // ...
}
```

> [!WARNING]
> If you are using MySQL 5.7 instead of 8.0+, you may need to change the `jdbcName` back to `"com.mysql.jdbc.Driver"`.

---

## 4. Compiling the Project

This project uses the `mysql-connector-java-8.0.30.jar` library located in the `lib` folder. You must include this jar in your classpath when compiling.

Open your terminal (or Command Prompt on Windows), navigate to the root directory of the project, and run the following command to compile all Java source files into the `bin` directory:

**For macOS / Linux:**
```bash
mkdir -p bin
javac -cp "lib/mysql-connector-java-8.0.30.jar:lib/itextpdf-5.5.13.3.jar" -d bin $(find src -name "*.java")
```

**For Windows (PowerShell):**
```powershell
mkdir bin
Get-ChildItem -Path src -Filter *.java -Recurse | ForEach-Object { javac -cp "lib/mysql-connector-java-8.0.30.jar;lib/itextpdf-5.5.13.3.jar" -d bin $_.FullName }
```

---

## 5. Running the Application

Once compiled, you can launch the application by running the `LogOnFrm` main class. Make sure to include both the `bin` folder and the MySQL connector library in your classpath.

**For macOS / Linux:**
```bash
java -cp "bin:lib/mysql-connector-java-8.0.30.jar:lib/itextpdf-5.5.13.3.jar" com.java1234.view.LoginPage
```

**For Windows:**
```powershell
java -cp "bin;lib/mysql-connector-java-8.0.30.jar;lib/itextpdf-5.5.13.3.jar" com.java1234.view.LoginPage
```

### Success!
The "Cafe Management System" login screen should now appear. You can log in using the credentials you created earlier (Email: **admin@gmail.com**, Password: **admin**).
