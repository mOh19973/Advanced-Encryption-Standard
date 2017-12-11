import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.swing.*;
import java.awt.*;
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
    private JLabel CharactersKeyLabel;

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
    private String separate(String s){
        s= s.replaceAll("..", "$0 ");
        StringBuilder sb = new StringBuilder(s);
        sb.replace(11,12,"\n");
        sb.replace(23,24,"\n");
        return sb.replace(35,36,"\n").toString();
    }


    private ApplicationGUI()
    {

        encryptButton.addActionListener(e -> {
            if (textField1.getText().length()==16 && textField2.getText().length()!=0) {
                int max=128;
                if (comboBox1.getSelectedItem().toString().equals("192-bit"))
                    max=192;
                else if(comboBox1.getSelectedItem().toString().equals("256-bit"))
                {
                    max=256;
                }
                if(max==128 && textField2.getText().length()==32) {
                    if(textField2.getText().contains("A") || textField2.getText().contains("B") || textField2.getText().contains("C") || textField2.getText().contains("D") || textField2.getText().contains("E") || textField2.getText().contains("F")) {
                        AES aes = new AES(toHex(textField1.getText()), textField2.getText(), max);
                        JOptionPane.showMessageDialog(null, separate(aes.encrypt(toHex(textField1.getText()), textField2.getText())));
                    }
                    else JOptionPane.showMessageDialog(null, "The key has an invalid hex character.");
                /*JFrame f = new JFrame("JTable Sample");
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Container content = f.getContentPane();
                Object rows[][] = { { aes.encrypt(toHex(textField1.getText()), textField2.getText()).substring(0,2), "Amazon", "67 9/16" },
                        { "AOL", "America Online", "68 3/4" },
                        { "BOUT", "About.com", "56 3/8" },
                        { "CDNW", "CDnow", "4 7/16" },
                        { "DCLK", "DoubleClick", "87 3/16" },
                        { "EBAY", "eBay", "180 7/8" },
                        { "EWBX", "EarthWeb", "18 1/4" },
                        { "MKTW", "MarketWatch", "29" },
                        { "TGLO", "Theglobe.com", "4 15/16" },
                        { "YHOO", "Yahoo!", "151 1/8" } };
                Object columns[] = { "Symbol", "Name", "Price" };
                JTable table = new JTable(rows, columns);
                JScrollPane scrollPane = new JScrollPane(table);
                content.add(scrollPane, BorderLayout.CENTER);
                f.setSize(300, 200);
                f.setVisible(true);*/
                }
                else if(max==192 && textField2.getText().length()==48)
                {
                    if(textField2.getText().contains("A") || textField2.getText().contains("B") || textField2.getText().contains("C") || textField2.getText().contains("D") || textField2.getText().contains("E") || textField2.getText().contains("F")) {
                        AES aes = new AES(toHex(textField1.getText()), textField2.getText(), max);
                        JOptionPane.showMessageDialog(null, separate(aes.encrypt(toHex(textField1.getText()), textField2.getText())));
                    }
                    else JOptionPane.showMessageDialog(null, "The key has an invalid hex character.");
                }
                else if(max==256 && textField2.getText().length()==64)
                {
                    if(textField2.getText().contains("A") || textField2.getText().contains("B") || textField2.getText().contains("C") || textField2.getText().contains("D") || textField2.getText().contains("E") || textField2.getText().contains("F")) {
                        AES aes = new AES(toHex(textField1.getText()), textField2.getText(), max);
                        JOptionPane.showMessageDialog(null, separate(aes.encrypt(toHex(textField1.getText()), textField2.getText())));
                    }
                    else JOptionPane.showMessageDialog(null, "The key has an invalid hex character.");
                }
                else JOptionPane.showMessageDialog(null, "Key has wrong size.");
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
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                CharactersLabel.setText("Characters Remaining: " + (16 - textField1.getText().length()));
            }
        });
        comboBox1.addActionListener(e -> {
            CharactersKeyLabel.setText("Characters Remaining: " + (32 - textField2.getText().length()));
            if(comboBox1.getSelectedItem().toString().equals("192-bit"))
                CharactersKeyLabel.setText("Characters Remaining: " + (48 - textField2.getText().length()));
            else if(comboBox1.getSelectedItem().toString().equals("256-bit"))
                CharactersKeyLabel.setText("Characters Remaining: " + (64 - textField2.getText().length()));
        });
        textField2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if(comboBox1.getSelectedItem().toString().equals("128-bit"))
                    CharactersKeyLabel.setText("Characters Remaining: " + (32 - textField2.getText().length()));
                else if(comboBox1.getSelectedItem().toString().equals("192-bit"))
                    CharactersKeyLabel.setText("Characters Remaining: " + (48 - textField2.getText().length()));
                else if(comboBox1.getSelectedItem().toString().equals("256-bit"))
                    CharactersKeyLabel.setText("Characters Remaining: " + (64 - textField2.getText().length()));
            }
        });
        textField2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(comboBox1.getSelectedItem().toString().equals("128-bit"))
                    CharactersKeyLabel.setText("Characters Remaining: " + (32 - textField2.getText().length()));
                else if(comboBox1.getSelectedItem().toString().equals("192-bit"))
                    CharactersKeyLabel.setText("Characters Remaining: " + (48 - textField2.getText().length()));
                else if(comboBox1.getSelectedItem().toString().equals("256-bit"))
                    CharactersKeyLabel.setText("Characters Remaining: " + (64 - textField2.getText().length()));

            }
        });
        textField1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CharactersLabel.setText("Characters Remaining: " + (16 - textField1.getText().length()));
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
