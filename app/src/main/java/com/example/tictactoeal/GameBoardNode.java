package com.example.tictactoeal;

/**
 * Created by Yogi on 4/5/2017.
 */

/**
 * Yogi Birbal
 */

public class GameBoardNode {

    private GameBoard board; //GameBoard of this node
    private boolean isEnd; //Whether the Game Ends at this node
    private Box currentTurn; //Current Turn of this Node
    private Box winner; //Winner of this Node
    private GameBoardNode config[]; //Children of this node, representing possible moves from this node
    private GameBoardNode parent;
    private double winProb; //Winning Probability from this node
    private double loseProb; //Losing Probability from this node
    private double drawProb; //Draw Probability from this node


    /**
     * Constructor for the GameBoard Node. Determines configurations based off of the parameter board.
     * @param board -> Current game Board for this node
     * @param currentTurn -> Current Turn used for the configurations of this node
     * @param parent -> reference to the parent node
     */
    public GameBoardNode(GameBoard board, Box currentTurn, GameBoardNode parent){
        this.board = board;
        this.parent = parent;

        this.config = new GameBoardNode[9];
        for(int i = 0; i < 9; i++){
            if(this.board.getBoard()[i] == Box.EMPTY && !this.isEnd){
                GameBoard x = (GameBoard) this.board.clone();
                x.getBoard()[i] = currentTurn;
                this.config[i] = new GameBoardNode(x);
            }else{
                config[i] = null;
            }
            this.isEnd = End();
        }
    }

    /**
     * Constructor for the GameBoard Node
     * @param board -> board of this node
     */
    public GameBoardNode(GameBoard board){
        this.board = board;
    }




    /**
     * Determines whether there is a win or if the board is filled.
     * Sets the Winner variable
     * @return -> Whether there is a win or if the board is done
     */
    public boolean win(){
        if(board.getBoard()[0] == board.getBoard()[3] &&
                board.getBoard()[0] == board.getBoard()[6] && board.getBoard()[0]!= Box.EMPTY){
            winner = board.getBoard()[0];
            return true;
        }
        else if(board.getBoard()[1] == board.getBoard()[4] &&
                board.getBoard()[1] == board.getBoard()[7] && board.getBoard()[1]!= Box.EMPTY){
            winner = board.getBoard()[1];
            return true;
        }
        else if(board.getBoard()[2] == board.getBoard()[5] &&
                board.getBoard()[2] == board.getBoard()[8] && board.getBoard()[2]!= Box.EMPTY){
            winner = board.getBoard()[2];
            return true;
        }
        else if(board.getBoard()[0] == board.getBoard()[1] &&
                board.getBoard()[0] == board.getBoard()[2] && board.getBoard()[0]!= Box.EMPTY){
            winner = board.getBoard()[0];
            return true;
        }
        else if(board.getBoard()[3] == board.getBoard()[4] &&
                board.getBoard()[3] == board.getBoard()[5] && board.getBoard()[3]!= Box.EMPTY){
            winner = board.getBoard()[3];
            return true;
        }
        else if(board.getBoard()[6] == board.getBoard()[7] &&
                board.getBoard()[6] == board.getBoard()[8] && board.getBoard()[6]!= Box.EMPTY){
            winner = board.getBoard()[6];
            return true;
        }
        else if(board.getBoard()[0] == board.getBoard()[4] &&
                board.getBoard()[0] == board.getBoard()[8] && board.getBoard()[0]!= Box.EMPTY){
            winner = board.getBoard()[0];
            return true;
        }
        else if(board.getBoard()[2] == board.getBoard()[4] &&
                board.getBoard()[2] == board.getBoard()[6] && board.getBoard()[2]!= Box.EMPTY){
            winner = board.getBoard()[2];
            return true;
        }
        else if(this.isFilled()){
            winner = Box.EMPTY;
            return true;
        }else{
            winner = null;
            return false;
        }
    }

    /**
     * Sets the probabilities of this node
     */
    public void setProbabilities(){
        double total;
        double win;
        double lose;
        double draw;
    }

    /**
     * Determines if the board is filled
     * @return
     */
    public boolean isFilled(){
        for(int i = 0; i < 9; i++){
            if(this.board.getBoard()[i]== Box.EMPTY)
                return false;
        }
        return true;
    }


    /**
     * Determines if this node is a leaf
     * @return -> Whether this node is a leaf
     */
    public boolean isLeaf(){
        for(int i = 0; i < 9; i++){
            if(config == null)
                return true;
            if(config[i] != null)
                return false;
        }
        return true;
    }


    /**
     * Determines whether the game has ended or not
     * @return -> Whether game has ended
     */
    public boolean End(){
        //boolean g = false;
        if(this.win()){
            return true;
        }
        for(int i = 0; i < 9; i++){
            if(this.board.getBoard()[i]==Box.EMPTY){
                return false;
            }
        }
        return true;
    }


    /**
     * Getter for the parent of the node
     * @return -> Parent
     */
    public GameBoardNode getParent() {
        return parent;
    }

    /**
     * Setter for the Parent of this node
     * @param parent -> new Parent
     */
    public void setParent(GameBoardNode parent) {
        this.parent = parent;
    }

    /**
     * Returns the Board of this node in a String
     */
    public String toString(){
        return this.board.toString();
    }

    /**
     * Getter for the GameBoard
     * @return -> GameBoard
     */
    public GameBoard getBoard() {
        return board;
    }

    /**
     * Setter for the GameBoard
     * @param board -> new Board
     */
    public void setBoard(GameBoard board) {
        this.board = board;
    }

    /**
     * Determines if the game can go on anymore
     * @return -> End of Game
     */
    public boolean isEnd() {
        return isEnd;
    }

    /**
     * Getter for the Current Turn
     * @return -> Current Turn of Node
     */
    public Box getCurrentTurn() {
        return currentTurn;
    }

    /**
     * Setter for the Current Turn
     * @param currentTurn -> New Current Turn
     */
    public void setCurrentTurn(Box currentTurn) {
        this.currentTurn = currentTurn;
    }

    /**
     * Getter for the Winner
     * @return -> Winner of Node
     */
    public Box getWinner() {
        return winner;
    }

    /**
     * Setter for the Winner
     * @param winner -> new Winner
     */
    public void setWinner(Box winner) {
        this.winner = winner;
    }

    /**
     * Getter for the Children of this node
     * @return -> Children
     */
    public GameBoardNode[] getConfig() {
        return config;
    }

    /**
     * Setter for the Children of this node
     * @param config -> New Children
     */
    public void setConfig(GameBoardNode[] config) {
        this.config = config;
    }

    /**
     * Getter for the Win Probability
     * @return -> Win probability
     */
    public double getWinProb() {
        return winProb;
    }

    /**
     * Setter for the Win Probability
     * @param winProb -> new Win Probability
     */
    public void setWinProb(double winProb) {
        this.winProb = winProb;
    }

    /**
     * Getter for the Lose Probability
     * @return -> Lose probability
     */
    public double getLoseProb() {
        return loseProb;
    }

    /**
     * Setter for the Lose Probability
     * @param loseProb -> new Lose Probability
     */
    public void setLoseProb(double loseProb) {
        this.loseProb = loseProb;
    }

    /**
     * Getter for the Draw Probability
     * @return -> Draw probability
     */
    public double getDrawProb() {
        return drawProb;
    }

    /**
     * Setter for the Draw Probability
     * @param drawProb -> new Draw Probability
     */
    public void setDrawProb(double drawProb) {
        this.drawProb = drawProb;
    }





}
