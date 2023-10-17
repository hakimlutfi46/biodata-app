package com.example.biodataapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class LihatBiodata extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    TextView no, nama, tLahir, jKelamin, alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_biodata);
        getSupportActionBar().setTitle("Lihat Biodata");

    dbHelper = new DataHelper(this);
    no = findViewById(R.id.tvNo);
    nama = findViewById(R.id.tvNama);
    tLahir = findViewById(R.id.tvTglLahir);
    jKelamin = findViewById(R.id.tvJK);
    alamat = findViewById(R.id.tvAlamat);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM biodata WHERE nama = '" + getIntent().getStringExtra("nama")+"'", null);
        cursor.moveToFirst();

        if (cursor.getCount()>0){
            cursor.moveToPosition(0);
            no.setText(cursor.getString(0).toString());
            nama.setText(cursor.getString(1).toString());
            tLahir.setText(cursor.getString(2).toString());
            jKelamin.setText(cursor.getString(3).toString());
            alamat.setText(cursor.getString(4).toString());
        }

    }
}