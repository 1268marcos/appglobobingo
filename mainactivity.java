package com.example.globobingonovo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView mTextViewNumber, mTextViewListNumber, mTextViewTotalBalls, mTextViewStats;
    private Random rnd = new Random();

    private List<Integer> mListBalls = new ArrayList<Integer>();

    private int mUnitBallsSorted=0;

    private String mLetter;

    private void initializeListBalls(){
        for( int i = 0; i < 75; i++){
            mListBalls.add(0);
        }
    }

    private void rotateGlobe(){
        int randomNumber = rnd.nextInt(75);
        while( !checkNumber(randomNumber) ){
            randomNumber = rnd.nextInt(75);
        }
        mListBalls.set(randomNumber, 1);
        showMessage(randomNumber);
    }

    private boolean checkNumber(int number){
        boolean result = true;
        if(mListBalls.get(number).equals(1)){
            result = false;
        }
        return result;
    }

    public class GlobeClick implements View.OnClickListener{
        @Override
        public void onClick(View view){
            rotateGlobe();
        }
    }

    public String defineLetterColumn( int number ){
        if( number >= 61) {
            mLetter = "O ";
        } else if ( number >= 46) {
            mLetter = "G ";
        } else if( number >= 31) {
            mLetter = "N ";
        } else if ( number >= 16) {
            mLetter = "I ";
        } else {
            mLetter = "B ";
        }
        return mLetter;
    }

    private void showMessage(int randomNumber){
        mUnitBallsSorted++;
        mTextViewTotalBalls.setText("Total de bolas " + String.valueOf(mUnitBallsSorted));
        mTextViewStats.setText("Restam " + String.valueOf( 100-(mUnitBallsSorted*100)/75) + "% bolas");
        if( mUnitBallsSorted == 75) {
            Toast.makeText(this,"Fim", Toast.LENGTH_LONG).show();
            finish();
        }
        mTextViewNumber.setText(defineLetterColumn(randomNumber+1) + String.valueOf(randomNumber+1));
        String listNumber = mTextViewListNumber.getText().toString();
        mTextViewListNumber.setText(String.valueOf(randomNumber+1) + " - " + listNumber);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_globo);

        mTextViewNumber = findViewById(R.id.text_view_number);
        mTextViewNumber.setOnClickListener(new GlobeClick());

        mTextViewListNumber = findViewById(R.id.text_view_list);
        mTextViewTotalBalls = findViewById(R.id.text_view_total_balls);
        mTextViewStats = findViewById(R.id.text_view_stats);
        initializeListBalls();
    }

}
