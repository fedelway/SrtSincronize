import UI.MainForm;

import javax.swing.*;
import java.nio.charset.Charset;

public class Application {

    public static void main(String[] args){

        try{
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());

            String crossName = UIManager.getCrossPlatformLookAndFeelClassName();
            String systemName = UIManager.getSystemLookAndFeelClassName();

            int b = 1 + 1;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        MainForm form = new MainForm();

        try{
            form.show();
        }catch(Exception ex){
            return;
        }
    }
}
