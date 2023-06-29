import java.sql.*;
import java.util.Arrays;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PurchasedProp extends JFrame {

    private JTable table;
    static PurchasedProp frame;
    static String fac;

    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
                    fac=args[0];
					frame = new PurchasedProp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}




    public PurchasedProp() {
        setTitle("Purchased property");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);


        JButton btnBack = new JButton("Back");
        btnBack.setBounds(450, 500, 100, 30);
        btnBack.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        frame.dispose();
        }
        });

    this.add(btnBack);

        try {
            // Connect to the database
            String url = "jdbc:mysql://localhost:3306/projectestate";
            String username = "root";
            String password = "ABHI@singh1301";
            Connection conn = DriverManager.getConnection(url, username, password);

            // Create a statement and execute the SQL query
            Statement stmt = conn.createStatement();
            String query = "select P_id,flat_type,P_name,P_type,sell_rent,P_area,Y_O_C,transaction.S_price_R_price as Price,P_address,Status from property,transaction where transaction.prop_id=property.p_id and buyer_id='" + fac + "'";
            ResultSet rs = stmt.executeQuery(query);

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

            // Add the scroll pane to the frame
            getContentPane().add(scrollPane, BorderLayout.CENTER);

            // Close the database connection and statement
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
