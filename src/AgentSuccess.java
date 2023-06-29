import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AgentSuccess extends JFrame {
    // JFrame f1 = new JFrame();
    static AgentSuccess frame;
    JLabel titlelable, nameLabel,idlabel, contactLabel, addressLabel,emaiLabel,
            totalPropertiesLabel, soldPropertiesLabel,request;
    JButton button1, b2;
    JPopupMenu popupMenu;
    static String username,pass;
     ResultSet resultSet,resultSet1;
     int A_id,p_count,p_count1;
     long A_contact;
     String A_name,A_email,A_address,A_username;


    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
                    username=args[0];
                    pass=args[1];
					frame = new AgentSuccess();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

    AgentSuccess(){

        Connection connection = null;
        // username="amandev";
        // pass="password8";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectestate", "root", "ABHI@singh1301");
            Statement st;
            st = connection.createStatement();
            System.out.println(username);
            System.out.println(pass);

            
            String query="Select * from agent where A_username ='" + username + "' and A_password ='" + pass + "' ";
            resultSet = st.executeQuery(query);
            resultSet.next();

            A_id = resultSet.getInt("A_id");
            A_contact = resultSet.getLong("A_contact_no");
            A_name = resultSet.getString("A_name");
            A_email = resultSet.getString("A_email");
            A_address = resultSet.getString("A_address");
            A_username = resultSet.getString("A_username");


            String query1= "select count(P_name) from property,manages where property.p_id=manages.pr_id and ag_id='" + A_id + "'";
            resultSet1 = st.executeQuery(query1);
            resultSet1.next();

            p_count=resultSet1.getInt(1);

            String query2="select count(AGE_id) from deals,property where property.status='available' and deals.prop_id=property.P_id and deals.AGE_id ='" + A_id + "'";
            resultSet1 = st.executeQuery(query2);
            resultSet1.next();
            p_count1=resultSet1.getInt(1);
            // A_password = resultSet.getString("A_password");
            // System.out.println(p_count);
        
           

            st.close();
            connection.close();
        } catch (Exception exception) {
            System.out.println(exception);
        }


        // Create labels for each field-
        titlelable = new JLabel("Welcome");
        titlelable.setBounds(230, 30,400, 25);
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
        totalPropertiesLabel.setText("Total properties holded:  "+Integer.toString(p_count));
        totalPropertiesLabel.setBounds(30, 320,550, 16);
        totalPropertiesLabel.setFont(new Font("Arial", Font.BOLD, 16));

        

        
        JButton btnNewButton = new JButton("Show");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
        btnNewButton.setBounds(250, 320, 80, 20);
        btnNewButton.setFocusable(false);
		btnNewButton.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                HoldedProp.main(new String[]{Integer.toString(A_id)});
			    // frame.dispose();
			}
		});


        // soldPropertiesLabel = new JLabel();
        // soldPropertiesLabel.setText("Total properties Sold:  "+Integer.toString(p_count));
        // soldPropertiesLabel.setBounds(30, 350,550, 16);
        // soldPropertiesLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // JButton btnNewButton1 = new JButton("Show");
        // btnNewButton1.setBounds(250, 350, 80, 20);
		// btnNewButton1.setFont(new Font("Tahoma", Font.PLAIN, 17));
        // btnNewButton1.setFocusable(false);
		// btnNewButton1.addActionListener((ActionListener) new ActionListener() {
		// 	public void actionPerformed(ActionEvent e) {
		// 	SoldProp.main(new String[]{});
		// 	// frame.dispose();
		// 	}
		// });


        request = new JLabel();
        request.setText("Buyer contacted you: "+Integer.toString(p_count1));
        request.setBounds(30, 350,550, 18);
        request.setFont(new Font("Arial", Font.BOLD, 18));

        JButton btnNewButton2 = new JButton("Show");
        btnNewButton2.setBounds(250, 350, 80, 20);
		btnNewButton2.setFont(new Font("Tahoma", Font.PLAIN, 17));
        btnNewButton2.setFocusable(false);
		btnNewButton2.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			RequestProp.main(new String[]{Integer.toString(A_id)});
			// frame.dispose();
			}
		});

        JButton btnBack = new JButton("Logout");
        btnBack.setBounds(220, 450, 130, 35);
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
        // add(soldPropertiesLabel);
        add(request);
        add(btnNewButton);
        // add(btnNewButton1);
        add(btnNewButton2);
        add(btnBack);
       
        // Set frame properties
        setTitle("Agent Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 700);
        setLayout(null);
        setVisible(true);
    }
}
