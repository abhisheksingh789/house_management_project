import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OfficeSuccess extends JFrame {
   static OfficeSuccess frame;
   private JButton button1, button2, button3,button4,button5;
   private JLabel label;

   public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new OfficeSuccess();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

   public OfficeSuccess() {
      // Set up the JFrame
      setTitle("Office Success");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(480, 400);
      setLayout(null);

      // Create the buttons
      button1 = new JButton("Show All Agent");
      button1.setBounds(120, 50, 200, 30);

      button2 = new JButton("Add Agent");
      button2.setBounds(120, 100, 200, 30);

      button3 = new JButton("Delete Agent");
      button3.setBounds(120, 150, 200, 30);

      button4 = new JButton("Show All Transaction");
      button4.setBounds(120, 200, 200, 30);

      button5 = new JButton("Logout");
      button5.setBounds(120, 250, 200, 30);

      // Add action listeners to the buttons
      button1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            ShowAllAgent.main(new String[]{});
            frame.dispose();
         }
      });
      button2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            AddAgent.main(new String[]{});
            frame.dispose();
         }
      });
      button3.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            // Perform the action for button3
            DeleteAgent.main(new String[]{});
            frame.dispose();
         }
      });

      button4.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            // Perform the action for button3
            AllTransactions.main(new String[]{});
              frame.dispose();
         }
      });

      button5.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            // Perform the action for button3
            frame.dispose();
         }
      });

      // Create the label with a heading
      label = new JLabel("Select A Option");
      label.setBounds(140, 15, 400, 25);
      label.setFont(new Font("Arial", Font.BOLD, 20));

      // Add the buttons and label to the JFrame
      add(button1);
      add(button2);
      add(button3);
      add(button4);
      add(button5);
      add(label);

      // Display the JFrame
      setVisible(true);
   }
}
