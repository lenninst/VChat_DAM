package com.example.vichat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class IngresarPerfiles extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_ingresar_perfiles);
  }

  public void agregarNuevoPerfil(View view) {

    CheckBox checkBoxPoliticas = (CheckBox) findViewById(R.id.checkBoxPoliticas);

    if (checkBoxPoliticas.isChecked()) {

      String mensaje = " ";

      EditText nombre_tmp = (EditText) findViewById(R.id.editNombre);
      EditText division_tmp = (EditText) findViewById(R.id.editDivision);
      EditText descripcion_tmp = (EditText) findViewById(R.id.editDescripcion);

      String nombre = nombre_tmp.getText().toString();
      String division = division_tmp.getText().toString();
      String descripcion = descripcion_tmp.getText().toString();

      almacenarDB(nombre, division, descripcion);

      Toast.makeText(view.getContext(), "Los datos se ingresaron correctamente ", Toast.LENGTH_LONG).show();



    } else {
      Toast.makeText(view.getContext(), " Acepte nuestras politicas ", Toast.LENGTH_LONG).show();
    }

  }
  public void almacenarDB(String nombre, String division, String descripcion) {
    DbMyApp dbMyApp = new DbMyApp(this);
    final SQLiteDatabase sqlDB = dbMyApp.getWritableDatabase();

    if (sqlDB != null){
      ContentValues contentValues = new ContentValues();
      contentValues.put("nombre", nombre);
      contentValues.put("division", division);
      contentValues.put("descripcion", descripcion);

      sqlDB.insert("perfil", null, contentValues);
      Toast.makeText(this, "Se insertaron los datos en ls DB", Toast.LENGTH_LONG).show();

      dbMyApp.close();
    }

  }
}