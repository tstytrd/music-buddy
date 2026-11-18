package edu.luc.mb;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import luc.mb.R;

public class NameTheNotesActivity extends AppCompatActivity {

    public static class NoteQuestion {
        public String clefTitle;
        public String prompt;
        public int drawableResId;
        public double frequencyHz;
        public String[] options;
        public int correctIndex;
        public String explanation;

        public NoteQuestion(String clefTitle, String prompt, int drawableResId, double frequencyHz, String[] options, int correctIndex, String explanation) {
            this.clefTitle = clefTitle;
            this.prompt = prompt;
            this.drawableResId = drawableResId;
            this.frequencyHz = frequencyHz;
            this.options = options;
            this.correctIndex = correctIndex;
            this.explanation = explanation;
        }
    }

    private NoteQuestion[] questions;
    private int currentIndex = 0;
    private int[] userAnswers;
    private int score = 0;

    private TextView tvClefBadge;
    private TextView tvQuestionPrompt;
    private ImageView ivNoteImage;
    private MaterialButton btnPlayAudio;
    private RadioGroup rgAnswers;
    private RadioButton[] radioOptions;
    private MaterialButton btnPrev;
    private MaterialButton btnNext;
    private MaterialButton btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_the_notes);

        initQuestions();
        userAnswers = new int[questions.length];
        for (int i = 0; i < userAnswers.length; i++) {
            userAnswers[i] = -1;
        }

        tvClefBadge = findViewById(R.id.tv_clef_badge);
        tvQuestionPrompt = findViewById(R.id.tv_question_prompt);
        ivNoteImage = findViewById(R.id.iv_note_image);
        btnPlayAudio = findViewById(R.id.btn_play_audio);
        rgAnswers = findViewById(R.id.rg_answers);

        radioOptions = new RadioButton[4];
        radioOptions[0] = findViewById(R.id.rb_option_0);
        radioOptions[1] = findViewById(R.id.rb_option_1);
        radioOptions[2] = findViewById(R.id.rb_option_2);
        radioOptions[3] = findViewById(R.id.rb_option_3);

        btnPrev = findViewById(R.id.btn_prev);
        btnNext = findViewById(R.id.btn_next);
        btnSubmit = findViewById(R.id.btn_submit);

        btnPlayAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NoteSynthesizer.playFrequency(questions[currentIndex].frequencyHz, 900);
            }
        });

        rgAnswers.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < radioOptions.length; i++) {
                    if (radioOptions[i].getId() == checkedId) {
                        userAnswers[currentIndex] = i;
                        break;
                    }
                }
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex > 0) {
                    currentIndex--;
                    displayQuestion(currentIndex);
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex < questions.length - 1) {
                    currentIndex++;
                    displayQuestion(currentIndex);
                }
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishQuiz();
            }
        });

        displayQuestion(0);
    }

    private void initQuestions() {
        questions = new NoteQuestion[] {
            new NoteQuestion(
                "🎼 Treble Clef",
                "Listen to the note and identify this pitch on the Treble Clef (Middle C):",
                R.drawable.treb_middle_c,
                261.63, // C4
                new String[] { "Middle C (C4)", "Treble E (First Line)", "Treble G (Second Line)", "High C (C5)" },
                0,
                "Middle C sits on a ledger line below the treble staff."
            ),
            new NoteQuestion(
                "🎼 Treble Clef",
                "Listen to the pitch. Which note is shown on the 2nd line of the Treble staff?",
                R.drawable.treb_g,
                392.00, // G4
                new String[] { "Treble F (First Space)", "Treble G (Second Line - Treble Clef reference)", "Treble B (Third Line)", "Treble D (Fourth Line)" },
                1,
                "The treble clef curls around the second line, defining it as G4."
            ),
            new NoteQuestion(
                "🎼 Treble Clef",
                "Listen to this higher tone. What note is sitting in the second space?",
                R.drawable.treb_a,
                440.00, // A4 (Standard Concert Pitch)
                new String[] { "E4", "F4", "A4 (Second Space - 440 Hz)", "C5" },
                2,
                "F-A-C-E spells the spaces in the treble clef. A sits in the 2nd space."
            ),
            new NoteQuestion(
                "🎵 Bass Clef",
                "Listen to this low pitch. Which note is shown on the 4th line of the Bass clef?",
                R.drawable.bass_f,
                174.61, // F3
                new String[] { "Bass G (First Line)", "Bass D (Third Line)", "Bass F (Fourth Line - Bass Clef reference)", "Bass C (Second Space)" },
                2,
                "The two dots of the Bass (F) clef surround the fourth line, marking it as F."
            ),
            new NoteQuestion(
                "🎵 Bass Clef",
                "Listen to this pitch. Identify the note sitting on the first line of the Bass clef:",
                R.drawable.bass_g,
                98.00, // G2
                new String[] { "Bass G (First Line)", "Bass B (Second Line)", "Bass E (Third Space)", "Bass A (Top Line)" },
                0,
                "Good Boys Do Fine Always: G is the first line of the bass staff."
            ),
            new NoteQuestion(
                "🎼 Clef Comparison",
                "Listen to the tone. Which note connects both clefs as the central reference?",
                R.drawable.bass_middle_c,
                261.63, // Middle C
                new String[] { "Bass Low C", "Middle C (Ledger line between clefs)", "Treble High C", "Bass F" },
                1,
                "Middle C connects the bass and treble staves right in the center."
            )
        };
    }

    private void displayQuestion(int index) {
        NoteQuestion q = questions[index];
        tvClefBadge.setText(q.clefTitle);
        tvQuestionPrompt.setText((index + 1) + ". " + q.prompt);
        ivNoteImage.setImageResource(q.drawableResId);

        rgAnswers.setOnCheckedChangeListener(null);
        rgAnswers.clearCheck();

        for (int i = 0; i < radioOptions.length; i++) {
            radioOptions[i].setText(q.options[i]);
            radioOptions[i].setTextColor(Color.parseColor("#F8FAFC"));
        }

        if (userAnswers[index] >= 0 && userAnswers[index] < 4) {
            radioOptions[userAnswers[index]].setChecked(true);
        }

        rgAnswers.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < radioOptions.length; i++) {
                    if (radioOptions[i].getId() == checkedId) {
                        userAnswers[currentIndex] = i;
                        break;
                    }
                }
            }
        });

        btnPrev.setEnabled(index > 0);
        if (index == questions.length - 1) {
            btnNext.setVisibility(View.GONE);
            btnSubmit.setVisibility(View.VISIBLE);
        } else {
            btnNext.setVisibility(View.VISIBLE);
            btnSubmit.setVisibility(View.GONE);
        }

        // Automatically play the note when displaying the question so user immediately hears it
        NoteSynthesizer.playFrequency(q.frequencyHz, 850);
    }

    private void finishQuiz() {
        score = 0;
        for (int i = 0; i < questions.length; i++) {
            if (userAnswers[i] == questions[i].correctIndex) {
                score++;
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Quiz Results");
        builder.setMessage("You scored " + score + " out of " + questions.length + "!\n\n"
                + (score == questions.length ? "🌟 Perfect pitch and notation reading!" : "Keep practicing to master ear and clef reading."));
        builder.setPositiveButton("Review Answers", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                reviewAnswers();
            }
        });
        builder.setNegativeButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    private void reviewAnswers() {
        currentIndex = 0;
        displayQuestion(0);
        showReviewHighlights(0);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex < questions.length - 1) {
                    currentIndex++;
                    displayQuestion(currentIndex);
                    showReviewHighlights(currentIndex);
                }
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex > 0) {
                    currentIndex--;
                    displayQuestion(currentIndex);
                    showReviewHighlights(currentIndex);
                }
            }
        });

        btnSubmit.setText("Finish Review");
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void showReviewHighlights(int index) {
        NoteQuestion q = questions[index];
        int userChoice = userAnswers[index];
        int correct = q.correctIndex;

        for (int i = 0; i < radioOptions.length; i++) {
            if (i == correct) {
                radioOptions[i].setTextColor(Color.parseColor("#4ADE80")); // Green for correct
            } else if (i == userChoice) {
                radioOptions[i].setTextColor(Color.parseColor("#F87171")); // Red for incorrect
            } else {
                radioOptions[i].setTextColor(Color.parseColor("#64748B"));
            }
        }

        Toast.makeText(this, q.explanation, Toast.LENGTH_LONG).show();
    }
}