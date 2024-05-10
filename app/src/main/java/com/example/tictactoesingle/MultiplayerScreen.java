package com.example.tictactoesingle;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MultiplayerScreen extends AppCompatActivity implements View.OnClickListener {


    String currentPlayer = "X";
    Button tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8, tile9;
    TextView winnerTV, turnTV, tieText;
    String[] t = new String[10];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_screen);


        tile1 = findViewById(R.id.tile1);
        tile2 = findViewById(R.id.tile2);
        tile3 = findViewById(R.id.tile3);
        tile4 = findViewById(R.id.tile4);
        tile5 = findViewById(R.id.tile5);
        tile6 = findViewById(R.id.tile6);
        tile7 = findViewById(R.id.tile7);
        tile8 = findViewById(R.id.tile8);
        tile9 = findViewById(R.id.tile9);


        tile1.setOnClickListener(this);
        tile2.setOnClickListener(this);
        tile3.setOnClickListener(this);
        tile4.setOnClickListener(this);
        tile5.setOnClickListener(this);
        tile6.setOnClickListener(this);
        tile7.setOnClickListener(this);
        tile8.setOnClickListener(this);
        tile9.setOnClickListener(this);


        winnerTV = findViewById(R.id.winnerTV);
        turnTV = findViewById(R.id.turnTV);
        tieText = findViewById(R.id.tieText);
    }


    @Override
    public void onClick(View v) {
        Button outBtn = findViewById(v.getId());
        if (outBtn.getText().equals("Empty")) {
            changeSymbol(v);
            fillArray();
            diag1();
            diag2();
            checkVert();
            checkHoriz();
            if (checkForWin()) {
                // Player won
                Toast.makeText(this, currentPlayer + " won", Toast.LENGTH_LONG).show();
                // Perform any necessary end-of-game actions
            } else if (checkForDraw()) {
                // Draw
                Toast.makeText(this, "It's a draw", Toast.LENGTH_LONG).show();
                // Perform any necessary end-of-game actions
            } else {
                // Switch player
                currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
                turnTV.setText("It is " + currentPlayer + "'s Turn");
            }
        }
    }


    public void changeSymbol(View v) {
        Button outBtn = findViewById(v.getId());
        outBtn.setText(currentPlayer);
    }


    public void fillArray() {
        t[0] = null;
        t[1] = (String)tile1.getText();
        t[2] = (String)tile2.getText();
        t[3] = (String)tile3.getText();
        t[4] = (String)tile4.getText();
        t[5] = (String)tile5.getText();
        t[6] = (String)tile6.getText();
        t[7] = (String)tile7.getText();
        t[8] = (String)tile8.getText();
        t[9] = (String)tile9.getText();
    }


    private boolean checkLine(CharSequence a, CharSequence b, CharSequence c) {
        return !a.equals("Empty") && a.equals(b) && b.equals(c);
    }


    private boolean checkForWin() {
        // Check horizontal lines
        if (checkLine(tile1.getText(), tile2.getText(), tile3.getText())
                || checkLine(tile4.getText(), tile5.getText(), tile6.getText())
                || checkLine(tile7.getText(), tile8.getText(), tile9.getText())) {
            return true;
        }
        // Check vertical lines
        if (checkLine(tile1.getText(), tile4.getText(), tile7.getText())
                || checkLine(tile2.getText(), tile5.getText(), tile8.getText())
                || checkLine(tile3.getText(), tile6.getText(), tile9.getText())) {
            return true;
        }
        // Check diagonal lines
        if (checkLine(tile1.getText(), tile5.getText(), tile9.getText())
                || checkLine(tile3.getText(), tile5.getText(), tile7.getText())) {
            return true;
        }
        return false;
    }


    private boolean checkForDraw() {
        return !tile1.getText().equals("Empty") && !tile2.getText().equals("Empty") && !tile3.getText().equals("Empty") &&
                !tile4.getText().equals("Empty") && !tile5.getText().equals("Empty") && !tile6.getText().equals("Empty") &&
                !tile7.getText().equals("Empty") && !tile8.getText().equals("Empty") && !tile9.getText().equals("Empty");
    }


    public void toHomePage(View v) {
        startActivity(new Intent(MultiplayerScreen.this, MainActivity.class));
    }

    public void diag1() {
        if(!t[1].equals("Empty") && !t[5].equals("Empty") && !t[9].equals("Empty") && !t[1].equals("Kaboom")) {
            if(t[1].equals(t[5]) && t[5].equals(t[9])) {
                System.out.println(currentPlayer + " won diagonally");
                win();
            }
        }
    }

    public void diag2() {
        if(!t[7].equals("Empty") && !t[5].equals("Empty") && !t[3].equals("Empty") && !t[1].equals("Kaboom")) {
            if(t[7].equals(t[5]) && t[5].equals(t[3])) {
                System.out.println(currentPlayer + " won diagonally");
                win();
            }
        }
    }

    public void checkVert() {
        for(int i = 1; i < 3; i++) {
            if(!t[i].equals("Empty") && !t[i + 3].equals("Empty") && !t[i + 6].equals("Empty") && !t[1].equals("Kaboom")) {
                if (t[i].equals(t[i + 3]) && t[i + 3].equals(t[i + 6])) {
                    System.out.println(currentPlayer + " won vertically");
                    win();
                }
            }
        }
    }

    public void checkHoriz() {
        for(int i = 1; i < 8; i += 3) {
            if (!t[i].equals("Empty") && !t[i + 1].equals("Empty") && !t[i + 2].equals("Empty") && !t[1].equals("Kaboom")) {
                if (t[i].equals(t[i + 1]) && t[i + 1].equals(t[i + 2])) {
                    System.out.println(currentPlayer + " won horizontally");
                    win();
                }
            }
        }
    }

    public void win() {
        kaboom();
        Toast.makeText(this, currentPlayer + " won", Toast.LENGTH_LONG).show();
        winnerTV.setText(currentPlayer + " Is The Winner");
    }

    public void kaboom() {
        tile1.setText("Kaboom");
        tile2.setText("Kaboom");
        tile3.setText("Kaboom");
        tile4.setText("Kaboom");
        tile5.setText("Kaboom");
        tile6.setText("Kaboom");
        tile7.setText("Kaboom");
        tile8.setText("Kaboom");
        tile9.setText("Kaboom");
    }
}
