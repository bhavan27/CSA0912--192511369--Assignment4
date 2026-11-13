# Task 3 – Layout design

## 3.1 Layout manager choices
- Main JFrame: BorderLayout. The centre results area receives remaining space and grows on resize.
- Search row: FlowLayout. The text field, category selector and Search button retain preferred sizes.
- Bottom controls: FlowLayout. Prev, Next and Reserve retain their preferred sizes.
- Bottom status container: BorderLayout, with controls at NORTH and status at SOUTH.
- Results: JScrollPane in CENTER, allowing the table viewport to expand.

## 3.2 Nested container structure
```text
JFrame (BorderLayout)
├── NORTH: title JLabel
├── PAGE_START: JPanel (BorderLayout)
│   └── CENTER: JPanel (FlowLayout) – search field, category, Search
├── CENTER: JScrollPane
│   └── JTable – results
└── SOUTH: JPanel (BorderLayout)
    ├── NORTH: JPanel (FlowLayout) – Prev, Next, Reserve
    └── SOUTH: JLabel – status
```

## 3.4 Resizing evidence
The three images in `output/` show illustrative small, medium and large layout states. The results region is designed to grow while the search row and bottom controls retain their preferred component sizes. Run the program and capture actual screenshots at the three sizes for submission.

## Alternative concept and complexity
Nested BorderLayout/FlowLayout uses a small number of containers and performs layout using each container's children; for c components, a pass is generally O(c). GridBagLayout provides more flexible constraints but requires more constraint configuration and layout calculations, also generally proportional to the number of components. Absolute positioning can have simple coordinate assignment, but does not adapt reliably to resizing, fonts or platform differences; it requires manually updating bounds. Container space is O(p) for p panels plus the components in each design.
