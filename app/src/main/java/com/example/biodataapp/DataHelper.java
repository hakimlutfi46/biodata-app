package com.example.biodataapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "biodatadiri.db";
    private static final int DATABASE_VERSION = 1;
    public DataHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table biodata(no integer primary key, nama text null, tgl text null, jk text null, alamat text null);";
        Log.d("Data", "onCreate" + sql);
        db.execSQL(sql);

        sql = "INSERT INTO biodata (no, nama, tgl, jk, alamat) VALUES ('1', 'Dariswan', '1996-07-12', 'Laki-laki', 'Indramayu');";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertData(String no, String nama, String tlahir, String jk, String alamat) {
        SQLiteDatabase db = this.getWritableDatabase(); // Mendapatkan referensi ke database untuk menulis data.

        // Membuat perintah SQL INSERT sesuai dengan data yang diterima.
        String sql = "INSERT INTO biodata (no, nama, tgl, jk, alamat) VALUES (?, ?, ?, ?, ?)";
        Object[] bindArgs = {no, nama, tlahir, jk, alamat};

        try {
            db.execSQL(sql, bindArgs); // Mengeksekusi perintah SQL dengan data yang diterima.
        } catch (Exception e) {
            Log.e("DataHelper", "Error inserting data: " + e.getMessage());
        }

        db.close(); // Tutup koneksi database setelah selesai.
    }

}
