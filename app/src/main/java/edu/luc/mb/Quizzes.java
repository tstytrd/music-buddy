package edu.luc.mb;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import luc.mb.R;

public class Quizzes extends AppCompatActivity {

    final Context context = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizzes);

        Button btnTheory = findViewById(R.id.btn_quiz_theory);
        if (btnTheory != null) {
            btnTheory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent b = new Intent(Quizzes.this, QuizFunActivity.class);
                    startActivity(b);
                    Toast.makeText(getBaseContext(), "Are you ready?", Toast.LENGTH_SHORT).show();
                }
            });
        }

        Button btnNotes = findViewById(R.id.btn_quiz_notes);
        if (btnNotes != null) {
            btnNotes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(context);
                    alert.setTitle("Name the Notes");
                    alert.setMessage("This quiz is currently in development, please come back at a later time, thank you!");
                    alert.setPositiveButton("OK", null);
                    alert.show();
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