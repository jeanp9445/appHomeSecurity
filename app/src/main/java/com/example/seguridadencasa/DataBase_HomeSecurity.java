package com.example.seguridadencasa;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase_HomeSecurity extends SQLiteOpenHelper {
    public DataBase_HomeSecurity(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Comuna(id int primary key, alias text, zona text, distrito text)");
        db.execSQL("create table Vecino(pin int primary key, nombres text, apellidos text, telefono int,idComuna int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
