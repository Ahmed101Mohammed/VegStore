package app.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import app.views.CacherSignUi;
import app.abstractData.Cacher;
import app.models.CacherModel;

public class CacherController {
    private static CacherController cacherController = null; 
    private CacherModel model = CacherModel.createCacherModel();
    private CacherSignUi view;

    // main method to test:
    public static void main(String[] args) {
        CacherController cacherController = CacherController.createCacherController();
        ArrayList<Cacher> cachers = cacherController.getAllCachers();
        System.out.println(cachers.get(0).getPassword());
    }

    private CacherController()
    {
        //NoThingDone
    }

    public static CacherController createCacherController()
    {
        if(cacherController == null)
        {
            cacherController =  new CacherController();
        }
        return cacherController;
    }

    public Cacher gitCacherWithId(int id)
    {
        ResultSet result =  this.model.getCacherWithId(id);

        try
        {
            if(result.next())
            {
                Cacher cacher = new Cacher(result.getString("name"), "////");
                cacher.setId(result.getInt("id")); 
                return cacher;
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public ArrayList<Cacher> getAllCachers()
    {
        ResultSet results = this.model.getAllCachers();
        ArrayList<Cacher> cachers = new ArrayList<>();

        try
        {
            while(results.next())
            {
                Cacher cacher = new Cacher(results.getString("name"), "////");
                cacher.setId(results.getInt("id"));
                cachers.add(cacher);
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return cachers;
    }

    public Cacher addNewCacher(Cacher cacher)
    {
        this.model.addNewCacher(cacher);
        ResultSet id = this.model.getCacherId(cacher);
        Cacher returnCacher = new Cacher(cacher.getName(), "////");

        try
        {
            int cacherId = id.getInt("id");
            returnCacher.setId(cacherId);
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return returnCacher;
    }

    public Cacher sign(Cacher cacher)
    {
        ResultSet id = this.model.getCacherId(cacher);
        Cacher fullDataCacher = new Cacher(cacher.getName(), "////");

        try
        {
            if(id.next())
            {
                fullDataCacher.setId(id.getInt("id"));
                return fullDataCacher;
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return this.addNewCacher(cacher);
    }

    public void setView(CacherSignUi cacherSignUi)
    {
        this.view = cacherSignUi;
    }


}
