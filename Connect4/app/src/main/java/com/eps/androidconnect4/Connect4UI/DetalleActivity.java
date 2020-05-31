package com.eps.androidconnect4.Connect4UI;


import android.os.Bundle;

import androidx.core.app.BundleCompat;
import androidx.fragment.app.FragmentActivity;

import com.eps.androidconnect4.Connect4UI.Fragments.FragmentDetalle;
import com.eps.androidconnect4.R;
import com.eps.androidconnect4.util.constants;

public class DetalleActivity extends FragmentActivity {
	//detalle activity es invocat per secondActivity

	//DetalleActivity<--->FragmentDetalle


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//DetalleActivity---->FragmentDetalle(FragmentDetalleLayout)
		setContentView(R.layout.activity_detalle);
		
		FragmentDetalle detalle =
				(FragmentDetalle)getSupportFragmentManager()
					.findFragmentById(R.id.FrgDetalle);

		//(by invocation) --->set FragmentDetalleLayout


		if (detalle != null) {
			detalle.mostrarDetalle(getIntent().getStringExtra(constants.KEY_RESULT_GAME)
					,getIntent().getStringExtra(constants.KEY_ALIAS_PLAYER)
					,getIntent().getIntExtra(constants.KEY_BOARD_SIZE,-1)
					,getIntent().getBooleanExtra(constants.KEY_TIME_CTRL,false)
					,getIntent().getStringExtra(constants.KEY_DATE_PLAYER));
			detalle.mostrarIconoDetalle(getIntent().getIntExtra(constants.KEY_RESULT_INT,-1));
		}


	}
  }
