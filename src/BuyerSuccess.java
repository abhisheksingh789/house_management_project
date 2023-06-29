import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class BuyerSuccess extends JFrame {
    // JFrame f1 = new JFrame();
    static BuyerSuccess frame;
    JLabel titlelable, nameLabel,idlabel, contactLabel, addressLabel,emaiLabel,
            totalPropertiesLabel, soldPropertiesLabel;
    JButton button1, b2;
    JPopupMenu popupMenu;
     static String username,pass;
     ResultSet resultSet,resultSet1;
     int A_id,p_count;
     long A_contact;
     String A_name,A_email,A_address,A_username;


    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
                    username=args[0];
                    pass=args[1];
					frame = new BuyerSuccess();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


    BuyerSuccess(){

        Connection connection = null;
        // username="crvtbynum";
        // pass="rcfbyhn";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "abcd_2076");
            Statement st;
            st = connection.createStatement();

            
            String query="Select * from buyer where B_username ='" + username + "' and B_password ='" + pass + "' ";
            resultSet = st.executeQuery(query);
            resultSet.next();

            A_id = resultSet.getInt("B_id");
            A_contact = resultSet.getLong("B_contact_no");
            A_name = resultSet.getString("B_name");
            A_email = resultSet.getString("B_email");
            A_address = resultSet.getString("B_address");
            // A_username = resultSet.getString("A_username");


            String query1= " select count(t_id) from transaction where buyer_id='" + A_id + "'";
            resultSet = st.executeQuery(query1);
            resultSet.next();

            p_count=resultSet.getInt(1);
            // A_password = resultSet.getString("A_password");
            // System.out.println(p_count);
        
           

            st.close();
            connection.close();
        } catch (Exception exception) {
            System.out.println(exception);
        }


        // Create labels for each field-
        titlelable = new JLabel("Welcome");
        titlelable.setBounds(200, 30,400, 25);
        titlelable.setFont(new Font("Arial", Font.BOLD, 25));

        idlabel= new JLabel();
        idlabel.setBounds(30, 120, 400, 16);
        idlabel.setFont(new Font("Arial", Font.BOLD, 16));
        idlabel.setText("Id:     "+Integer.toString(A_id));

        nameLabel = new JLabel("Name: "+A_name);
        nameLabel.setBounds(30, 150,400, 16);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));

        contactLabel = new JLabel();
        contactLabel.setBounds(30, 180,400, 16);
        contactLabel.setFont(new Font("Arial", Font.BOLD, 16));
        contactLabel.setText("Contact no:  "+Long.toString(A_contact));

        emaiLabel = new JLabel("Email Id  :"+A_email);
        emaiLabel.setBounds(30, 210,400, 20);
        emaiLabel.setFont(new Font("Arial", Font.BOLD, 16));

        addressLabel = new JLabel("Address  :"+A_address);
        addressLabel.setBounds(30, 240,550, 20);
        addressLabel.setFont(new Font("Arial", Font.BOLD, 16));

        totalPropertiesLabel = new JLabel();
        totalPropertiesLabel.setText("Properties Purchased:  "+Integer.toString(p_count));
        totalPropertiesLabel.setBounds(30, 320,550, 16);
        totalPropertiesLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JButton btnNewButton = new JButton("Show");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
        btnNewButton.setBounds(250, 320, 80, 20);
        btnNewButton.setFocusable(false);
		btnNewButton.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    // frame.dispose();
                PurchasedProp.main(new String[]{Integer.toString(A_id)});

			}
		});


        JButton btnBack = new JButton("Logout");
        btnBack.setBounds(180, 420, 130, 35);
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 17));
        btnBack.setFocusable(false);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			frame.dispose();
			}
		});


        
        add(titlelable);
        add(nameLabel);
        add(idlabel);
        add(contactLabel);
        add(emaiLabel);
        add(addressLabel);
        add(totalPropertiesLabel);
        add(btnNewButton);
        add(btnBack);

        // Set frame properties
        setTitle("Buyer Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 550);
        setLayout(null);
        setVisible(true);
    }
}
