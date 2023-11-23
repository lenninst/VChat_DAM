package com.example.vichat;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BuscarActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_buscar);
  }
  public void agregarPerfil(View view) {
    Intent ingresarPerfilIntent = new Intent(this, IngresarPerfiles.class);
    startActivity(ingresarPerfilIntent);
  }

  public void consultarPerfil (View view) {

    EditText nombrePerfil = findViewById(R.id.nickName);
    TextView resultadoTextView = findViewById(R.id.txtResultado);

    DbMyApp dbMyApp = new DbMyApp(this);
    final SQLiteDatabase sqlDB = dbMyApp.getReadableDatabase();

    if (sqlDB != null){
      String[] args = {"%" + nombrePerfil.getText().toString().trim() + "%"};
      Cursor respuestaDb = sqlDB.rawQuery("SELECT nombre, division, descripcion FROM perfil WHERE nombre LIKE ?", args);

      if (respuestaDb.getCount() > 0){
        StringBuilder resultadoString = new StringBuilder();

        while (respuestaDb.moveToNext()) {
          @SuppressLint("Range") String nombre = respuestaDb.getString(respuestaDb.getColumnIndex("nombre"));
          @SuppressLint("Range") String division = respuestaDb.getString(respuestaDb.getColumnIndex("division"));
          @SuppressLint("Range") String descripcion = respuestaDb.getString(respuestaDb.getColumnIndex("descripcion"));

          resultadoString.append("Nombre: ").append(nombre).append("\n");
          resultadoString.append("División: ").append(division).append("\n");
          resultadoString.append("Descripción: ").append(descripcion).append("\n\n");
        }

        resultadoTextView.setText(resultadoString.toString());

      } else {
        Toast.makeText(this,"No se encontraron registros", Toast.LENGTH_LONG).show();
        resultadoTextView.setText("");
      }
      respuestaDb.close();

      Toast.makeText(this,"Consulta exitosa", Toast.LENGTH_LONG).show();

    }

  }

  public void eliminarAmigoId(int perfilId) {
    DbMyApp dbMyApp = new DbMyApp(this);
    SQLiteDatabase db = dbMyApp.getWritableDatabase();

    String consultaSQL = "DELETE FROM perfil WHERE Id = ?";
    String[] argumentos = new String[]{String.valueOf(perfilId)};

    db.execSQL(consultaSQL, argumentos);

    dbMyApp.close();
  }

  public void eliminar(View view) {
    EditText editTextPerfilId = findViewById(R.id.perfilId);

    String perfilIdTexto = editTextPerfilId.getText().toString();

    if (!perfilIdTexto.isEmpty()) {
      int perfilId = Integer.parseInt(perfilIdTexto);

      eliminarAmigoId(perfilId);

      Toast.makeText(this, "Ya no son amigos eliminado correctamente", Toast.LENGTH_SHORT).show();
    } else {
      Toast.makeText(this, "Ingrese un ID de perfil válido", Toast.LENGTH_SHORT).show();
    }
  }


}