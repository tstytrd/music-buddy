package edu.luc.mb;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.Assert.*;

public class QuestionsDataUnitTest {

    @Test
    public void testQuestionsJsonValidAndStructured() throws Exception {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("raw/questions.txt");
        if (stream == null) {
            // Also try relative to project root
            stream = new java.io.FileInputStream("src/main/res/raw/questions.txt");
        }
        assertNotNull("questions.txt should exist in raw resources", stream);

        Scanner scanner = new Scanner(stream, StandardCharsets.UTF_8.name()).useDelimiter("\\A");
        String jsonText = scanner.hasNext() ? scanner.next() : "";
        scanner.close();

        JSONObject root = new JSONObject(jsonText);
        assertTrue("JSON should contain Questions array", root.has("Questions"));

        JSONArray questions = root.getJSONArray("Questions");
        assertTrue("Should contain at least one question", questions.length() > 0);

        for (int i = 0; i < questions.length(); i++) {
            JSONObject q = questions.getJSONObject(i);
            assertTrue("Question " + i + " must have 'Question' text", q.has("Question"));
            assertFalse("Question text should not be empty", q.getString("Question").trim().isEmpty());

            assertTrue("Question " + i + " must have 'CorrectAnswer'", q.has("CorrectAnswer"));
            int correctIndex = q.getInt("CorrectAnswer");

            assertTrue("Question " + i + " must have 'Answers' array", q.has("Answers"));
            JSONArray answers = q.getJSONArray("Answers");
            assertEquals("Question " + i + " must have 4 choices", 4, answers.length());

            assertTrue("Correct answer index must be between 0 and 3", correctIndex >= 0 && correctIndex < 4);
        }
    }
}