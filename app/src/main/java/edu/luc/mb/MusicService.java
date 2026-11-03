package edu.luc.mb;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.IBinder;
import luc.mb.R.raw;

public class MusicService extends Service {
    private static final String PREFS_NAME = "MusicBuddyPrefs";
    private static final String KEY_MUTED = "is_muted";

    private MediaPlayer mediaPlayer;
    private static boolean isPlaying = false;

    public static boolean isMuted(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        return prefs.getBoolean(KEY_MUTED, false);
    }

    public static void setMuted(Context context, boolean muted) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        prefs.edit().putBoolean(KEY_MUTED, muted).apply();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            mediaPlayer = MediaPlayer.create(this, raw.music_buddy_theme);
            if (mediaPlayer != null) {
                mediaPlayer.setLooping(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && intent.getAction() != null) {
            String action = intent.getAction();
            if ("TOGGLE".equals(action)) {
                boolean muted = !isMuted(this);
                setMuted(this, muted);
                if (muted) {
                    pauseMusic();
                } else {
                    startMusic();
                }
            } else if ("PAUSE".equals(action)) {
                pauseMusic();
            } else if ("RESUME".equals(action)) {
                if (!isMuted(this)) {
                    startMusic();
                }
            }
        } else {
            if (!isMuted(this)) {
                startMusic();
            }
        }
        return START_STICKY;
    }

    private void startMusic() {
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            isPlaying = true;
        }
    }

    private void pauseMusic() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            isPlaying = false;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
            mediaPlayer = null;
        }
        isPlaying = false;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}