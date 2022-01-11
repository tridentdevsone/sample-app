package com.polass.inaks.playingto;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.polass.inaks.R;

import java.util.Arrays;
import java.util.Collections;

public class SecondActivity extends AppCompatActivity {


    private double mistakeCounter,correctCounter;
    int numImg[] = new int[10];
    int clickFirst,clickSecond,numberOfRows,numberOfCols;
    int cardNumber = 1;
    ImageView firstClick,secondclick;
    ImageView imgArr[] = new ImageView[12];
    boolean isClicked = false;
    Switch switchMod;
    ProgressBar progressBar1,progressBar2;
    ImageButton buttonArr [];
    Integer cards[];
    TableLayout table;
    TextView textView1,textView2;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_second);

        String Rows = getIntent().getStringExtra("Rows");
        String Cols = getIntent().getStringExtra("Cols");

        numberOfRows = Integer.parseInt(Rows);
        numberOfCols = Integer.parseInt(Cols);

        buttonArr = new ImageButton[numberOfRows*numberOfCols];
        switchMod = (Switch) findViewById(R.id.switch1);
        progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
        progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        progressBar1.getProgressDrawable().setColorFilter(
                Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
        progressBar2.getProgressDrawable().setColorFilter(
                Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        textView1 = (TextView)findViewById(R.id.textBar1);
        textView2 = (TextView)findViewById(R.id.textbar2);

        switchMod.setOnClickListener(new View.OnClickListener() {
            ConstraintLayout c = (ConstraintLayout)findViewById(R.id.main);
            @Override
            public void onClick(View v) {
                if(switchMod.isChecked()){
                    c.setBackgroundResource(R.color.colorPrimaryDark);
                    switchMod.setTextColor(getResources().getColor(R.color.white));
                    textView1.setTextColor(getResources().getColor(R.color.white));
                    textView2.setTextColor(getResources().getColor(R.color.white));
                }
                else {
                    c.setBackgroundResource(R.drawable.backs_fon);
                    switchMod.setTextColor(getResources().getColor(R.color.black));
                    textView2.setTextColor(getResources().getColor(R.color.black));
                    textView1.setTextColor(getResources().getColor(R.color.black));


                }

            }
        });
        newGame();

    }


    private void newGame(){
        mistakeCounter = 0;
        correctCounter = 0;

        cards= new Integer[numberOfRows*numberOfCols];
        for(int i=0 ; i<numberOfRows*numberOfCols/2 ;i++){
            cards[i*2] = i;
            cards[i*2+1] = i;
        }
        Collections.shuffle(Arrays.asList(cards));

        table = (TableLayout)findViewById(R.id.table);
        int  counter = 0;

        for(int row = 0 ; row < numberOfRows ; row++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            table.addView(tableRow);


            for (int col = 0; col < numberOfCols; col++) {
                int finalI = counter;
                buttonArr[counter] = new ImageButton(this);
                buttonArr[counter].setTag(counter);
                buttonArr[counter].setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));
                buttonArr[counter].setScaleType(ImageView.ScaleType.FIT_CENTER);
                buttonArr[counter].setBackground(null);


                buttonArr[counter].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v){
                        String tag = String.valueOf(v.getTag());
                        doStuff(buttonArr[finalI],tag);
                    }
                });
                buttonArr[counter].setImageResource(R.drawable.p);
                tableRow.addView(buttonArr[counter]);
                counter++;
            }

        }
        frontofcard();

    }



    private void doStuff(ImageButton img,String tag) {
        int save = 0;
        if(!isClicked) {
            for (int i = 0; i < numberOfRows*numberOfCols; i++) {
                if (tag == String.valueOf(i)) {
                    img.setImageResource(numImg[cards[i]]);
                    save = cards[i];
                }
            }

            if (cardNumber == 1) {
                firstClick = img;
                clickFirst = save;
                cardNumber++;
                img.setEnabled(false);
            } else {
                secondclick = img;
                clickSecond = save;
                cardNumber--;
                img.setEnabled(false);
                isClicked = true;
                checkIfPair(firstClick, secondclick);
            }

        }
    }


    private void checkIfPair(ImageView firstClick, ImageView secondclick) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(clickFirst != clickSecond) {
                    firstClick.setEnabled(true);
                    secondclick.setEnabled(true);
                    firstClick.setImageResource(numImg[8]);
                    secondclick.setImageResource(numImg[8]);
                    mistakeCounter++; }
                else{
                    firstClick.setVisibility(View.INVISIBLE);
                    secondclick.setVisibility(View.INVISIBLE);
                    correctCounter ++;
                }

                progressBar1.setProgress((int)getScore());
                progressBar2.setProgress((int)((correctCounter/(numberOfRows*numberOfCols/2))*100));
                isClicked = false;
                if (correctCounter == numberOfRows*numberOfCols/2)endGame();
            }

        }, 1000);

    }


    private void frontofcard() {
        numImg[0] = R.drawable.p1;
        numImg[1] = R.drawable.p2;
        numImg[2] = R.drawable.p3;
        numImg[3] = R.drawable.p4;
        numImg[4] = R.drawable.p5;
        numImg[5] = R.drawable.p6;
        numImg[6] = R.drawable.p7;
        numImg[7] = R.drawable.p8;
        numImg[8] = R.drawable.p;


    }


    private void endGame() {
        double finalScore = ((correctCounter )/ (correctCounter + mistakeCounter))*100;
        String finals = String.format("%.2f",finalScore);
        AlertDialog.Builder alert = new AlertDialog.Builder(SecondActivity.this);
        alert.setMessage(getString(R.string.END_GAME) + "\n" + getString(R.string.SCORE)+":"+finals).setCancelable(false).setPositiveButton(R.string.BACK_MENU, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        AlertDialog alertdialog = alert.create();
        alertdialog.show();
    }

    private double getScore(){
        return ((correctCounter )/ (correctCounter + mistakeCounter))*100;
    }

}