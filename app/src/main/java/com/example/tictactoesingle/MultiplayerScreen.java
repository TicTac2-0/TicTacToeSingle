package com.example.tictactoesingle;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MultiplayerScreen extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer_screen);
    }

    public void toHomePage(View v)
    {
        startActivity(new Intent(MultiplayerScreen.this, MainActivity.class));
    }
}
