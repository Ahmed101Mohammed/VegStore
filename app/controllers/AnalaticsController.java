package app.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import app.abstractData.Cacher;
import app.abstractData.Date;
import app.views.AnalaticsUi;

public class AnalaticsController {
    private static AnalaticsController analaticsController;
    private CacherController cacherController = CacherController.createCacherController();
    private OrderController orderController = OrderController.createOrderController();
    private AnalaticsUi analaticsUi;

    private AnalaticsController(){}

    public static AnalaticsController createAnalaticsController()
    {
        if(analaticsController == null)
        {
            analaticsController = new AnalaticsController();
        }
        return analaticsController;
    }

    public HashMap<Cacher, Double> getTotalOrdersPricesForEachCacherForSpecificPeriod(Date date1, Date date2)
    {
        HashMap<Cacher, Double> eachCachersWithTotalSells = new HashMap<>();
        ArrayList<Cacher> cachers = cacherController.getAllCachers();

        for(Cacher cacher:cachers)
        {
            Double totalSells = orderController.getTotalPriceOfOrdersWithCacherForSpecificPeriod(cacher, date1, date2);
            eachCachersWithTotalSells.put(cacher, totalSells);
        }

        return eachCachersWithTotalSells;
    }

    public double getTotalPriceForAllOrdersInSpecificPeriod(Date date1, Date date2)
    {
        return this.orderController.getTotalPriceOfOrdersWithSpecificPeriod(date1, date2);
    }

    public void closeConnectionToDB()
    {
        this.orderController.closeConnectionToDB();
        this.cacherController.closeConnectionToDB();
    }
}
