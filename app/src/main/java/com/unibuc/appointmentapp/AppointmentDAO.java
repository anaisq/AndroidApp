package com.unibuc.appointmentapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private String[] allColumns = {
            DatabaseHelper.COLUMN_ID,
            DatabaseHelper.COLUMN_NAME,
            DatabaseHelper.COLUMN_PHONE,
            DatabaseHelper.COLUMN_DATE
    };

    public AppointmentDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Appointment createAppointment(String name, String phone,String date) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, name);
        values.put(DatabaseHelper.COLUMN_DATE, date);
        values.put(DatabaseHelper.COLUMN_PHONE, phone);

        long insertId = database.insert(DatabaseHelper.TABLE_APPOINTMENTS, null, values);

        Cursor cursor = database.query(DatabaseHelper.TABLE_APPOINTMENTS,
                allColumns, DatabaseHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Appointment newAppointment = cursorToAppointment(cursor);
        cursor.close();
        return newAppointment;
    }

    public void deleteAppointment(Appointment appointment) {
        long id = appointment.getId();
        database.delete(DatabaseHelper.TABLE_APPOINTMENTS, DatabaseHelper.COLUMN_ID + " = " + id, null);
    }

    public void deleteAppointment() {
        database.delete(DatabaseHelper.TABLE_APPOINTMENTS, null, null);
    }

    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();

        Cursor cursor = database.query(DatabaseHelper.TABLE_APPOINTMENTS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Appointment appointment = cursorToAppointment(cursor);
            appointments.add(appointment);
            cursor.moveToNext();
        }
        cursor.close();
        return appointments;
    }

    private Appointment cursorToAppointment(Cursor cursor) {
        return new Appointment(
                cursor.getLong(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3)
        );
    }
}
