package com.eps.androidconnect4.util;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.eps.androidconnect4.MainActivity;
import com.eps.androidconnect4.R;
import com.eps.androidconnect4.Connect4UI.QueryActivity;

public class Manual_input extends AppCompatActivity implements View.OnClickListener{
    private EditText txtNombre;
    private EditText txtBoardSize;
    private EditText booleanTimeControl;
    private EditText txtFecha;
    private EditText intResultado_int;

    private Button btnInsertar;
    private Button btnActualizar;
    private Button btnEliminar;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_input);

        Button b = findViewById(R.id.button);
        b.setOnClickListener(this);




        //Obtenemos las referencias a los controles

        txtNombre = findViewById(R.id.txtNom);
        txtBoardSize = findViewById(R.id.txtBoard);
        booleanTimeControl = findViewById(R.id.txtTimeBoolean);
        txtFecha =  findViewById(R.id.txtFecha);
        intResultado_int = findViewById(R.id.txtResultInt);


        btnInsertar = findViewById(R.id.btnInsertar);
        btnInsertar.setOnClickListener(this);

       // UsuariosSQLiteHelper usdbh =new UsuariosSQLiteHelper(this, "DBUsuarios", null, 3);
       // db = usdbh.getWritableDatabase();
        UsuariosSQLiteHelper usdbh = UsuariosSQLiteHelper.instance;
        db = UsuariosSQLiteHelper.instance.getWritableDatabase();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                Toast.makeText(getBaseContext(),"ok",Toast.LENGTH_LONG).show();
                Intent in = new Intent(this, QueryActivity.class);
                startActivity(in);

                break;
            case R.id.btnInsertar:


                String nom =txtNombre.getText().toString();
                int size = Integer.parseInt(txtBoardSize.getText().toString());
                int time_bool =  (Integer.parseInt(booleanTimeControl.getText().toString())==0) ? 0 : 1;
                String fecha = txtFecha.getText().toString();
                int resultado_token = Integer.parseInt(intResultado_int.getText().toString());


                String sql = "INSERT INTO Usuarios (alias,size,time_control,fecha_txt,resultado_int) VALUES ('" + nom + "','" + size + "','" + time_bool + "','" + fecha + "','" + resultado_token + "') ";
               // String sql = "INSERT INTO Usuarios (alias) VALUES ('" + nom  + "') ";

                db.execSQL(sql);


                Toast.makeText(getBaseContext(),"insertado",Toast.LENGTH_LONG).show();
                break;
            case R.id.button2:
                Toast.makeText(getBaseContext(),"ok",Toast.LENGTH_LONG).show();
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                finish();

                break;
        }
    }
}
