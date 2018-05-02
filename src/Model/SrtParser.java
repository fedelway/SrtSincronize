package Model;

import com.google.gdata.util.io.base.UnicodeReader;

import java.io.*;

public class SrtParser {

    private enum ParseResult{
        PARSE_SUCCESS,
        PARSE_FAILED
    }

    public SrtFile parseFile(File file) throws IOException
    {
        SrtFile srt = new SrtFile();

        BufferedReader reader = new BufferedReader(
                //new InputStreamReader(new FileInputStream(file), "UTF-8"));
                new UnicodeReader(new FileInputStream(file),"UTF-8"));

        ParseResult res = parseIndividualSub(reader,srt);

        while( res == ParseResult.PARSE_SUCCESS ){
            res = parseIndividualSub(reader,srt);
        }

        reader.close();
        return srt;
    }

    private ParseResult parseIndividualSub(BufferedReader reader, SrtFile srt)
    {
        try{
            Subtitle sub;
            String line = reader.readLine();

            if( !isNumber(line) )
                return ParseResult.PARSE_FAILED;

            line = reader.readLine();
            sub = parseTimeStamps(line);

            line = reader.readLine();
            sub.text = line;

            line = reader.readLine();

            while( !line.isEmpty() ){
                sub.text += System.lineSeparator() + line;
                line = reader.readLine();
            }

            srt.addSub(sub);
        }
        catch(Exception ex){
            return ParseResult.PARSE_FAILED;
        }

        return ParseResult.PARSE_SUCCESS;
    }

    /* Construye un subtitle con los timestamps parseados */
    private Subtitle parseTimeStamps(String text){

        Subtitle ret = new Subtitle();

        String[] timestamps = text.split(" --> ");

        String start = timestamps[0];
        String end = timestamps[1];

        ret.start = parseTime(start);
        ret.end = parseTime(end);

        return ret;
    }

    private long parseTime(String text){

        long milis = 0;

        String[] parts = text.split(":");

        milis += Long.parseLong(parts[0]) * 1000 * 60 * 60;
                                        //  SEG    MIN  HOR

        milis += Long.parseLong(parts[1]) * 1000 * 60;

        String[] lastPart = parts[2].split(",");

        milis += Long.parseLong(lastPart[0]) * 1000;

        milis += Long.parseLong(lastPart[1]);

        return milis;
    }

    private boolean isNumber(String string){
        try{
            Integer.parseInt(string);
        }catch(NumberFormatException ex){
            return false;
        }
        return true;
    }
}
