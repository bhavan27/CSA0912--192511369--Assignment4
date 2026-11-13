# CSA09 – Programming in Java – Assignment 4 (CO4)

**Student:** V. C. Bhavan Bairav  
**Register Number:** 192511369  
**Course:** Programming in Java – CSA0912 (CO4)  
**Assignment:** Assignment 4 – 50 Marks

## Event-Driven GUI Design

This repository contains my Java Assignment 4, which focuses on designing and implementing event-driven graphical user interfaces using Java Swing and AWT. It is organised into six task folders based on the assignment requirements.

## Requirements

- **Java:** JDK 17 or later.
- **IDE:** VS Code or any Java IDE with terminal support.
- **Database:** MySQL and MySQL Connector/J for Task 06.

## Repository Structure

| Folder | Description |
|---|---|
| `Task01/` | Fee payment GUI design review and repaired Swing implementation |
| `Task02/` | Hospital outpatient appointment desk |
| `Task03/` | Campus library search screen and resizing layouts |
| `Task04/` | AWT drawing pad event-handling repair |
| `Task05/` | Currency converter using four event-handling styles |
| `Task06/` | Campus event registration application using Swing and JDBC |

Each task folder contains `src/`, `input/`, `output/` and a task-specific `README.md`. Design explanations and pseudocode are included in `DESIGN_NOTES.md` wherever applicable. Task 06 also contains the SQL schema at `sql/schema.sql`.

## Compile and Run

Open the required task folder in VS Code and follow the instructions in its README.

For most tasks, use:

```bash
javac -d out src/*.java
java -cp out MainClassName
```

Task 06 uses a separate JDBC classpath command because it requires the MySQL Connector/J driver. The database schema is available in `sql/schema.sql`.

## Tasks Overview

- **Task 01:** Reviews a fee payment GUI and repairs the Swing implementation.
- **Task 02:** Develops a hospital outpatient appointment desk.
- **Task 03:** Designs a campus library search interface with layouts that support resizing.
- **Task 04:** Repairs event handling in an AWT drawing pad.
- **Task 05:** Implements a currency converter using four different event-handling styles.
- **Task 06:** Integrates a Swing-based campus event registration application with a MySQL database using JDBC.

## Execution Evidence

The `output/` folders contain illustrative interface mock-ups showing the expected evidence layout. These are not screenshots captured from actual Java program execution.

Task 06 requires a configured MySQL database and MySQL Connector/J driver to perform database operations. Without the database connection, the application interface alone cannot complete those operations.

## Assignment Requirements

The assignment follows an individual implementation and execution format. Each task is organised separately with its source code, input files and output evidence.

The submission also includes design explanations, diagnoses, tables, diagrams, pseudocode and comparisons wherever required. Completed implementations are included for Tasks 1.2, 3, 4, 5 and 6, along with the SQL script required for Task 6.

Progressive Git commits maintain the development history, with at least one meaningful commit for each task.

**Total: 6 Tasks – 50 Marks**
