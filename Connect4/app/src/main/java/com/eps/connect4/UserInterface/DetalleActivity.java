package com.eps.connect4.UserInterface;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.eps.connect4.R;
import com.eps.connect4.UserInterface.Fragments.FragmentDetalle;
import com.eps.connect4.util.constants;

public class DetalleActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.title_screen_detail);
        setContentView(R.layout.activity_detalle);

        FragmentDetalle detalle =
                (FragmentDetalle)getSupportFragmentManager()
                        .findFragmentById(R.id.FrgDetalle);


        detalle.mostrarDetalle((String) getIntent().getStringExtra(constants.C4_USER),
                getIntent().getStringExtra(constants.C4_DATE),
                getIntent().getIntExtra(constants.C4_SIZE,0),
                getIntent().getBooleanExtra(constants.C4_TIME_CTRL,false),
                getIntent().getIntExtra(constants.C4_RESULT,-1),
                getIntent().getIntExtra(constants.C4_RESULT_ICON,-1));
    }
}
