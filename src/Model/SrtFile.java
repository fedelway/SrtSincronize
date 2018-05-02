package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SrtFile {

    List<Subtitle> subtitleList = new ArrayList<Subtitle>();

    public void addSub(Subtitle sub){
        subtitleList.add(sub);
    }

    public void writeToFile(File file, long sync) throws IOException {

        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));

        for(int i=0; i<subtitleList.size(); i++){
            Subtitle sub = subtitleList.get(i);
            sub.start += sync;
            sub.end += sync;

            writer.write( String.valueOf(i+1) );
            writer.newLine();

            writer.write(sub.getTimeStamp());
            writer.newLine();

            writer.write(sub.text);
            writer.newLine();
            writer.newLine();
        }

        writer.flush();
    }

}
