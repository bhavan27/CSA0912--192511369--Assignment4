# Task 6 – Design and implementation notes

## Stage 1 – Users, screens, data and rules
**Users:** students registering for campus events; committee members viewing, filtering and exporting registrations.

**Screen:** registration form, event filter, registration JTable, menu bar and status/error feedback.

**Data:** student ID, student name, event name, email, registration ID and timestamp.

**Testable rules:**
1. A student ID must be present and match the accepted identifier format.
2. Name must contain at least two characters.
3. An event must be selected.
4. Email must have a valid basic email format.
5. A student may register only once for the same event.
6. Database loading and registration must not block the Swing EDT.
7. Committee users can filter registrations by event and export the displayed page.

## Stage 2 – Screen and event flow
```text
Launch
  |
  v
Registration Window
  |-- Register --> Validate --> SwingWorker/JDBC insert --> Success or error dialog
  |                                               |
  |                                               v
  |                                          Refresh table
  |
  |-- Event filter --> SwingWorker/JDBC SELECT --> JTable
  |-- Previous/Next --> LIMIT/OFFSET page query --> JTable
  |-- File > Export --> Save current JTable page as CSV
  |-- Window close --> Dispose
```

The menu bar contains File > Refresh and File > Export current page.

## Stage 3 – Critical design decisions
- Input validation is performed in RegistrationService before the DAO call.
- Duplicate checking is enforced by a database UNIQUE constraint on `(student_id,event_name)`; SQLState 23000 is translated into a user-facing duplicate message.
- SQL uses PreparedStatement parameters.
- JDBC operations are executed inside SwingWorker.doInBackground(), not on the EDT.
- The result table is loaded in pages of 25 using SQL LIMIT and OFFSET.

## Stage 4 – Class and handler design
```text
EventRegistrationApp (JFrame / UI)
        |
        v
RegistrationService (validation and business rules)
        |
        v
RegistrationDao (JDBC data access)
        |
        v
MySQL: campus_events.registrations

Db (connection configuration)
Registration (row model)
```

**Register pseudocode**
```text
PROCEDURE registerAction
    read student ID, name, event and email from form
    show "Registering..."
    execute SwingWorker
        DO IN BACKGROUND
            validate fields in RegistrationService
            insert row using DAO and PreparedStatement
            database unique constraint rejects duplicate student/event
        ON COMPLETION
            if success: show success and refresh first page
            otherwise: show validation/database error
END PROCEDURE
```

**Filter pseudocode**
```text
PROCEDURE filterAction
    selectedEvent ← filterCombo selection
    offset ← 0
    execute SwingWorker
        DO IN BACKGROUND
            rows ← DAO.page(selectedEvent, pageSize, offset)
        ON COMPLETION
            clear JTable model
            add returned rows
            update status message
END PROCEDURE
```

## Stage 5 – Demonstration cases
Use `input/registration-test-cases.csv` and the SQL seed rows. Demonstrate one valid registration, the same student/event pair again (duplicate), invalid student ID, invalid name and invalid email.

## Alternative concept and complexity
For 100,000 registrations, loading all rows at once requires O(N) database transfer and O(N) row storage in the GUI, and can cause long pauses and high memory use. Paginating with page size p requires O(p) row storage per displayed page and transfers O(p) rows per request; retrieving a particular offset can still require the database to scan/skip earlier matching rows depending on the query plan/indexes. SwingWorker keeps the EDT responsive during either approach, but paging reduces the amount of data transferred and retained at one time.
