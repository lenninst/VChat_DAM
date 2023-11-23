package com.example.vichat;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbMyApp extends SQLiteOpenHelper {
  private static final String tabla_perfil = "CREATE TABLE perfil(Id integer primary key autoincrement," +
      "nombre TEXT," +
      "division TEXT," +
      "descripcion TEXT)" ;
  private static final String db_name = "DbMyApp-sqlite";
  private static final int db_version = 1;

  public DbMyApp (Context context) {
    super(context, db_name, null, db_version);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL(tabla_perfil);

  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int i, int i1) {

  }
}
