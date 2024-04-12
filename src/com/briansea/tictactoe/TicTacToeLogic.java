package com.briansea.tictactoe;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * @author Brian Sea
 * @date 8/20/2022
 *
 * The logic for a generic Tic Tac Toe game
 */

public class TicTacToeLogic {

    /**
     * Represents the board of Tic Tac Toe.
     * The index indicates the space
     * The value indicates who is in the space (-1 = O, 1 = X, 0 = Empty)
     */
    private int[] board;

    // 1 = X, -1 = O
    private int turn;
    private boolean gameOver;

    /**
     * Create a 3x3 board
     */
    public TicTacToeLogic(){
        this(3);
    }

    /**
     * Creates a NxN board
     * @param dimension number of rows and columns; anything < 3 is set to 3
     */
    public TicTacToeLogic( int dimension ){
        if(dimension < 3){ dimension = 3;}
        board = new int[dimension * dimension];
        for(int i = 0; i < dimension * dimension; i++){
            board[i] = 0;

        }
        // sets x to go first
        turn = 1;

        }


    /**
     * Resets the board to the start of the game
     */
    public void reset(){
        int dimension = getDimension();
        board = new int[dimension * dimension];
        for(int i = 0; i < dimension * dimension; i++){
            board[i] = 0;
        }
        turn = 1;

    }

    /**
     * Attempts to make a move
     * @param space space to make the move
     * @return true if the move was made, false if invalid
     */
    public boolean makeMove( int space ){
        if(gameOver){
            return false;
        }
        if (board[space] == 0){
            board[space] = turn;
            turn = turn * -1;
            return true;
        } else{
            return false;
        }


    }

    /**
     * Check for a winner
     * @return "X", "O", or "TIE" if the game is over, empty string otherwise
     */
    public String checkWinner() {
        int dimension = getDimension();
        int total;

        // Check rows
        for (int i = 0; i < board.length; i += dimension) {
            total = 0;
            for (int j = i; j < i + dimension; j++) {
                total += board[j];
            }
            String result = checkScore(total);
            if (!result.isEmpty()) {
                return result;
            }
        }

        // Check columns
        for (int col = 0; col < dimension; col++) {
            total = 0;
            for (int row = 0; row < dimension; row++) {
                int index = col + row * dimension;
                total += board[index];
            }
            String result = checkScore(total);
            if (!result.isEmpty()) {
                return result;
            }
        }

        // Check diagonals
        total = 0;
        for (int i = 0; i < board.length; i += dimension + 1) {
            total += board[i];
        }
        String result = checkScore(total);
        if (!result.isEmpty()) {
            return result;
        }

        total = 0;
        for (int i = dimension - 1; i < board.length - 1; i += dimension - 1) {
            total += board[i];
        }
        result = checkScore(total);
        if (!result.isEmpty()) {
            return result;
        }

        // Check for a tie

        boolean filled = true;
        for (int index = 0; index < board.length; index++) { // Check for a tie
            if (board[index] == 0) {
                filled = false;
            }
        }
        if (filled) { return "TIE"; }
        return "";
            }

    private String checkScore( int score ){
        String rtn = "";
        gameOver = true;
        if(score == getDimension()){
            rtn = "X";
        }
        else if(score == getDimension() * -1){
            rtn = "O";
        }
        else {
            gameOver = false;

        }
            return rtn;

    }

    /**
     * Check the owner of a space
     * @param spot the space to check
     * @return the owner ("X" or "O"), empty string if not occupied or spot is invalid
     */
    public String getOwner(int spot){
        String rtn = "";

        if( spot >= 0 && spot < board.length ) {
            if( board[spot] == 1 ){
                rtn = "X";
            }
            else if( board[spot] == -1 ){
                rtn = "O";
            }
        }
        return rtn;
    }

    /**
     * Whose turn is it?
     * @return "X" or "O" depending on whose turn it currently is
     */
    public String whoseTurn(){
        String rtn = "X";
        if( turn == -1 ){
            rtn = "O";
        }
        return rtn;
    }

    /**
     * Is the game over?
     * @return true if the game is over, false otherwise
     */
    public boolean isGameOver(){
        String result = checkWinner();
        gameOver = result.equals("X") || result.equals("O") || result.equals("TIE");
        return gameOver;
    }

    /**
     * Get the number of rows and columns currently in use
     * @return the number rows/columns in the board
     */
    public int getDimension(){
        return (int)Math.sqrt(board.length);
    }
}
