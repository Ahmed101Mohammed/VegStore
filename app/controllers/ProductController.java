package app.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import app.abstractData.Product;
import app.models.ProductModel;

public class ProductController {
    private static ProductController productController;
    private ProductModel model = ProductModel.createProductModel();
    
    private ProductController(){};

    public static ProductController createProductController()
    {
        if(productController == null)
        {
            productController = new ProductController();
        }
        return productController;
    }

    public ArrayList<Product> getAllProducts()
    {
        ArrayList<Product> products = new ArrayList<>();
        ResultSet result = this.model.getAllProducts();

        try
        {
            while(result.next())
            {
                Product product = new Product(result.getString("name"), 
                                                result.getString("image_path"), 
                                                result.getDouble("price"));
                product.setId(result.getInt("id"));
                products.add(product);
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return products;
    }

    public Product getProductWithId(int id)
    {
        try
        {
            ResultSet result = this.model.getProductWithId(id);
            if(result.next())
            {
                Product product = new Product(result.getString("name"), 
                                                result.getString("image_path"), 
                                                result.getDouble("price"));
                product.setId(result.getInt("id"));

                return product;
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return null;
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
