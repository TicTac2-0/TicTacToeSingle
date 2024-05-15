package com.example.tictactoesingle;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

public class SinglePlayer extends AppCompatActivity implements View.OnClickListener {


    String[] t = new String[10];
    Button tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8, tile9;
    TextView winnerTV, turnTV;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);


        tile1 = findViewById(R.id.tile12);
        tile2 = findViewById(R.id.tile22);
        tile3 = findViewById(R.id.tile32);
        tile4 = findViewById(R.id.tile42);
        tile5 = findViewById(R.id.tile52);
        tile6 = findViewById(R.id.tile62);
        tile7 = findViewById(R.id.tile72);
        tile8 = findViewById(R.id.tile82);
        tile9 = findViewById(R.id.tile92);


        tile1.setOnClickListener(this);
        tile2.setOnClickListener(this);
        tile3.setOnClickListener(this);
        tile4.setOnClickListener(this);
        tile5.setOnClickListener(this);
        tile6.setOnClickListener(this);
        tile7.setOnClickListener(this);
        tile8.setOnClickListener(this);
        tile9.setOnClickListener(this);


        winnerTV = findViewById(R.id.winnerTV2);
        turnTV = findViewById(R.id.turnTV2);


        initializeGameState();
        turnTV.setText("Your Turn");
    }


    private void initializeGameState() {
        for (int i = 1; i <= 9; i++) {
            t[i] = "Empty";
        }
    }


    @Override
    public void onClick(View v) {
        Button clickedButton = findViewById(v.getId());
        if (clickedButton.getText().equals("Empty")) {
            // Player's move
            clickedButton.setText("X");
            t[Integer.parseInt(v.getTag().toString())] = "X";

            if (checkWin("X")) {
                displayResult("You win!");
                return;
            }
            if (checkTie()) {
                displayResult("It's a tie!");
                return;
            }

            // Computer's move
            makeComputerMove();

            if (checkWin("O")) {
                displayResult("Computer wins!");
                return;
            }
            if (checkTie()) {
                displayResult("It's a tie!");
            }
        }
    }
    
    private void makeComputerMove() {
        // Implement logic for the computer's move
        // For simplicity, let's make the computer choose a random empty tile
        ArrayList<Integer> emptyTiles = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            if (t[i].equals("Empty")) {
                emptyTiles.add(i);
            }
        }
        if (!emptyTiles.isEmpty()) {
            int randomIndex = new Random().nextInt(emptyTiles.size());
            int tileIndex = emptyTiles.get(randomIndex);
            t[tileIndex] = "O";
            switch (tileIndex) {
                case 1:
                    tile1.setText("O");
                    break;
                case 2:
                    tile2.setText("O");
                    break;
                case 3:
                    tile3.setText("O");
                    break;
                case 4:
                    tile4.setText("O");
                    break;
                case 5:
                    tile5.setText("O");
                    break;
                case 6:
                    tile6.setText("O");
                    break;
                case 7:
                    tile7.setText("O");
                    break;
                case 8:
                    tile8.setText("O");
                    break;
                case 9:
                    tile9.setText("O");
                    break;
            }
        }
    }


    private boolean checkWin(String player)
    {
        for (int i = 1; i <= 7; i += 3) {
            if (t[i].equals(player) && t[i + 1].equals(player) && t[i + 2].equals(player)) {
                return true;
            }
        }
        // Vertical check
        for (int i = 1; i <= 3; i++) {
            if (t[i].equals(player) && t[i + 3].equals(player) && t[i + 6].equals(player)) {
                return true;
            }
        }
        // Diagonal check
        if (t[1].equals(player) && t[5].equals(player) && t[9].equals(player)) {
            return true;
        }
        return t[3].equals(player) && t[5].equals(player) && t[7].equals(player);
    }


    private boolean checkTie()
    {
        // Implement tie condition check
        for (int i = 1; i <= 9; i++) {
            if (t[i].equals("Empty")) {
                return false; // If any empty tile found, game is not a tie
            }
        }
        return true; // If no empty tile found, game is a tie
    }

    private void displayResult(String result) {
        winnerTV.setText(result);
        tile1.setEnabled(false);
        tile2.setEnabled(false);
        tile3.setEnabled(false);
        tile4.setEnabled(false);
        tile5.setEnabled(false);
        tile6.setEnabled(false);
        tile7.setEnabled(false);
        tile8.setEnabled(false);
        tile9.setEnabled(false);
    }
}
