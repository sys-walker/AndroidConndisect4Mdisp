package com.eps.connect4.UserInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.eps.connect4.R;
import com.eps.connect4.UserInterface.Fragments.AjustesFragment;

public class AjustesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);
        setTitle(R.string.title_screen_settings);
         getSupportFragmentManager().beginTransaction().replace(R.id.host,new AjustesFragment()).commit();
    }
}
