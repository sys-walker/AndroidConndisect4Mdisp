package com.eps.androidconnect4;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.eps.androidconnect4.util.ImageAdapter;

import java.util.ArrayList;

public class Connect4GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect4_game);

        int size = 7 ;//from shared preferences
        //COLOCAR BUTTONS
        GridView grid = findViewById(R.id.gridviewbuttons);
        ArrayList<Integer> b = new ArrayList<>();
        for (int i = 0; i < size ; i++) {
            b.add(R.drawable.fitxa_vermell);
        }
        ImageAdapter Adapter = new ImageAdapter(getBaseContext());
        Adapter.putArray(b.toArray(new Integer[0]));
        grid.setNumColumns(size);
        grid.setAdapter(Adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(),"Columna "+position,Toast.LENGTH_LONG).show();
            }
        });

//////////////////////////////////////////////////////////////////////////////////////////////
        GridView buttons_grid = findViewById(R.id.gridview2);
        ArrayList<Integer> buttons = new ArrayList<>();
        for (int i = 0; i < size *size ; i++) {
            buttons.add(R.drawable.fitxa_buida);
        }
        ImageAdapter buttonAdapter = new ImageAdapter(getBaseContext());
        buttonAdapter.putArray(buttons.toArray(new Integer[0]));

        buttons_grid.setBackgroundColor(Color.BLUE);
        buttons_grid.setNumColumns(size);
        buttons_grid.setAdapter(buttonAdapter);
    }
}
