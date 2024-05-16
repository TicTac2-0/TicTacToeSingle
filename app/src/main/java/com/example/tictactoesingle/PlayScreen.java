package com.example.tictactoesingle;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class PlayScreen extends AppCompatActivity implements View.OnClickListener {


    String turn = "X";
    int moves = 0; // To track the number of moves
    Button[] tiles = new Button[9]; // Array to hold the buttons representing the tiles
    TextView winnerTV, turnTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_screen);


        // Initialize tiles array with button references
        for (int i = 0; i < tiles.length; i++) {
            int resID = getResources().getIdentifier("tile" + (i + 1), "id", getPackageName());
            tiles[i] = findViewById(resID);
            tiles[i].setOnClickListener(this);
        }


        winnerTV = findViewById(R.id.winnerTV);
        turnTV = findViewById(R.id.turnTV);
        updateTurnText();
    }


    // Method to update the text indicating whose turn it is
    private void updateTurnText() {
        turnTV.setText("It is " + turn + "'s Turn");
    }


    // Method to handle a win
    private void win() {
        kaboom();
        Toast.makeText(this, turn + " won", Toast.LENGTH_LONG).show();
        winnerTV.setText(turn + " Is The Winner");
    }


    // Method to check for a tie
    private boolean checkTie() {
        return moves == 9; // If all tiles are filled, it's a tie
    }


    // Method to handle a tie
    private void tie() {
        kaboom();
        Toast.makeText(this, "It's a tie", Toast.LENGTH_LONG).show();
        winnerTV.setText("It's a Tie");
    }


    // Method to reset the game
    private void resetGame() {
        // Reset all tiles
        for (Button tile : tiles) {
            tile.setText("Empty");
        }
        // Reset winner text
        winnerTV.setText("");
        // Reset turn
        turn = "X";
        // Reset number of moves
        moves = 0;
        // Update turn text
        updateTurnText();
    }


    // Method to handle clicking on a tile
    @Override
    public void onClick(View v) {
        Button clickedTile = (Button) v;
        // Check if the tile is already filled
        if (clickedTile.getText().equals("Empty")) {
            clickedTile.setText(turn);
            moves++; // Increment the number of moves
            // Check for win or tie
            if (checkWin() || checkTie()) {
                if (checkWin()) {
                    win();
                } else {
                    tie();
                }
                // Reset the game after a win or tie
                resetGame();
            } else {
                // Switch turns
                turn = (turn.equals("X")) ? "O" : "X";
                updateTurnText();
            }
        }
    }

    // Method to check for a win
    private boolean checkWin() {
        // Check rows, columns, and diagonals for a win
        return (checkRows() || checkColumns() || checkDiagonals());
    }

    // Method to check rows for a win
    private boolean checkRows() {
        for (int i = 0; i < 9; i += 3) {
            if (!tiles[i].getText().equals("Empty") &&
                    tiles[i].getText().equals(tiles[i + 1].getText()) && tiles[i].getText().equals(tiles[i + 2].getText()))
            {
                return true;
            }
        }
        return false;
    }


    // Method to check columns for a win
    private boolean checkColumns() {
        for (int i = 0; i < 3; i++) {
            if (!tiles[i].getText().equals("Empty") &&
                    tiles[i].getText().equals(tiles[i + 3].getText()) && tiles[i].getText().equals(tiles[i + 6].getText()))
            {
                return true;
            }
        }
        return false;
    }


    // Method to check diagonals for a win
    private boolean checkDiagonals() {
        return (!tiles[0].getText().equals("Empty") &&
                tiles[0].getText().equals(tiles[4].getText()) &&
                tiles[0].getText().equals(tiles[8].getText())) ||
                (!tiles[2].getText().equals("Empty") && tiles[2].getText().equals(tiles[4].getText()) && tiles[2].getText().equals(tiles[6].getText()));
    }


    // Method to change text of all tiles to "Kaboom"
    private void kaboom() {
        for (Button tile : tiles) {
            tile.setText("Kaboom");
        }
    }

    // Method to return to the home page
    public void toHomePage(View v) {
        startActivity(new Intent(PlayScreen.this, MainActivity.class));
    }
}
