import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.lang.String;

public class DeleteAgent extends JFrame {
   private JTextField textField;
   private JTextField textField1;
   private JButton button;
   private String inputText;
   private String inputText1;
   private JLabel label;
   private JLabel label1;
   static DeleteAgent frame;
   static final String DB_URL = "jdbc:mysql://localhost:3306/projectestate";
	static final String USER = "root";
	static final String PASS = "ABHI@singh1301";

   public static void DeleteAndAssign(String del_id,String old_id )
   {
      Connection conn = null;

      try {
			Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String SQL;
         Statement st;
            st = conn.createStatement();
		
         // int delId = Integer.parseInt(del_id);
         // int oldId = Integer.parseInt(old_id);
			ResultSet result = null;
            
            SQL = "Update manages set AG_id='"+old_id+"' where AG_id='"+del_id+"'";
           
			   st.executeUpdate(SQL);
            // stmt.setInt(2, delId);
            // stmt.setInt(1, oldId);
            // stmt.executeQuery();
            System.out.println("manages update ho gya");


          //  SQL = "Update contact set AGEN_id=? where AGEN_id=? ";
            String SQL1 = "Update contact set AGEN_id='"+old_id+"' where AGEN_id='"+del_id+"'";
           
            st.executeUpdate(SQL1);
            System.out.println("contact update ho gya");
          

            String query="Select * from agent where A_id =' "+del_id+ "' ";
            result = st.executeQuery(query);
            result.next();

           int A_id = result.getInt("A_id");
           long A_contact = result.getLong("A_contact_no");
           String A_name = result.getString("A_name");
           String A_email = result.getString("A_email");
           String A_address = result.getString("A_address");
           String A_username = result.getString("A_username");
           String A_password = result.getString("A_password");
         
          String A_contact_str = Long.toString(A_contact);
          String A_ID=Integer.toString(A_id);

          //st.close();
         String add_del_agent;
           add_del_agent = "insert into Deleted_agent" +"(DA_id,DA_name,DA_password,DA_email,DA_address,DA_username,DA_contact_no)" +"VALUES(' "+A_ID+" ','"+A_name+"','"+A_password+"','"+A_email+"','"+A_address+"','"+A_username+"',"+A_contact_str+")";
		
			st.executeUpdate(add_del_agent);
         System.out.println("deleted agent delete table me chala gya");



         String del_del_agent="Delete from agent where A_id='"+del_id+"'";
         st.executeUpdate(del_del_agent);
         System.out.println("agent delete ho gya");
			//stmt.close();

           
      
            st.close();
		} 
      catch (Exception ex) {
			System.out.println("SQL is BAD!!" + ex.getMessage());
		}

      
	}
   

   


   public static int agent_exists(String id) {
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement stmt = null;
			String SQL;
		
		  
			ResultSet result = null;
            
            SQL = "SELECT 1 FROM agent WHERE  A_id=?";
            stmt = conn.prepareStatement(SQL);
			int parsedId = Integer.parseInt(id);
            stmt.setInt(1, parsedId);
            result = stmt.executeQuery();
          
			if (result.next() && result.getString(1).equals("1")) {
				return 1;
			}
           
      
            stmt.close();
		} catch (ClassNotFoundException ex) {
			System.out.println("class not found, check the jar");
		} catch (SQLException ex) {
			System.out.println("SQL is BAD!!" + ex.getMessage());
		}

      return -1;
	}
   public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new DeleteAgent();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

   public DeleteAgent() {
      // Set up the JFrame
      setTitle("Delete Agent Window");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(600, 400);
      setLayout(null);

      // Create the text field and button
      textField = new JTextField(40);
      textField.setBounds(100, 100, 200, 20);

      textField1 = new JTextField(40);
      textField1.setBounds(100, 190, 200, 20);


      button = new JButton("Confirm");
      button.setBounds(150, 250, 100, 30);

      // Add an action listener to the button
      button.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            // Get the text from the text field and store it in the inputText variable
            //inputText1="";
            inputText = textField.getText();
            inputText1 = textField1.getText();
            
            if( inputText.isEmpty()||inputText1.isEmpty())
            {
               System.out.println("chuitiya ho kya");
               JOptionPane.showMessageDialog(null,"*Please fill both fields*","Warning",JOptionPane.WARNING_MESSAGE);
            }
            else
            {
            // try {
               // Connection connection = null;
               // Class.forName("com.mysql.cj.jdbc.Driver");
               // connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectestate", "root", "ABHI@singh1301");
               // Statement st;
               // st = connection.createStatement();
               // ResultSet resultSet;
   
               
               
               if(agent_exists(inputText)==1)
               {
                 DeleteAndAssign(inputText,inputText1);
                 JOptionPane.showMessageDialog(null,"Deleted Successfully","Message",JOptionPane.INFORMATION_MESSAGE);
           // frame.dispose();
           // OfficeSuccess.main(new String[]{});
               }
               else
               {
                  JOptionPane.showMessageDialog(null,"NO SUCH AGENT EXISTS");
               }
              
              
   
         //       st.close();
         //       connection.close();
         //   } catch (Exception exception) {
         //       System.out.println(exception);
         //   }
            
            
         }  
         }
      });

      JButton btnBack = new JButton("Back");
      btnBack.setBounds(280, 250, 100, 30);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
         OfficeSuccess.main(new String[]{});
			frame.dispose();
			}
		});

      // Create the label with a heading
      label = new JLabel("Enter the id of Agent you want to delete:");
      label.setBounds(70, 60, 400, 20);
      label.setFont(new Font("Arial", Font.BOLD, 15));


      label1 = new JLabel("Enter the id of Agent you want to assign the deleted agent property:");
      label1.setBounds(70, 150, 500, 20);
      label1.setFont(new Font("Arial", Font.BOLD, 15));

      // Add the text field, button, and label to the JFrame
      add(label);
      add(label1);
      add(textField);
      add(textField1);
      add(button);
      add(btnBack);

      // Display the JFrame
      setVisible(true);
   }
}
