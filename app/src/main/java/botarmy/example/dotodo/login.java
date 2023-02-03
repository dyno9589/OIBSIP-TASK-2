package botarmy.example.dotodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {

    EditText email,password;
    TextView forgotpass,alreadyreg;
    Button login;
    DBHelper DB;

    SharedPreferences sharedPreferences;
    //sharedpref name and key name

    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        email= findViewById(R.id.emailet);
        password= findViewById(R.id.passet);
        forgotpass= findViewById(R.id.forgottv);
        alreadyreg= findViewById(R.id.alreadyregtv);
        login= findViewById(R.id.loginbtn);

        DB = new DBHelper(this);

        //shared pref
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME,null);

        if(name != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailcontent = email.getText().toString();
                String passcontent = password.getText().toString();

                if (emailcontent.equals("")||passcontent.equals(""))
                    Toast.makeText(login.this, "The fields should not empty", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkemailpassword(emailcontent,passcontent);
                    if (checkuserpass == true){
                        //shared pref
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(KEY_EMAIL,emailcontent);
                        editor.apply();

                        Toast.makeText(login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                    }
                    else {
                        Toast.makeText(login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });


        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        alreadyreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,register.class));
                finish();
            }
        });


    }
}