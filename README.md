# CSA09 – Programming in Java – Assignment 4 (CO4)
## Event-Driven GUI Design

**Assignment structure:** six tasks, 50 marks total. The source PDF states that this is an individual assignment and each student must execute every task.

## Repository structure
- `Task01/` Fee payment GUI design review and repaired Swing implementation
- `Task02/` Hospital outpatient appointment desk
- `Task03/` Campus library search screen and resizing layouts
- `Task04/` AWT drawing pad event-handling repair
- `Task05/` Currency converter using four event-handling styles
- `Task06/` Campus event registration Swing–JDBC application and SQL script

Each task folder contains `src/`, `input/`, `output/` and `README.md`. Design-only material and pseudocode are in `DESIGN_NOTES.md` where applicable. Task 6 also contains `sql/schema.sql`.

## Student details
- Name: ______________________________
- Register number: ____________________

## Java version
JDK 17 or later.

## Important evidence note
PNG files in `output/` are illustrative interface mock-ups created to show the expected evidence layout. They are not screenshots captured from executing the Java programs. Run each program on your computer and replace or supplement them with actual screenshots of the tested interactions. Task 6 requires MySQL and MySQL Connector/J; its UI cannot complete database operations until the database is configured.

## Compile and run
Open the relevant task folder in VS Code and follow that task's README. Most tasks use:
```bash
javac -d out src/*.java
java -cp out MainClassName
```
Task 6 has a separate JDBC classpath command in its README.

## Assignment requirements from the PDF
- One folder per task with Java source, input files and output screenshots.
- A README with student name, register number, Java version, compile/run steps and task result. Fill in your own name and register number before submission.
- At least one progressive Git commit per task.
- Report with diagnoses, tables, diagrams, pseudocode and comparisons.
- Completed code for Tasks 1.2, 3, 4, 5 and 6.
- Task 6 SQL script.
- Each screenshot should be linked to the requirement/test it demonstrates.
