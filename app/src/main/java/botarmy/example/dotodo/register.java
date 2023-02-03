package botarmy.example.dotodo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class register extends AppCompatActivity {

    EditText username,email,crtpass,reenterpass;
    TextView alreadyreg;
    Button register;
    DBHelper DB;

    SharedPreferences sharedPreferences;
    //sharedpref name and key name

    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        username= findViewById(R.id.usernameet);
        email= findViewById(R.id.emailet);
        crtpass= findViewById(R.id.passet);
        reenterpass=findViewById(R.id.passagainet);
        alreadyreg= findViewById(R.id.alreadyregtv);
        register= findViewById(R.id.regbtn);

        DB = new DBHelper(this);

        // shared pref
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);

        String name = sharedPreferences.getString(KEY_NAME,null);

        if(name != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userstr = username.getText().toString();
                String emailstr = email.getText().toString();
                String crtpassstr = crtpass.getText().toString();
                String repassstr = reenterpass.getText().toString();

                if (userstr.equals("")||emailstr.equals("")||crtpassstr.equals("")||repassstr.equals(""))
                    Toast.makeText(register.this, "The fields should not be empty", Toast.LENGTH_SHORT).show();
                else{
                    if (crtpassstr.equals(repassstr)){
                        Boolean checkuser = DB.checkemail(emailstr);
                        if (checkuser == false){
                            Boolean insert = DB.insertData(userstr,emailstr,crtpassstr);
                            if (insert == true){
                                //sharedpref

                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString(KEY_NAME,userstr);
                                editor.putString(KEY_EMAIL,emailstr);
                                editor.apply();


                                Toast.makeText(register.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                finish();
                            }
                            else {
                                Toast.makeText(register.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(register.this, "User already exists! please login", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(register.this, "Password not matching", Toast.LENGTH_SHORT).show();
                    }
                }






            }
        });

        alreadyreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(register.this,login.class));
                finish();
            }
        });
    }
}