package app.controllers;

import java.util.ArrayList;

import app.abstractData.Order;
import app.abstractData.Product;
import app.abstractData.SubOrder;
import app.views.MakeOrderUi;

public class MakeOrderController {
    private static MakeOrderController makeOrderController;
    private ProductController productController = ProductController.createProductController();
    private OrderController orderController = OrderController.createOrderController();
    private SubOrderController subOrderController = SubOrderController.createSubOrderController();
    
    private MakeOrderUi view; 

    private MakeOrderController(){}

    public static MakeOrderController createMakeOrderController()
    {
        if(makeOrderController == null)
        {
            makeOrderController = new MakeOrderController();
        }
        return makeOrderController;
    }

    public Order saveComplateOrderUnit(Order order)
    {
        // save order
        // set all subOrders orderId
        // seve all subOrders
        Order orderComplateData = orderController.addNewOrder(order);
        orderComplateData.setAllSubOrdersId();
        ArrayList<SubOrder> subOrders = orderComplateData.getSubOrders();

        for(SubOrder subOrder:subOrders)
        {
            System.out.println("Product id: "+ subOrder.getProductId());
            subOrderController.addNewSubOrder(subOrder);
        }

        return orderComplateData;
    }

    public ArrayList<Product> getAllProducts()
    {
        return productController.getAllProducts();
    }

    public void setView(MakeOrderUi makeOrderUi)
    {
        this.view = makeOrderUi;
    }
}
