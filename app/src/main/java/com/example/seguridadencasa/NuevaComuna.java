package com.example.seguridadencasa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NuevaComuna extends AppCompatActivity {

    private EditText ali, direcc, dist;
    private String nombre, apellido, telefono, clave;
    private String alias, direccion, distrito;
    String cantComuna,idco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_comuna);

        ali = (EditText) findViewById(R.id.alias);
        direcc = (EditText) findViewById(R.id.direccion);
        dist = (EditText) findViewById(R.id.distrito);

        nombre = getIntent().getStringExtra("envio1");
        apellido = getIntent().getStringExtra("envio2");
        telefono = getIntent().getStringExtra("envio3");
        clave = getIntent().getStringExtra("envio4");

    }

    public void crearRegistro(View view){
        alias = ali.getText().toString();
        direccion = direcc.getText().toString();
        distrito = dist.getText().toString();

        DataBase_HomeSecurity bd = new DataBase_HomeSecurity(this, "Administracion", null, 1);
        SQLiteDatabase gestor = bd.getWritableDatabase();

        if(!alias.isEmpty() && !direccion.isEmpty() && !distrito.isEmpty()){
            ContentValues lapiz = new ContentValues();
            lapiz.put("id",idComuna());
            lapiz.put("alias",alias);
            lapiz.put("zona",direccion);
            lapiz.put("distrito",distrito);

            gestor.insert("Comuna",null,lapiz);
            gestor.close();
            lapiz.clear();
        }else{
            Toast.makeText(this, "Debes de completar los campos", Toast.LENGTH_SHORT).show();
        }

        crearUsuario();
        Toast.makeText(this,"Registro Exitoso", Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"Puedes iniciar sesión", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void crearUsuario(){
        DataBase_HomeSecurity bd = new DataBase_HomeSecurity(this, "Administracion", null, 1);
        SQLiteDatabase gestor = bd.getWritableDatabase();

        ContentValues lapiz = new ContentValues();
        lapiz.put("pin",clave);
        lapiz.put("nombres",nombre);
        lapiz.put("apellidos",apellido);
        lapiz.put("telefono",telefono);
        lapiz.put("idComuna",idco);

        gestor.insert("Vecino", null, lapiz);

        gestor.close();
        lapiz.clear();
    }

    public String idComuna(){
        DataBase_HomeSecurity bd = new DataBase_HomeSecurity(this, "Administracion", null, 1);
        SQLiteDatabase gestor = bd.getWritableDatabase();

        Cursor fila = gestor.rawQuery("Select id from Comuna", null);

        if(fila.moveToFirst()){
            do{
                cantComuna = fila.getString(0);
            }while(fila.moveToNext());
        }else{
            return "0";
        }
        gestor.close();
        fila.close();

        int cs = Integer.parseInt(cantComuna);
        cs++;

        idco = Integer.toString(cs);

        return Integer.toString(cs);
    }

    @Override
    public void onBackPressed(){

    }
}