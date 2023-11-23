package com.example.vichat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void iniciarSesion(View view) {
    Intent buscarIntent = new Intent(this, BuscarActivity.class);
    startActivity(buscarIntent);
  }
}