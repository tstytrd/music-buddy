package edu.luc.mb;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import luc.mb.R;

import org.json.JSONObject;
import org.json.JSONArray;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class QuizFunActivity extends Activity {
    BufferedReader bReader = null;
    static JSONArray quesList = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        
        Thread thread = new Thread() {
            public void run() {
                try {
                    Thread.sleep(1500);
                    finish();
                    loadQuestions();
                    Intent intent = new Intent(QuizFunActivity.this, QuestionActivity.class);
                    QuizFunActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            QuizFunActivity.this.startActivity(intent);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                } 
            }
        };
        thread.start();
    }
    
    private void loadQuestions() {
        try {
            InputStream questions = this.getBaseContext().getResources()
                    .openRawResource(R.raw.questions);
            bReader = new BufferedReader(new InputStreamReader(questions));
            StringBuilder quesString = new StringBuilder();
            String aJsonLine = null;
            while ((aJsonLine = bReader.readLine()) != null) {
                quesString.append(aJsonLine);
            }
            
            JSONObject quesObj = new JSONObject(quesString.toString());
            JSONArray originalArray = quesObj.getJSONArray("Questions");
            
            // Randomize questions order
            List<JSONObject> list = new ArrayList<JSONObject>();
            for (int i = 0; i < originalArray.length(); i++) {
                list.add(originalArray.getJSONObject(i));
            }
            Collections.shuffle(list);
            
            quesList = new JSONArray();
            for (JSONObject item : list) {
                quesList.put(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bReader != null) {
                    bReader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static JSONArray getQuesList() {
        return quesList;
    }
}