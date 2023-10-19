package app.models;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.abstractData.Cacher;

public class CacherModel extends Observable{
    private MainModel mainModel = MainModel.createMainModel();
    private static CacherModel cacherModel;
    // main method for just testing.
    public static void main(String[] args) {
        CacherModel cacherModel = CacherModel.createCacherModel();
        Cacher cacher = new Cacher("3asem", "3asem222");
        cacherModel.addNewCacher(cacher);
    }

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


}
