import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

public class ApplicationGUI {
    private JPanel panelMain;
    private JTextField textField1;
    private JTextField textField2;
    private JButton button1;
    private JComboBox comboBox1;

    public ApplicationGUI()
    {

        textField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InputStream text=System.in;
                text.toString();
            }
        });
    }
    public static void main(String[] args){
        JFrame frame=new JFrame("App");
        frame.setContentPane(new ApplicationGUI().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
