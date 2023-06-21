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

public class DisplayAlarma extends AppCompatActivity {

    private Button bt1,bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_alarma);

        bt1 = (Button) findViewById(R.id.bt2);
        bt2 = (Button) findViewById(R.id.bt3);

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
    }
}