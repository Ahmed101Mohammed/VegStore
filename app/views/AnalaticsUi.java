package app.views;

import app.abstractData.Cacher;
import app.controllers.AnalaticsController;

public class AnalaticsUi {
    private Cacher cacher;
    private static AnalaticsUi analaticsUi;
    private AnalaticsController controller = AnalaticsController.createAnalaticsController();

    private AnalaticsUi(){};
    public static AnalaticsUi createAnalaticsUi(Cacher cacher)
    {
        if(analaticsUi != null)
        {
            analaticsUi = new AnalaticsUi();
        }
        analaticsUi.setCacher(cacher);
        return analaticsUi;
    }

    public void setCacher(Cacher cacher) {
        this.cacher = cacher;
    }
}
