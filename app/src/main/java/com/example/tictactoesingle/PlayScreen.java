package com.example.tictactoesingle;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class PlayScreen extends AppCompatActivity implements View.OnClickListener {

    /* Robot move planning

    Still use "turn" variable
    Whenever it is O's turn, robot will move
    Look for an empty space to replace with O
    once it is placed, change turn back to X
    Check for win once robot moves

    Do all of that ^^^ on every click?

        Rest of logic
            Place anywhere where the symbols to the sides are the same
            Place anywhere there are two symbols matching
                Select random spot otherwise

     */


    String turn = "X";
    //Start off with X outside onCreate because I *THINK* it will mess something up
    Button tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8, tile9;
    TextView winnerTV, turnTV;
    String[] t = new String[10];

    Button[] buttons = new Button[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_screen);

        //9 tiles in total
        //tiles are counted left to right, top to bottom
        /*
          e.g   1   2   3
                4   5   6
                7   8   9
         */



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

        //make an array of buttons to have robot choose from
        //essentially link the tile and button list
        buttons[0] = null;
        buttons[1] = tile1;
        buttons[2] = tile2;
        buttons[3] = tile3;
        buttons[4] = tile4;
        buttons[5] = tile5;
        buttons[6] = tile6;
        buttons[7] = tile7;
        buttons[8] = tile8;
        buttons[9] = tile9;

        winnerTV = findViewById(R.id.winnerTV);
        turnTV = findViewById(R.id.turnTV);


        /* Brainstorm of how to change image of tiles
            Need two different conditions, Dale's turn, and Rico's turn
                have variable called turn?
            Need to check src/image of the tile being pressed
                If its the "empty" image then change
         */
    }


    //what happens when you win
    @SuppressLint("SetTextI18n")
    public void win()
    {
        kaboom();
        Toast.makeText(this, turn + " won", Toast.LENGTH_LONG).show();
        winnerTV.setText(turn + " Is The Winner");
    }

    @SuppressLint("SetTextI18n")
    public void kaboom()
    { 
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

    //start winning logic

    public void checkHoriz()
    {
        for(int i = 1; i<8; i+= 3)
        {
            //checks if the row is full of empty text or not
            if (!t[i].equals("Empty") && !t[i + 1].equals("Empty") && !t[i + 2].equals("Empty") && !t[1].equals("Kaboom"))
            {
                if (t[i].equals(t[i + 1]) && t[i + 1].equals(t[i + 2]) )
                {
                    System.out.println(turn + " won horizontally");
                    win();
                }
            }
        }

    }//end checkHoriz

    public void checkVert()
    {
        for(int i = 1; i < 3; i++)
        {
            if(!t[i].equals("Empty") && !t[i + 3].equals("Empty") && !t[i + 6].equals("Empty") && !t[1].equals("Kaboom"))
            {
                if (t[i].equals(t[i + 3]) && t[i + 3].equals(t[i + 6]))
                {
                    System.out.println(turn + " won vertically");
                    win();
                }
            }
        }
    }//end checkVert

    //diagonal from top left to bottom right
    public void diag1()
    {
        if(!t[1].equals("Empty") && !t[5].equals("Empty") && !t[9].equals("Empty") && !t[1].equals("Kaboom"))
        {
            if(t[1].equals(t[5]) && t[5].equals(t[9]))
            {
                System.out.println(turn + " won diagonally");
                win();
            }
        }
    }//end diag1

    //diagonal from bottom left to top right
    public void diag2()
    {
        if(!t[7].equals("Empty") && !t[5].equals("Empty") && !t[3].equals("Empty") && !t[1].equals("Kaboom"))
        {
            if(t[7].equals(t[5]) && t[5].equals(t[3]))
            {
                System.out.println(turn + " won diagonally");
                win();
            }
        }
    }//end diag2


    //end logic

    public void fillArray()
    {
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

    //main function of the buttons
    public void changeSymbol(View v)
    {
        Button outBtn = findViewById(v.getId());
        outBtn.setText(turn);

    }

    @SuppressLint("SetTextI18n")
    @Override
    //Like a toString
    public void onClick(View v)
    {
        Button outBtn = findViewById(v.getId());
        if(outBtn.getText().equals("Empty"))
        {
            changeSymbol(v);

            fillArray();
            checkAllWins();

            if(turn.equals( "X"))
            {
                turn = "O";
                //do all brain stuff here
                //directly change the information of the "tile" views
                //fillLayout shouldn't be affected by this implementation

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Do something after 1s
                        robotMove();
                        turnTV.setText("It is " + turn + "'s Turn");
                    }
                }, 1000);


            }
            else
                turn = "X";

            turnTV.setText("It is " + turn + "'s Turn");
        }
    }
    //end main function of the buttons


    /* Robot Win Planning
        First move logic
            Go for center tile if empty
                Go for any corner if not

        Rest of logic
            Place anywhere where the symbols to the sides are the same
            Place anywhere there are two symbols matching
                Select random spot otherwise

     */

    //make an array of even numbers to randomly choose an edge
    //only 4 even numbers from 1-8
    //same thing for odd numbers
    //only 5 odd numbers from 1-9
    int[] edges = {2,4,6,8};
    int[] corners = {1,3,7,9};

    //If all corners are taken, go for a random edge.
    public void robotMove()
    {
        int select = 5; // choose middle if not taken

        if (!t[select].equals("Empty"))
        {
            //checks if any of the corners are empty
            if(t[1].equals("Empty") || t[3].equals("Empty") || t[7].equals("Empty") || t[9].equals("Empty") )
            {
                while(!t[select].equals("Empty"));
                {
                    Random rand = new Random();
                    select = corners[rand.nextInt(3)];
                }
            }
            else if(t[2].equals("Empty") || t[4].equals("Empty") ||  t[6].equals("Empty") ||  t[8].equals("Empty") )
            {
                //checks if any of the edges are empty
                while(!buttons[select].getText().equals("Empty"))
                {
                    Random rand = new Random();
                    select = edges[rand.nextInt(4)];
                }
            }
            else
            {
                while(!buttons[select].getText().equals("Empty"))
                {
                    Random rand = new Random();
                    select = rand.nextInt(8) + 1;
                }
            }
        }

        buttons[select].setText(turn);
        fillArray();
        checkAllWins();
        turn = "X";
    }

    public void checkAllWins()
    {
        diag1();
        diag2();
        checkVert();
        checkHoriz();
    }

    public void toHomePage(View v)
    {
        startActivity(new Intent(PlayScreen.this, MainActivity.class));
    }

}//end playScreen class