package app.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductModel extends Observable{
    private MainModel mainModel = MainModel.createMainModel();
    private static ProductModel productModel;

    private ProductModel()
    {
        mainModel.buidProjectDB();
    }

    public static ProductModel createProductModel()
    {
        if(productModel == null)
        {
            productModel = new ProductModel();
        }

        return productModel;
    }

    public ResultSet getAllProducts()
    {
        String getAllProductsQuery = "SELECT * FROM products;";
        try
        {
            Statement getQuery = mainModel.connectRef.createStatement();
            ResultSet allProducts = getQuery.executeQuery(getAllProductsQuery);
            return allProducts;
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            System.out.println("Field to get all product from DB.");
            return null;
        }
    }

    public ResultSet getProductWithId(int id)
    {
        String getProductWithIdQuery = "SELECT * FROM products WHERE id = "+ id+ " ;";
        try
        {
            Statement getQuery = mainModel.connectRef.createStatement();
            ResultSet allProducts = getQuery.executeQuery(getProductWithIdQuery);
            return allProducts;
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            System.out.println("Field to get product from DB with this id: " + id + ".");
            return null;
        }
    }

    public void closeConnectionToDB()
    {
        this.mainModel.closeConnect();
    }

    public void connectToDB()
    {
        this.mainModel.connectToDB();
    }
}
