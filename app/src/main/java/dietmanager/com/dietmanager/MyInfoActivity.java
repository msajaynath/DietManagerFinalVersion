package dietmanager.com.dietmanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import Entity.User;

public class MyInfoActivity extends AppCompatActivity {

    EditText name,email,age;
    CheckBox dis1,dis2,dis3,dis4;
    Button update;
    ImageView changepass;
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
        changepass= (ImageView) findViewById(R.id.changepass);
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

        changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckAndSavePassword(u);
            }
        });

    }

    private void CheckAndSavePassword(final User u) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MyInfoActivity.this);
        alertDialog.setTitle("Change");
        final EditText oldPass = new EditText(MyInfoActivity.this);
        final EditText newPass = new EditText(MyInfoActivity.this);
        final EditText confirmPass = new EditText(MyInfoActivity.this);


        oldPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
        newPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
        confirmPass.setTransformationMethod(PasswordTransformationMethod.getInstance());

        oldPass.setHint("Old Password");
        newPass.setHint("New Password");
        confirmPass.setHint("Confirm Password");
        LinearLayout ll=new LinearLayout(MyInfoActivity.this);
        ll.setOrientation(LinearLayout.VERTICAL);

        ll.addView(oldPass);

        ll.addView(newPass);
        ll.addView(confirmPass);
        alertDialog.setView(ll);
        alertDialog.setPositiveButton("Yes",null);
        alertDialog.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                    }
                });
        alertDialog.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        final AlertDialog alert11 = alertDialog.create();
        alert11.show();

        alert11.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean wantToCloseDialog = false;
                //Do stuff, possibly set wantToCloseDialog to true then...
                if (!oldPass.getText().toString().contentEquals(u.password)) {

                    Toast.makeText(MyInfoActivity.this, "Invalid current password!", Toast.LENGTH_SHORT).show();
                    return;

                } else {
                    if (newPass.getText().toString().length() < 6) {
                        Toast.makeText(MyInfoActivity.this, "New Password should be more than 6 characters!", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        if (!newPass.getText().toString().contentEquals(confirmPass.getText().toString())) {
                            Toast.makeText(MyInfoActivity.this, "Confirm password not same!", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            u.password = newPass.getText().toString();
                            u.save();
                            Toast.makeText(MyInfoActivity.this, "Password updated!", Toast.LENGTH_SHORT).show();
                            alert11.cancel();

                        }

                    }
                }
                //else dialog stays open. Make sure you have an obvious way to close the dialog especially if you set cancellable to false.
            }
        });
    }

}
