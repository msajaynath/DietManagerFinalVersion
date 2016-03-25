package dietmanager.com.dietmanager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import Entity.Diet;
import Entity.Notify;
import Entity.User;

public class LoginActivity extends AppCompatActivity {

    Button login;
    EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        seedFunction();
        setContentView(R.layout.activity_login);
        login= (Button) findViewById(R.id.login);
        username= (EditText) findViewById(R.id.username);
        password= (EditText) findViewById(R.id.password);
        final SharedPreferences sharedpreferences = getSharedPreferences("Diet", Context.MODE_PRIVATE);
        if(sharedpreferences.getBoolean("logged",false))
        {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().length()==0||password.getText().toString().length()==0)
                {

                    Toast.makeText(getApplicationContext(),"Username/Password required!!",Toast.LENGTH_SHORT).show();
                    return;
                }
              List<User>log=  User.find(User.class, "username = ? and password = ?", username.getText().toString(), password.getText().toString());
                if(log.size()==0) {
                    Toast.makeText(getApplicationContext(),"Invalid credentials!!",Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getApplicationContext(),"Welcome "+log.get(0).name,Toast.LENGTH_SHORT).show();
                sharedpreferences.edit().putBoolean("logged", true).commit();
                sharedpreferences.edit().putLong("userid", log.get(0).getId()).commit();

                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                Intent myIntent = new Intent(LoginActivity.this , NotificationService.class);
                startService(myIntent);
                finish();
            }
        });


    }



    private void seedFunction() {
        List<String> dis1breakfast=Arrays.asList("Orange Juice", "Berries", "Cereals");
        List<String> dis1lunch=Arrays.asList("Salad","Tomato soup","Kiwi Yogurt");
        List<String> dis1dinner=Arrays.asList("Steamed vegetables", "Papaya salad");

        List<String> dis2breakfast=Arrays.asList("Oats", "Berries", "Black Coffee","Cereals");
        List<String> dis2lunch=Arrays.asList("Salad","Ham sandwich","Wheat bread","Chicken soup","Kiwi yogurt");
        List<String> dis2dinner=Arrays.asList("Fish","Steamed vegetables", "Brown Rice");

        List<String> dis3breakfast=Arrays.asList("Orange Juice", "Berries", "Cereals");
        List<String> dis3lunch=Arrays.asList("Salad","Tomato soup","Kiwi Yogurt");
        List<String> dis3dinner=Arrays.asList("Steamed vegetables", "Papaya salad");

        List<String> dis4breakfast=Arrays.asList("Oats", "Berries", "Black Coffee","Cereals");
        List<String> dis4lunch=Arrays.asList("Salad","Ham sandwich,","Wheat bread","Chicken soup","Kiwi yogurt");
        List<String> dis4dinner=Arrays.asList("Fish","Steamed vegetables", "Brown Rice");


        List<User> users = User.listAll(User.class);
       // new Notify("","l",true).save();

        if(users.size()==0)
        {

            User u=new User();
            u.name="test1";
            u.username="test1";
            u.password="pass";
            u.email="test1@gmail.com";
            u.age=22;
            u.dis1=true;
            u.save();


            User u1=new User();
            u1.name="test2";
            u1.username="test2";
            u1.password="pass";
            u1.email="test2@gmail.com";
            u1.age=20;
            u1.dis2=true;
            u1.save();

        for(String s : dis1breakfast)
        {
            Diet d=new Diet();
            d.diettype=getResources().getString(R.string.dis1);
            d.period="Breakfast";
            d.food=s;
            d.save();

        }

            for(String s : dis2breakfast)
            {
                Diet d=new Diet();
                d.diettype=getResources().getString(R.string.dis2);
                d.period="Breakfast";
                d.food=s;
                d.save();

            }

            for(String s : dis3breakfast)
            {
                Diet d=new Diet();
                d.diettype=getResources().getString(R.string.dis3);
                d.period="Breakfast";
                d.food=s;
                d.save();

            }

            for(String s : dis4breakfast)
            {
                Diet d=new Diet();
                d.diettype=getResources().getString(R.string.dis4);
                d.period="Breakfast";
                d.food=s;
                d.save();

            }
        }
        
        

        for(String s : dis1lunch)
        {
            Diet d=new Diet();
            d.diettype=getResources().getString(R.string.dis1);
            d.period="Lunch";
            d.food=s;
            d.save();

        }

        for(String s : dis2lunch)
        {
            Diet d=new Diet();
            d.diettype=getResources().getString(R.string.dis2);
            d.period="Lunch";
            d.food=s;
            d.save();

        }

        for(String s : dis3lunch)
        {
            Diet d=new Diet();
            d.diettype=getResources().getString(R.string.dis3);
            d.period="Lunch";
            d.food=s;
            d.save();

        }

        for(String s : dis4lunch)
        {
            Diet d=new Diet();
            d.diettype=getResources().getString(R.string.dis4);
            d.period="Lunch";
            d.food=s;
            d.save();

        }




        for(String s : dis1dinner)
        {
            Diet d=new Diet();
            d.diettype=getResources().getString(R.string.dis1);
            d.period="Dinner";
            d.food=s;
            d.save();

        }

        for(String s : dis2dinner)
        {
            Diet d=new Diet();
            d.diettype=getResources().getString(R.string.dis2);
            d.period="Dinner";
            d.food=s;
            d.save();

        }

        for(String s : dis3dinner)
        {
            Diet d=new Diet();
            d.diettype=getResources().getString(R.string.dis3);
            d.period="Dinner";
            d.food=s;
            d.save();

        }

        for(String s : dis4dinner)
        {
            Diet d=new Diet();
            d.diettype=getResources().getString(R.string.dis4);
            d.period="Dinner";
            d.food=s;
            d.save();

        }
    }


}




