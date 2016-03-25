package dietmanager.com.dietmanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
ImageView myinfo,diet,logout;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myinfo= (ImageView) findViewById(R.id.myinfo);
        diet= (ImageView) findViewById(R.id.diet);
        logout= (ImageView) findViewById(R.id.logout);
        myinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MyInfoActivity.class));

            }
        });

        diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DietActivity.class));

            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedpreferences = getSharedPreferences("Diet", Context.MODE_PRIVATE);

                sharedpreferences.edit().putBoolean("logged", false).commit();
                Toast.makeText(getApplicationContext(), "Logged out!!", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(MainActivity.this, NotificationService.class);
                stopService(myIntent);
                finish();

            }
        });




    }
}
