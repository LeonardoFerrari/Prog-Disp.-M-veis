package br.unicamp.ft.ulisses.aula11_drawer2019;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.unicamp.ft.ulisses.aula09_drawer2018.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Stats extends Fragment {

    private TextView textView;

    public Stats() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_stats, container, false);
        textView     = v.findViewById(R.id.estat);

        DatabaseHelper dbHelper = new DatabaseHelper(getActivity());
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();

        int certo1=0, certo0=0, total=0;



        String sql = "Select count(certo) from tabela where certo =0";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        if (cursor.moveToFirst()){
            String str = "";
            do {
                certo0 = cursor.getInt(0);
            }while(cursor.moveToNext());
        }
        cursor.close();

        sql = "Select count(certo) from tabela where certo =1";
        cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()){
            do {
                certo1 = cursor.getInt(0);
            }while(cursor.moveToNext());
        }
        cursor.close();

        sql = "Select count(*) from tabela";
        cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()){
            do {
                total = cursor.getInt(0);
            }while(cursor.moveToNext());
        }
        cursor.close();


        textView.setText(Integer.toString(certo0)+" "+Integer.toString(certo1));

        sqLiteDatabase.close();
        dbHelper.close();


        return v ;
    }

}
/*
    String sql = "Select count(certo) from tabela where certo =0";
    Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        if (cursor.moveToFirst()){
                String str = "";
                do {

                //str = str + cursor.getInt(0)+ " "+cursor.getInt(1)+ " "+cursor.getInt(2)+ " "+cursor.getInt(3) + "\n";
                certo = str + cursor.getInt(0);
                }while(cursor.moveToNext());
                textView.setText(str);
                }
                cursor.close();

                sqLiteDatabase.close();
                dbHelper.close();*/