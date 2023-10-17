package app.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.abstractData.Cacher;
import app.abstractData.Date;
import app.abstractData.Order;
import app.abstractData.Product;
import app.abstractData.SubOrder;

public class OrderModel extends MainModel{
    private static OrderModel orderModel;
    // main method for just testing:
    public static void main(String[] args) {
        Order order = new Order(1);
        Product product = new Product("Apple", "/apple.jpg", 10);
        product.setId(0);
        SubOrder subOrder = new SubOrder(0, 10, product.getPrice() * 10);
        order.addSubOrder(subOrder);
        
        OrderModel orderModel = OrderModel.createOrderModel();
        orderModel.addNewOrder(order);
    }

    private OrderModel()
    {
        this.buidProjectDB();
    }

    public static OrderModel createOrderModel()
    {
        if(orderModel == null)
        {
            orderModel = new OrderModel();
            return orderModel;
        }
        return orderModel;
    }

    public void addNewOrder(Order order)
    {
        String insertOrderQuery = "INSERT INTO orders(cacher_id, price, date) VALUES(?,?,?);";
        try
        {
            PreparedStatement insert = connectRef.prepareStatement(insertOrderQuery);
            insert.setInt(1, order.getCacherId());
            insert.setDouble(2, order.getPrice());
            insert.setInt(3, order.getDate());
            insert.executeUpdate();     
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            System.out.println("Field to save new order.");
        }
    }

    public ResultSet getTotalPriceForOrdersInSpeceficPeriodForSpecificCacher(Date date1, Date date2, Cacher cacher)
    {
        String getTotalPriceQuery = "SELECT SUM(price) AS total FROM orders WHERE cacher_id = " 
                                    + cacher.getId() + " AND date BETWEEN "
                                    + date1.getIntValueOfDate() +" AND " + date2.getIntValueOfDate() +";";
        try
        {
            Statement getQuery = this.connectRef.createStatement();
            ResultSet result = getQuery.executeQuery(getTotalPriceQuery);
            return result;
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            System.out.println("Field to get the all prices of order in time from "+ date1.getDateInStringForm() + " to " + date2.getDateInStringForm()
                                + " for cacher with this id: " + cacher.getId() + ".");
            return null;
        }
    }

    public ResultSet getTotalPriceForAllOrdersInSpecificPeriod(Date date1, Date date2)
    {
        String getTotalPriceQuery = "SELECT SUM(price) AS total FROM orders WHERE date BETWEEN "+ date1.getIntValueOfDate() +" AND "
                                    + date2.getIntValueOfDate() +";";
        try
        {
            Statement getQuery = this.connectRef.createStatement();
            ResultSet result = getQuery.executeQuery(getTotalPriceQuery);
            return result;
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            System.out.println("Field to get the all prices of all orders in period from "+ date1.getDateInStringForm() + " to " + date2.getDateInStringForm()
                                + ".");
            return null;
        }
    }



}
