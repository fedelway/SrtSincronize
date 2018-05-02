import UI.MainForm;

import java.nio.charset.Charset;

public class Application {

    public static void main(String[] args){
        MainForm form = new MainForm();

        try{
            form.show();
        }catch(Exception ex){
            return;
        }
    }
}
