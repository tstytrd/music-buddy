package edu.luc.mb;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import luc.mb.R;

public class Resources extends AppCompatActivity {
        
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resources);
        
        setupLinkButton(R.id.music_theory_net, "https://www.musictheory.net");
        setupLinkButton(R.id.teoria_music, "https://www.teoria.com");
        setupLinkButton(R.id.capotastomusic, "https://www.capotastomusic.com/piano-sheet-music/basic.htm");
        setupLinkButton(R.id.ted_ed_music, "https://www.youtube.com/watch?v=ZN41d7Txcq0");
        setupLinkButton(R.id.pre_piano, "https://prepiano.com/");
        setupLinkButton(R.id.imslp_library, "https://imslp.org/");
    }

    private void setupLinkButton(int buttonId, final String url) {
        Button btn = findViewById(buttonId);
        if (btn != null) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                }
            });
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
        
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_exit) {
            finishAffinity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}