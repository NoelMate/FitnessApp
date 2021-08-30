package de.noelmate.fitness;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.time.LocalDate;

public class FitnessDB extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Fitness.db";
    private static final int DATABASE_VERSION = 6;

    private static final String TABLE_NAME = "Uebung";
    private static final String COLUMN_ID = "fid";
    private static final String COLUMN_NAME = "name";


    private final String TABLE_NAME2 = "Tage";
    private static final String COLUMN_ID2 = "tid";
    private static final String COLUMN_FKEY = "ffid";
    private static final String COLUMN_SAETZE = "saetze";
    private static final String COLUMN_GEWICHT = "gewicht";
    private static final String COLUMN_DATUM = "datum";


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

        query = "CREATE TABLE " + TABLE_NAME2 +
                " ("+COLUMN_ID2 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FKEY + " INTEGER, "+
                COLUMN_SAETZE + " DOUBLE, " +
                COLUMN_GEWICHT + " DOUBLE, " +
                COLUMN_DATUM + " DATE, " +
                "CONSTRAINT FK_Fitness FOREIGN KEY (ffid) REFERENCES Uebung(fid));";

        sqLiteDatabase.execSQL(query);



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);

        onCreate(sqLiteDatabase);

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    void addItem(int id, int saetze, int gewicht){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        LocalDate date = LocalDate.now();

        cv.put(COLUMN_FKEY, id);
        cv.put(COLUMN_SAETZE, saetze);
        cv.put(COLUMN_GEWICHT, gewicht);
        cv.put(COLUMN_DATUM, String.valueOf(date));
        long result = db.insert(TABLE_NAME2, null, cv);
        if (result == -1) {
            Toast.makeText(context, "ALARM!\nALARM!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Erfolgreich hinzugefügt!", Toast.LENGTH_LONG).show();
        }
    }
    

    Cursor readMainTable(){
        //String query = "SELECT * FROM " + TABLE_NAME + " INNER JOIN " +TABLE_NAME2 + " ON " +TABLE_NAME+"."+COLUMN_ID+" = "+TABLE_NAME2+"."+COLUMN_ID2;
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor= db.rawQuery(query, null);
        }

        return cursor;

    }

    Cursor readSecondaryTable(){
        //String query = "SELECT * FROM " + TABLE_NAME + " INNER JOIN " +TABLE_NAME2 + " ON " +TABLE_NAME+"."+COLUMN_ID+" = "+TABLE_NAME2+"."+COLUMN_ID2;
        String query = "SELECT * FROM " + TABLE_NAME2;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor= db.rawQuery(query, null);
        }

        return cursor;

    }

    Cursor getSpecificItemsFromSecondaryTable(int id){
        String query = "SELECT * FROM " + TABLE_NAME2 + " WHERE Tage.ffid = "+id;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor= db.rawQuery(query, null);
        }

        return cursor;
    }
}
