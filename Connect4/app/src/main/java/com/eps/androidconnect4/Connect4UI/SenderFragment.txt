package com.eps.androidconnect4.UIgame;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.eps.androidconnect4.MainActivity;
import com.eps.androidconnect4.R;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class SenderFragment extends Fragment implements View.OnClickListener {

    EditText enviar_a;
    EditText subject_mail;
    EditText body_mail;
    String body;



    public SenderFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Final");
        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.fragment_sender, container, false);



        //subject mail (data i hora)
        subject_mail =  v.findViewById(R.id.asunto_field);
        String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        subject_mail.setText(mydate);

        //body_mail (valors de log i aquestes coses)
        body_mail = v.findViewById(R.id.text_mail); //body valors de logs
        body = "Alias:"+ MainActivity.b.get("ALIAS")+" Mida graella:"+ MainActivity.b.get("MIDA_GRAELLA")+" Temps Total:(0 sec manual handwrite)";
        body_mail.setText(body);

        //correu destinatari
        enviar_a = v.findViewById(R.id.envaiar_a);

        //Botons de menu
        Button enviar_email = v.findViewById(R.id.enviar);
        Button newgame_btn = v.findViewById(R.id.new_game);
        Button exit_btmn = v.findViewById(R.id.sortir_joc);
        enviar_email.setOnClickListener(this);
        newgame_btn.setOnClickListener(this);
        exit_btmn.setOnClickListener(this);




        return v;
    }

    @Override
    public void onClick(View v) {
        switch ( v.getId()){
            case R.id.enviar:
                enviar_mail();
                break;

            case R.id.new_game:
                Navigation.findNavController(v).navigate(R.id.action_senderFragment_to_preambleFragment);
                break;
            case R.id.sortir_joc:
                MainFragment.a.finishAffinity();
                MainFragment.a.finish();
                break;
        }
    }

    private void enviar_mail() {

        String  ENVIAR_A=enviar_a.getText().toString();

        String  ASUNTO="LOG -"+subject_mail.getText(); //subject mail LOG-data i hora
        String  TEXTO=body; // alias= graella = estat.....


        if (enviar_a  != null) {

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{ENVIAR_A});
            intent.putExtra(Intent.EXTRA_SUBJECT, ASUNTO);
            intent.putExtra(Intent.EXTRA_TEXT, TEXTO);

            try {
                //Enviamos el Correo iniciando una nueva Activity con el emailIntent.
                startActivity(Intent.createChooser(intent, "Enviar Correo..."));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(getContext(), "No hay ningun cliente de correo instalado.", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
