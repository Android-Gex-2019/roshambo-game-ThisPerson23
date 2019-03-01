/*
    File:  MainActivity.java

    Author: Justin Lange
    This assignment represents my own work and is in accordance with the College Academic Policy

    Copyright(c) 2019 All Right Reserved by Justin Lange

    Date: 28-02-19
*/

package com.example.roshambo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView playerMoveImage_;
    private ImageView computerMoveImage_;
    private TextView gameResultText_;

    private Roshambo game = new Roshambo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get a handle on all views
        getHandleOnViews();
    }

    /**
     * Helper method to get a handle on all views within the activity
     */
    private void getHandleOnViews() {
        playerMoveImage_ = findViewById(R.id.imgPlayerMove);
        computerMoveImage_ = findViewById(R.id.imgComputerMove);
        gameResultText_ = findViewById(R.id.txtGameResult);
    }

    /**
     * Handle the player's move, finding out which image they clicked on
     * @param view The view that was clicked
     */
    public void handlePlayerMove(View view) {
        switch (view.getId()) {
            case R.id.imgRockButton:
                game.playerMakesMove(0);
                break;
            case R.id.imgPaperButton:
                game.playerMakesMove(1);
                break;
            case R.id.imgScissorsButton:
                game.playerMakesMove(2);
                break;
            default:
                //Do nothing
                break;
        }

        drawView();
    }

    /**
     * Draw the game's view
     */
    public void drawView() {
        drawPlayerMove();
        drawComputerMove();
        updateResultText();
        animateResultImages();
    }

    /**
     * Updates the game result text based on the outcome
     */
    private void updateResultText() {
        switch (game.winLoseOrDraw()) {
            case R.string.win:
                gameResultText_.setText(R.string.win);
                break;
            case R.string.lose:
                gameResultText_.setText(R.string.lose);
                break;
            case R.string.draw:
                gameResultText_.setText(R.string.draw);
                break;
            default:
                //Do nothing
                break;
        }
    }

    /**
     * Draws the computer's move on screen
     */
    private void drawComputerMove() {
        if (isTablet(this))
        {
            switch (game.getGameMove()) {
                case 0:
                    computerMoveImage_.setImageResource(R.drawable.rock);
                    break;
                case 1:
                    computerMoveImage_.setImageResource(R.drawable.paper);
                    break;
                case 2:
                    computerMoveImage_.setImageResource(R.drawable.scissors);
                    break;
                default:
                    //Do nothing
                    break;
            }
        } else {
            switch (game.getGameMove()) {
                case 0:
                    computerMoveImage_.setImageResource(R.drawable.rock_button);
                    break;
                case 1:
                    computerMoveImage_.setImageResource(R.drawable.paper_button);
                    break;
                case 2:
                    computerMoveImage_.setImageResource(R.drawable.scissor_button);
                    break;
                default:
                    //Do nothing
                    break;
            }
        }
    }

    /**
     * Draws the player's move on screen
     */
    private void drawPlayerMove() {
        if (isTablet(this)) {
            switch (game.getPlayerMove()) {
                case 0:
                    playerMoveImage_.setImageResource(R.drawable.rock);
                    break;
                case 1:
                    playerMoveImage_.setImageResource(R.drawable.paper);
                    break;
                case 2:
                    playerMoveImage_.setImageResource(R.drawable.scissors);
                    break;
                default:
                    //Do nothing
                    break;
            }
        } else {
            switch (game.getPlayerMove()) {
                case 0:
                    playerMoveImage_.setImageResource(R.drawable.rock_button);
                    break;
                case 1:
                    playerMoveImage_.setImageResource(R.drawable.paper_button);
                    break;
                case 2:
                    playerMoveImage_.setImageResource(R.drawable.scissor_button);
                    break;
                default:
                    //Do nothing
                    break;
            }
        }
    }

    /**
     * Animate the result images
     */
    private void animateResultImages() {
        ObjectAnimator animatorPlayer = ObjectAnimator.ofFloat(playerMoveImage_,
                "rotationX", 0f, 360f).setDuration(500);

        ObjectAnimator animatorComputer = ObjectAnimator.ofFloat(computerMoveImage_,
                "rotationX", 0f, 360f).setDuration(500);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(animatorComputer, animatorPlayer);
        set.setInterpolator(new AccelerateDecelerateInterpolator());
        set.start();
    }

    /**
     * Checks if the device being used is of tablet size or not
     * @param context The state of the current class
     * @return True if device is a tablet, false if not
     */
    private static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}
