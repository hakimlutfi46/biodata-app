package com.example.biodataapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    String[] daftar;
    ListView listView01;
    Menu menu;
    protected Cursor cursor;
    DataHelper dbcenter;
    public static MainActivity main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton btnAdd = findViewById(R.id.buttonAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, BuatBiodata.class);
                startActivity(i);
            }
        });

        main = this;
        dbcenter = new DataHelper(this);
        RefreshList();

    }

        public void RefreshList(){
            SQLiteDatabase db = dbcenter.getReadableDatabase();
            cursor = db.rawQuery("SELECT * FROM biodata", null);
            daftar = new String[cursor.getCount()];
            cursor.moveToFirst();

            for (int cc = 0; cc < cursor.getCount(); cc++){
                cursor.moveToPosition(cc);
                daftar[cc] = cursor.getString(1).toString();
            }

            listView01 = findViewById(R.id.listView1);
            listView01.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, daftar));
            listView01.setSelected(true);

            listView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View arg1, int arg2, long agr3) {
                    final String selection = daftar[arg2];
                    final CharSequence[] dialogitem = {"Lihat Biodata", "Update Biodata", "Hapus Biodata"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Pilihan");

                    builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int item) {
                            switch (item){
                                case 0 :
                                    Intent i = new Intent(getApplicationContext(), LihatBiodata.class);
                                    i.putExtra("nama",selection);
                                    startActivity(i);
                                    break;

                                case 1 :
                                    Intent in = new Intent(getApplicationContext(), UpdateBiodata.class);
                                    in.putExtra("nama",selection);
                                    startActivity(in);
                                    break;

                                case 2 :
                                    SQLiteDatabase db = dbcenter.getWritableDatabase();
                                    db.execSQL("delete from biodata where nama = '"+selection+"' ");
                                    RefreshList();
                                    break;
                            }
                        }
                    });
                    builder.create().show();
                }});
            ((ArrayAdapter<?>)listView01.getAdapter()).notifyDataSetInvalidated();
        }
    }