import java.sql.*;
import java.util.Arrays;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExecuteQuery extends JFrame {

    private JTable table;
    static ExecuteQuery frame;
    String input;
    static String fac;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    fac=args[0];
                    frame = new ExecuteQuery();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ExecuteQuery() {
        setTitle("ADMIN THE BAAP");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setSize(600, 600);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        // Create the first panel with the JTable
        JPanel tablePanel = new JPanel(new BorderLayout());

        try {
            // Connect to the database
            String url = "jdbc:mysql://localhost:3306/projectestate";
            String username = "root";
            String password = "ABHI@singh1301";
            Connection conn = DriverManager.getConnection(url, username, password);

            // Create a statement and execute the SQL query
            Statement stmt = conn.createStatement();
            // String query = "SELECT * FROM property";



            ResultSet rs = stmt.executeQuery(fac);

            // Get the metadata of the result set to determine the number and names of columns
            ResultSetMetaData rsmd = rs.getMetaData();
            int numColumns = rsmd.getColumnCount();
            String[] columnNames = new String[numColumns];
            for (int i = 1; i <= numColumns; i++) {
                columnNames[i - 1] = rsmd.getColumnName(i);
            }

            // Create a two-dimensional array to hold the table data
            Object[][] data = new Object[0][numColumns];

            // Retrieve the table data from the result set
            int rowCount = 0;
            while (rs.next()) {
                Object[] row = new Object[numColumns];
                for (int i = 1; i <= numColumns; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                data = Arrays.copyOf(data, data.length + 1);
                data[rowCount] = row;
                rowCount++;
            }

            // Create the JTable with the retrieved data and column names, and add it to a scroll pane
            table = new JTable(data, columnNames);
            JScrollPane scrollPane = new JScrollPane(table);

            // Add the scroll pane to the table panel
            tablePanel.add(scrollPane, BorderLayout.CENTER);

            // Close the database connection and statement
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // Create the second panel with a text box
            JPanel textPanel = new JPanel();
            textPanel.setLayout(null);
            textPanel.setPreferredSize(new Dimension(0, 50)); // Set preferred height to 200 pixels

        // Add the two panels to the frame
        getContentPane().add(tablePanel, BorderLayout.CENTER);
        getContentPane().add(textPanel, BorderLayout.SOUTH);

        // Create the back button and add it to the text panel
        JButton btnBack = new JButton("Back");
        btnBack.setBounds(1400, 8, 80,30);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                RunQuery.main(new String[]{});

            }
        });
        textPanel.add(btnBack);
    }
}
