package com.eps.androidconnect4.Connect4UI;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.eps.androidconnect4.MainActivity;
import com.eps.androidconnect4.R;

public class helpActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        setTitle(R.string.help_screen);
        Button b = findViewById(R.id.button_b_main);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch ( v.getId()){
            case R.id.button_b_main:
                Intent in = new Intent(this, MainActivity.class);
                startActivity(in);
                finish();
                break;
        }
    }
}
