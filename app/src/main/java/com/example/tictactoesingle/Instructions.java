package com.example.tictactoesingle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Instructions extends AppCompatActivity
{

    TextView instructionsTitle;

    TextView instructionsText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        System.out.println("Started Instructions activity");
        instructionsTitle = (TextView) findViewById(R.id.InstructionsTitle);
        instructionsText = (TextView) findViewById(R.id.InstructionsText);
    }

    public void toHomePage(View v)
    {
        startActivity(new Intent(Instructions.this, MainActivity.class));
    }
}