package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button[][] btn = new Button[3][3];
    private boolean player1Turn = true;
    private int count = 0;
    private int player1points = 0;
    private int player2points = 0;
    private TextView textViewPlayer1;
    private TextView textViewPlayer2;
    private Button reset_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPlayer1 = (TextView) findViewById(R.id.text_view_player1);
        textViewPlayer2 = (TextView) findViewById(R.id.text_view_player2);
        reset_button = (Button) findViewById(R.id.button_reset);
        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

        btn[0][0] = (Button) findViewById(R.id.button_00);
        btn[0][1] = (Button) findViewById(R.id.button_01);
        btn[0][2] = (Button) findViewById(R.id.button_02);
        btn[1][0] = (Button) findViewById(R.id.button_10);
        btn[1][1] = (Button) findViewById(R.id.button_11);
        btn[1][2] = (Button) findViewById(R.id.button_12);
        btn[2][0] = (Button) findViewById(R.id.button_20);
        btn[2][1] = (Button) findViewById(R.id.button_21);
        btn[2][2] = (Button) findViewById(R.id.button_22);

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                btn[i][j].setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Button b = (Button) v;
        if (!b.getText().toString().equals("")) {
            return;
        }
        if (player1Turn) {
            b.setText("X");
        } else {
            b.setText("O");
        }
        count++;

        if(checkWinner()) {
            if (player1Turn) {
                player1wins();
            } else {
                player2wins();
            }
        }
        else
        if(count == 9){
            drawGame();
        }
        else {
            player1Turn = !player1Turn;

        }

    }

    private void player1wins() {
        player1points++;
        Toast.makeText(this, "Player1 is the winner!", Toast.LENGTH_SHORT).show();
        updatePointText();
        resetBoard();
    }

    private void player2wins() {
        player2points++;
        Toast.makeText(this, "Player2 is the winner!", Toast.LENGTH_SHORT).show();
        updatePointText();
        resetBoard();
    }

    private void drawGame() {
        Toast.makeText(this, "This game is draw!", Toast.LENGTH_SHORT).show();
        resetBoard();

    }

    private void updatePointText(){
        textViewPlayer1.setText("Player 1 :"+player1points);
        textViewPlayer2.setText("Player 2 :"+player2points);


    }
    private void resetBoard(){
        for(int i = 0; i<3; i++)
            for(int j=0; j<3; j++)
                btn[i][j].setText("");
        count=0;
        player1Turn = true;
    }

    private void resetGame(){
        player1points=0;
        player2points=0;

        updatePointText();
        resetBoard();
    }


    private boolean checkWinner() {
        String[][] field = new String[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                field[i][j] = btn[i][j].getText().toString();


        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("")) {

                return true;
            }

        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("")) {

                return true;
            }

        }

        if(field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")){
            return true;
        }

        if(field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("")){
            return true;
        }

        return false;
    }
}
