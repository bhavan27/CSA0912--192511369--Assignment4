# Task 05 – Currency Converter: Four Event-Handling Styles

## Compile and run one implementation at a time
```bash
javac -d out src/ConverterFrameListener.java
java -cp out ConverterFrameListener

javac -d out src/ConverterNamedInner.java
java -cp out ConverterNamedInner

javac -d out src/ConverterAnonymous.java
java -cp out ConverterAnonymous

javac -d out src/Converter.java
java -cp out Converter
```
All versions handle both the Convert button and Enter in the amount field and display the result in a dialog. Window closing uses WindowAdapter. The lambda version completes the supplied TODOs. Rates are the fixed rates supplied in the assignment (0.012 USD and 0.011 EUR per INR).
