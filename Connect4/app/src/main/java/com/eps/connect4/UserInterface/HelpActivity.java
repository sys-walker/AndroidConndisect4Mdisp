package com.eps.connect4.UserInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.eps.connect4.MainActivity;
import com.eps.connect4.R;

public class HelpActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        setTitle(R.string.help_screen);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch ( v.getId()){
            case R.id.button:
                Intent in = new Intent(this, MainActivity.class);
                startActivity(in);
                finish();
                break;
        }
    }
}
