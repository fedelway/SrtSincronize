package Model;

public class Subtitle {

    public long start;
    public long end;
    public String text;

    public String getTimeStamp(){
        return createTimeStamp(start) + " --> " + createTimeStamp(end);
    }

    private String createTimeStamp(long time){

        Long milis = start%1000;
        Long secs = (start/1000)%100;
        Long mins = (start/100000)%100;
        Long hour = (start/10000000)%100;

        return hour.toString() + ":" + mins.toString() + ":" + secs.toString() + "," + milis;
    }
}
