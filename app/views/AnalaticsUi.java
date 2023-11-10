package app.views;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import app.abstractData.Cacher;
import app.controllers.AnalaticsController;

public class AnalaticsUi {
    private Cacher cacher;
    private static AnalaticsUi analaticsUi;
    private AnalaticsController controller = AnalaticsController.createAnalaticsController();
    private JFrame window;
    private JPanel mainBody;
    private JButton backButton;

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

    private void baseUi()
    {
        this.prepareWindow();
        //this.prepareMainBody();
    }
    
    private void prepareWindow()
    {
        this.window = new JFrame();
        this.window.setTitle("Services");
        this.window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.window.setSize(800, 600);
        this.window.setResizable(false);
        this.window.setLocationRelativeTo(null);
        this.window.setVisible(true);

        // Continue...
    }
}
