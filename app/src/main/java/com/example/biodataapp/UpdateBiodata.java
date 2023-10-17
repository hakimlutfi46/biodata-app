package com.example.biodataapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class UpdateBiodata extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_biodata);
        getSupportActionBar().setTitle("Update Biodata");
    }
}