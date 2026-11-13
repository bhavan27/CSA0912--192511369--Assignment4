# Task 01 – GUI Design Review: Fee Payment

## Files
- `src/FeeWindow.java`: Swing interface; collects input and displays results.
- `src/FeeService.java`: separate validation and payment service.
- `input/test-cases.csv`: test inputs and expected outcomes.
- `output/`: illustrative UI evidence and text test transcript.

## Compile and run (JDK 17+)
```bash
javac -d out src/*.java
java -cp out FeeWindow
```

The payment operation is simulated in `FeeService`; this task focuses on separation of UI and application logic. The original code's JDBC statement is not copied into the event handler. See `DESIGN_NOTES.md` for the diagnosis, class structure, design defence and alternative listener/complexity analysis.
