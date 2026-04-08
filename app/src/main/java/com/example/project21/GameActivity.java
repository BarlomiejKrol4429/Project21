package com.example.project21;

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
    private int currentPlayerCard = 0;
    private int currentDealerCard = 0;
    private int dealerCardSum = 0;
    private int visiblePlayerCardSum = 0;
    private int playerCardSum = 0;
    private boolean gameInputsEnabled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        LinearLayout game_layout = findViewById(R.id.game);
        ImageButton standButton = findViewById(R.id.stand);
        ImageButton hitButton = findViewById(R.id.hit);

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

        allCards = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11));
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
            while (dealerCardSum < 17 && visiblePlayerCardSum <= 21) {
                int card = drawDealerCard(allCards);
                if (currentDealerCard < dealerCards.length) {
                    dealerCards[currentDealerCard].setImageResource(getResources().getIdentifier("card_" + card, "drawable", getPackageName()));
                    dealerCards[currentDealerCard].getDrawable().setFilterBitmap(false);
                    dealerCards[currentDealerCard].setVisibility(View.VISIBLE);
                }
            }
        }
        gameInputsEnabled = false;
        roundEnd();
        Toast.makeText(this, allCards+" "+visiblePlayerCardSum+" "+playerCardSum+" "+dealerCardSum, Toast.LENGTH_SHORT).show();
    }
    public void hit(View view) {
        if (gameInputsEnabled && playerCardSum < 21) {
            int card = drawPlayerCard(allCards);
            playerCards[currentPlayerCard].setImageResource(
                    getResources().getIdentifier("card_" + card, "drawable", getPackageName())
            );
            playerCards[currentPlayerCard].getDrawable().setFilterBitmap(false);
            playerCards[currentPlayerCard].setVisibility(View.VISIBLE);
            currentPlayerCard++;
        }
        if(dealerCardSum < 17 && visiblePlayerCardSum <= 21) {
            int card = drawDealerCard(allCards);
            if (currentDealerCard < dealerCards.length) {
                dealerCards[currentDealerCard].setImageResource(getResources().getIdentifier("card_" + card, "drawable", getPackageName()));
                dealerCards[currentDealerCard].getDrawable().setFilterBitmap(false);
                dealerCards[currentDealerCard].setVisibility(View.VISIBLE);
            }
        }
        checkAbilityToDraw();
    }
    public void checkAbilityToDraw(){
        ImageButton hitButton = findViewById(R.id.hit);
        if(playerCardSum > 21){
            hitButton.setBackground(ContextCompat.getDrawable(this, R.drawable.hit_blocked));
        }else{
            hitButton.setBackground(ContextCompat.getDrawable(this, R.drawable.hit));
        }
    }

    public void roundEnd(){
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

        if (playerBust && !dealerBust) {
            loss();
        }

        if (!playerBust && dealerBust) {
            win();
        }

        if (playerCardSum > dealerCardSum) {
            win();
        } else if (dealerCardSum > playerCardSum) {
            loss();
        } else {
            tie();
        }
    }
    public void win(){
        if(roundNumber != 5){
            roundNumber += 1;
            Toast.makeText(this, "you win this round!", Toast.LENGTH_SHORT).show();
        }
    }
    public void loss(){
        Toast.makeText(this, "you lose!", Toast.LENGTH_SHORT).show();
    }
    public void tie(){
        Toast.makeText(this, "you've tied!", Toast.LENGTH_SHORT).show();
    }
}
