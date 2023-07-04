package com.example.seguridadencasa;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DisplayAlarma extends AppCompatActivity {

    private Button bt1,bt2, bt3;
    //private TextView tv1, tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_alarma);

        bt1 = (Button) findViewById(R.id.bt2);
        bt2 = (Button) findViewById(R.id.bt3);
        bt3 = (Button) findViewById(R.id.bt4);
        //tv1 = (TextView) findViewById(R.id.deNom);
        //tv2 = (TextView) findViewById(R.id.nomCom);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "105";
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+phoneNumber));

                if (ActivityCompat.checkSelfPermission(DisplayAlarma.this, Manifest.permission.CALL_PHONE)
                        == PackageManager.PERMISSION_GRANTED) {
                    startActivity(intent);
                } else {
                    ActivityCompat.requestPermissions(DisplayAlarma.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            1);
                }

        }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "012096000";
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+phoneNumber));

                if (ActivityCompat.checkSelfPermission(DisplayAlarma.this, Manifest.permission.CALL_PHONE)
                        == PackageManager.PERMISSION_GRANTED) {
                    startActivity(intent);
                } else {
                    ActivityCompat.requestPermissions(DisplayAlarma.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            1);
                }

            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmDialog();
            }
        });
        //String nomb = getIntent().getStringExtra("nbs");
        //String apl = getIntent().getStringExtra("apll");
        //String ico = getIntent().getStringExtra("idc");

        //tv1.setText(nomb);
    }
    private void showConfirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cerrar sesión");
        builder.setMessage("¿Estás seguro de cerrar sesión?");
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Regresar a la pantalla de inicio de sesión
                Intent intent = new Intent(DisplayAlarma.this, MainActivity.class);
                startActivity(intent);
                finish(); // Cierra la actividad actual (DisplayAlarma)
            }
        });
        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }
}