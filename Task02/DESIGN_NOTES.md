# Task 2 – Design artefacts

## 2.1 Event model

| Event source | Event object | Listener interface | Action |
|---|---|---|---|
| Department JComboBox | ItemEvent / ActionEvent | ItemListener / ActionListener | Refresh doctor list for selected department |
| Book JButton | ActionEvent | ActionListener | Validate fields and book appointment |
| Window close control | WindowEvent | WindowListener / WindowAdapter | Ask before closing if booking is in progress |
| Time-slot JComboBox | ActionEvent | ActionListener (if additional validation is needed) | Read selected slot |
| Doctor JComboBox | ActionEvent | ActionListener (if additional validation is needed) | Read selected doctor |

## 2.2 Component containment tree

```text
JFrame: AppointmentDesk (BorderLayout)
├── CENTER: JPanel (GridLayout 5 x 2)
│   ├── JLabel + JTextField (patient ID)
│   ├── JLabel + JComboBox (department)
│   ├── JLabel + JComboBox (doctor)
│   ├── JLabel + JComboBox (time slot)
│   └── JLabel + JButton (Book appointment)
└── SOUTH: JLabel (status)
```

## 2.3 Handler pseudocode

**Refresh doctor list**
```text
PROCEDURE refreshDoctors
    selectedDepartment ← departmentCombo.getSelectedItem()
    doctorCombo.removeAllItems()
    doctorList ← doctorMap.get(selectedDepartment)
    FOR EACH doctor IN doctorList
        doctorCombo.addItem(doctor)
    END FOR
END PROCEDURE
```

**Validate and book**
```text
PROCEDURE bookAppointment
    patient ← trim(patientIdField.getText())
    IF patient is empty OR patient format is invalid THEN
        display validation error
        RETURN
    END IF
    IF doctor or time slot is not selected THEN
        display selection error
        RETURN
    END IF
    bookingInProgress ← TRUE
    TRY
        create appointment using patient, department, doctor and slot
        display confirmation
    FINALLY
        bookingInProgress ← FALSE
    END TRY
END PROCEDURE
```

## 2.4 Event trace and applet comparison

1. `main()` calls `SwingUtilities.invokeLater`.
2. The EDT constructs `AppointmentDesk`.
3. Components and listeners are created and registered.
4. `refreshDoctors()` loads the initial doctor list.
5. `setVisible(true)` displays the window.
6. User actions generate ActionEvent/ItemEvent objects; the EDT invokes the registered handlers.
7. A close action generates WindowEvent and invokes `windowClosing`.
8. If booking is in progress, the user is asked to confirm; otherwise the frame is disposed.

An applet uses `init()` for initialization, `start()` when it becomes active, `paint()` for drawing, `stop()` when it becomes inactive and `destroy()` before removal. A JFrame instead responds to construction, visibility, repainting and window events; it does not use the applet lifecycle methods.

## Alternative concept and complexity
Loading all doctors once into a map uses O(dm) memory for d departments and up to m doctors per department. A department change can retrieve the list in O(1) average map lookup plus O(m) to populate the combo box. Fetching from a database each time incurs database round-trip/query cost per change (approximately O(query + m) returned rows) and avoids retaining the complete catalogue in application memory. The map approach trades memory for lower repeated fetch latency.
