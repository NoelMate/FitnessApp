package de.noelmate.fitness;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class CustomDB extends SQLiteOpenHelper {

    private final Context context;
    private static final String DATABASE_NAME = "Fitness.db";
    private static final int DATABASE_VERSION = 1;

    private final String TABLE_NAME;
    private static final String COLUMN_ID = "cid";
    private static final String COLUMN_SAETZE = "saetze";
    private static final String COLUMN_GEWICHT = "gewicht";
    private static final String COLUMN_DATUM = "datum";


    public CustomDB(@Nullable Context context, String TABLE_NAME) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        this.TABLE_NAME = TABLE_NAME;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " ("+COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_SAETZE + " DOUBLE, " +
                COLUMN_GEWICHT + " DOUBLE, " +
                COLUMN_DATUM + " DATE);";
        sqLiteDatabase.execSQL(query);

        /*query = "CREATE TABLE FitnessDB2CustomDB (fid INTEGER, cid INTEGER," +
                "PRIMARY KEY(fid, cid)," +
                "CONSTRAINT " +
                ");";*/

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

    }

    void addUebung(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        System.out.println("\n\n\n\n"+db+"\n\n\n\n");

        if(!name.equals("")) {
            cv.put(COLUMN_DATUM, name);
            long result = db.insert(TABLE_NAME, null, cv);
            if (result == -1) {
                Toast.makeText(context, "ALARM!\nALARM!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "Erfolgreich hinzugefügt!", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(context, "Textfeld darf nicht Leer sein!", Toast.LENGTH_LONG).show();
        }
    }


    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor= db.rawQuery(query, null);
        }

        return cursor;

    }
}
