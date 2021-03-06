/*
    File:  Roshambo.java

    Author: David Burchill
    Edited By: Justin Lange

    Copyright(c) 2019 All Right Reserved by David Burchill

    Date: 28-02-19
*/

package com.example.roshambo;

import java.io.Serializable;
import java.util.Random;

public class Roshambo implements Serializable {

    public static final int ROCK = 0;
    public static final int PAPER = 1;
    public static final int SCISSOR = 2;
    public static final int NONE = 3;

    public static final int WIN = R.string.win;
    public static final int LOSE = R.string.lose;
    public static final int DRAW = R.string.draw;


    private int gameMove;
    private int playerMove;
    private Random rand;


    public Roshambo() {
        rand = new Random();
        gameMove = NONE;
        playerMove = NONE;
    }

    /**
     * Player chooses their next move, the game immediately follows and
     * makes its next move. Use winLoseOrDraw() to see the outcome
     *
     * @param playerMove  ROCK, PAPER, or SCISSORS are valid arguments
     */
    public void playerMakesMove(int playerMove) {

        if ( Integer.compareUnsigned(playerMove , NONE) > 0)
            throw new IllegalArgumentException("Not a valid player move");

        this.playerMove = playerMove;
        gameMakesMove();
    }

    /**
     * The game makes a random choice of ROCK, PAPER, or SCISSOR
     */
    private void gameMakesMove() {
        gameMove = rand.nextInt(3);
    }

    public int getGameMove() {
        return gameMove;
    }

    public int getPlayerMove() {
        return playerMove;
    }

    public int winLoseOrDraw() {

        if (playerMove == ROCK) {
            if (gameMove == ROCK) {
                return DRAW;
            } else if (gameMove == PAPER) {
                return LOSE;
            } else {
                return WIN;
            }
        } else if (playerMove == PAPER ) {
            if (gameMove == PAPER) {
                return DRAW;
            } else if (gameMove == SCISSOR) {
                return LOSE;
            } else {
                return WIN;
            }
        } else if (playerMove == SCISSOR ) {
            if (gameMove == SCISSOR) {
                return DRAW;
            } else if (gameMove == ROCK) {
                return LOSE;
            } else {
                return WIN;
            }
        }

        return DRAW; //shouldn't get here
    }
}
