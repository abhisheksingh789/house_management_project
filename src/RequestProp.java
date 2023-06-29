import java.sql.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RequestProp extends JFrame {

    private JTable table;
    static RequestProp frame;
    static String v1,v2,v3;
    String Dateinput;
    ResultSet rs;
    String selectedP_id,input;
    int selectedB_id;
    int Priceinput;
    JTextField PriceField,DateField;
    static int count=0;
    static int T_id;
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
					frame = new RequestProp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


    public static void updateprop(String propertyid)
    {
     try{
     Class.forName("com.mysql.cj.jdbc.Driver");
    Connection  conn = DriverManager.getConnection(url, username, password);
 
     // Create a statement and execute the SQL query
     Statement stmt = conn.createStatement();
     String query2="update property set status='Sold' where p_id='" + propertyid + "'";
     stmt.executeUpdate(query2);
     stmt.close();
     conn.close();
 
     }
     catch(Exception ex)
     {
         System.out.println(ex);
         
     }
    }


    public static void update(String propertyid,int price,String Date,int B_id)
    {
     try{
         
     Class.forName("com.mysql.cj.jdbc.Driver");
    Connection  conn = DriverManager.getConnection(url, username,password);
 
     // Create a statement and execute the SQL query
     Statement stmt = conn.createStatement();
     ResultSet resultSet;
     String query2="select AG_id from manages where pr_id='" + propertyid + "'";
     resultSet = stmt.executeQuery(query2);
     resultSet.next();
     int A_id=resultSet.getInt(1);
    //  System.out.println(A_id);

    resultSet = stmt.executeQuery("select * from transaction");
			

            while (resultSet.next()) 
			{
            count++;
            }


            T_id=count+1;
 
      String SQL = "insert into transaction" +"(prop_id,T_id,S_price_R_price,sell_Rent_date,agent_id,buyer_id)" +"VALUES('"+propertyid+"','"+T_id+"','"+price+"','"+Date+"','"+A_id+"','"+B_id+"')";		
     stmt.executeUpdate(SQL);
     stmt.close();
     conn.close();
 
     }
     catch(Exception ex)
     {
         System.out.println(ex);
         
     }
    }

    public RequestProp() {
        setTitle("JTable From Database");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        // setExtendedState(JFrame.MAXIMIZED_BOTH);
        // setLocationRelativeTo(null);

        // Create the first panel with the JTable
        JPanel tablePanel = new JPanel(new BorderLayout());
        // v1="18";

        try {
            // Connect to the database
            
         Connection conn = DriverManager.getConnection(url, username, password);

            // Create a statement and execute the SQL query
            Statement stmt = conn.createStatement();
            String query =" select B_id,B_name,B_email,B_contact_no,B_address,prop_id,status from buyer,deals,property where age_id='"+ v1 +"' and status='Available' and buyer.b_id=deals.bu_id and deals.prop_id=property.p_id;";

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
            textPanel.setPreferredSize(new Dimension(0, 200)); // Set preferred height to 200 pixels


        // Get the selection model of the table
ListSelectionModel selectionModel = table.getSelectionModel();

// Add a ListSelectionListener to the selection model
selectionModel.addListSelectionListener((ListSelectionListener) new ListSelectionListener() {
    public void valueChanged(ListSelectionEvent e) {
        // Get the index of the selected row
        int selectedRow = table.getSelectedRow();
        selectedP_id=(String) table.getValueAt(selectedRow,5);
        selectedB_id=(int) table.getValueAt(selectedRow,0);
    }
});
try{
        JButton submit = new JButton("Continue");
        submit.setFocusable(false);
        submit.setBounds(300, 150, 110,25);
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                input=PriceField.getText();
                Dateinput=DateField.getText();
                Priceinput = Integer.parseInt(input);
               
                System.out.println(selectedB_id);
                // frame.dispose();
                if(input.isBlank()||Dateinput.isBlank())
                {
                    JOptionPane.showMessageDialog(null,"Please enter Price and Date","Warning",JOptionPane.WARNING_MESSAGE);

                }
                else
                {
                if(selectedP_id==null)
                {
                JOptionPane.showMessageDialog(null,"Please Select a Property to continue","Warning",JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    update(selectedP_id,Priceinput,Dateinput,selectedB_id);
                    updateprop(selectedP_id);
                    JOptionPane.showMessageDialog(null,"Property Sold with transaction id: "+T_id,"Information",JOptionPane.INFORMATION_MESSAGE);  
                    submit.setEnabled(false);   
                 }
        }
    }
        });
        textPanel.add(submit);
    }
    catch (Exception ex) {
        ex.printStackTrace();
    }

    JLabel Pricelabel = new JLabel("Enter Price: ");
    Pricelabel.setFont(new Font("Arial", Font.BOLD, 14));
    Pricelabel.setBounds(20, 50,500, 25);
    textPanel.add(Pricelabel);

    JLabel Datelabel = new JLabel("Enter Date: ");
    Datelabel.setFont(new Font("Arial", Font.BOLD, 14));
    Datelabel.setBounds(20, 90,500, 25);
    textPanel.add(Datelabel);
    // textPanel.add(textField);
    

        JLabel label = new JLabel("Select a Property and click on continue to confirm that it has been sold:");
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setBounds(10, 0,500, 25);
        textPanel.add(label);


        PriceField=new JTextField();
        PriceField.setBounds(120, 50, 200,25);
        textPanel.add(PriceField);

        DateField=new JTextField();
        DateField.setBounds(120, 90, 200,25);
        textPanel.add(DateField);

       

        // Add the two panels to the frame
        getContentPane().add(tablePanel, BorderLayout.CENTER);
        getContentPane().add(textPanel, BorderLayout.SOUTH);

        // Create the back button and add it to the text panel
        JButton btnBack = new JButton("Back");
        btnBack.setBounds(700, 100, 80,40);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                // System.out.println(fac);
            }
        });
        textPanel.add(btnBack);
        setVisible(true);
       
        
    }
}
