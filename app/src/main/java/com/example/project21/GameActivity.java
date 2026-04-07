package com.example.project21;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {
    ImageView d_card1, d_card2, d_card3, d_card4, d_card5, d_card6, p_card1, p_card2, p_card3, p_card4, p_card5, p_card6;
    private ImageView[] player_cards;
    private ImageView[] dealer_cards;
    private int[] all_cards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        LinearLayout game_layout = findViewById(R.id.game);
        Drawable gbg = game_layout.getBackground();
        gbg.setFilterBitmap(false);

        ImageButton standButton = findViewById(R.id.stand);
        Drawable  standButtonBg = standButton.getBackground();
        standButtonBg.setFilterBitmap(false);

        ImageButton hitButton = findViewById(R.id.hit);
        Drawable  hitButtonBg = hitButton.getBackground();
        hitButtonBg.setFilterBitmap(false);

        d_card1 = findViewById(R.id.d_card1);
        d_card2 = findViewById(R.id.d_card2);
        d_card3 = findViewById(R.id.d_card3);
        d_card4 = findViewById(R.id.d_card4);
        d_card5 = findViewById(R.id.d_card5);
        d_card6 = findViewById(R.id.d_card6);
        p_card1 = findViewById(R.id.p_card1);
        p_card2 = findViewById(R.id.p_card2);
        p_card3 = findViewById(R.id.p_card3);
        p_card4 = findViewById(R.id.p_card4);
        p_card5 = findViewById(R.id.p_card5);
        p_card6 = findViewById(R.id.p_card6);

        dealer_cards = new ImageView[]{
                d_card1, d_card2, d_card3, d_card4, d_card5, d_card6
        };

        player_cards = new ImageView[]{
                p_card1, p_card2, p_card3, p_card4, p_card5, p_card6
        };

        for (ImageView imageView : player_cards) {
            imageView.getDrawable().setFilterBitmap(false);
        }
        for (ImageView imageView : dealer_cards) {
            imageView.getDrawable().setFilterBitmap(false);
        }
        all_cards = new int[]{
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
        };
    }
    public void stand(View view) {

    }
    public void hit(View view) {

    }
}