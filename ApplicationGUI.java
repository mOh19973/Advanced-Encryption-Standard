import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.swing.*;
import java.awt.event.*;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

public class ApplicationGUI {
    private JPanel panelMain;
    private JTextField textField1;
    private JTextField textField2;
    private JButton encryptButton;
    private JComboBox comboBox1;
    private JButton generateButton;
    private JLabel CharactersLabel;

    private String generateKey(int max) throws NoSuchAlgorithmException {
        KeyGenerator gen = KeyGenerator.getInstance("AES");
        gen.init(max); /* 128-bit or 192 or 256 */
        SecretKey secret = gen.generateKey();
        byte[] binary = secret.getEncoded();
        return String.format("%032X", new BigInteger(+1, binary));
    }
    private String toHex(String arg) {
        return String.format("%040x", new BigInteger(1, arg.getBytes(/*YOUR_CHARSET?*/)));
    }

    private ApplicationGUI()
    {


        encryptButton.addActionListener(e -> {
            if (textField1.getText().length()==16 && textField2.getText().length()!=0) {
                AES aes = new AES(toHex(textField1.getText()), textField2.getText());
                aes.encrypt(toHex(textField1.getText()), textField2.getText());
            }
            else if(textField2.getText().length()==0)
                JOptionPane.showMessageDialog(null, "No key was entered");
            else
                JOptionPane.showMessageDialog(null, "Message is required to be 16 characters");
        });

        generateButton.addActionListener(e -> {
            try {
                if(comboBox1.getSelectedItem().toString().equals("128-bit"))
                textField2.setText(generateKey(128));
                else if (comboBox1.getSelectedItem().toString().equals("192-bit"))
                    textField2.setText(generateKey(192));
                else textField2.setText(generateKey(256));
            } catch (NoSuchAlgorithmException e1) {
                e1.printStackTrace();
            }
        });


        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                CharactersLabel.setText("Characters Remaining: " + (15 - textField1.getText().length()));
            }
        });
    }
    public static void main(String[] args){
        JFrame frame=new JFrame("App");
        frame.setContentPane(new ApplicationGUI().panelMain);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
