package com.example.tictactoesingle;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PlayScreen extends AppCompatActivity implements View.OnClickListener
{

    String turn = "X";
    //Start off with X outside onCreate because I *THINK* it will mess something up

    Button tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8, tile9;
    TextView winnerTV, turnTV, tieText;
    String[] t = new String[10];

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

        winnerTV = findViewById(R.id.winnerTV);
        turnTV = findViewById(R.id.turnTV);
        tieText = findViewById(R.id.tieText);

        }


    //what happens when you win
    public void win()
    {
        kaboom();
        Toast.makeText(this, turn + " won", Toast.LENGTH_LONG).show();
        winnerTV.setText(turn + " Is The Winner");
    }

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

    @Override
    //Like a toString
    public void onClick(View v)
    {
        Button outBtn = findViewById(v.getId());
        if(outBtn.getText().equals("Empty"))
        {
            changeSymbol(v);

            fillArray();
            diag1();
            diag2();
            checkVert();
            checkHoriz();
            if(turn.equals( "X"))
            {
                turn = "O";
            }
            else
                turn = "X";
            turnTV.setText("It is " + turn + "'s Turn");
        }
    }
    //end main function of the buttons

    public void toHomePage(View v)
    {
        startActivity(new Intent(PlayScreen.this, MainActivity.class));
    }

}//end playScreen class