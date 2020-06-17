package com.eps.connect4.UserInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.eps.connect4.R;
import com.eps.connect4.Connect4Logical.Connect4HostActivity;
import com.eps.connect4.util.PlayedGame;
import com.eps.connect4.util.PreferenciasJuego;
import com.eps.connect4.util.Status;
import com.eps.connect4.util.UsuariosSQLiteHelper;
import com.eps.connect4.util.constants;
import com.google.android.material.textfield.TextInputEditText;

import java.io.Serializable;
import java.util.Date;

public class MailSender extends AppCompatActivity implements View.OnClickListener {
    Date simply_date;

    public TextInputEditText date_subject_part;
    public TextInputEditText text_mail;
    public TextInputEditText send_mail_to_person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.MailSenderTitle);

        simply_date = new Date();

        saveToBBDD();

        setContentView(R.layout.activity_mail_sender);

        date_subject_part  = findViewById(R.id.date_subject_part);
        send_mail_to_person = findViewById(R.id.send_mail_to_person);
        text_mail =  findViewById(R.id.text_mail);

        date_subject_part.setText(simply_date.toString());

        text_mail.setText(setBodyText());


        Button send_mail_button = findViewById(R.id.send_mail_button);
        Button start_game_button = findViewById(R.id.start_game_button);
        Button exit_game_button = findViewById(R.id.exit_game_button);
        send_mail_button.setOnClickListener(this);
        start_game_button.setOnClickListener(this);
        exit_game_button.setOnClickListener(this);



    }

    private void saveToBBDD() {

        SQLiteDatabase db = UsuariosSQLiteHelper.INSTANCE.getWritableDatabase();

        String nom = PreferenciasJuego.prefs_user_game;
        int size = PreferenciasJuego.prefs_connect4_size;
        int time_bool = Boolean.compare(PreferenciasJuego.prefs_time_ctrl_bool, false);
        String fecha = simply_date.toString();


        Status status =(Status) ((getIntent()!=null && getIntent().getSerializableExtra(constants.GAME_STATUS) !=null)?getIntent().getSerializableExtra(constants.GAME_STATUS):Status.UNKNOWN);
        int resultado_token =  PlayedGame.getResultInt(status);

        String sql = "INSERT INTO Usuarios (alias,size,time_control,fecha_txt,resultado_int) VALUES ('" + nom + "','" + size + "','" + time_bool + "','" + fecha + "','" + resultado_token + "') ";
        db.execSQL(sql);
    }

    private String setBodyText() {
        return "Alias: "+PreferenciasJuego.prefs_user_game +
                "\nConnect 4 size: "+PreferenciasJuego.prefs_connect4_size+
                "\nTime elapsed: "+getIntent().getIntExtra(constants.TIME_LAPSE,-1)+" sec"+
                "\n"+getResultGame((getIntent()!=null)?getIntent().getSerializableExtra(constants.GAME_STATUS):null);
    }

    private String getResultGame(Serializable serializableExtra) {
        if (serializableExtra != null) {
            Status s = (Status) serializableExtra;
            System.out.println(s.name());
            return PlayedGame.getResultGameString((Status) serializableExtra);
        }
        return "?";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent in = new Intent(this, AjustesActivity.class);
                startActivity(in);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        Intent in;
        switch ( v.getId()){
            case R.id.send_mail_button:
                sendMail();
                break;
            case R.id.start_game_button:
                in = new Intent(this, Connect4HostActivity.class);
                in.putExtra(constants.PREFS_ALIAS, PreferenciasJuego.prefs_user_game);
                in.putExtra(constants.PREFS_SIZE,PreferenciasJuego.prefs_connect4_size);
                in.putExtra(constants.PREFS_TIME_CTRL,PreferenciasJuego.prefs_time_ctrl_bool);
                startActivity(in);
                finish();
                break;
            case R.id.exit_game_button:
                MailSender.this.finish();
                break;
        }
    }

    private void sendMail() {
        if (send_mail_to_person  != null && date_subject_part != null && text_mail != null) {

            String  ENVIAR_A= (send_mail_to_person.getText() != null)? send_mail_to_person.getText().toString() : "" ;
            String  ASUNTO="LOG -"+ ((date_subject_part.getText() !=null)? date_subject_part.getText().toString() : "No date" ); //subject mail LOG-data i hora
            String  TEXTO = (text_mail.getText() != null)? text_mail.getText().toString(): "No logs" ;  // alias= graella = estat.....


            if (ENVIAR_A.equals("")){
                Toast.makeText(this, getText(R.string.error_mail), Toast.LENGTH_SHORT).show();
            }else{

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{ENVIAR_A});
                intent.putExtra(Intent.EXTRA_SUBJECT, ASUNTO);
                intent.putExtra(Intent.EXTRA_TEXT, TEXTO);

                try {
                    //Enviamos el Correo iniciando una nueva Activity con el emailIntent.
                    startActivity(Intent.createChooser(intent, "Enviar Correo..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(this, "No hay ningun cliente de correo instalado.", Toast.LENGTH_SHORT).show();
                }
            }

        }



    }
}
