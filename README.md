# Book Management System - Setup & Installation Guide

Welcome to the Book Management System! This guide will walk you through setting up the project on a new computer. It covers Java installation, MySQL database setup, and compiling/running the application.

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

The application requires a MySQL database named `db_book` to store users, books, and book categories.

### Step 2.1: Create the Database
Open your MySQL client (like MySQL Workbench, DBeaver, or terminal) and execute the following commands:

```sql
CREATE DATABASE IF NOT EXISTS db_book DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE db_book;
```

### Step 2.2: Create the Tables
Copy and paste the following DDL script to create the necessary tables:

```sql
-- 1. Create Book Types Table
CREATE TABLE `t_booktype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bookTypeName` varchar(20) DEFAULT NULL,
  `bookTypeDesc` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 2. Create Books Table
CREATE TABLE `t_book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bookName` varchar(20) DEFAULT NULL,
  `author` varchar(20) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `bookDesc` varchar(1000) DEFAULT NULL,
  `bookTypeId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_t_book` (`bookTypeId`),
  CONSTRAINT `FK_t_book` FOREIGN KEY (`bookTypeId`) REFERENCES `t_booktype` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 3. Create Users Table
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

### Step 2.3: Insert Default Admin User
To be able to log into the application, insert an initial admin account:

```sql
INSERT INTO `t_user` (`userName`, `password`) VALUES ('admin', 'admin');
```

---

## 3. Environment & Application Setup

Now that the database is ready, you need to configure the Java application to connect to it.

### Step 3.1: Update Database Credentials
Open the project folder and navigate to `src/com/java1234/util/DbUtil.java`. 

Update the `dbUserName` and `dbPassword` variables to match your local MySQL credentials:

```java
public class DbUtil {
    private String dbUrl = "jdbc:mysql://localhost:3306/db_book?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
    
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
javac -cp "lib/mysql-connector-java-8.0.30.jar" -d bin $(find src -name "*.java")
```

**For Windows (PowerShell):**
```powershell
mkdir bin
Get-ChildItem -Path src -Filter *.java -Recurse | ForEach-Object { javac -cp "lib/mysql-connector-java-8.0.30.jar" -d bin $_.FullName }
```

---

## 5. Running the Application

Once compiled, you can launch the application by running the `LogOnFrm` main class. Make sure to include both the `bin` folder and the MySQL connector library in your classpath.

**For macOS / Linux:**
```bash
java -cp "bin:lib/mysql-connector-java-8.0.30.jar" com.java1234.view.LogOnFrm
```

**For Windows:**
```powershell
java -cp "bin;lib/mysql-connector-java-8.0.30.jar" com.java1234.view.LogOnFrm
```

### Success!
The "Library Management System" login screen should now appear. You can log in using the credentials you created earlier (Username: **admin**, Password: **admin**).
