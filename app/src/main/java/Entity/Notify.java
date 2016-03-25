package Entity;

import com.orm.SugarRecord;

/**
 * Created by msajaynath on 19/03/16.
 */
public class Notify extends SugarRecord {
    public String date,period;
    public  Boolean notified;

    // You must provide an empty constructor
    public Notify(String date,String period,Boolean notified) {
        this.date=date;
        this.period=period;
        this.notified=notified;

    }

}
