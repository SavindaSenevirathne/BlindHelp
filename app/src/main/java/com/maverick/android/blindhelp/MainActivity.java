package com.maverick.android.blindhelp;

import android.Manifest;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.pedromassango.doubleclick.DoubleClick;
import com.pedromassango.doubleclick.DoubleClickListener;

public class MainActivity extends AppCompatActivity {

    private Button sinhalaButton;
    private Button englishButton;
    private Button tamilButton;
    private static final int CAMERA_REQUEST = 1888;
    private MediaPlayer mediaPlayer;
    public enum Modes {
        Sinhala,
        English,
        Tamil
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sinhalaButton = (Button)findViewById(R.id.sinhalaBtn);
        englishButton = (Button)findViewById(R.id.englishBtn);
        tamilButton = (Button)findViewById(R.id.tamilButton);

        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            stopAllPlayingSounds();
            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.select_lan_sinhala);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener((v)-> {
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.select_lan_english);
                mediaPlayer.start();
                mediaPlayer = null;
            });
        }, 250);

        if(Build.VERSION.SDK_INT>=23) {
            requestPermissions(new String[] {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
        }

        GlobalVariables.getInstance().currentMode = Modes.Sinhala;

        sinhalaButton.setOnClickListener( new DoubleClick(new DoubleClickListener() {
            @Override
            public void onSingleClick(View view) {
                GlobalVariables.getInstance().currentMode = Modes.Sinhala;
                stopAllPlayingSounds();
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.lan_sinhala);
                mediaPlayer.start();
            }

            @Override
            public void onDoubleClick(View view) {
                GlobalVariables.getInstance().currentMode = Modes.Sinhala;
                stopAllPlayingSounds();
                openCamera();

            }
        }));

        englishButton.setOnClickListener( new DoubleClick(new DoubleClickListener() {
            @Override
            public void onSingleClick(View view) {
                GlobalVariables.getInstance().currentMode = Modes.English;
                stopAllPlayingSounds();
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.lan_english);
                mediaPlayer.start();
            }

            @Override
            public void onDoubleClick(View view) {
                GlobalVariables.getInstance().currentMode = Modes.English;
                stopAllPlayingSounds();
                openCamera();

            }
        }));

        tamilButton.setOnClickListener( new DoubleClick(new DoubleClickListener() {
            @Override
            public void onSingleClick(View view) {
                GlobalVariables.getInstance().currentMode = Modes.Tamil;
                stopAllPlayingSounds();
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.lan_tamil);
                mediaPlayer.start();
            }

            @Override
            public void onDoubleClick(View view) {
                GlobalVariables.getInstance().currentMode = Modes.Tamil;
                stopAllPlayingSounds();
                openCamera();
            }
        }));
    }

    private void stopAllPlayingSounds() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void openCamera() {
        Intent intent = new Intent(MainActivity.this,
                CameraActivity.class);
        startActivity(intent);
    }

}

