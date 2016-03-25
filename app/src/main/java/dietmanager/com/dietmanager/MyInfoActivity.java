package dietmanager.com.dietmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import Entity.User;

public class MyInfoActivity extends AppCompatActivity {

    EditText name,email,age;
    CheckBox dis1,dis2,dis3,dis4;
    Button update;
    private User u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        final SharedPreferences sharedpreferences = getSharedPreferences("Diet", Context.MODE_PRIVATE);

        long id=sharedpreferences.getLong("userid",1);
         u=User.findById(User.class,id);
        name= (EditText) findViewById(R.id.name);
        email= (EditText) findViewById(R.id.email);
        age= (EditText) findViewById(R.id.age);
        dis1= (CheckBox) findViewById(R.id.dis1);
        dis2= (CheckBox) findViewById(R.id.dis2);
        dis3= (CheckBox) findViewById(R.id.dis3);
        dis4= (CheckBox) findViewById(R.id.dis4);
        update= (Button) findViewById(R.id.update);

        if(u!=null)
        {

            name.setText(u.name);
            email.setText(u.email);
            age.setText(u.age+"");

            if(u.dis1)
                dis1.setChecked(true);
            if(u.dis2)
                dis2.setChecked(true);
            if(u.dis3)
                dis3.setChecked(true);
            if(u.dis4)
                dis4.setChecked(true);

        }


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Name is required!",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(age.getText().toString().length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Age is required!",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(email.getText().toString().length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Email is required!",Toast.LENGTH_SHORT).show();
                    return;
                }

                u.name=name.getText().toString();
                u.age=Integer.parseInt(age.getText().toString());
                u.email=email.getText().toString();
                u.dis1=dis1.isChecked();
                u.dis2=dis2.isChecked();
                u.dis3=dis3.isChecked();
                u.dis4=dis4.isChecked();
                u.save();
                Toast.makeText(getApplicationContext(),"Updated!",Toast.LENGTH_SHORT).show();
                finish();

            }
        });


    }
}
