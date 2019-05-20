package br.unicamp.ft.ulisses.aula11_drawer2019;

/**
 * Created by ulisses on 03/04/17.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ulisses on 3/30/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "EXEMPLO";
    private static final int DB_VERSION = 2;

    DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*db.execSQL("CREATE TABLE tabela " +
                "(_id INTEGER PRIMARY KEY);");*/

        db.execSQL("CREATE TABLE tabela " +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "idadeselecionada INTEGER,"+
                "certo INTEGER,"+
                "RA  INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion < 2){
            //db.execSQL("ALTER TABLE tabela " +
            //           "ADD COLUMN texto;");
        }

    }
}
