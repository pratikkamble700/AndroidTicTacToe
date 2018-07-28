package com.pratik.tictactoe;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_reset;

    private Button[][] buttons = new Button[3][3];
    private boolean player1Turn = true;
    private int count;
    public static String firstPlayerName, secoundPlayerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        count = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonId = "btn_" + i + j;
                int resId = getResources().getIdentifier(buttonId, "id", getPackageName());
                buttons[i][j] = (Button) findViewById(resId);
                buttons[i][j].setOnClickListener(this);
            }
        }

        btn_reset = (Button) findViewById(R.id.btn_reset);
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetBoard();
            }
        });
    }

    @Override
    public void onClick(View view) {

        if (!TextUtils.isEmpty(((Button) view).getText())) {
            return;
        }
        if (player1Turn) {
            ((Button) view).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPlayerOne));
            ((Button) view).setText("X");
        } else {
            ((Button) view).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));
            ((Button) view).setText("O");
        }
        count++;

        if (checkForWin()) {
            if (player1Turn) {
                Toast.makeText(MainActivity.this, "Player "+ firstPlayerName +" Wins!!!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MainActivity.this, "Player "+ secoundPlayerName +" Wins!!!", Toast.LENGTH_LONG).show();
            }
            // after win wait for a sec to clear the board
            new Handler().postDelayed(new Runnable() {
                public void run() {
                  resetBoard();
                }
            }, 1000);
        } else if (count == 9) {
            Toast.makeText(MainActivity.this, "!!!Draw!!!", Toast.LENGTH_LONG).show();
            resetBoard();
        }else{player1Turn = !player1Turn;}

    }


    private boolean checkForWin() {
//        getting text
        String[][] fields = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                fields[i][j] = buttons[i][j].getText().toString();
            }
        }

        // horizontal check
        for (int i = 0; i < 3; i++) {
            if (fields[i][0].equals(fields[i][1])
                    && fields[i][1].equals(fields[i][2])
                    && !TextUtils.isEmpty(fields[i][0])) {
                return true;
            }
        }

        // vertical check
        for (int i = 0; i < 3; i++) {
            if (fields[0][i].equals(fields[1][i])
                    && fields[1][i].equals(fields[2][i])
                    && !TextUtils.isEmpty(fields[0][i])) {
                return true;
            }
        }

//          forward diagonal
        if (fields[0][0].equals(fields[1][1])
                && fields[1][1].equals(fields[2][2])
                && !TextUtils.isEmpty(fields[0][0])) {
            return true;
        }

//        backward diagonal
        if (fields[0][2].equals(fields[1][1])
                && fields[1][1].equals(fields[2][0])
                && !TextUtils.isEmpty(fields[0][2])) {
            return true;
        }
        return false;
    }

    public void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        player1Turn = true;
        count = 0;
    }
}
