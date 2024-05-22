package com.example.tictactoesingle;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

public class PlayScreen extends AppCompatActivity implements View.OnClickListener {

    String turn = "X";
    Button[] tiles = new Button[9];
    TextView winnerTV, turnTV;
    Button[] buttons = new Button[10];
    int numTurns = 0;
    String[] t = new String[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_screen);

        for (int i = 0; i < tiles.length; i++) {
            int resID = getResources().getIdentifier("tile" + (i + 1), "id", getPackageName());
            tiles[i] = findViewById(resID);
            tiles[i].setOnClickListener(this);
            t[i] = "Empty";
        }

        buttons[0] = null;
        for (int i = 1; i < buttons.length; i++) {
            buttons[i] = tiles[i - 1];
        }

        winnerTV = findViewById(R.id.winnerTV);
        turnTV = findViewById(R.id.turnTV);
        updateTurnText();

        Random random = new Random();
        int randomTurn = random.nextInt(2);

        if (randomTurn == 1) {
            turn = "O";
            robotMove();
            numTurns++;
            turnTV.setText("It is " + turn + "'s Turn");
        } else {
            turn = "X";
        }
    }

    private void updateTurnText() {
        turnTV.setText("It is " + turn + "'s Turn");
    }

    @SuppressLint("SetTextI18n")
    public void win() {
        Toast.makeText(this, turn + " won", Toast.LENGTH_LONG).show();
        winnerTV.setText(turn + " Is The Winner");
        turnTV.setText("");
        kaboom();
    }

    @SuppressLint("SetTextI18n")
    public void kaboom() {
        for (Button tile : tiles) {
            tile.setText("Kaboom");
        }
    }

    private void tie() {
        kaboom();
        Toast.makeText(this, "It's a tie", Toast.LENGTH_LONG).show();
        winnerTV.setText("It's a Tie");
    }

    private void resetGame() {
        for (Button tile : tiles) {
            tile.setText("Empty");
        }
        winnerTV.setText("");
        turn = "X";
        numTurns = 0;
        updateTurnText();
    }

    @Override
    public void onClick(View v) {
        Button clickedTile = (Button) v;
        if (clickedTile.getText().equals("Empty")) {
            clickedTile.setText(turn);
            numTurns++;
            t[Arrays.asList(tiles).indexOf(clickedTile)] = turn;
            if (checkWin() || numTurns == 9) {
                if (checkWin()) {
                    win();
                } else {
                    tie();
                }
                resetGame();
            } else {
                turn = (turn.equals("X")) ? "O" : "X";
                updateTurnText();
                if (turn.equals("O")) {
                    robotMove();
                    numTurns++;
                    turnTV.setText("It is " + turn + "'s Turn");
                }
            }
        }
    }

    private boolean checkWin() {
        return checkRows() || checkColumns() || checkDiagonals();
    }

    private boolean checkRows() {
        for (int i = 0; i < 9; i += 3) {
            if (!tiles[i].getText().equals("Empty") &&
                    tiles[i].getText().equals(tiles[i + 1].getText()) && tiles[i].getText().equals(tiles[i + 2].getText())) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns() {
        for (int i = 0; i < 3; i++) {
            if (!tiles[i].getText().equals("Empty") &&
                    tiles[i].getText().equals(tiles[i + 3].getText()) && tiles[i].getText().equals(tiles[i + 6].getText())) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals() {
        return (!tiles[0].getText().equals("Empty") &&
                tiles[0].getText().equals(tiles[4].getText()) &&
                tiles[0].getText().equals(tiles[8].getText())) ||
                (!tiles[2].getText().equals("Empty") && tiles[2].getText().equals(tiles[4].getText()) && tiles[2].getText().equals(tiles[6].getText()));
    }

    public void toHomePage(View v) {
        startActivity(new Intent(PlayScreen.this, MainActivity.class));
    }

    public void robotMove() {
        int select = 5; 
        if (t[5].equals("Empty")) {
            buttons[select].setText(turn);
            t[5] = turn;
        } else if (!t[select].equals("Empty") && !t[select].equals("Kaboom")) {
            if ((t[1].equals("Empty") || t[3].equals("Empty") || t[7].equals("Empty") || t[9].equals("Empty")) && !Arrays.stream(t).anyMatch("Kaboom"::equals)) {
                while (!t[select].equals("Empty")) {
                    Random rand = new Random();
                    select = corners[rand.nextInt(4)];
                }
            } else {
                while (!t[select].equals("Empty") && !Arrays.stream(t).anyMatch("Kaboom"::equals)) {
                    Random rand = new Random();
                    select = edges[rand.nextInt(4)];
                }
            }
        }
        if (Arrays.stream(t).anyMatch("Empty"::equals)) {
            buttons[select].setText(turn);
            t[select] = turn;
        }
        turn = "X";
    }
}
