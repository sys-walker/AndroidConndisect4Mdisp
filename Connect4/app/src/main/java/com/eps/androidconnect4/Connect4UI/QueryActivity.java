package com.eps.androidconnect4.Connect4UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.FragmentActivity;


;import com.eps.androidconnect4.Connect4UI.DetalleActivity;
import com.eps.androidconnect4.Connect4UI.Fragments.FragmentDetalle;
import com.eps.androidconnect4.Connect4UI.Fragments.FragmentListado;
import com.eps.androidconnect4.MainActivity;
import com.eps.androidconnect4.R;
import com.eps.androidconnect4.util.GameResult;
import com.eps.androidconnect4.util.constants;

public class QueryActivity extends FragmentActivity implements FragmentListado.GameResultListener, View.OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		//secondActivity--->FragmentListado
		Button b = findViewById(R.id.button);
		b.setOnClickListener(this);
		FragmentListado frgListado
				=(FragmentListado)getSupportFragmentManager()
				.findFragmentById(R.id.FrgListado);

		if (frgListado != null) {
			frgListado.onGameResultSelected(this);
		}
	}



	@Override
	public void onGameResultSelected(GameResult c) {
		//mostra eld detalls del resultat
		//secondActivity<--->FragmentDetalle
		FragmentDetalle fgdet = (FragmentDetalle) getSupportFragmentManager().findFragmentById(R.id.FrgDetalle);
		boolean hayDetalle = (fgdet != null && fgdet.isInLayout());


		if (hayDetalle) {
			fgdet.mostrarDetalle(c.getResultGameString(),c.getPlayer_alias(),c.getBoard_size(),c.getTime_control(),c.getDate());

		}
		else {
			Intent i = new Intent(this, DetalleActivity.class);
			i.putExtra(constants.KEY_RESULT_INT,c.getResult_int());
			i.putExtra(constants.KEY_RESULT_GAME,c.getResultGameString());
			i.putExtra(constants.KEY_ALIAS_PLAYER,c.getPlayer_alias());
			i.putExtra(constants.KEY_BOARD_SIZE,c.getBoard_size());
			i.putExtra(constants.KEY_TIME_CTRL,c.getTime_control());
			i.putExtra(constants.KEY_DATE_PLAYER,c.getDate());
			startActivity(i);

		}

	}

	@Override
	public void onClick(View v) {
		Intent in;
		switch ( v.getId()){
			case R.id.button:
				in= new Intent(this, MainActivity.class);
				startActivity(in);
				finish();
				break;
		}
	}
}
