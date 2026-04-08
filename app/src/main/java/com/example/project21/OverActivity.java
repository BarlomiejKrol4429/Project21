package com.example.project21;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class OverActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actity_over);

        LinearLayout game_layout = findViewById(R.id.over);
        ImageView gameOver = findViewById(R.id.game_over);
        ImageButton restartButton = findViewById(R.id.restart);

        Drawable gbg = game_layout.getBackground();
        gbg.setFilterBitmap(false);
        gameOver.getDrawable().setFilterBitmap(false);
        Drawable hitButtonBg = restartButton.getBackground();
        hitButtonBg.setFilterBitmap(false);
    }
    public void play(View view) {
        Intent intent = new Intent(OverActivity.this, GameActivity.class);
        startActivity(intent);
        finish();
    }
}
