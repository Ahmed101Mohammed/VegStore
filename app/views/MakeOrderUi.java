package app.views;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import app.abstractData.Cacher;
import app.abstractData.Order;
import app.abstractData.Product;
import app.abstractData.SubOrder;
import app.controllers.MakeOrderController;

public class MakeOrderUi implements IObserver{
    private static MakeOrderUi makeOrderUi;
    private Cacher cacher;
    private MakeOrderController controller = MakeOrderController.createMakeOrderController();
    private JFrame window;
    private JPanel mainBody;
    private JButton backButton;
    private ArrayList<ProductUi> productsCards = new ArrayList<>();
    private JButton sellButton;

    // Main method for testing:
    public static void main(String[] args) {
        Cacher cacher = new Cacher("ali", "1234");
        cacher.setId(6);
        MakeOrderUi makeOrderUi = MakeOrderUi.createMakeOrderUi(cacher);
        makeOrderUi.buildBaseUi();
    }

    public void update(){}

    public static MakeOrderUi createMakeOrderUi(Cacher cacher)
    {
        if(makeOrderUi == null)
        {
            makeOrderUi = new MakeOrderUi();
        }
        makeOrderUi.setCacher(cacher);
        return makeOrderUi;
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

    private MakeOrderUi(){}

    private void setCacher(Cacher cacher)
    {
        this.cacher = cacher;
    }

    private void baseUi()
    {
        this.prepareWindow();
        this.prepareMainBody();
    }

    private void prepareWindow()
    {
        this.window = new JFrame();
        this.window.setTitle("Make Order");
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.setSize(1800, 1000);
        this.window.setResizable(false);
        this.window.setLocationRelativeTo(null);
        this.window.setVisible(true);

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
            }
        });

        JPanel buttom = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 5));
        JButton reset = new JButton("Reset");
        reset.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.sellButton = new JButton("Sell");
        this.sellButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttom.add(this.sellButton);
        buttom.add(reset);
        this.sellButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                Order order = new Order(cacher.getId());
                for(ProductUi productUi:productsCards)
                {
                    if(productUi.getItemNumbers() > 0)
                    {
                        Product product = productUi.getProduct();
                        int quantity = productUi.getItemNumbers();

                        SubOrder subOrder = new SubOrder(product.getId(), quantity, quantity*product.getPrice());
                        order.addSubOrder(subOrder);
                    }
                }

                if(order.getSubOrders().size() > 0)
                {
                    Order fullDataOrder = controller.saveComplateOrderUnit(order);
                    RecieptUi recieptUi = RecieptUi.createRecieptUi(cacher, fullDataOrder);
                    recieptUi.buildBaseUi();
                    // Appear ReceptUi and not closing this window.
                }
            }
        });

        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                for (ProductUi productUi:productsCards)
                {
                    productUi.resetItemNumbers();
                }
            }
        });
        

        this.mainBody = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 30));

        this.window.add(top, BorderLayout.NORTH);
        this.window.add(buttom, BorderLayout.SOUTH);
        this.window.add(this.mainBody, BorderLayout.CENTER);
    }

    public void prepareMainBody()
    {
        ArrayList<Product> products = controller.getAllProducts();
        for (Product product:products)
        {
            System.out.println("From source: " + product.getId());
            this.productsCards.add(new ProductUi(product));
        }
        
        for (ProductUi productUi:this.productsCards)
        {
            System.out.println("Item Num: " + productUi.getItemNumbers());
            this.mainBody.add(productUi.buildBaseUi());
        }
    }
}
