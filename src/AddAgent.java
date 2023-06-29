import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

public class AddAgent extends JFrame {
	static  AddAgent frame;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
    private JTextField textField_5;
	private JPasswordField passwordField;
	static final String DB_URL = "jdbc:mysql://localhost:3306/projectestate";
	static final String USER = "root";
	static final String PASS = "ABHI@singh1301";

	/**
	 * Launch the application.
	 */
	public static int agent_exists(String id,String username) {
		Connection conn = null;

		//PreparedStatement stmt = null;
	//	String SQL;


		//ResultSet result = null;
		// long contact = Integer.parseInt(contactno); // Mark is an integer


		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement stmt = null;
			String SQL;
		
		  
			ResultSet result = null;
            
            SQL = "SELECT 1 FROM agent WHERE A_username=? or A_id=?";
            stmt = conn.prepareStatement(SQL);
			int parsedId = Integer.parseInt(id);
            stmt.setInt(2, parsedId);
            stmt.setString(1, username);
            result = stmt.executeQuery();
          
			if (result.next() && result.getString(1).equals("1")) {
				return -1;
			}
           
      
            stmt.close();
		} catch (ClassNotFoundException ex) {
			System.out.println("class not found, check the jar");
		} catch (SQLException ex) {
			System.out.println("SQL is BAD!!" + ex.getMessage());
		}

      return 1;
	}


	public static void insertnewagent(String id,String name,String password,String email,String address,String username,String contactno) {
		Connection conn = null;

		//PreparedStatement stmt = null;
		String SQL;


		//ResultSet result = null;
		// long contact = Integer.parseInt(contactno); // Mark is an integer


		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt=conn.createStatement();



			SQL = "insert into agent" +"(A_id,A_name,A_password,A_email,A_address,A_username,A_contact_no)" +"VALUES('"+id+"','"+name+"','"+password+"','"+email+"','"+address+"','"+username+"',"+contactno+")";
//			
			stmt.executeUpdate(SQL);
			stmt.close();
		} catch (ClassNotFoundException ex) {
			System.out.println("class not found, check the jar");
		} catch (SQLException ex) {
			System.out.println("SQL is BAD!!" + ex.getMessage());
		}


	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new AddAgent();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the frame.
	 */
	public AddAgent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		

		JLabel AddAgent = new JLabel("Add Agent");
		AddAgent.setForeground(Color.DARK_GRAY);
		AddAgent.setFont(new Font("Tahoma", Font.PLAIN, 22));

		JLabel lblId = new JLabel("Id:");

		JLabel lblName = new JLabel("Name:");

        JLabel lblContactNo = new JLabel("Contact No:");

        JLabel lblEmail = new JLabel("Email:");

        JLabel lblAddress = new JLabel("Address:");

		JLabel lblUsername = new JLabel("Username:");

        JLabel lblPassword = new JLabel("Password:");


		textField = new JTextField();
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setColumns(10);

        textField_5 = new JTextField();
		textField_5.setColumns(10);

		passwordField = new JPasswordField();

		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setFocusable(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id=textField_5.getText();
				String name=textField.getText();
				String password=String.valueOf(passwordField.getPassword());
				String email=textField_1.getText();
				String address=textField_2.getText();
				String username=textField_3.getText();
				String contact=textField_4.getText();
				if(id.isBlank()||name.isBlank()||password.isBlank()||email.isBlank()||address.isBlank()||username.isBlank()||contact.isBlank())
                {
                    JOptionPane.showMessageDialog(null,"Enter All Information","Warning",JOptionPane.WARNING_MESSAGE);

                }
				else
				{
				
			     if(agent_exists(id,username)==1)
				{
                 insertnewagent(id,name,password,email,address,username,contact);

				JOptionPane.showMessageDialog(null, "Inserted successfully!");
				frame.dispose();
				}
				else
				{
                   JOptionPane.showMessageDialog(null," Agent username/id already exist");
				}

			   }
			}
		});
		btnNewButton.setForeground(Color.DARK_GRAY);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OfficeSuccess.main(new String[]{});
				frame.dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(20)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblPassword, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
										.addComponent(lblName)
										.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblAddress, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblContactNo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblId, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGap(58)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(textField_4, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                        .addComponent(textField_5, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
										.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
										.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
										.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
										.addComponent(textField, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
										.addComponent(passwordField))
								.addContainerGap(107, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
								.addContainerGap(151, Short.MAX_VALUE)
								.addComponent(AddAgent)
								.addGap(144))
						.addGroup(gl_contentPane.createSequentialGroup()
								.addContainerGap(160, Short.MAX_VALUE)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
								.addGap(133))
						.addGroup(gl_contentPane.createSequentialGroup()
								.addContainerGap(200, Short.MAX_VALUE)
								.addComponent(btnBack)
								.addGap(169))
		);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(AddAgent)
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
                        .addComponent(lblId)
												.addGap(18)
												.addComponent(lblName)
												.addGap(18)
												.addComponent(lblPassword))
										.addGroup(gl_contentPane.createSequentialGroup()
                        .addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblEmail)
										.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblAddress)
										.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblUsername)
										.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblContactNo)
										.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
								.addComponent(btnBack)
								.addGap(19))
		);
		contentPane.setLayout(gl_contentPane);
		
	}
   
}
