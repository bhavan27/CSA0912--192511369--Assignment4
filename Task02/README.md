# Task 02 – Hospital Outpatient Appointment Desk

## Compile and run
```bash
javac -d out src/*.java
java -cp out AppointmentDesk
```
The Swing form supports patient ID entry, department selection, department-dependent doctor lists, time-slot selection, booking confirmation/error feedback and a close confirmation when a booking is in progress. The doctor catalogue is loaded once into a `Map<String,List<String>>`.

`DESIGN_NOTES.md` contains the event-source table, component containment tree, handler pseudocode, window event trace, applet lifecycle comparison and alternative database-fetch complexity analysis.
