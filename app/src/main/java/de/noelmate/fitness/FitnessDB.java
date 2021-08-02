package de.noelmate.fitness;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class FitnessDB extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Fitness.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "Uebung";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";


    public FitnessDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " ("+COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT);";
        sqLiteDatabase.execSQL(query);

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
            cv.put(COLUMN_NAME, name);
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
