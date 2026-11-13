# Task 06 – Campus Event Registration Desktop Application

## Requirements
- JDK 17+
- MySQL Server
- MySQL Connector/J (JDBC driver JAR)

## Database setup
Run `sql/schema.sql` in MySQL. The schema creates `campus_events.registrations` and enforces a unique constraint on `(student_id, event_name)` to reject duplicate registrations.

## Configure connection
Set environment variables:
- `CO4_DB_URL` (default `jdbc:mysql://localhost:3306/campus_events`)
- `CO4_DB_USER` (default `root`)
- `CO4_DB_PASSWORD` (default empty)

## Compile and run (Windows PowerShell)
Replace `mysql-connector-j-8.x.x.jar` with the actual Connector/J path:
```powershell
javac -cp ".;lib/mysql-connector-j-8.x.x.jar" -d out src/*.java
java -cp "out;lib/mysql-connector-j-8.x.x.jar" EventRegistrationApp
```
On macOS/Linux replace `;` with `:` in the classpath.

The Swing application uses JFrame, JPanel, JTextField, JComboBox, JTable and JMenuBar. Registration and paginated database loading run in SwingWorker so JDBC work does not block the event-dispatch thread. The table uses SQL LIMIT/OFFSET pagination. Registration validation occurs in RegistrationService; the database unique constraint is the final protection against duplicate student/event pairs. The File menu exports the currently displayed page to CSV.

The GUI requires a running, configured MySQL database and Connector/J driver. `output/` contains illustrative UI evidence; capture actual application screenshots after configuring and running the database.
