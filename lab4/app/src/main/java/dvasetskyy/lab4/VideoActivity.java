package dvasetskyy.lab4;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    VideoView mVideoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        Intent intent = getIntent();
        int trackSource = intent.getIntExtra(MainActivity.TRACK_SOURCE, -1);

        mVideoPlayer = (VideoView)findViewById(R.id.videoPlayer);
        Uri trackUri = Uri.parse( "android.resource://" + getPackageName() + "/" + trackSource);
        mVideoPlayer.setVideoURI(trackUri);

        MediaController mediaController = new MediaController(this);
        mVideoPlayer.setMediaController(mediaController);
        mediaController.setMediaPlayer(mVideoPlayer);

        mVideoPlayer.start();
    }
}
