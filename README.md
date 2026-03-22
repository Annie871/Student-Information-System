# 🎓 Student Information System (SIS)

A robust, console-based backend application designed to manage student records efficiently. This project demonstrates a complete CRUD (Create, Read, Update, Delete) lifecycle utilizing Core Java and a relational database architecture.

## 🛠️ Tech Stack
* **Language:** Java (Core/JDK)
* **Database:** MySQL
* **Database Connectivity:** JDBC (MySQL Connector/J)
* **Architecture:** Monolithic Console Application

## ✨ System Features
* **Admissions Module:** Registers new students with unique Roll Numbers, Full Names, Courses, and Ages.
* **Directory Retrieval:** Dynamically queries the database to generate a formatted, aligned table of all active students.
* **Record Management:** Safely removes outdated or incorrect student records using primary key database IDs.
* **Security Measures:** Implemented `PreparedStatement` interfaces for all database transactions to strictly prevent SQL injection vulnerabilities.
* **Resource Management:** Utilizes Java `try-with-resources` to ensure database connections and memory scanners are safely closed, preventing resource leaks.

## 📸 Console Output Showcase

**1. Main Menu & Student Registration**
*(Drag and drop the screenshot of adding a student here)*

**2. Formatted Student Directory**
*(Drag and drop the screenshot of the view students table here)*

## 🚀 Local Setup & Installation

1. **Database Configuration:**
   Execute the following SQL script in your MySQL environment to initialize the database:
   ```sql
   CREATE DATABASE student_db;
   USE student_db;
   CREATE TABLE students (
       id INT AUTO_INCREMENT PRIMARY KEY,
       roll_number VARCHAR(20) UNIQUE NOT NULL,
       full_name VARCHAR(100) NOT NULL,
       course VARCHAR(50) NOT NULL,
       age INT
   );
Application Configuration:
Open StudentSystem.java and update the USER and PASS variables with your local MySQL credentials.

Compilation & Execution:
Ensure the MySQL JDBC Driver (mysql-connector-j-9.6.0.jar) is in the root directory, then run:

Bash
javac -cp "mysql-connector-j-9.6.0.jar" StudentSystem.java
java -cp ".;mysql-connector-j-9.6.0.jar" StudentSystem
