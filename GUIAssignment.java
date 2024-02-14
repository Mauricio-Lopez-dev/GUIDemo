/* Author: Mauricio Lopez Alvarez               **
 * Date written: February 14th, 2024            **
 * Purpose:... Building a simple GUI calculator **
 */

package cop2805;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.lang.NumberFormatException;


public class GUIAssignment 
{
    private static void constructGUI()
    {
        JFrame.setDefaultLookAndFeelDecorated(true);
        MyFrame frame = new MyFrame();
        frame.setVisible(true);
    } // end constructGUI method
        
    public static void main(String[] args) 
    {
      SwingUtilities.invokeLater(new Runnable()
      {
          @Override
          public void run()
          {
              constructGUI();
          }
      });
    } // end main  
} // end GUIAssignment class

class ButtonListener implements ActionListener
{
    MyFrame frame;
    
    public ButtonListener(MyFrame frame)
    {
        this.frame = frame;
    } // end constructor
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        try
        {
            // Variables 
            JButton button = (JButton) e.getSource();
            Double firstNum = Double.parseDouble(frame.firstNum.getText());
            Double secondNum = Double.parseDouble(frame.secondNum.getText());
            double solution = 0.0;
            String userSelection = frame.comboBox.getSelectedItem().toString();

            // Validation
            if(userSelection == "Add")
            {
                solution = firstNum + secondNum;
                frame.result.setText("<html><B>" + Double.toString(solution) + "</B></hmtl>");
            }
            else if(userSelection == "Subtract")
            {
                solution = firstNum - secondNum;
                frame.result.setText("<html><B>" + Double.toString(solution) + "</B></hmtl>");
            }
            else if(userSelection == "Multiply")
            {
                solution = firstNum * secondNum;
                frame.result.setText("<html><B>" + Double.toString(solution) + "</B></hmtl>");
            }
            else if(userSelection == "Divide")
            {
                if(secondNum > 0)
                {
                    solution = firstNum / secondNum;
                    frame.result.setText("<html><B>" + Double.toString(solution) + "</B></hmtl>");
                }
                else
                {
                    frame.result.setText("<html><B>Error: Cannot divide by zero</B><html>");
                }
          
            }
        }
        catch(NumberFormatException error)
        {
            JOptionPane.showMessageDialog(null, "Error: Please enter only numbers");
        }
        
    } // end actionPerformed
} // end ActionListener class

class MyFrame extends JFrame
{
    // Variables
    public JTextField firstNum;
    public JTextField secondNum;
    public JLabel result;
    public JComboBox comboBox;
    
    public MyFrame()
    {
        super();
        init();
    } // end default constructor
    
    private void init()
    {
        // Setting up basics
        JFrame frame = new JFrame();
        frame.setTitle("Simple Calcultor");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        firstNum = new JTextField();
        secondNum = new JTextField();
        result = new JLabel();
        
        // Creating JComboBox
        String[] selections = {"Add", "Subtract", "Multiply", "Divide"};
        comboBox = new JComboBox(selections);
       
        //Creating calulate button
        JButton button = new JButton("<html><B>Calculate</B></html>");
        button.addActionListener(new ButtonListener(this));
        
        // Setting frame
        this.setLayout(new GridLayout(5,2)); 
        // First row of items
        this.add(new JLabel("<html><B>First number:</B></html>"));
        this.add(firstNum);
        // Second row of items
        this.add(new JLabel("<html><B>Second number:</B></html>"));
        this.add(secondNum);
        // Third row of items
        this.add(new JLabel());
        this.add(comboBox);
        // Fourth row of items
        this.add(new JLabel());
        this.add(button);
        // Fifth row of items
        this.add(new JLabel("<html><B>Result:</B></html>"));
        this.add( result);
        
        // Setting size and positioning frame
        int frameWidth = 260;
        int frameHeight = 125;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(
                (int) (screenSize.getWidth() / 2) - frameWidth,
                (int) (screenSize.getHeight() / 2) - frameHeight,
                frameWidth,
               frameHeight);
    }// end init method
} // end MyFrame class
