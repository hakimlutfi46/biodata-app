package com.example.biodataapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class BuatBiodata extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button btnSave, btnBack;
    EditText eNo, eNama, eTlahir, eAlamat;
    RadioGroup rJK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_biodata);
        getSupportActionBar().setTitle("Buat Biodata");

        dbHelper = new DataHelper(this);
        eNo = findViewById(R.id.etNo);
        eNama = findViewById(R.id.etNama);
        eTlahir = findViewById(R.id.etLahir);
        rJK = findViewById(R.id.radioGroup1);
        eAlamat = findViewById(R.id.etAlamat);
        btnSave = findViewById(R.id.btnSimpan);
        btnBack = findViewById(R.id.btnKembali);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mendapatkan nilai dari elemen tampilan yang diisi oleh pengguna
                String no = eNo.getText().toString();
                String nama = eNama.getText().toString();
                String tlahir = eTlahir.getText().toString();
                String alamat = eAlamat.getText().toString();

                int selectedRadioButtonId = rJK.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                String jk = selectedRadioButton.getText().toString();

                // Sekarang Anda bisa menyimpan data ke database menggunakan dbHelper
                dbHelper.insertData(no, nama, tlahir, jk, alamat);

                // Setelah menyimpan data, Anda mungkin ingin membersihkan formulir atau melakukan tindakan lain.
                eNo.setText("");
                eNama.setText("");
                eTlahir.setText("");
                rJK.clearCheck(); // Untuk menghapus pilihan di RadioGroup
                eAlamat.setText("");

                // Mungkin Anda ingin menambahkan logika lain di sini, misalnya menampilkan pesan konfirmasi, atau kembali ke halaman sebelumnya.
            }
        });


    }
}