package com.example.tictactoesingle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView titleText;
    Button playButton;
    Button instructionsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instructionsButton = (Button) findViewById(R.id.InstructionsButton);
        playButton = (Button) findViewById(R.id.SinglePlayerButton);
        titleText = (TextView) findViewById(R.id.title_text);
    }

    public void toInstructions(View v)
    {
        System.out.println("entered toInstructions");
        startActivity(new Intent(MainActivity.this, Instructions.class));
    }

    public void toPlay(View v)
    {
        startActivity(new Intent(MainActivity.this, PlayScreen.class));
    }

    public void toMultiplayer(View v)
    {
        startActivity(new Intent(MainActivity.this, MultiplayerScreen.class));
    }
}