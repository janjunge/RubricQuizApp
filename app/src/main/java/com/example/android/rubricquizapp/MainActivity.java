package com.example.android.rubricquizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    /*
    creating an inline ad-hoc table to store 3 values bound to the id of a view
    in this way being able to store multiple States for one view or view group
    */
    private final int[] viewIds = new int[32];
    private final int[] viewGroupIds = new int[14];
    private final boolean[] correctAnswer = new boolean[32];
    private final boolean[] checkedAnswer = new boolean[32];
    private final boolean[] viewGroupScores = new boolean[14];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // initializing inline ad-hoc db
        initializeKeyAndCorrectAnswer();
        initializeCheckedAnswer();
        initializeKeyAndGroupScores();
    }

    //  set every ad-hoc db value to store whether a box or button was checked to false
    private void initializeCheckedAnswer() {
        for (int i = 0; i < viewIds.length; i++) {
            checkedAnswer[i] = false;
        }
    }

    private void initializeKeyAndGroupScores() {
        viewGroupIds[0] = R.id.a;
        //   b; this would be check box which follows a different logic - to be reevaluated TODO
        viewGroupIds[2] = R.id.c;
        viewGroupIds[3] = R.id.d;
        //  e; this would be check box which follows a different logic - to be reevaluated
        viewGroupIds[5] = R.id.f;
        viewGroupIds[6] = R.id.g;
        viewGroupIds[7] = R.id.h;
        viewGroupIds[8] = R.id.i;
        viewGroupIds[9] = R.id.j;
        viewGroupIds[10] = R.id.k;
        viewGroupIds[11] = R.id.l;
        viewGroupIds[12] = R.id.m;
        viewGroupIds[13] = R.id.n;
        for (int i = 0; i < viewGroupScores.length; i++) {
            viewGroupScores[i] = false;
        }
    }

    //  set every ad-hoc db value to true for the correct answers and false for the others
    private void initializeKeyAndCorrectAnswer() {
        viewIds[0] = R.id.a1;
        correctAnswer[0] = false;
        viewIds[1] = R.id.a2;
        correctAnswer[1] = true;
        viewIds[2] = R.id.b1;
        correctAnswer[2] = true;
        viewIds[3] = R.id.b2;
        correctAnswer[3] = true;
        viewIds[4] = R.id.b3;
        correctAnswer[4] = true;
        viewIds[5] = R.id.b4;
        correctAnswer[5] = false;
        viewIds[6] = R.id.c1;
        correctAnswer[6] = true;
        viewIds[7] = R.id.c2;
        correctAnswer[7] = false;
        viewIds[8] = R.id.d1;
        correctAnswer[8] = true;
        viewIds[9] = R.id.d2;
        correctAnswer[9] = false;
        viewIds[10] = R.id.e1;
        correctAnswer[10] = true;
        viewIds[11] = R.id.e2;
        correctAnswer[11] = true;
        viewIds[12] = R.id.e3;
        correctAnswer[12] = false;
        viewIds[13] = R.id.f1;
        correctAnswer[13] = true;
        viewIds[14] = R.id.f2;
        correctAnswer[14] = false;
        viewIds[15] = R.id.g1;
        correctAnswer[15] = true;
        viewIds[16] = R.id.g2;
        correctAnswer[16] = false;
        viewIds[17] = R.id.h1;
        correctAnswer[17] = true;
        viewIds[18] = R.id.h2;
        correctAnswer[18] = false;
        viewIds[19] = R.id.i1;
        correctAnswer[19] = true;
        viewIds[20] = R.id.i2;
        correctAnswer[20] = false;
        viewIds[21] = R.id.j1;
        correctAnswer[21] = true;
        viewIds[22] = R.id.j2;
        correctAnswer[22] = false;
        viewIds[23] = R.id.k1;
        correctAnswer[23] = true;
        viewIds[24] = R.id.k2;
        correctAnswer[24] = false;
        viewIds[25] = R.id.l1;
        correctAnswer[25] = true;
        viewIds[26] = R.id.l2;
        correctAnswer[26] = false;
        viewIds[27] = R.id.m1;
        correctAnswer[27] = true;
        viewIds[28] = R.id.m2;
        correctAnswer[28] = false;
        viewIds[29] = R.id.n1;
        correctAnswer[29] = true;
        viewIds[31] = R.id.n2;
        correctAnswer[31] = false;
    }

    //  handle button click has two jobs: 1. Add up score 2. compose message
    public void submitButtonClicked(View v) {
        int score = 0;
        EditText editText;
        editText = findViewById(R.id.asking_name_edit_text);
        String message = editText.getText().toString();
        //   As one of the questions should be in an EditText View according to the rubric
        //   a Point should be awarded for entering a name  : )
        if (message.length() > 0) {
            score = 1;
        }
        score = score + calculateCheckboxes();
        score = score + calculateRadios();
        if (score < 15) {
            if (message.length() < 1) {
                message += getText(R.string.score_button_enter_name);
            } else {
                message += ", ";
                message += getText(R.string.score_button_fail);
                message += (15 - score);
            }
        } else {
            message += ", ";
            message += getText(R.string.score_button_win);
            message += score;
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    // get the score from the radioGroups
    private int calculateRadios() {
        int radioScore = 0;
        for (int i = 1; i < viewGroupScores.length; i++) {
            if (viewGroupScores[i]) {
                radioScore += 1;
            }
        }
        return radioScore;
    }

    // get the score from the checkboxes
    private int calculateCheckboxes() {
        int score = 0;
        if (checkedAnswer[getIndex(R.id.b1)]
                && checkedAnswer[getIndex(R.id.b2)]
                && checkedAnswer[getIndex(R.id.b3)]
                && !checkedAnswer[getIndex(R.id.b4)]
                ) {
            score += 1;
        }
        if (checkedAnswer[getIndex(R.id.e1)]
                && checkedAnswer[getIndex(R.id.e2)]
                && !checkedAnswer[getIndex(R.id.e3)]
                ) {
            score += 1;
        }
        return score;
    }

    //  handle radio clicked
    public void radioClicked(View view) {
//        store whether a radioButton is checked
        int currentIndexFromId = getIndex(view.getId());
        checkedAnswer[currentIndexFromId] = true;
//      store the score for the group the button belongs to
        View parentView = (View) view.getParent();
        int currentGroupIndex = getParentIndex(parentView.getId());
        viewGroupScores[currentGroupIndex] = correctAnswer[currentIndexFromId];
    }

    //  handle checkBox  clicked
    public void checkBoxClicked(View view) {
        int currentIndexFromId = getIndex(view.getId());
        checkedAnswer[currentIndexFromId] = true;
    }

    //  get the integer index value that  holds the view key for checkbox and radioButton
    private int getIndex(int viewId) {
        int indexFound = 0;
        for (int i = 1; i < viewIds.length; i++) {
            if (viewIds[i] == viewId) {
                indexFound = i;
            }
        }
        return indexFound;
    }

    //  get the integer index value that  holds the view key radioGroup
    private int getParentIndex(int viewId) {
        int indexFound = 0;
        for (int i = 1; i < viewGroupIds.length; i++) {
            if (viewGroupIds[i] == viewId) {
                indexFound = i;
            }
        }
        return indexFound;
    }
}
