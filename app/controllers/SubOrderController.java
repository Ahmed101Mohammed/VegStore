package app.controllers;

import app.abstractData.Product;
import app.abstractData.SubOrder;
import app.models.SubOrderModel;

public class SubOrderController {
    private static SubOrderController subOrderController;
    private SubOrderModel model = SubOrderModel.creatSubOrderModel();
    // private null view;

    // main method to test:
    public static void main(String[] args) {
        //ProductController productController = ProductController.createProductController();
        //Product product = productController.getProductWithId(1);

        SubOrder subOrder = new SubOrder(1, 20, 20*10);
        subOrder.setId(0);

        SubOrderController subOrderController = SubOrderController.createSubOrderController();
        subOrderController.addNewSubOrder(subOrder);
    }

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

}
