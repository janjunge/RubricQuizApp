package com.example.android.rubricquizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //  creating an ad-hoc data base to store 3 values bound to one key
    private final int[] keys = new int[32];
    private final int[] groupKeys = new int[14];
    private final boolean[] correctAnswer = new boolean[32];
    private final boolean[] checkedAnswer = new boolean[32];
    private final boolean[] isRadioButton = new boolean[32];
    private final boolean[] groupScores = new boolean[14];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// initializing ad-hoc db
        initializeKeyAndCorrectAnswer();
        initializeCheckedAnswer();
        initializeIsRadioButton();
        initializeKeyAndGroupScores();
    }

    //  set every ad-hoc db value to store whether a box or button was checked to false
    private void initializeCheckedAnswer() {
        for (int i = 0; i < keys.length; i++) {
            checkedAnswer[i] = false;
        }
    }

    //  set every ad-hoc db value to store whether it is a radioButton to false
    private void initializeIsRadioButton() {
        for (int i = 0; i < keys.length; i++) {
            isRadioButton[i] = false;
        }
    }

    //  set every ad-hoc db value to store whether  RadioButton Group scores or not
    private void initializeKeyAndGroupScores() {
        groupKeys[0] = R.id.a;
//       b; this would be check box which follows a different logic - to be reevaluated TODO
        groupKeys[2] = R.id.c;
        groupKeys[3] = R.id.d;
//      e; this would be check box which follows a different logic - to be reevaluated
        groupKeys[5] = R.id.f;
        groupKeys[6] = R.id.g;
        groupKeys[7] = R.id.h;
        groupKeys[8] = R.id.i;
        groupKeys[9] = R.id.j;
        groupKeys[10] = R.id.k;
        groupKeys[11] = R.id.l;
        groupKeys[12] = R.id.m;
        groupKeys[13] = R.id.n;
        for (int i = 0; i < groupScores.length; i++) {
            groupScores[i] = false;
        }

    }

    //  set every ad-hoc db value to true for the correct answers and false for the others
    private void initializeKeyAndCorrectAnswer() {
        keys[0] = R.id.a1;
        correctAnswer[0] = false;
        keys[1] = R.id.a2;
        correctAnswer[1] = true;
        keys[2] = R.id.b1;
        correctAnswer[2] = true;
        keys[3] = R.id.b2;
        correctAnswer[3] = true;
        keys[4] = R.id.b3;
        correctAnswer[4] = true;
        keys[5] = R.id.b4;
        correctAnswer[5] = false;
        keys[6] = R.id.c1;
        correctAnswer[6] = true;
        keys[7] = R.id.c2;
        correctAnswer[7] = false;
        keys[8] = R.id.d1;
        correctAnswer[8] = true;
        keys[9] = R.id.d2;
        correctAnswer[9] = false;
        keys[10] = R.id.e1;
        correctAnswer[10] = true;
        keys[11] = R.id.e2;
        correctAnswer[11] = true;
        keys[12] = R.id.e3;
        correctAnswer[12] = false;
        keys[13] = R.id.f1;
        correctAnswer[13] = true;
        keys[14] = R.id.f2;
        correctAnswer[14] = false;
        keys[15] = R.id.g1;
        correctAnswer[15] = true;
        keys[16] = R.id.g2;
        correctAnswer[16] = false;
        keys[17] = R.id.h1;
        correctAnswer[17] = true;
        keys[18] = R.id.h2;
        correctAnswer[18] = false;
        keys[19] = R.id.i1;
        correctAnswer[19] = true;
        keys[20] = R.id.i2;
        correctAnswer[20] = false;
        keys[21] = R.id.j1;
        correctAnswer[21] = true;
        keys[22] = R.id.j2;
        correctAnswer[22] = false;
        keys[23] = R.id.k1;
        correctAnswer[23] = true;
        keys[24] = R.id.k2;
        correctAnswer[24] = false;
        keys[25] = R.id.l1;
        correctAnswer[25] = true;
        keys[26] = R.id.l2;
        correctAnswer[26] = false;
        keys[27] = R.id.m1;
        correctAnswer[27] = true;
        keys[28] = R.id.m2;
        correctAnswer[28] = false;
        keys[29] = R.id.n1;
        correctAnswer[29] = true;
        keys[31] = R.id.n2;
        correctAnswer[31] = false;
    }

    //  handle button click has two jobs: 1. Add up score 2. compose message
    public void submitButtonClicked(View v) {
        int score = 0;
        EditText editText;
        editText = findViewById(R.id.asking_name_edit_text);
        String message = editText.getText().toString();
//      As one of the questions should be in an EditText View according to the rubric
//      a Point should be awarded for entering a name  : )
        if (message.length() > 0) {
            score = 1;
        }
        score = score + calculateCheckboxes();
        score = score + calculateRadios();
        if (score < 15) {
            if (message.length() < 1) {
                message += getText(R.string.score_button_name_missing);
            } else {
                message += ", ";
                message += getText(R.string.score_button_result_wrong);
                message += (15 - score);
            }
        } else {
            message += ", ";
            message += getText(R.string.score_button_result_right);
            message += score;
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    // get the score from the radioGroups
    private int calculateRadios() {
        int radioScore = 0;
        for (int i = 1; i < groupScores.length; i++) {
            if (groupScores[i]) {
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
        isRadioButton[currentIndexFromId] = true;
        checkedAnswer[currentIndexFromId] = true;
//      store the score for the group the button belongs to
        View parentView = (View) view.getParent();
        int currentGroupIndex = getParentIndex(parentView.getId());
        groupScores[currentGroupIndex] = correctAnswer[currentIndexFromId];
    }

    //  handle checkBox  clicked
    public void checkBoxClicked(View view) {
        int currentIndexFromId = getIndex(view.getId());
        isRadioButton[currentIndexFromId] = false;
        checkedAnswer[currentIndexFromId] = true;
    }

    //  get the integer index value that  holds the view key for checkbox and radioButton
    private int getIndex(int viewId) {
        int indexFound = 0;
        for (int i = 1; i < keys.length; i++) {
            if (keys[i] == viewId) {
                indexFound = i;
            }
        }
        return indexFound;
    }

    //  get the integer index value that  holds the view key radioGroup
    private int getParentIndex(int viewId) {
        int indexFound = 0;
        for (int i = 1; i < groupKeys.length; i++) {
            if (groupKeys[i] == viewId) {
                indexFound = i;
            }
        }
        return indexFound;
    }
}
