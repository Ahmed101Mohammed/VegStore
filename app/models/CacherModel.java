package app.models;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.abstractData.Cacher;

public class CacherModel extends Observable{
    private MainModel mainModel = MainModel.createMainModel();
    private static CacherModel cacherModel;
    
    private CacherModel()
    {
        mainModel.buidProjectDB();
    }

    public static CacherModel createCacherModel()
    {
        if(cacherModel == null)
        {
            cacherModel =  new CacherModel();
        }
        return cacherModel;
    }

    public void addNewCacher(Cacher cacher)
    {
        String insertCacherQuery = "INSERT INTO cachers(name, password) VALUES(?,?);";
        mainModel.connectToDB();
        try
        {
            PreparedStatement insert = mainModel.connectRef.prepareStatement(insertCacherQuery);
            insert.setString(1, cacher.getName());
            insert.setString(2, cacher.getPassword());
            insert.executeUpdate();    
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            System.out.println("Field to save new cacher.");
        }
        
    }

    public ResultSet getAllCachers()
    {
        String getAllCachersQuery = "SELECT * FROM cachers;";
        mainModel.connectToDB();
        try
        {
            Statement getAllCachers = mainModel.connectRef.createStatement();
            ResultSet resultSet = getAllCachers.executeQuery(getAllCachersQuery);
            return resultSet;
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            System.out.println("Field to get all cachers from DB.");
            return null;
        }
        
    }

    public ResultSet getCacherWithId(int id)
    {
        String getCacherWithId = "SELECT * FROM cachers WHERE id = "+ id +";";
        mainModel.connectToDB();
        try
        {
            Statement getCacherWithIdQuery = mainModel.connectRef.createStatement();
            ResultSet resultSet = getCacherWithIdQuery.executeQuery(getCacherWithId);
            return resultSet;
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return null;
        }
        
    }

    public ResultSet getCacherId(Cacher cacher)
    {
        String getIdOfCacher = "SELECT id FROM cachers WHERE name LIKE \""+ cacher.getName() +"\" AND password LIKE \"" + cacher.getPassword() + "\";";
        mainModel.connectToDB();
        try
        {
            Statement getIdOfCacherQuery = mainModel.connectRef.createStatement();
            ResultSet resultSet = getIdOfCacherQuery.executeQuery(getIdOfCacher);
            return resultSet;
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
