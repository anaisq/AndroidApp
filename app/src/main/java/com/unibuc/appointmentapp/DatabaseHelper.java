package com.unibuc.appointmentapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;

    private static final String DATABASE_NAME = "appointment_app.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_APPOINTMENTS = "appointments";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";

    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_DATE = "date";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_APPOINTMENTS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_PHONE + " TEXT, " +
                    COLUMN_DATE + " TEXT);";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_APPOINTMENTS);
        onCreate(db);
    }

    void addAppointment(String name, String phone, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_PHONE, phone);
        cv.put(COLUMN_DATE, date);

        long result = db.insert(TABLE_APPOINTMENTS, null, cv);
        if(result==-1){
            Toast.makeText(context,"Failed to make appointment", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context,"Appointment made", Toast.LENGTH_SHORT).show();
        }
    }
}
