package com.example.seguridadencasa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.content.Intent;
import android.widget.Toast;
import android.widget.VideoView;

public class NuevoUsuario extends AppCompatActivity {

    private VideoView vv2;
    private Spinner spin1;
    private CheckBox cbox;
    private EditText nombres, apells, telef, pin;
    private int videoPosition;

    private String cComunas[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_usuario);

        spin1 = (Spinner) findViewById(R.id.spin1);
        cbox = (CheckBox) findViewById(R.id.box1);
        vv2 = (VideoView) findViewById(R.id.vv2);
        nombres = (EditText) findViewById(R.id.et13);
        apells = (EditText) findViewById(R.id.et14);
        telef = (EditText) findViewById(R.id.fono1);
        pin = (EditText) findViewById(R.id.pin2);


        vv2.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.banner));

        vv2.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                vv2.start();
                int duration = vv2.getDuration();
                int delay = duration -1500; // Retraso de 1 segundo antes de finalizar el video
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (vv2.isPlaying()) {
                            videoPosition = vv2.getCurrentPosition();
                            vv2.pause();
                        }
                    }
                }, delay);
            }
        });

        vv2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                vv2.seekTo(videoPosition);
            }
        });

        String [] comunas = {"Crear nueva comuna"};
        llenarcomunas(comunas);
        cComunas = comunas;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, comunas);
        spin1.setAdapter(adapter);

    }

    public void CrearRegistro(View view) {
        String names = nombres.getText().toString();
        String lastname = apells.getText().toString();
        String phone = telef.getText().toString();
        String clave = pin.getText().toString();

        if(!names.isEmpty() && !lastname.isEmpty() && !phone.isEmpty() && !clave.isEmpty()){
            if(cbox.isChecked()){
                String selection = spin1.getSelectedItem().toString();

                if(selection.equals("Crear nueva comuna")){
                    Intent intent = new Intent(this, NuevaComuna.class);
                    intent.putExtra("envio1",names);
                    intent.putExtra("envio2",lastname);
                    intent.putExtra("envio3",phone);
                    intent.putExtra("envio4",clave);
                    startActivity(intent);  // Se direcciona para crear una comunidad nueva en la tabla "Comunidad"
                    finish();
                }

                int nclave = Integer.parseInt(clave);
                int nphone = Integer.parseInt(phone);
                int idCo = obtenerId(selection);

                RegistrarVecino(nclave,names,lastname,nphone,idCo); // Registramos en la tabla "Vecino"

                Toast.makeText(this,"Registro Exitoso", Toast.LENGTH_SHORT).show();
                Toast.makeText(this,"Puedes iniciar sesión", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(this, "Debes aceptar los términos y condiciones", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this,"Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }

    }

    public void RegistrarVecino(int clave, String nombre, String apellido, int celular, int idComuna){
        String pass = Integer.toString(clave);
        String cel = Integer.toString(celular);
        String ic = Integer.toString(idComuna);

        DataBase_HomeSecurity bd = new DataBase_HomeSecurity(this, "Administracion", null, 1);
        SQLiteDatabase gestor = bd.getWritableDatabase();

        ContentValues lapiz = new ContentValues();
        lapiz.put("pin",pass);
        lapiz.put("nombres",nombre);
        lapiz.put("apellidos",apellido);
        lapiz.put("telefono",cel);
        lapiz.put("idComuna",ic);

        gestor.insert("Vecino", null, lapiz);

        gestor.close();
        lapiz.clear();
    }

    public int obtenerId(String alia){
        DataBase_HomeSecurity bd = new DataBase_HomeSecurity(this, "Administracion", null, 1);
        SQLiteDatabase gestor = bd.getWritableDatabase();

        Cursor fila = gestor.rawQuery("Select id from Comuna where alias="+alia, null);
        gestor.close();

        return fila.getInt(0);
    }

    public void llenarcomunas(String[] comunas){
        int i=1;

        DataBase_HomeSecurity bd = new DataBase_HomeSecurity(this, "Administracion", null, 1);
        SQLiteDatabase gestor = bd.getWritableDatabase();

        Cursor fila = gestor.rawQuery("Select alias from Comuna", null);

        if(fila.moveToFirst()){
            do{
                comunas[i] = fila.getString(0);
                i++;
            }while(fila.moveToNext());

        }

        fila.close();
        gestor.close();
    }

    @Override
    public void onBackPressed(){

    }
}