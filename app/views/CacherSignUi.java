package app.views;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import app.controllers.CacherController;

public class CacherSignUi implements IObserver{
    private static CacherSignUi cacherSignUi;
    private CacherController controller = CacherController.createCacherController();
    private JFrame window;
    private JTextField userNameInput;
    private JPasswordField passwordInput;
    private JButton signButton;
    
    private CacherSignUi(){}

    public CacherSignUi createCacherSignUi()
    {
        if(cacherSignUi == null)
        {
            cacherSignUi = new CacherSignUi();
        }
        return cacherSignUi;
    }

    public void update(){}

    public void buildBaseUi()
    {
        // continue
    }

    
}
