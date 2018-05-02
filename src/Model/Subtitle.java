package Model;

import com.google.common.base.Strings;

public class Subtitle {

    public long start;
    public long end;
    public String text;

    public String getTimeStamp(){
        return createTimeStamp(start) + " --> " + createTimeStamp(end);
    }

    private String createTimeStamp(long time){

        Long milis = time%1000;
        Long secs = (time/1000)%100;
        Long mins = (time/100000)%100;
        Long hour = (time/10000000)%100;

        return hour.toString() + ":" + mins.toString() + ":" + secs.toString() + "," + Strings.padStart(milis.toString(),3,'0');
    }
}
