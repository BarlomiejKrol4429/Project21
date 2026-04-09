package com.example.project21;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    ImageView d_card1, d_card2, d_card3, d_card4, d_card5, d_card6, p_card1, p_card2, p_card3, p_card4, p_card5, p_card6;
    private ImageView[] playerCards;
    private ImageView[] dealerCards;
    private ArrayList<Integer> allCards;
    private int roundNumber = 1;
    private int dealersHiddenCard;
    private int currentPlayerCard;
    private int currentDealerCard;
    private int dealerCardSum;
    private int visiblePlayerCardSum;
    private int playerCardSum;
    private boolean gameInputsEnabled = false;
    private boolean loss = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        LinearLayout game_layout = findViewById(R.id.game);
        ImageButton standButton = findViewById(R.id.stand);
        ImageButton hitButton = findViewById(R.id.hit);
        game_layout.setClickable(false);

        Drawable gbg = game_layout.getBackground();
        gbg.setFilterBitmap(false);
        Drawable standButtonBg = standButton.getBackground();
        standButtonBg.setFilterBitmap(false);
        Drawable hitButtonBg = hitButton.getBackground();
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
        changeInputStatus();
        startGame();
    }
    public void startGame(){
        ImageButton standButton = findViewById(R.id.stand);
        ImageButton hitButton = findViewById(R.id.hit);
        standButton.setVisibility(View.VISIBLE);
        Drawable standButtonBg = standButton.getBackground();
        standButtonBg.setFilterBitmap(false);
        hitButton.setVisibility(View.VISIBLE);
        Drawable hitButtonBg = hitButton.getBackground();
        hitButtonBg.setFilterBitmap(false);

        d_card1.setImageResource(getResources().getIdentifier("card_q", "drawable", getPackageName()));
        d_card1.getDrawable().setFilterBitmap(false);
        //Toast.makeText(GameActivity.this, roundNumber+"", Toast.LENGTH_SHORT).show();
        currentPlayerCard = 0;
        currentDealerCard = 0;
        dealerCardSum = 0;
        visiblePlayerCardSum = 0;
        playerCardSum = 0;
        allCards = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11));

        dealerCards = new ImageView[]{
                d_card1, d_card2, d_card3, d_card4, d_card5, d_card6
        };
        playerCards = new ImageView[]{
                p_card1, p_card2, p_card3, p_card4, p_card5, p_card6
        };

        for (ImageView imageView : playerCards) {
            imageView.getDrawable().setFilterBitmap(false);
            imageView.setVisibility(View.INVISIBLE);
        }
        for (ImageView imageView : dealerCards) {
            imageView.getDrawable().setFilterBitmap(false);
            imageView.setVisibility(View.INVISIBLE);
        }

        dealersHiddenCard = drawDealerCard(allCards);
        d_card1.setVisibility(View.VISIBLE);
        currentDealerCard++;

        int playerHiddenCard = drawPlayerCard(allCards);
        visiblePlayerCardSum -= playerHiddenCard;
        p_card1.setImageResource(getResources().getIdentifier("card_" + playerHiddenCard, "drawable", getPackageName()));
        p_card1.getDrawable().setFilterBitmap(false);
        p_card1.setVisibility(View.VISIBLE);
        currentPlayerCard++;

        d_card2.setImageResource(getResources().getIdentifier("card_" + drawDealerCard(allCards), "drawable", getPackageName()));
        d_card2.getDrawable().setFilterBitmap(false);
        d_card2.setVisibility(View.VISIBLE);
        currentDealerCard++;

        p_card2.setImageResource(getResources().getIdentifier("card_" + drawPlayerCard(allCards), "drawable", getPackageName()));
        p_card2.getDrawable().setFilterBitmap(false);
        p_card2.setVisibility(View.VISIBLE);
        currentPlayerCard++;
        checkAbilityToDraw();
        showRoundText();
    }
    public int drawPlayerCard(ArrayList<Integer> deck) {
        Random rand = new Random();
        int index = rand.nextInt(deck.size());
        int card = deck.get(index);
        deck.remove(index);
        playerCardSum += card;
        visiblePlayerCardSum += card;
        return card;
    }
    public int drawDealerCard(ArrayList<Integer> deck) {
        Random rand = new Random();
        int index = rand.nextInt(deck.size());
        int card = deck.get(index);
        deck.remove(index);
        dealerCardSum += card;
        return card;
    }
    public void changeInputStatus() {
        gameInputsEnabled = !gameInputsEnabled;
    }
    public void stand(View view) {
        if (gameInputsEnabled) {
            if(dealerCardSum < 17 && visiblePlayerCardSum <= 21) {
                int card = drawDealerCard(allCards);
                dealerCards[currentDealerCard].setImageResource(getResources().getIdentifier("card_" + card, "drawable", getPackageName()));
                dealerCards[currentDealerCard].getDrawable().setFilterBitmap(false);
                dealerCards[currentDealerCard].setVisibility(View.VISIBLE);
                currentDealerCard++;
            }
        }
        gameInputsEnabled = false;
        roundEnd();
    }
    public void hit(View view) {
        if(gameInputsEnabled){
            if (playerCardSum < 21) {
                int card = drawPlayerCard(allCards);
                playerCards[currentPlayerCard].setImageResource(getResources().getIdentifier("card_" + card, "drawable", getPackageName()));
                playerCards[currentPlayerCard].getDrawable().setFilterBitmap(false);
                playerCards[currentPlayerCard].setVisibility(View.VISIBLE);
                currentPlayerCard++;
            }
            if(dealerCardSum < 17 && visiblePlayerCardSum <= 21) {
                int card = drawDealerCard(allCards);
                dealerCards[currentDealerCard].setImageResource(getResources().getIdentifier("card_" + card, "drawable", getPackageName()));
                dealerCards[currentDealerCard].getDrawable().setFilterBitmap(false);
                dealerCards[currentDealerCard].setVisibility(View.VISIBLE);
                currentDealerCard++;
            }
            checkAbilityToDraw();
        }
    }
    public void checkAbilityToDraw(){
        ImageButton hitButton = findViewById(R.id.hit);
        if(playerCardSum > 21){
            hitButton.setBackground(ContextCompat.getDrawable(this, R.drawable.hit_blocked));
            Drawable hitButtonBg = hitButton.getBackground();
            hitButtonBg.setFilterBitmap(false);

        } else if (playerCardSum == 21) {
            hitButton.setBackground(ContextCompat.getDrawable(this, R.drawable.hit_perfect));
            Drawable hitButtonBg = hitButton.getBackground();
            hitButtonBg.setFilterBitmap(false);

        }
        else{
            hitButton.setBackground(ContextCompat.getDrawable(this, R.drawable.hit));
        }
    }

    public void roundEnd(){
        ImageButton standButton = findViewById(R.id.stand);
        ImageButton hitButton = findViewById(R.id.hit);
        standButton.setVisibility(View.INVISIBLE);
        hitButton.setVisibility(View.INVISIBLE);
        //Toast.makeText(GameActivity.this, playerCardSum+" "+dealerCardSum, Toast.LENGTH_SHORT).show();
        d_card1.setImageResource(getResources().getIdentifier("card_" + dealersHiddenCard, "drawable", getPackageName()));
        d_card1.getDrawable().setFilterBitmap(false);
        boolean playerBust = playerCardSum > 21;
        boolean dealerBust = dealerCardSum > 21;
        if (playerBust && dealerBust) {
            if (playerCardSum < dealerCardSum) {
                win();
            } else if (dealerCardSum < playerCardSum) {
                loss();
            } else {
                tie();
            }
        }
        else if (playerBust && !dealerBust) {
            loss();
        }
        else if (!playerBust && dealerBust) {
            win();
        }
        else if (playerCardSum > dealerCardSum) {
            win();
        } else if (dealerCardSum > playerCardSum) {
            loss();
        } else {
            tie();
        }
    }
    public void win(){
        LinearLayout game_layout = findViewById(R.id.game);
        if(roundNumber != 7){
            roundNumber += 1;
            game_layout.setClickable(true);
        }else{
            Intent intent = new Intent(GameActivity.this, WinActivity.class);
            startActivity(intent);
            finish();
        }
    }
    public void loss(){
        LinearLayout game_layout = findViewById(R.id.game);
        game_layout.setClickable(true);
        loss = true;
    }
    public void tie(){
        LinearLayout game_layout = findViewById(R.id.game);
        game_layout.setClickable(true);
    }
    public void continueGame(View view){
        LinearLayout game_layout = findViewById(R.id.game);
        if(game_layout.isClickable()){
            if(loss){
                Intent intent = new Intent(GameActivity.this, OverActivity.class);
                startActivity(intent);
                finish();
            }else{
                gameInputsEnabled = true;
                startGame();
            }
        }
        game_layout.setClickable(false);
        loss = false;
    }
    private void showRoundText() {
        ImageView roundText = findViewById(R.id.roundText);
        int resId = getResources().getIdentifier(
                "round" + roundNumber,
                "drawable",
                getPackageName()
        );
        roundText.setImageResource(resId);
        roundText.getDrawable().setFilterBitmap(false);
        roundText.setAlpha(0f);
        roundText.setVisibility(View.VISIBLE);
        roundText.animate().alpha(1f).setDuration(300).withEndAction(() -> {
                    roundText.postDelayed(() -> {
                        roundText.animate().alpha(0f).setDuration(300).withEndAction(() -> {
                            roundText.setVisibility(View.INVISIBLE);}).start();
                    }, 1000);
                }).start();
    }
}
