package app.abstractData;

import java.util.ArrayList;

public class Order {
    private int id;
    private int cacherId;
    private double price = 0;
    private ArrayList<SubOrder> subOrders = new ArrayList<>();
    private int date = new Date(null).getIntValueOfDate();

    public Order(int cacherId)
    {
        this.id = id;
        this.cacherId = cacherId;
    }

    public void addSubOrder(SubOrder subOrder)
    {
        this.subOrders.add(subOrder);
        this.price += subOrder.getTotalPrice();
    }

    public int getCacherId() {
        return cacherId;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public ArrayList<SubOrder> getSubOrders() {
        return subOrders;
    }

    public int getDate() {
        return date;
    }

    public void setId(int id)
    {
        this.id = id;
    }

}
