package pudic.alkolator;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Calculation {

    public static SimpleDateFormat CALCULATION_DATE_FORMAT = new SimpleDateFormat("HH:mm");

    private String time;
    private double bac;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTime(Date date){
        time = CALCULATION_DATE_FORMAT.format(date);
    }
    public double getBac() {
        return bac;
    }

    public void setBac(double bac) {
        this.bac = bac;
    }

    public Calculation(String time, double bac) {
        this.time = time;
        this.bac = bac;
    }
    public Calculation(Date time, double bac) {
        setTime(time);
        this.bac = bac;
    }
}
