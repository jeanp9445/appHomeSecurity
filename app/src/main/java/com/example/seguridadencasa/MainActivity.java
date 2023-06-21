package com.example.seguridadencasa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private TextView tv1;
    private EditText et1;
    private VideoView vv1;
    private int videoPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView) findViewById(R.id.tv8);
        et1 = (EditText) findViewById(R.id.password);
        vv1 = (VideoView) findViewById(R.id.vivi1);

        vv1.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.portada));
        vv1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                vv1.start();
                int duration = vv1.getDuration();
                int delay = duration -1500; // Retraso de 1 segundo antes de finalizar el video
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (vv1.isPlaying()) {
                            videoPosition = vv1.getCurrentPosition();
                            vv1.pause();
                        }
                    }
                }, delay);
            }
        });

        vv1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                vv1.seekTo(videoPosition);
            }
        });

        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No se necesita implementar
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // No se necesita implementar
            }

            @Override
            public void afterTextChanged(Editable s) {
                String pin = s.toString();
                if (pin.length() == 6) {

                    Intent siguiente = new Intent(MainActivity.this, DisplayAlarma.class);
                    startActivity(siguiente);

                }
            }
        });
    }

    public void IrValidarPhone(View view){
        Intent intent = new Intent(this, ValidarTelefono.class);
        startActivity(intent);
    }

    public void IrRegistrar(View view){
        Intent intent = new Intent(this, NuevoUsuario.class);
        startActivity(intent);
    }

}