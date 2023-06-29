import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RunQuery extends JFrame {
   private JTextField textField;
   private JButton button;
   private String inputText;
   private JLabel label;
   static RunQuery frame;


   public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new RunQuery();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

   public RunQuery() {
      // Set up the JFrame
      setTitle("Text Field Example");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(600, 400);
      setLayout(null);

      // Create the text field and button
      textField = new JTextField(40);
      textField.setBounds(100, 100, 400, 40);
      button = new JButton("Submit");
      button.setBounds(250, 150, 100, 30);

   
      JButton btnBack = new JButton("Back");
      btnBack.setBounds(450, 300, 100, 30);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			frame.dispose();
			}
		});

      // Add an action listener to the button
      button.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            // Get the text from the text field and store it in the inputText variable
            inputText = textField.getText();
            ExecuteQuery.main(new String[]{inputText});
            frame.dispose();
            
         }
      });

      // Create the label with a heading
      label = new JLabel("Enter your Query");
      label.setBounds(180, 60, 400, 30);
      label.setFont(new Font("Arial", Font.BOLD, 30));

      // Add the text field, button, and label to the JFrame
      add(label);
      add(textField);
      add(button);
      add(btnBack);

      // Display the JFrame
      setVisible(true);
   }
}
