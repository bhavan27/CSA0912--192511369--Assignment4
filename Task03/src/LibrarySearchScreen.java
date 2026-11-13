import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class LibrarySearchScreen extends JFrame {
    private final JTextField query = new JTextField(24);
    private final JComboBox<String> category = new JComboBox<>(new String[]{"All","Java","Database","Networking"});
    private final JLabel status = new JLabel("Ready");
    private final DefaultTableModel model = new DefaultTableModel(new Object[]{"Title","Category","Availability"},0);
    private final JTable results = new JTable(model);

    public LibrarySearchScreen() {
        super("Campus Library Search");
        setLayout(new BorderLayout(8,8));
        JLabel title = new JLabel("Campus Library Catalogue",SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(Font.BOLD,20f));

        JPanel searchRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchRow.add(new JLabel("Search")); searchRow.add(query);
        searchRow.add(category); JButton search=new JButton("Search"); searchRow.add(search);
        JPanel top = new JPanel(new BorderLayout());
        top.add(title,BorderLayout.NORTH);
        top.add(searchRow,BorderLayout.SOUTH);
        add(top,BorderLayout.NORTH);

        results.setFillsViewportHeight(true);
        add(new JScrollPane(results),BorderLayout.CENTER);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton prev=new JButton("Prev"), next=new JButton("Next"), reserve=new JButton("Reserve");
        bottom.add(prev); bottom.add(next); bottom.add(reserve);
        JPanel south = new JPanel(new BorderLayout());
        south.add(bottom,BorderLayout.NORTH); south.add(status,BorderLayout.SOUTH);
        add(south,BorderLayout.SOUTH);

        search.addActionListener(e -> searchBooks());
        query.addActionListener(e -> searchBooks());
        reserve.addActionListener(e -> status.setText(results.getSelectedRow()<0?"Select a result first.":"Reservation request recorded."));
        prev.addActionListener(e -> status.setText("Previous page selected."));
        next.addActionListener(e -> status.setText("Next page selected."));
        addWindowListener(new WindowAdapter(){@Override public void windowClosing(WindowEvent e){dispose();}});
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        model.addRow(new Object[]{"Effective Java","Java","Available"});
        model.addRow(new Object[]{"Database System Concepts","Database","Available"});
        model.addRow(new Object[]{"Computer Networks","Networking","Issued"});
        setSize(760,500); setLocationRelativeTo(null);
    }
    private void searchBooks() {
        String term=query.getText().trim().toLowerCase();
        String cat=(String)category.getSelectedItem();
        Object[][] books={
            {"Effective Java","Java","Available"},
            {"Database System Concepts","Database","Available"},
            {"Computer Networks","Networking","Issued"}
        };
        model.setRowCount(0);
        for(Object[] book:books) {
            String title=book[0].toString().toLowerCase();
            String c=book[1].toString();
            boolean titleMatches=term.isEmpty() || title.contains(term);
            boolean categoryMatches="All".equals(cat) || cat.equals(c);
            if(titleMatches && categoryMatches) model.addRow(book);
        }
        status.setText("Search applied. Displayed rows: "+model.getRowCount());
    }
    public static void main(String[] args){SwingUtilities.invokeLater(()->new LibrarySearchScreen().setVisible(true));}
}
