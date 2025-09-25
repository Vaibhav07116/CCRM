# Campus Course & Records Manager (CCRM)

### Author Details:

Name:Vaibhav

Registration no:24BCE10717

## 1. Project Overview
This project is a comprehensive, console-based Java application designed to manage student and course records for an academic institution. I built this application using Java SE 17 and the Eclipse IDE, focusing on clean code, Object-Oriented principles, and modern Java features.

The application provides a menu-driven interface for administrators to manage students, courses, enrollments, and grades. It also includes robust file utilities for importing initial data and for exporting and backing up the current application state.

## 2. Key Features
Student Management: Add new students, list all students, and view a detailed profile and academic transcript for any student.

Course Management: List all available courses and search for courses by instructor.

Enrollment & Grading: Enroll students in courses with validation for business rules (e.g., duplicate enrollments, max credit limits) and assign grades.

Transcript Generation: Automatically calculate a student's GPA and generate a formatted transcript.

File I/O: Load initial data from CSV files and export the current data (students, courses, enrollments) to new CSV files.

Backup Utility: Create a timestamped backup of all exported data in a separate directory.

## 3.How to Run This Project
Prerequisites
Java Development Kit (JDK) 17 or later.

Eclipse IDE for Java Developers (or another IDE, but these instructions are for Eclipse).

Setup & Run Instructions
Clone the Repository: Clone or download this project to your local machine.

Open Eclipse IDE: Launch the Eclipse IDE.

Import the Project:

Go to File > Import....

In the import wizard, type Existing Projects into Workspace and select it.

Click "Next".

Click the "Browse..." button next to "Select root directory" and navigate to the root folder of this project (CCRM).

The project should appear in the "Projects" box. Make sure it's checked.

Click "Finish".
![project import](https://github.com/Vaibhav07116/CCRM/blob/f558893c028058e9a711be8d0c84edb83e4bc85f/screenshots/eclipse-project-import.png)

###  Run the Application:

In the "Package Explorer" on the left, navigate to src > edu.ccrm.cli.

Find the CCRMApp.java file.

Right-click on CCRMApp.java and select Run As > 1 Java Application.

The application will start running in the Console window at the bottom of the IDE.
![working](https://github.com/Vaibhav07116/CCRM/blob/2eb2e6d44422f3ddfc495a7a22c518537069f400/screenshots/working.jpeg)

## 4. Usage Walkthrough
Once the application is running, you can interact with it using the console menu:

Load Data: The application automatically loads initial student, course, and instructor data from the /test-data directory on startup.

Add a Student: Navigate to 1. Manage Students -> 1. Add New Student to add a new student to the system.

List Students/Courses: Use options in the "Manage Students" or "Manage Courses" menus to see all the data loaded.

Enroll a Student: Go to 3. Manage Enrollments -> 1. Enroll Student in a Course. You will be prompted for a Student ID and a Course Code. The system will validate the request against business rules.

Assign a Grade: Use option 3. Manage Enrollments -> 2. Assign Grade to Student.

View Transcript: Go to 1. Manage Students -> 3. View Student Profile & Transcript and enter a Student ID to see their complete academic record and calculated GPA.

Export & Backup: Navigate to 4. File Utilities to export the current data to the /exports folder and create a timestamped backup in the /backups folder.

## 5. Core Java Concepts Demonstrated
This project was built to demonstrate a wide range of core Java concepts as required by the course syllabus.

A. The Java Platform
Evolution of Java: Java has evolved significantly since its inception. Key milestones include Java 5 (Generics, Enums), Java 8 (Lambdas, Streams), Java 9 (Modules), and the new six-month release cadence starting with Java 10, leading to rapid feature integration like the enhancements to switch expressions and text blocks used in this project.

Java ME vs. SE vs. EE:

Java SE (Standard Edition): The core Java platform used for this project. It provides the fundamental libraries and APIs for developing desktop and console applications.

Java ME (Micro Edition): A subset of Java SE designed for resource-constrained devices like mobile phones and embedded systems.

Java EE (Enterprise Edition): Built on top of Java SE, this edition provides a framework for building large-scale, multi-tiered, and distributed enterprise applications (e.g., web services, server-side applications).

## JDK vs. JRE vs. JVM:

### JVM (Java Virtual Machine):
An abstract machine that provides the runtime environment in which Java bytecode can be executed. It's the component that makes Java "platform-independent."

### JRE (Java Runtime Environment):
A software package that contains what is required to run a Java program. It includes the JVM, core libraries, and other supporting files.

### JDK (Java Development Kit):
A superset of the JRE. It contains everything in the JRE, plus the tools necessary to develop Java applications, most importantly the compiler (javac).

## B. Syllabus Topic Mapping
Syllabus Topic

File(s) / Class / Method Where Demonstrated

### OOP - Encapsulation

All domain classes (e.g., Student.java, Course.java) use private fields with public getters/setters.

### OOP - Inheritance

Student.java and Instructor.java both extend the abstract Person.java class.

### OOP - Abstraction

Person.java is an abstract class with an abstract String getProfile() method.

### OOP - Polymorphism

The getProfile() method is called on Person objects, but the Student or Instructor version is run.

### Interfaces

Searchable.java defines a generic contract, implemented by StudentService and CourseService.

### Enums with Constructors

Grade.java is an enum where each constant (S, A, B) holds its own gradePoint value.

### Design Pattern - Singleton

DataStore.java uses the Singleton pattern to ensure only one instance of the app's data exists.

### Design Pattern - Builder

Course.java uses a static nested CourseBuilder class to construct Course objects cleanly.

### Custom Exceptions

EnrollmentException.java and MaxCreditsException.java are custom checked exceptions.

### Modern I/O (NIO.2)

FileService.java and BackupService.java use java.nio.file.Path and java.nio.file.Files.

### Streams & Lambdas

TranscriptService.calculateGpa() and CourseService.findCoursesByInstructor().

### Recursion

BackupService.calculateDirectorySize() uses Files.walk to traverse the backup directory.

### Date/Time API

Student.java and Enrollment.java use java.time.LocalDate. BackupService uses LocalDateTime.

### Collections Framework

ArrayList is used throughout the DataStore and services to manage lists of objects.

### Enhanced Switch

The main application loop in CCRMApp.java uses the modern arrow-style switch expression.

## 6. JAVA version
JDK Installation Verification
![working](https://github.com/Vaibhav07116/CCRM/blob/9374f6a52ccae4727cb73e094f41efea1806996d/screenshots/Java%20version.png)



## 7. Installing Eclipse IDE

   
![working](https://github.com/Vaibhav07116/CCRM/blob/e178fb327e7f44db75fe8465890ce476d9fa56fd/screenshots/EclipseInstallation2.png)


![working](https://github.com/Vaibhav07116/CCRM/blob/e178fb327e7f44db75fe8465890ce476d9fa56fd/screenshots/EclipseInstallation5.png)


![working](https://github.com/Vaibhav07116/CCRM/blob/e178fb327e7f44db75fe8465890ce476d9fa56fd/screenshots/EclipseInstallation4.png)



