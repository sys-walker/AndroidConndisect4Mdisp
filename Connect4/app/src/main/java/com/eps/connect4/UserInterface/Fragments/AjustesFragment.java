package com.eps.connect4.UserInterface.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceFragmentCompat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eps.connect4.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AjustesFragment extends PreferenceFragmentCompat{
    public AjustesFragment(){
        // Requires empty public constructor
    }

    @Override
    public void onCreatePreferences(Bundle bundle,String key){
        setPreferencesFromResource(R.xml.preferencias, key);
    }

}
