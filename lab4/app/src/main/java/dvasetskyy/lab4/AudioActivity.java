package dvasetskyy.lab4;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AudioActivity extends AppCompatActivity {

    MediaPlayer mPlayer;
    Button startBtn, pauseBtn, stopBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        Intent intent = getIntent();
        int trackId = intent.getIntExtra(MainActivity.TRACK_SOURCE, -1);

        mPlayer=MediaPlayer.create(this, trackId);
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlay();
            }
        });
        startBtn = (Button) findViewById(R.id.start);
        pauseBtn = (Button) findViewById(R.id.pause);
        stopBtn = (Button) findViewById(R.id.stop);

        pauseBtn.setEnabled(false);
        stopBtn.setEnabled(false);
    }
    private void stopPlay(){
        mPlayer.stop();
        pauseBtn.setEnabled(false);
        stopBtn.setEnabled(false);

        try {
            mPlayer.prepare();
            mPlayer.seekTo(0);
            startBtn.setEnabled(true);
        }
        catch (Throwable t) {
            Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void play(View view){

        mPlayer.start();
        startBtn.setEnabled(false);
        pauseBtn.setEnabled(true);
        stopBtn.setEnabled(true);
    }

    public void pause(View view){

        mPlayer.pause();
        startBtn.setEnabled(true);
        pauseBtn.setEnabled(false);
        stopBtn.setEnabled(true);
    }

    public void stop(View view){
        stopPlay();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPlayer.isPlaying()) {
            stopPlay();
        }
    }
}
