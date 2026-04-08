package com.example.project21;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class WinActivity extends AppCompatActivity {
    private boolean lock1unlocked = false;
    private boolean lock2unlocked = false;
    private boolean lidunlocked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        LinearLayout win_layout = findViewById(R.id.win);
        ImageButton lock1Button = findViewById(R.id.lock1);
        ImageButton lock2Button = findViewById(R.id.lock2);
        ImageButton lidButton = findViewById(R.id.lid_closed);

        Drawable gbg = win_layout.getBackground();
        gbg.setFilterBitmap(false);


        Drawable lock1ButtonBg = lock1Button.getBackground();
        lock1ButtonBg.setFilterBitmap(false);
        Drawable lock2ButtonBg = lock2Button.getBackground();
        lock2ButtonBg.setFilterBitmap(false);
        Drawable lidButtonBg = lidButton.getBackground();
        lidButtonBg.setFilterBitmap(false);
    }
    public void unlock1(View view) {
        ImageButton lock1Button = findViewById(R.id.lock1);
        lock1Button.setVisibility(View.INVISIBLE);
        lock1unlocked = true;
    }
    public void unlock2(View view) {
        ImageButton lock2Button = findViewById(R.id.lock2);
        lock2Button.setVisibility(View.INVISIBLE);
        lock2unlocked = true;
    }
    public void unlockLid(View view){
        if(lidunlocked){
            Intent intent = new Intent(WinActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        if(lock1unlocked && lock2unlocked){
            ImageButton lidButton = findViewById(R.id.lid_closed);
            ImageView lidView = findViewById(R.id.lid_open);
            lidButton.setBackground(Drawable.createFromPath(""));
            lidView.setVisibility(View.VISIBLE);
            lidView.getDrawable().setFilterBitmap(false);
            lidunlocked = true;
        }
    }
}
