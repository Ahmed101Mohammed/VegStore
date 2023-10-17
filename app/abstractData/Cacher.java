package app.abstractData;

public class Cacher
{
    private String name;
    private String password;
    private int id;

    public Cacher(String name, String password)
    {
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id)
    {
        this.id = id;
    }

}