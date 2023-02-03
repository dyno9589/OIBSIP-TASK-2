package botarmy.example.dotodo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        //showing fragment-home by default
        FragmentTransaction fthome;
        fthome = getSupportFragmentManager().beginTransaction();
        fthome.replace(R.id.fragmentView,new HomeFragment());
        fthome.commit();

        bottomNavigationView.setOnItemSelectedListener(item -> {
            FragmentTransaction ft;

            switch (item.getItemId()){

                case R.id.home:
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.fragmentView,new HomeFragment());
                    ft.commit();
                    return true;

                case R.id.profile:
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.fragmentView,new ProfileFragment());
                    ft.commit();
                    return true;

                case R.id.notes:
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.fragmentView,new NotesFragment());
                    ft.commit();
                    return true;
            }
            return false;
        });




    }

}




