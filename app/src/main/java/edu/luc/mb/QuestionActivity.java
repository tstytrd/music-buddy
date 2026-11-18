package edu.luc.mb;

import java.io.InputStream;
import java.util.Arrays;
import luc.mb.R;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.util.Log;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class QuestionActivity extends Activity {

	EditText question = null;
	TextView tvCounter = null;
	RadioButton answer1 = null;
	RadioButton answer2 = null;
	RadioButton answer3 = null;
	RadioButton answer4 = null;
	RadioGroup answers = null;
	Button finish = null;
	int quesIndex = 0;
	int selected[] = null;
	int correctAns[] = null;
	boolean review = false;
	Button prev, next = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question);
		
		View quizLayout = findViewById(R.id.quizLayout);
		quizLayout.setVisibility(android.view.View.INVISIBLE);
		
		try {
			question = (EditText) findViewById(R.id.question);
			tvCounter = (TextView) findViewById(R.id.tv_question_counter);
			answer1 = (RadioButton) findViewById(R.id.a0);
			answer2 = (RadioButton) findViewById(R.id.a1);
			answer3 = (RadioButton) findViewById(R.id.a2);
			answer4 = (RadioButton) findViewById(R.id.a3);
			answers = (RadioGroup) findViewById(R.id.answers);
			Button finish = (Button) findViewById(R.id.finish);
			finish.setOnClickListener(finishListener);
			
			prev = (Button) findViewById(R.id.Prev);
			prev.setOnClickListener(prevListener);
			next = (Button) findViewById(R.id.Next);
			next.setOnClickListener(nextListener);
			
			int totalQuestions = QuizFunActivity.getQuesList().length();
			selected = new int[totalQuestions];
			java.util.Arrays.fill(selected, -1);
			correctAns = new int[totalQuestions];
			java.util.Arrays.fill(correctAns, -1);

			this.showQuestion(0, review);

			quizLayout.setVisibility(android.view.View.VISIBLE);
			
		} catch (Exception e) {
			Log.e("QuestionActivity", e.getMessage(), e);
		} 
	}
	
	private void showQuestion(int qIndex, boolean review) {
		try {
			JSONObject aQues = QuizFunActivity.getQuesList().getJSONObject(qIndex);
			String quesValue = aQues.getString("Question");
			if (correctAns[qIndex] == -1) {
				String correctAnsStr = aQues.getString("CorrectAnswer");
				correctAns[qIndex] = Integer.parseInt(correctAnsStr);
			}
			
			int totalQuestions = QuizFunActivity.getQuesList().length();
			if (tvCounter != null) {
				tvCounter.setText("Question " + (qIndex + 1) + " of " + totalQuestions);
			}
			this.setTitle("Music Theory Quiz (" + (qIndex + 1) + "/" + totalQuestions + ")");
			
			question.setText(quesValue.toCharArray(), 0, quesValue.length());
			answers.check(-1);
			answer1.setTextColor(Color.WHITE);
			answer2.setTextColor(Color.WHITE);
			answer3.setTextColor(Color.WHITE);
			answer4.setTextColor(Color.WHITE);
			
			JSONArray ansList = aQues.getJSONArray("Answers");
			String aAns = ansList.getJSONObject(0).getString("Answer");
			answer1.setText(aAns.toCharArray(), 0, aAns.length());
			aAns = ansList.getJSONObject(1).getString("Answer");
			answer2.setText(aAns.toCharArray(), 0, aAns.length());
			aAns = ansList.getJSONObject(2).getString("Answer");
			answer3.setText(aAns.toCharArray(), 0, aAns.length());
			aAns = ansList.getJSONObject(3).getString("Answer");
			answer4.setText(aAns.toCharArray(), 0, aAns.length());
			
			if (selected[qIndex] == 0) answers.check(R.id.a0);
			if (selected[qIndex] == 1) answers.check(R.id.a1);
			if (selected[qIndex] == 2) answers.check(R.id.a2);
			if (selected[qIndex] == 3) answers.check(R.id.a3);
			
			prev.setEnabled(qIndex > 0);
			next.setEnabled(qIndex < totalQuestions - 1);
			
			if (review) {
				if (selected[qIndex] != correctAns[qIndex]) {
					if (selected[qIndex] == 0) answer1.setTextColor(Color.RED);
					if (selected[qIndex] == 1) answer2.setTextColor(Color.RED);
					if (selected[qIndex] == 2) answer3.setTextColor(Color.RED);
					if (selected[qIndex] == 3) answer4.setTextColor(Color.RED);
				}
				if (correctAns[qIndex] == 0) answer1.setTextColor(Color.GREEN);
				if (correctAns[qIndex] == 1) answer2.setTextColor(Color.GREEN);
				if (correctAns[qIndex] == 2) answer3.setTextColor(Color.GREEN);
				if (correctAns[qIndex] == 3) answer4.setTextColor(Color.GREEN);
			}
		} catch (Exception e) {
			Log.e("QuestionActivity", e.getMessage(), e);
		}
	}

	private OnClickListener finishListener = new OnClickListener() {
		public void onClick(View v) {
			setAnswer();
			int score = 0;
			for (int i = 0; i < correctAns.length; i++) {
				if ((correctAns[i] != -1) && (correctAns[i] == selected[i]))
					score++;
			}
			AlertDialog alertDialog;
			alertDialog = new AlertDialog.Builder(QuestionActivity.this).create();
			alertDialog.setTitle("Score");
			alertDialog.setMessage(score + " out of " + QuizFunActivity.getQuesList().length());
			
			alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Retake", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					review = false;
					quesIndex = 0;
					QuestionActivity.this.showQuestion(0, review);
				}
			});

			alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Review", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					review = true;
					quesIndex = 0;
					QuestionActivity.this.showQuestion(0, review);
				}
			});

			alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Quit", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					review = false;
					finish();
				}
			});

			alertDialog.show();
		}
	};

	private void setAnswer() {
		if (answer1.isChecked()) selected[quesIndex] = 0;
		if (answer2.isChecked()) selected[quesIndex] = 1;
		if (answer3.isChecked()) selected[quesIndex] = 2;
		if (answer4.isChecked()) selected[quesIndex] = 3;
	}
	
	private OnClickListener nextListener = new OnClickListener() {
		public void onClick(View v) {
			setAnswer();
			quesIndex++;
			if (quesIndex >= QuizFunActivity.getQuesList().length())
				quesIndex = QuizFunActivity.getQuesList().length() - 1;
			
			showQuestion(quesIndex, review);
		}
	};

	private OnClickListener prevListener = new OnClickListener() {
		public void onClick(View v) {
			setAnswer();
			quesIndex--;
			if (quesIndex < 0)
				quesIndex = 0;

			showQuestion(quesIndex, review);
		}
	};

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