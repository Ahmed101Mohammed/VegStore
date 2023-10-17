package app.models;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainModel extends Observable{
    protected Connection connectRef;
    private String dbFilePath = "jdbc:sqlite:vegStore.db";

    public void buidProjectDB()
    {
        String[] appTables = new String[4];
            appTables[0] = "CREATE TABLE IF NOT EXISTS cachers (\n"
            +  "    id INTEGER PRIMARY KEY NOT NULL,\n"
            +  "    name TEXT NOT NULL,\n"
            +  "    password TEXT NOT NULL);";

            appTables[1] = "CREATE TABLE IF NOT EXISTS products (\n"
            +  "    id INTEGER PRIMARY KEY NOT NULL,\n"
            +  "    name TEXT NOT NULL,\n"
            +  "    price real NOT NULL,\n"
            +  "    image_path TEXT NOT NULL);";

            appTables[2] = "CREATE TABLE IF NOT EXISTS orders (\n"
            +  "    id INTEGER PRIMARY KEY NOT NULL,\n"
            +  "    cacher_id INTEGER NOT NULL,\n"
            +  "    price real NOT NULL,\n"
            +  "    date INTEGER NOT NULL);";

            appTables[3] = "CREATE TABLE IF NOT EXISTS sub_orders (\n"
            +  "    id INTEGER PRIMARY KEY NOT NULL,\n"
            +  "    order_id INTEGER NOT NULL,\n"
            +  "    product_id INTEGER NOT NULL,\n"
            +  "    quantity INTEGER NOT NULL,\n"
            +  "    total_price real NOT NULL);";

        this.connectToDB();
        this.createTablesInDB(appTables);
    }

    private void connectToDB()
    {
        try
        {
            this.connectRef = DriverManager.getConnection(this.dbFilePath);
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to connect with DB.");
        }
    }

    private void createTablesInDB(String[] tablesCreationQueries)
    {
        try
        {
            Statement createTableStatement = this.connectRef.createStatement();
            createTableStatement.execute(tablesCreationQueries[0]);
            createTableStatement.execute(tablesCreationQueries[1]);
            createTableStatement.execute(tablesCreationQueries[2]);
            createTableStatement.execute(tablesCreationQueries[3]);
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to Create project DB tables.");  
        }
    }
}
