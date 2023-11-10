package app.controllers;

import app.abstractData.SubOrder;
import app.models.SubOrderModel;

public class SubOrderController {
    private static SubOrderController subOrderController;
    private SubOrderModel model = SubOrderModel.creatSubOrderModel();

    private SubOrderController(){}
    
    public static SubOrderController createSubOrderController()
    {
        if(subOrderController == null)
        {
            subOrderController = new SubOrderController();
        }
        return subOrderController;
    }

    public void addNewSubOrder(SubOrder subOrder)
    {
        this.model.addNewSubOrder(subOrder);
    }

    public void closeConnectionToDB()
    {
        this.model.closeConnectionToDB();
    }

    public void connectToDB()
    {
        this.model.connectToDB();
    }

}
