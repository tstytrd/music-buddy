package edu.luc.mb;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import luc.mb.R;

public class MainActivity extends AppCompatActivity {

    private MaterialButton btnMusicToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Start background music service
        startService(new Intent(this, MusicService.class));

        // Mute / Unmute Button Setup
        btnMusicToggle = findViewById(R.id.btn_music_toggle);
        updateMuteButtonUI();

        btnMusicToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toggleIntent = new Intent(MainActivity.this, MusicService.class);
                toggleIntent.setAction("TOGGLE");
                startService(toggleIntent);

                // Small delay to let prefs write, then update UI
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        updateMuteButtonUI();
                        boolean isMuted = MusicService.isMuted(MainActivity.this);
                        Toast.makeText(MainActivity.this, isMuted ? "Music Muted" : "Music Playing", Toast.LENGTH_SHORT).show();
                    }
                }, 100);
            }
        });

        Button a = findViewById(R.id.learn_button);
        a.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, Learn.class);  
                startActivity(a);
            }
        });

        Button test = findViewById(R.id.test_button);
        test.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent b = new Intent(MainActivity.this, Quizzes.class);
                startActivity(b);
                Toast.makeText(getBaseContext(), "Select a Quiz", Toast.LENGTH_SHORT).show();
            }
        });

        Button resources = findViewById(R.id.resources_button);
        resources.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent c = new Intent(MainActivity.this, Resources.class);
                startActivity(c);
            }
        });

        Button contact = findViewById(R.id.contact_button);
        contact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent d = new Intent(MainActivity.this, ContactUs.class);
                startActivity(d);
            }
        });
    }

    private void updateMuteButtonUI() {
        if (btnMusicToggle != null) {
            boolean isMuted = MusicService.isMuted(this);
            if (isMuted) {
                btnMusicToggle.setText("🔇 Unmute");
            } else {
                btnMusicToggle.setText("🔊 Mute");
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateMuteButtonUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_exit) {
            stopService(new Intent(this, MusicService.class));
            finishAffinity();
            return true;
        } else if (id == R.id.music_off) {
            Intent toggleIntent = new Intent(this, MusicService.class);
            toggleIntent.setAction("TOGGLE");
            startService(toggleIntent);
            updateMuteButtonUI();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}