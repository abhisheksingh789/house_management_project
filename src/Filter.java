import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Filter extends JFrame {
   // private JTextField textField;
   private JButton button;
   private String inputText;
   private JLabel label,label1,label2,label3;
   static Filter frame;
   JComboBox combobox,combobox2,combobox3;
   String C1,C2,C3;


   public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Filter();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

   public Filter() {
      // Set up the JFrame
      setTitle("Text Field Example");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(500, 400);
      setLayout(null);


      String[] area={"select","Paltan Bazaar","Panbazar","Gorchuk","Beltola","Six Mile","Hathigaon","Maligaon","Silpukhuri","Kahilipara",
      "Rehabari","Cristian Basti","Bhangagarh","Chandmari","Ganeshguri","Basistha Chariali","Dispur"};
      combobox= new JComboBox(area);
      combobox.setBounds(205, 110, 150, 20);

		combobox.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			// frame.dispose();
          C1=(String)combobox.getSelectedItem();
         //  System.out.println(combobox.getSelectedIndex());
			}
		});


      String[] bhk={"select","1BHK", "2BHK", "3BHK", "4BHK"};
      combobox2=new JComboBox(bhk);
      combobox2.setBounds(205, 150, 150, 20);

		combobox2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			// frame.dispose();
         C2=(String)combobox2.getSelectedItem();
         //  System.out.println(combobox2.getSelectedItem());
         //  System.out.println(combobox2.getSelectedIndex());
			}
		});


      String[] price={"select","1000-20000", "21000-40000", "41000-60000", "61000-100000" ,"100000-50000000"};
      combobox3=new JComboBox(price);
      combobox3.setBounds(205, 190, 150, 20);

		combobox3.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			// frame.dispose();
         C3=(String)combobox3.getSelectedItem();
         //  System.out.println(combobox3.getSelectedItem());
         //  System.out.println(combobox3.getSelectedIndex());
			}
		});

      // Create the text field and button
      // textField = new JTextField(40);
      // textField.setBounds(100, 100, 400, 40);
      button = new JButton("Submit");
      button.setBounds(215, 235, 100, 30);

      button.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            // Get the text from the text field and store it in the inputText variable
            // inputText = textField.getText();
            FilterQuery.main(new String[]{C1,C2,C3});
            // ExecuteQuery.main(new String[]{C1});
            // System.out.println(C1);
            // checkstring.main(new String[]{C1,C2,C3});

            frame.dispose();
            
         }
      });

   
      JButton btnBack = new JButton("Back");
      btnBack.setBounds(350, 300, 100, 30);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			frame.dispose();
			}
		});

      // Add an action listener to the button
     

      // Create the label with a heading

      label = new JLabel("Apply Filter:");
      label.setBounds(30, 25, 400, 30);
      label.setFont(new Font("Arial", Font.BOLD, 25));

      label1 = new JLabel("Select Area:");
      label1.setBounds(50, 105, 400, 30);
      label1.setFont(new Font("Arial", Font.BOLD, 15));

      label2 = new JLabel("Select Flat type:");
      label2.setBounds(50, 145, 400, 30);
      label2.setFont(new Font("Arial", Font.BOLD, 15));

      label3 = new JLabel("Select Price:");
      label3.setBounds(50, 185, 400, 30);
      label3.setFont(new Font("Arial", Font.BOLD, 15));

      // Add the text field, button, and label to the JFrame
      add(label);
      add(label1);
      add(label2);
      add(label3);
      // add(textField);
      add(button);
      add(btnBack);
      add(combobox);
      add(combobox2);
      add(combobox3);

      // Display the JFrame
      setVisible(true);
   }
}
