package com.eps.androidconnect4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.eps.androidconnect4.Connect4UI.QueryActivity;
import com.eps.androidconnect4.Connect4UI.helpActivity;
import com.eps.androidconnect4.Connect4UI.preamble;
import com.eps.androidconnect4.util.Manual_input;
import com.eps.androidconnect4.util.UsuariosSQLiteHelper;
import com.eps.androidconnect4.util.constants;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Serializable {
    public UsuariosSQLiteHelper usdbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_help = findViewById(R.id.button_help);
        Button btn_preamble = findViewById(R.id.goPreamble);
        Button btn_exit = findViewById(R.id.button_exit);
        Button btn_stats_results = findViewById(R.id.goBDD);

        btn_help.setOnClickListener( this);
        btn_preamble.setOnClickListener(this);
        btn_exit.setOnClickListener(this);
        btn_stats_results.setOnClickListener(this);



    }
    @Override
    public void onClick(View v) {
        Intent in;
        switch ( v.getId()){
            case R.id.button_help:
                in= new Intent(this, helpActivity.class);
                startActivity(in);
                finish();

                break;
            case R.id.goPreamble:
                in = new Intent(this, Connect4GameActivity.class);
                startActivity(in);
                finish();
                break;
            case R.id.goBDD:
                createDB();
                in = new Intent(this, QueryActivity.class);
                startActivity(in);
                finish();
                break;
            case R.id.button_exit:
                finish();
                break;
        }
    }

    private void createDB() {
        usdbh = new UsuariosSQLiteHelper(this, constants.DATABASE_NAME);
        UsuariosSQLiteHelper.saveInstanceHelper(usdbh);

        //SQLiteDatabase db = usdbh.getWritableDatabase();
        //usdbh.create_samples();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.preferences, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(getBaseContext(),"opening shared pref",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
