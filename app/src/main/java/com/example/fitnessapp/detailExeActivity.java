package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class detailExeActivity extends YouTubeBaseActivity {
    private static final String TAG = "dumbellFlyeActivity";
    private static String videoSrc;
    private static String textDescription;
    YouTubePlayerView youTubePlayerView;
    Button playBtn;
    YouTubePlayer.OnInitializedListener onInitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dumbell_flye);
        setVideo();
        setText();

    }

    private void setVideo(){
        playBtn = findViewById(R.id.playButton);
        youTubePlayerView = findViewById(R.id.youtubePlay);
        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d(TAG, "Onclick");
                youTubePlayer.loadVideo(videoSrc);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Onclick, Intializing YouTube Player");
                youTubePlayerView.initialize(YouTubeConfig.getApiKey(), onInitializedListener);
                Log.d(TAG, "Done initized");
            }
        });
    }

    private void setText(){
        TextView textView = findViewById(R.id.descriptiontView);
        textView.setText(textDescription);
    }

    public static void setTextDescription(String text){
        textDescription = text;
    }

    public static void setVideoSrc(String src){
        videoSrc = src;
    }
}

