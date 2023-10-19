package app.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import app.abstractData.Cacher;
import app.abstractData.Date;
import app.abstractData.Order;
import app.models.OrderModel;

public class OrderController {
    private static OrderController orderController;
    private OrderModel model = OrderModel.createOrderModel();
    // private null view;

    private OrderController(){}

    public static OrderController createOrderController()
    {
        if(orderController == null)
        {
            orderController = new OrderController();
        }
        return orderController;
    }

    public Order addNewOrder(Order order)
    {
        this.model.addNewOrder(order);
        ResultSet idSet = this.model.getAllOrdersCount();

        try
        {
            order.setId(idSet.getInt("count"));
            return order;
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public double getTotalPriceOfOrdersWithCacherForSpecificPeriod(Cacher cacher, Date date1, Date date2)
    {
        double field = -1;
        ResultSet result = this.model.getTotalPriceForOrdersInSpeceficPeriodForSpecificCacher(date1, date2, cacher);
        try
        {
            if(result.next())
            {
                return result.getDouble("total");
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return field;
        }
        return field;
    }

    public double getTotalPriceOfOrdersWithSpecificPeriod(Date date1, Date date2)
    {
        double field = -1;
        ResultSet result = this.model.getTotalPriceForAllOrdersInSpecificPeriod(date1, date2);
        try
        {
            if(result.next())
            {
                return result.getDouble("total");
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return field;
        }
        return field;
    }

}
