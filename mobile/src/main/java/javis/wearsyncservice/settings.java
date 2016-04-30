package javis.wearsyncservice;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class settings extends Activity {

    final String SETTINGS_FILE = "BRUSH_SETTINGS";
    private SharedPreferences settings;

    private String first_name = "";
    private String last_name = "";
    private int age = -1;
    private boolean is_male = true;
    private boolean is_left_hand = true;

    private boolean local_is_male;
    private boolean local_is_left_hand;

    private RelativeLayout cancel_button;
    private RelativeLayout edit_button;
    private EditText view_first_name;
    private EditText view_last_name;
    private EditText view_age;
    private TextView view_male;
    private TextView view_female;
    private TextView view_left_hand;
    private TextView view_right_hand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        cancel_button = (RelativeLayout) findViewById(R.id.cancel_button);
        edit_button = (RelativeLayout) findViewById(R.id.edit_button);
        view_first_name = (EditText) findViewById(R.id.editTextFirstName);
        view_last_name = (EditText) findViewById(R.id.editTextLastName);
        view_age = (EditText) findViewById(R.id.editTextAge);
        view_male = (TextView) findViewById(R.id.textViewMale);
        view_female = (TextView) findViewById(R.id.textViewFemale);
        view_left_hand = (TextView) findViewById(R.id.textViewLeft);
        view_right_hand = (TextView) findViewById(R.id.textViewRight);



        // get shared preferences
        settings = getSharedPreferences(SETTINGS_FILE, MODE_PRIVATE);
        first_name = settings.getString("FIRST_NAME", "First Name");
        last_name = settings.getString("LAST_NAME", "Last Name");
        age = settings.getInt("AGE", 5);
        is_male = settings.getBoolean("IS_MALE", true);
        is_left_hand = settings.getBoolean("IS_LEFT_HAND", true);



        // update xml
        view_first_name.setText(first_name);
        Log.d("TAG", "set text: " + first_name);
        view_last_name.setText(last_name);
        view_age.setText(String.valueOf(age));
        switch_gender(is_male);
        switch_hand(is_left_hand);



        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view_first_name.setText(first_name);
                view_last_name.setText(last_name);
                view_age.setText(String.valueOf(age));
                switch_gender(is_male);
                switch_hand(is_left_hand);
            }
        });


        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = settings.edit();
                String fn = view_first_name.getText().toString();
                String ln = view_last_name.getText().toString();
                int ag = Integer.valueOf(view_age.getText().toString());

                editor.putString("FIRST_NAME", fn);
                editor.putString("LAST_NAME", ln);
                editor.putInt("AGE", ag);
                editor.putBoolean("IS_MALE", local_is_male);
                editor.putBoolean("IS_LEFT_HAND", local_is_left_hand);
                editor.commit();
                Log.d("TAG", "put first_name: " + fn);

                first_name = fn;
                last_name = ln;
                age = ag;
                is_male = local_is_male;
                is_left_hand = local_is_left_hand;

                Toast.makeText(getBaseContext(), "New data saved", Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void switch_gender(boolean to_male) {
        if (to_male) {
            view_male.setTextColor(getResources().getColor(R.color.orange));
            view_female.setTextColor(getResources().getColor(R.color.darkGrey));
            local_is_male = true;
        } else {
            view_male.setTextColor(getResources().getColor(R.color.darkGrey));
            view_female.setTextColor(getResources().getColor(R.color.orange));
            local_is_male = false;
        }
    }



    private void switch_hand(boolean to_left) {
        if (to_left) {
            view_left_hand.setTextColor(getResources().getColor(R.color.orange));
            view_right_hand.setTextColor(getResources().getColor(R.color.darkGrey));
            local_is_left_hand = true;
        } else {
            view_left_hand.setTextColor(getResources().getColor(R.color.darkGrey));
            view_right_hand.setTextColor(getResources().getColor(R.color.orange));
            local_is_left_hand = false;
        }
    }
}