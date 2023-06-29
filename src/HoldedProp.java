import java.sql.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HoldedProp extends JFrame {

    private JTable table;
    static HoldedProp frame;
    static String v1,v2,v3;
    String input;
    ResultSet rs;
    String fac;
   static final String url= "jdbc:mysql://localhost:3306/projectestate";
        static final    String username = "root";
          static final  String password = "ABHI@singh1301";
      //   static final  Connection conn;

    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
                    v1=args[0];
                    // v2=args[1];
                    // v3=args[2];
					frame = new HoldedProp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

//    public static void update(String propertyid)
//    {
//     try{
//     Class.forName("com.mysql.cj.jdbc.Driver");
//    Connection  conn = DriverManager.getConnection(url, username, password);

//     // Create a statement and execute the SQL query
//     Statement stmt = conn.createStatement();
//     String query2="update property set status='Sold' where p_id='" + propertyid + "'";
//     stmt.executeUpdate(query2);
//     stmt.close();
//     conn.close();

//     }
//     catch(Exception ex)
//     {
//         System.out.println(ex);
        
//     }
//    }


    public HoldedProp() {
        setTitle("HOLDED PROPERTY");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        // setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        // Create the first panel with the JTable
        JPanel tablePanel = new JPanel(new BorderLayout());
        // v1="11";

        try {
            // Connect to the database
            
         Connection conn = DriverManager.getConnection(url, username, password);

            // Create a statement and execute the SQL query
            Statement stmt = conn.createStatement();
            String query ="select P_id,flat_type,p_name,p_type,sell_rent,p_area,y_o_c,s_price_r_price,p_address,status from property,manages where property.p_id=manages.pr_id and ag_id='" + v1 + "'";

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

            // Add the scroll pane to the table panel
            tablePanel.add(scrollPane, BorderLayout.CENTER);

          //  Close the database connection and statement
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


        // Get the selection model of the table
ListSelectionModel selectionModel = table.getSelectionModel();

// Add a ListSelectionListener to the selection model
selectionModel.addListSelectionListener((ListSelectionListener) new ListSelectionListener() {
    public void valueChanged(ListSelectionEvent e) {
        // Get the index of the selected row
        int selectedRow = table.getSelectedRow();
        fac=(String) table.getValueAt(selectedRow,0);
    }
});
// try{
//         JButton submit = new JButton("Continue");
//         submit.setFocusable(false);
//         submit.setBounds(300, 30, 110,25);
//         submit.addActionListener(new ActionListener() {
//             public void actionPerformed(ActionEvent e) {
//                 // input=textField.getText();
               
//                 System.out.println(fac);
//                 // frame.dispose();
//                 if(fac==null)
//                 {
//                 JOptionPane.showMessageDialog(null,"Please Select a Property to continue","Warning",JOptionPane.WARNING_MESSAGE);
//                 }
//                 else
//                 {
//                     // update(fac);
//                     JOptionPane.showMessageDialog(null,"Property Sold","Information",JOptionPane.INFORMATION_MESSAGE);   
//                      }
//         }
//         });
//         textPanel.add(submit);
    // }
    // catch (Exception ex) {
    //     ex.printStackTrace();
    // }
    

        // JLabel label = new JLabel("Select a Property and click on continue to confirm that it has been sold:");
        // label.setFont(new Font("Arial", Font.BOLD, 14));
        // label.setBounds(10, 5,500, 25);
        // textPanel.add(label);
        // textPanel.add(textField);

        // Add the two panels to the frame
        getContentPane().add(tablePanel, BorderLayout.CENTER);
        getContentPane().add(textPanel, BorderLayout.SOUTH);

        // Create the back button and add it to the text panel
        JButton btnBack = new JButton("Back");
        btnBack.setBounds(700, 6, 80,40);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                // System.out.println(fac);
            }
        });
        textPanel.add(btnBack);
       
        
    }
}
