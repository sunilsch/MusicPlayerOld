package gui;

import javax.swing.*;
import java.awt.*;

public class loginWindow extends JFrame{
    private JPanel mainPanel;
    private JButton loginButton;
    private JTextField textField1;
    private JPasswordField passwordField1;

    public loginWindow(String title){
        super(title);
        initWindow();
    }

    private void initWindow(){
        this.setPreferredSize(new Dimension(1250,1000));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setVisible(true);
    }
}
