package app.views;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import app.abstractData.Cacher;
import app.controllers.CacherController;

public class CacherSignUi implements IObserver{
    private static CacherSignUi cacherSignUi;
    private CacherController controller = CacherController.createCacherController();
    private JFrame window;
    private JTextField userNameInput;
    private JPasswordField passwordInput;
    private JButton signButton;
    
    // main method to test:
    public static void main(String[] args) {
        CacherSignUi cacherSignUi = CacherSignUi.createCacherSignUi();
        cacherSignUi.buildBaseUi();
    }

    private CacherSignUi(){this.controller.connectToDB();}

    public static CacherSignUi createCacherSignUi()
    {
        if(cacherSignUi == null)
        {
            cacherSignUi = new CacherSignUi();
        }
        return cacherSignUi;
    }

    public void update(){}

    public void buildBaseUi()
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run()
            {
                baseUi();
            }
        });
    }

    private void baseUi()
    {
        this.prepareWindow();
        this.prepareMainBody();
    }

    private void prepareWindow()
    {
        this.window = new JFrame();
        this.window.setTitle("Sign Cacher");
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.setSize(800, 600);
        this.window.setResizable(false);
        this.window.setLocationRelativeTo(null);
        this.window.setVisible(true);
        
        JPanel northPanel = new JPanel();
        northPanel.setPreferredSize(new Dimension(100, 100));
        JPanel southPanel = new JPanel();
        southPanel.setPreferredSize(new Dimension(100, 100));
        JPanel eastPanel = new JPanel();
        eastPanel.setPreferredSize(new Dimension(100, 100));
        JPanel westPanel = new JPanel();
        westPanel.setPreferredSize(new Dimension(100, 100));

        this.window.add(northPanel, BorderLayout.NORTH);
        this.window.add(southPanel, BorderLayout.SOUTH);
        this.window.add(eastPanel, BorderLayout.EAST);
        this.window.add(westPanel, BorderLayout.WEST);  
    }

    private void prepareMainBody()
    {
        // Prepere main panel:
        JPanel mainPanel = new JPanel(new GridLayout(3, 1, 100 , 100));
        this.window.add(mainPanel, BorderLayout.CENTER);

        // Prapere lables and inputs fields:
        JLabel userNameLabel = new JLabel("Cacher Name:");
        this.userNameInput = new JTextField();
        this.userNameInput.setSize(50, 20);
        this.userNameInput.setFont(new Font(Font.SANS_SERIF, Font.CENTER_BASELINE, 20));

        JLabel passwordLabel = new JLabel("Password:");
        this.passwordInput = new JPasswordField();
        this.passwordInput.setFont(new Font(Font.SANS_SERIF, Font.CENTER_BASELINE, 15));

        // Prapere Sign buttom:
        this.signButton = new JButton("Sign");
        this.signButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.signButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                String cacherName = userNameInput.getText();
                char[] cacherPassword = passwordInput.getPassword();
                String cacherPasswordString = new String(cacherPassword);
                Cacher cacher = new Cacher(cacherName, cacherPasswordString);
                Cacher cacherFullData = controller.sign(cacher);
                window.dispose();
                System.out.print(cacherFullData.getName() + " "+ cacherFullData.getId());
                ServicesUi servicesUi = ServicesUi.createServicesUi(cacherFullData);
                servicesUi.buildBaseUi();
                controller.closeConnectionToDB();
            }
        });
        // add to panel:
        mainPanel.add(userNameLabel);
        mainPanel.add(this.userNameInput);
        mainPanel.add(passwordLabel);
        mainPanel.add(this.passwordInput);
        mainPanel.add(this.signButton);
    }



    
}
