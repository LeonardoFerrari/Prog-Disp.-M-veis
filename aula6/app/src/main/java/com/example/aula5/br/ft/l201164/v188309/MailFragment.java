package com.example.aula5.br.ft.l201164.v188309;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */


public class MailFragment extends Fragment {

    View view;
    String texto = "";

    public MailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (view == null){
            view = inflater.inflate(R.layout.linear_layout, container, false);
        }

        TextView textView = view.findViewById(R.id.txtlogin);
        textView.setText(texto);

        return view;
    }

    public void setTexto(String txt){
        this.texto = txt;
    }

}
