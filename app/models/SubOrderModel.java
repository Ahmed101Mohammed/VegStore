package app.models;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import app.abstractData.SubOrder;

public class SubOrderModel extends MainModel{
    private static SubOrderModel subOrderModel;
    
    // main method just for testing:
    public static void main(String[] args) {
        SubOrder subOrder = new SubOrder(0, 20, 200);
        subOrder.setOrderId(0);
        SubOrderModel subOrderModel = SubOrderModel.creatSubOrderModel();
        subOrderModel.addNewSubOrder(subOrder);    
    }

    private SubOrderModel()
    {
        this.buidProjectDB();
    }

    public static SubOrderModel creatSubOrderModel()
    {
        if(subOrderModel == null)
        {
            subOrderModel = new SubOrderModel();
        }
        return subOrderModel;
    }

    public void addNewSubOrder(SubOrder subOrder)
    {
        String insertSubOrderQuery = "INSERT INTO sub_orders(order_id, product_id, quantity, total_price) VALUES(?,?,?,?);";
        try
        {
            PreparedStatement insert = connectRef.prepareStatement(insertSubOrderQuery);
            insert.setInt(1, subOrder.getOrderId());
            insert.setInt(2, subOrder.getProductId());
            insert.setInt(3, subOrder.getQuantity());
            insert.setDouble(4, subOrder.getTotalPrice());
        
            insert.executeUpdate();     
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            System.out.println("Field to save new SubOrder.");
        }
    }
}
