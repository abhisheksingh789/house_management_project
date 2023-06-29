import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.sql.*;

public class ContactAgent extends JFrame {
    // JFrame f1 = new JFrame();
    static ContactAgent frame;
    JLabel titlelable, nameLabel,idlabel, contactLabel, addressLabel,emaiLabel;
    JButton button1, b2;
    JPopupMenu popupMenu;
     static String id;
     long A_contact;
     ResultSet resultSet,resultSet1;
     String A_name,A_email;


    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
                    id=args[0];
					frame = new ContactAgent();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

    ContactAgent(){

        Connection connection = null;
        // id="106c";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectestate", "root", "ABHI@singh1301");
            Statement st;
            st = connection.createStatement();

            
            String query=" select A_name,A_email,A_contact_no from agent,manages where A_id=AG_id and Pr_id='" + id + "' ";
            resultSet = st.executeQuery(query);
            resultSet.next();

            // A_id = resultSet.getInt("A_id");
             A_contact =resultSet.getLong("A_contact_no");
            A_name = resultSet.getString("A_name");
            A_email = resultSet.getString("A_email");
           

            st.close();
            connection.close();
        } catch (Exception exception) {
            System.out.println(exception);
        }


        // Create labels for each field-
        titlelable = new JLabel("You May Contact");
        titlelable.setBounds(110, 30,400, 25);
        titlelable.setFont(new Font("Arial", Font.BOLD, 20));

        nameLabel = new JLabel("Name: "+A_name);
        nameLabel.setBounds(30, 100,400, 18);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));

        contactLabel = new JLabel();
        contactLabel.setBounds(30, 130,400, 16);
        contactLabel.setFont(new Font("Arial", Font.BOLD, 16));
        contactLabel.setText("Contact no:  "+Long.toString(A_contact));

        emaiLabel = new JLabel("Email Id  :"+A_email);
        emaiLabel.setBounds(30, 160,400, 20);
        emaiLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // addressLabel = new JLabel("Address  :"+A_address);
        // addressLabel.setBounds(30, 240,550, 20);
        // addressLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(220, 300, 100, 25);
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 17));
        btnBack.setFocusable(false);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			frame.dispose();
			}
		});


        
        add(titlelable);
        add(nameLabel);
        add(contactLabel);
        add(emaiLabel);
        add(btnBack);

        // Set frame properties
        setTitle("Agent Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(null);
        setVisible(true);
    }
}
