package app.views;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import app.abstractData.Cacher;
import app.abstractData.Date;
import app.controllers.AnalaticsController;

public class AnalaticsUi {
    private Cacher cacher;
    private AnalaticsController controller = AnalaticsController.createAnalaticsController();
    private JFrame window;
    private JPanel mainBody;
    private JButton backButton;
    private HashMap<Cacher, Double> cachersAndTheirAllPricesForLastMonth = controller.getTotalOrdersPricesForEachCacherForSpecificPeriod(new Date(LocalDate.now()).lastMonthDate(), new Date(LocalDate.now()));

    public AnalaticsUi(Cacher cacher)
    {
        this.cacher = cacher;
    };

    public void setCacher(Cacher cacher) {
        this.cacher = cacher;
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
        this.window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.window.setSize(800, 600);
        this.window.setResizable(false);
        this.window.setLocationRelativeTo(null);
        this.window.setVisible(true);

        // back:
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 5));
        this.backButton = new JButton("Back");
        this.backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        top.add(this.backButton);
        this.backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                ServicesUi servicesUi = ServicesUi.createServicesUi(cacher);
                servicesUi.buildBaseUi();
                window.dispose();
                controller.closeConnectionToDB();
            }
        });

        // main:
        this.mainBody = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 5));
        this.window.add(this.mainBody, BorderLayout.CENTER);
        this.window.add(top, BorderLayout.NORTH);
    }

    private void prepareMainBody()
    {
        for (Map.Entry<Cacher, Double> entry : this.cachersAndTheirAllPricesForLastMonth.entrySet())
        {
            Cacher key = entry.getKey();
            Double value = entry.getValue();

            JLabel label = new JLabel("Cacher id: "+ key.getId() + " | Cacher name: " + key.getName() + " | Total Seles: " + value + "$");
            label.setPreferredSize(new Dimension(900,30));
            this.mainBody.add(label);
        }
        JLabel label = new JLabel("All seles in this month: " +this.controller.getTotalPriceForAllOrdersInSpecificPeriod(new Date(LocalDate.now()).lastMonthDate(),new Date(LocalDate.now()))+"$");
        this.mainBody.add(label);
    }
}
