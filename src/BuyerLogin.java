import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.sql.*;


import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class BuyerLogin extends JFrame {
    static BuyerLogin frame;
    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;
    static final String DB_URL = "jdbc:mysql://localhost:3306/projectestate";
    static final String USER = "root";
    static final String PASS = "ABHI@singh1301";


    public static int auth(String username,String pwd) {
		Connection conn = null;
        PreparedStatement stmt = null;
        String SQL;
    
      
        ResultSet result = null;


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            SQL = "SELECT 1 FROM buyer WHERE B_username=? and B_password=?";
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, username);
            stmt.setString(2, pwd);
            result = stmt.executeQuery();
          
			if (result.next() && result.getString(1).equals("1")) {
				return 1;
			}
           
      
            stmt.close();
       }
       catch (ClassNotFoundException ex) {
        System.out.println("class not found, check the jar");
      } catch (SQLException ex) {
        System.out.println("SQL is BAD!!" + ex.getMessage());
      }


			return 0;
	}
    

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
         public void run() {
                try {
                    frame = new BuyerLogin();
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
    public BuyerLogin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        
        JLabel lblBuyerLoginForm = new JLabel("Buyer Login Form");
        lblBuyerLoginForm.setForeground(Color.GRAY);
        lblBuyerLoginForm.setFont(new Font("Tahoma", Font.PLAIN, 18));
        
        JLabel lblEnterName = new JLabel("Enter UserName:");
        
        JLabel lblEnterPassword = new JLabel("Enter Password:");
        
        textField = new JTextField();
        textField.setColumns(10);
        
        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            String name=textField.getText();
            String password=String.valueOf(passwordField.getPassword());
            if(auth(name,password)==1){
                BuyerSuccess.main(new String[]{name,password});
               frame.dispose();
            }
            // if(name.equals("admin")&&password.equals("admin123")){
            //     BuyerSuccess.main(new String[]{});
            //     frame.dispose();
            // }
            else{
                JOptionPane.showMessageDialog(BuyerLogin.this, "Sorry, Username or Password Error","Login Error!", JOptionPane.ERROR_MESSAGE);
                textField.setText("");
                passwordField.setText("");
            }
            }
        });

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(310, 200, 100, 30);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			frame.dispose();
			}
		});

		this.add(btnBack);
        
        passwordField = new JPasswordField();
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addGap(124)
                            .addComponent(lblBuyerLoginForm))
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addGap(19)
                            .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                .addComponent(lblEnterName)
                                .addComponent(lblEnterPassword))
                            .addGap(47)
                            .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(passwordField)
                                .addComponent(textField, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))))
                    .addContainerGap(107, Short.MAX_VALUE))
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addContainerGap(187, Short.MAX_VALUE)
                    .addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                    .addGap(151))
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addComponent(lblBuyerLoginForm)
                    .addGap(26)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblEnterName)
                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(28)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblEnterPassword)
                        .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(80, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
    }
}


