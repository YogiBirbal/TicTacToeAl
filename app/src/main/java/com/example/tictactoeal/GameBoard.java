package com.example.tictactoeal;

/**
 * Created by Yogi on 4/5/2017.
 */

/**
 * Yogi Birbal
 */


public class GameBoard {

    private Box[] board; //Sequence of Gameboard
    private final int boardsize = 9; //Final size of GameBoard
    private Box win; //Winner of this GameBoard

    /**
     * Default Constructor for GameBoard
     * Initializes Board size to 9
     */
    public GameBoard(){
        this.board = new Box[boardsize];
    }

    /**
     * Getter for the Array of the GameBoard
     *
     * @return -> Array of GameBoard
     */
    public Box[] getBoard() {
        return board;
    }

    /**
     * Setter for the Array of the GameBoard
     * @param board -> new Array of Board
     */
    public void setBoard(Box[] board) {
        this.board = board;
    }

    /**
     * Getter for the Board size
     * @return -> Board Size
     */
    public int getBoardsize() {
        return boardsize;
    }

    /**
     * Puts GameBoard into a readable String
     * @return -> String of GameBoard
     */
    public String toString(){
        String x = "";
        for(int i = 0; i < this.board.length; i++){
            x += "|";
            if(board[i]==Box.X){
                x += "X";
            }else if (board[i]==Box.O){
                x += "O";
            }else{
                x += "_";
            }
            if(i==2 || i==5){
                x += "|\n";
            }
            if(i == 8){
                x += "|";
            }
        }
        return x;
    }


    /**
     * Creates a clone of the current GameBoard.
     * @return -> Clone
     */
    public GameBoard clone(){
        Box[] clone = new Box[boardsize];
        GameBoard c = new GameBoard();
        for(int i = 0; i < this.board.length; i++){
            clone[i] = this.board[i];
        }
        c.board = clone;
        return c;
    }

    /**
     * Getter for the Winner of the Board
     * @return -> Winner
     */
    public Box getWin() {
        return win;
    }

    /**
     * Setter for the Winner of the Board
     * @param win -> new Winner
     */
    public void setWin(Box win) {
        this.win = win;
    }


}
