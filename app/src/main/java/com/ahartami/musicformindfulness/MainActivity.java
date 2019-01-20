package com.ahartami.musicformindfulness;

import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import co.mobiwise.library.InteractivePlayerView;
import co.mobiwise.library.OnActionClickedListener;

public class MainActivity extends AppCompatActivity implements OnActionClickedListener {

    private InteractivePlayerView ipv;
    private Button control;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.morninghappinesbygustedja);

        // to call music from raw
        Uri uri = Uri.parse("android.resource://com.ahartami.musicformindfulness/raw/morninghappinessbygustedja");

        // to measure the music duration
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(MainActivity.this, uri);
        String durationString = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        int milli = Integer.parseInt(durationString);
        milli = milli/1000;

        ipv = (InteractivePlayerView) findViewById(R.id.ipv);
        ipv.setMax(milli);
        ipv.setProgress(0);
        ipv.setOnActionClickedListener(this);

        control = (Button)findViewById(R.id.btnPlay);
        control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ipv.isPlaying()){
                    ipv.start();
                    control.setText("PAUSE");
                    mediaPlayer.start();
                }
                else{
                    ipv.stop();
                    control.setText("PLAY");
                    mediaPlayer.pause();
                }
            }
        });

    }


    @Override
    public void onActionClicked(int id) {
        switch (id){
            case 1:
                Toast.makeText(MainActivity.this, "Shuffled", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(MainActivity.this, "Love", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(MainActivity.this, "Repeat", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

}




