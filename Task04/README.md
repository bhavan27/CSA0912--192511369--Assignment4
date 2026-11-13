# Task 04 – Event-Handling Forensics: Draw Pad

## Compile and run
```bash
javac -d out src/*.java
java -cp out DrawPad
```
The completed code corrects `mouseclicked` to `mouseClicked`, stores clicked points, updates the click-count label and redraws every stored point from `paint(Graphics)`. A WindowAdapter handles closing.

`DESIGN_NOTES.md` records the supplied defects, failing interaction, repair, `@Override` prevention and BufferedImage alternative.
