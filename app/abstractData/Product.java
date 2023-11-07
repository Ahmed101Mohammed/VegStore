package app.abstractData;

public class Product {
    private String name;
    private String imagePath;
    private int id;
    private double price;

    public Product(String name, String imagePath, double price)
    {
        this.name = name;
        this.imagePath = imagePath;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setId(int id)
    {
        this.id = id;
    }
}
