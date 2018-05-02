package Model;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class SimpleFileFilter extends FileFilter {

    String ext;
    String desc;

    public SimpleFileFilter(String ext, String desc){
        this.ext = ext;
        this.desc = desc;
    }

    @Override
    public boolean accept(File f) {
        return f.getName().toLowerCase().endsWith(ext);
    }

    @Override
    public String getDescription() {
        return desc;
    }
}
