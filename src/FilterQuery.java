import java.sql.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Class;


public class FilterQuery extends JFrame {

    private JTable table;
    static FilterQuery frame;
    static String v1,v2,v3;
    String input;
    ResultSet rs;
    String fac,status;

    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
                    v1=args[0];
                    v2=args[1];
                    v3=args[2];
					frame = new FilterQuery();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}




    public FilterQuery() {
        setTitle("List of all propeties");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setSize(600, 600);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        // Create the first panel with the JTable
        JPanel tablePanel = new JPanel(new BorderLayout());
        System.out.println(v1);

        try {
            // Connect to the database
           Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/projectestate";
            String username = "root";
            String password = "ABHI@singh1301";
            Connection conn = DriverManager.getConnection(url, username, password);

            // Create a statement and execute the SQL query
            Statement stmt = conn.createStatement();
            // String query = "SELECT * FROM property";

//-----------------------------------------------------filter Query-------------------------------------------------------------------------------
            if(v1==null||v1=="select")
            {
                if(v2==null||v2=="select")
                {
                    if(v3==null||v3=="select")
                    {
                        String query1 = "SELECT * FROM property";
                        rs = stmt.executeQuery(query1);
                        
                    }
                    else
                    {
                        String [] v33=v3.split("-");
                        String query2 = "SELECT * FROM property where S_price_R_price between '" + v33[0] + "' and '" + v33[1] + "'";
                        rs = stmt.executeQuery(query2);
                    }

                }
                else
                {
                    if(v3==null||v3=="select")
                    {
                        String query3 = "SELECT * FROM property where flat_type = '" + v2 + "'";
                        rs = stmt.executeQuery(query3);
                    }
                    else
                    {
                        String [] v33=v3.split("-");
                        String query4 = "SELECT * FROM property where flat_type = '" + v2 + "' and S_price_R_price between '" + v33[0] + "' and '" + v33[1] + "' ";
                        rs = stmt.executeQuery(query4);
                    }
                }
               
            }
            else
            {
                if(v2==null||v2=="select")
                {
                    if(v3==null||v3=="select")
                    {
                        String query4 ="SELECT * FROM property WHERE P_address LIKE '%" + v1 + "%'";
                        rs = stmt.executeQuery(query4);
                    }
                    else
                    {
                        String [] v33=v3.split("-");
                        String query5 ="SELECT * FROM property WHERE P_address LIKE '%" + v1 + "%' and S_price_R_price between '" + v33[0] + "' and '" + v33[1] + "'";
                        rs = stmt.executeQuery(query5);
                    }
                }
                else
                {
                    if(v3==null||v3=="select")
                    {
                        String query6 ="SELECT * FROM property WHERE P_address LIKE '%" + v1 + "%' and flat_type = '" + v2 + "' ";
                        rs = stmt.executeQuery(query6);
                    }
                    else
                    {
                        String [] v33=v3.split("-");
                        String query7 ="SELECT * FROM property WHERE P_address LIKE '%" + v1 + "%' and S_price_R_price between '" + v33[0] + "' and '" + v33[1] + "' and flat_type = '" + v2 + "' ";
                        rs = stmt.executeQuery(query7);
                    }
                }
            }



            // ResultSet rs = stmt.executeQuery(query);

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

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Create the second panel with a text box
            JPanel textPanel = new JPanel();
            textPanel.setLayout(null);
            textPanel.setPreferredSize(new Dimension(0, 70)); // Set preferred height to 200 pixels
        // 
        // JTextField textField = new JTextField();
        // textField.setBounds(170,30, 150, 20);


        // Get the selection model of the table
ListSelectionModel selectionModel = table.getSelectionModel();

// Add a ListSelectionListener to the selection model
selectionModel.addListSelectionListener((ListSelectionListener) new ListSelectionListener() {
    public void valueChanged(ListSelectionEvent e) {
        // Get the index of the selected row
        int selectedRow = table.getSelectedRow();
        fac=(String) table.getValueAt(selectedRow,0);
        status=(String) table.getValueAt(selectedRow,9);
    }
});

        JButton submit = new JButton("Continue");
        submit.setFocusable(false);
        submit.setBounds(380, 30, 110,25);
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // input=textField.getText();
                System.out.println(status);
                // frame.dispose();
                if(fac==null)
                {
                JOptionPane.showMessageDialog(null,"Please Select a Property to continue","Warning",JOptionPane.WARNING_MESSAGE);
                }
                else if(status.equalsIgnoreCase("Sold"))
                {
                JOptionPane.showMessageDialog(null,"This Property is already Sold","Warning",JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                BuyerSingup.main(new String[]{fac});
                }
            }
        });

        JLabel label = new JLabel("Select a Property and click on submit Button:");
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setBounds(30, 30,400, 25);
        textPanel.add(label);
        // textPanel.add(textField);

        // Add the two panels to the frame
        getContentPane().add(tablePanel, BorderLayout.CENTER);
        getContentPane().add(textPanel, BorderLayout.SOUTH);

        // Create the back button and add it to the text panel
        JButton btnBack = new JButton("Back");
        btnBack.setBounds(1400, 20, 80,40);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Filter.main(new String[]{});
                // System.out.println(fac);
            }
        });
        textPanel.add(btnBack);
        textPanel.add(submit);
    }
}
