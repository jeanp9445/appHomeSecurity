package com.example.seguridadencasa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DisplayAlarma extends AppCompatActivity {

    private Button bt1,bt2;
    //private TextView tv1, tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_alarma);

        bt1 = (Button) findViewById(R.id.bt2);
        bt2 = (Button) findViewById(R.id.bt3);
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

        //String nomb = getIntent().getStringExtra("nbs");
        //String apl = getIntent().getStringExtra("apll");
        //String ico = getIntent().getStringExtra("idc");

        //tv1.setText(nomb);
    }
}