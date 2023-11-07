package app.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.abstractData.Product;

public class ProductUi {
    private int itemsNumber = 0;
    private JLabel numElement;
    private Product product;

    public ProductUi(Product product)
    {
        this.product = product;
    }

    public JPanel buildBaseUi()
    {
        // maint card
        JPanel card = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        card.setBackground(Color.LIGHT_GRAY);
        card.setPreferredSize(new Dimension(200,300));
        
        // image
        JLabel productImage = new JLabel();
        ImageIcon makeOrderIcon = new ImageIcon(this.product.getImagePath());
        productImage.setIcon(makeOrderIcon);
        card.add(productImage);

        // info
        JLabel info = new JLabel(this.product.getName() + " | " + this.product.getPrice()+"$");
        card.add(info);

        // adding and remove:
        this.numElement = new JLabel(this.itemsNumber+"");
        JPanel controllers = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JButton minus = new JButton("-");
        minus.setCursor(new Cursor(Cursor.HAND_CURSOR));
        minus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if(itemsNumber > 0)
                {
                    itemsNumber -= 1;
                    numElement.setText(itemsNumber+"");
                }
            }
        });

        JButton plus = new JButton("+");
        plus.setCursor(new Cursor(Cursor.HAND_CURSOR));
        plus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                itemsNumber += 1;
                numElement.setText(itemsNumber+"");
            }
        });

        controllers.add(minus);
        controllers.add(this.numElement);
        controllers.add(plus);

        card.add(controllers);

        return card;
    }

    public int getItemNumbers()
    {
        return this.itemsNumber;
    }

    public Product getProduct() {
        return product;
    }

    public void resetItemNumbers()
    {
        this.itemsNumber = 0;
        this.numElement.setText("0");
    }

}
