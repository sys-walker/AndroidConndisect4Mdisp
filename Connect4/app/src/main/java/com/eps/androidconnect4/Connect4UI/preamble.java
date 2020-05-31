package com.eps.androidconnect4.Connect4UI;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.eps.androidconnect4.R;

public class preamble extends AppCompatActivity implements View.OnClickListener {
    EditText alias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preamble);
        setTitle(R.string.preamble_screen);

        Button commence_btn = findViewById(R.id.commence_game);
        alias = findViewById(R.id.alias_in);
        commence_btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch ( v.getId()){
            case R.id.commence_game:
                //MainActivity.b.putString("ALIAS", alias.getText().toString());
                //MainActivity.b.putInt("MIDA_GRAELLA",getGameSize());
                int mida_graella = getGameSize();
                boolean control_temps = getTimeOption();

                if (control_temps){
                    Toast.makeText(getBaseContext(),"emps activtat",Toast.LENGTH_LONG).show();
                }

                //Navigation.findNavController(v).navigate(R.id.action_preambleFragment_to_connect4Fragment);
                finish();
                break;
        }
    }


    private boolean getTimeOption(){
        ToggleButton t = findViewById(R.id.toggleButton);
        if (t != null) {
            return t.isChecked();
        }
        return false;

    }
    private int getGameSize() {
        RadioGroup r = findViewById(R.id.graella_radiogrup);
        if (r != null) {
            int id = r.getCheckedRadioButtonId();
            switch (id){
                case R.id.grid7:
                    return 7;
                case R.id.grid6:
                    return 6;
                case R.id.grid5:
                    return 5;
            }
        }

        Toast.makeText(getBaseContext(),R.string.error_size_grid,Toast.LENGTH_LONG).show();
        return 7;

    }
}
