# Task 4 – Event-handling forensic notes

## 4.1 Defect
The supplied method is named `mouseclicked`, but MouseListener/MouseAdapter defines `mouseClicked` with a capital C. Java method names are case-sensitive, so the method does not override the callback and is never invoked. The label is never updated because the callback body does not execute. The supplied code also does not update the status label.

## 4.2 Failing interaction
Click to draw a dot, minimise the window and restore it. The dot disappears because `getGraphics()` draws directly onto the current screen surface. That drawing is not retained as application state and may be erased during repainting.

## 4.3 Repair
The completed `DrawPad.java` stores each click as a Point in a List, updates the label, calls repaint(), and redraws all stored points in paint(Graphics).

## 4.4 @Override
With `@Override` above `mouseclicked`, the compiler reports that the method does not override or implement a method from a supertype. This exposes the spelling/capitalisation error at compile time.

## Alternative concept and complexity
With a List<Point>, drawing each repaint takes O(n) time for n clicks and O(n) point storage. With a BufferedImage, each click paints once into the off-screen image; repaint copies/draws the image, which is O(width × height) for a full image copy (or proportional to the copied pixel region). Image storage is O(width × height) pixels. The image approach makes repaint independent of the number of points but uses memory proportional to the drawing surface.
