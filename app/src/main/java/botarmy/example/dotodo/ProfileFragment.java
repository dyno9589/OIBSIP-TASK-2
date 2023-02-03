package botarmy.example.dotodo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

//    DBHelper DB;

    SharedPreferences sharedPreferences;
    //sharedpref name and key name

    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        ImageButton editib = view.findViewById(R.id.editib);
        EditText username = view.findViewById(R.id.profile_useret);
        EditText email = view.findViewById(R.id.profile_emailet);
        TextView changepass = view.findViewById(R.id.chngpasstv);
        Button savebtn = view.findViewById(R.id.save_profile_btn);
        Button cancelbtn = view.findViewById(R.id.cancel_profile_btn);
        Button logout = view.findViewById(R.id.logoutbtn);





        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        String name = sharedPreferences.getString(KEY_NAME,null);
        String spemail = sharedPreferences.getString(KEY_EMAIL,null);



        if (name != null || spemail != null){
            username.setText(name);
            email.setText(spemail);
        }



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //shared pref
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(getActivity(), "Logged out successfully", Toast.LENGTH_SHORT).show();
                getActivity().finish();

            }
        });



        editib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username.setVisibility(View.VISIBLE);
                username.setEnabled(true);

                email.setVisibility(View.VISIBLE);
                email.setEnabled(true);

                savebtn.setVisibility(View.VISIBLE);
                savebtn.setEnabled(true);

                cancelbtn.setVisibility(View.VISIBLE);
                cancelbtn.setEnabled(true);

            }
        });

        changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change password
            }
        });

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save profile details
            }
        });

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username.setVisibility(View.VISIBLE);
                username.setEnabled(false);

                email.setVisibility(View.VISIBLE);
                email.setEnabled(false);

                savebtn.setVisibility(View.INVISIBLE);
                savebtn.setEnabled(false);

                cancelbtn.setVisibility(View.INVISIBLE);
                cancelbtn.setEnabled(false);
            }
        });




        return view;


    }
}