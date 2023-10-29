package app.views;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import app.abstractData.Cacher;

public class ServicesUi{
    private static ServicesUi servicesUi;
    private Cacher cacher;
    private JFrame window;
    private JButton makeOrderServiceMoveButton;
    private JButton last30DaysAnalaysisMoveButton;
    private JButton backButton;

    // main method to test:
    public static void main(String[] args) {
        Cacher cacher = new Cacher("Ahmed", "####");
        cacher.setId(1);
        ServicesUi servicesUi = ServicesUi.createServicesUi(cacher);
        servicesUi.buildBaseUi();
    }
    private ServicesUi(){};
    public static ServicesUi createServicesUi(Cacher cacher)
    {
        if(servicesUi == null)
        {
            servicesUi = new ServicesUi();
        }
        servicesUi.setCacher(cacher);
        return servicesUi;
    }

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
        this.window.setTitle("Services");
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.setSize(800, 600);
        this.window.setResizable(false);
        this.window.setLocationRelativeTo(null);
        this.window.setVisible(true);
        
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 5));
        this.backButton = new JButton("Back");
        this.backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        northPanel.add(this.backButton);
        this.backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                CacherSignUi cacherSignUi = CacherSignUi.createCacherSignUi();
                cacherSignUi.buildBaseUi();
                window.dispose();
            }
        });

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
        JPanel middilPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.window.add(middilPanel, BorderLayout.CENTER);

        this.makeOrderServiceMoveButton = new JButton("Make Order");
        ImageIcon makeOrderIcon = new ImageIcon("cartIcon.png");
        this.makeOrderServiceMoveButton.setIcon(makeOrderIcon);
        this.makeOrderServiceMoveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        this.last30DaysAnalaysisMoveButton = new JButton("last 30D Analysis");
        ImageIcon last30DaysAnalysisIcon = new ImageIcon("chartIcon.png");
        this.last30DaysAnalaysisMoveButton.setIcon(last30DaysAnalysisIcon);
        this.last30DaysAnalaysisMoveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        middilPanel.add(this.makeOrderServiceMoveButton);
        middilPanel.add(this.last30DaysAnalaysisMoveButton);
    }

    private void setCacher(Cacher cacher)
    {
        this.cacher = cacher;
    }
}
