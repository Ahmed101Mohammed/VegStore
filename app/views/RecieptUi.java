package app.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import app.abstractData.Order;
import app.abstractData.Product;
import app.abstractData.SubOrder;
import app.controllers.ProductController;

public class RecieptUi {
    private static RecieptUi recieptUi;
    private Order order;
    private JFrame window;
    private JButton printButton;
    private JPanel mainBody;
    private ProductController controller = ProductController.createProductController();

    public static RecieptUi createRecieptUi(Order order)
    {
        if(recieptUi == null)
        {
            recieptUi = new RecieptUi();
        }
        recieptUi.setOrder(order);
        recieptUi.controller.connectToDB();
        return recieptUi;
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

    private RecieptUi(){}

    private void setOrder(Order order) {
        this.order = order;
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

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 5));
        this.printButton = new JButton("Print");
        southPanel.add(this.printButton);

        this.mainBody = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 10));
        
        this.window.add(southPanel, BorderLayout.SOUTH);
        this.window.add(this.mainBody, BorderLayout.CENTER);
    }

    private void prepareMainBody()
    {
        for(SubOrder subOrder:this.order.getSubOrders())
        {
            Product product = controller.getProductWithId(subOrder.getProductId());
            String productName = product.getName();
            int quantity = subOrder.getQuantity();
            double unitPrice = product.getPrice();
            double total = subOrder.getTotalPrice();

            JLabel label = new JLabel("Product: " + productName + " | Quantity: " + quantity + " | Unit Price: " + unitPrice + " | Total:" + total);
            label.setPreferredSize(new Dimension(900,13));
            this.mainBody.add(label);
        }

        JLabel cacherLabel = new JLabel("Cacher Id: " + this.order.getCacherId());
        cacherLabel.setPreferredSize(new Dimension(900,13));
        JLabel totalOrderPrice = new JLabel(("Total Order Price: " + this.order.getPrice()));
        this.mainBody.add(cacherLabel);
        this.mainBody.add(totalOrderPrice); 
        this.closeConnectionToDB();
    }

    public void closeConnectionToDB()
    {
        this.controller.closeConnectionToDB();
    }

    // Complete: you need to test the reciept and connect it with sell buttom. firstly commit every thing in git.
}
