package app.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.abstractData.Cacher;
import app.abstractData.Date;
import app.abstractData.Order;

public class OrderModel extends Observable{
    private MainModel mainModel = MainModel.createMainModel();
    private static OrderModel orderModel;

    private OrderModel()
    {
        mainModel.buidProjectDB();
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
            PreparedStatement insert = mainModel.connectRef.prepareStatement(insertOrderQuery);
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
            Statement getQuery = this.mainModel.connectRef.createStatement();
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
            Statement getQuery = this.mainModel.connectRef.createStatement();
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

    public ResultSet getAllOrdersCount()
    {
        String getAllOrdersCountQuery = "SELECT COUNT(*) AS count FROM orders";
        
        try
        {
            Statement getQuery = this.mainModel.connectRef.createStatement();
            ResultSet result = getQuery.executeQuery(getAllOrdersCountQuery);
            return result;
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void connectToDB()
    {
        this.mainModel.connectToDB();
    }

    public void closeConnectionToDB()
    {
        this.mainModel.closeConnect();
    }
}
