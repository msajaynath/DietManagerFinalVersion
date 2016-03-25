package Entity;

import com.orm.SugarRecord;

/**
 * Created by msajaynath on 19/03/16.
 */public class User extends SugarRecord {
    public String username,name,email;
    public int age;
    public String password;

    public boolean dis1,dis2,dis3,dis4;

    // You must provide an empty constructor
    public User() {}

}
