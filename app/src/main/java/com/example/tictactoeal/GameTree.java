package com.example.tictactoeal;

/**
 * Created by Yogi on 4/5/2017.
 */

/**
 * Yoganand Birbal
 * 111151626
 * Homework #5
 * CSE 214 R14
 * Tayo Amuneke & Yiwen Wang
 * Anand Aiyer
 */
public class GameTree {

    private GameBoardNode root; //Root of the tree
    private GameBoardNode cursor; //Cursor of the tree


    /**
     * Default Constructor for a GameTree.
     * An empty board is created and passed as the Board of the Root
     */
    public GameTree(){
        GameBoard x = new GameBoard();
        Box[]c = new Box[9];
        for(int i = 0; i < 9; i++){
            c[i] = Box.EMPTY;
        }
        x.setBoard(c);
        this.root = new GameBoardNode(x);
        this.cursor = root;


    }

    /**
     * Makes the move at the parameter position
     * @param position -> Position of the next move
     * @throws IllegalArgumentException -> If node is null or invalid
     */
    public void makeMove(int position) throws IllegalArgumentException{
        if(position == -1){
            this.setCursor(this.getCursor().getParent().getParent().getParent().getParent());
            return;
        }
        if(position + 1 < 0||position + 1 > 9|| this.cursor.getConfig()[position]==null){
            throw new IllegalArgumentException();
        }
        for(int i = 0; i < 9; i++){
            if(i == position){
                this.setCursor(this.cursor.getConfig()[i]);

            }
        }
    }

    /**
     * Builds the entire Tree of a given node
     * @param root -> Root of the tree
     * @param turn -> Current turn of the next configuration
     * @return -> Root of the tree
     */
    public static GameBoardNode buildTree(GameBoardNode root, Box turn){
        root = new GameBoardNode(root.getBoard(), turn, root);

        for(int i = 0; i < 9; i++){
            if(root.getConfig()[i] != null){
                if(root.End()){
                    continue;
                }
                Box d = nextTurn(turn);
                root.getConfig()[i] = new GameBoardNode(root.getConfig()[i].getBoard(), d, root);
                root.getConfig()[i] = buildTree(root.getConfig()[i], d);
            }else{
                continue;
            }
        }
        return root;


    }

    /**
     * Determines the next turn
     * @param turn -> Current turn
     * @return -> Next Turn
     */
    private static Box nextTurn(Box turn) {
        if(turn == Box.O)
            return Box.X;
        else return Box.O;

    }

    /**
     * Determines the win of a particular node
     * If there is no Winner, null is returned. If draw, Box.EMPTY is returned
     * @param node -> Node being checked
     * @return -> Winner of this Node
     */
    public static Box checkWin(GameBoardNode node){

        for(int i = 0; i < 9; i++){

            if(node.getConfig()[i] != null)
                return null;
        }
        if(node.getWinner()==null){
            return Box.EMPTY;
        }else{
            return node.getWinner();
        }

    }

    /**
     * Sets the probabilities of the cursor
     * @param node -> Cursor Node
     */
    public void cursorProbability(GameBoardNode node){
        double total = this.getLeaves(node);
        double win = this.getWin(node);
        double lose = this.getLoss(node);
        double draw = this.getDraw(node);
        node.setWinProb(win/total);
        node.setLoseProb(lose/total);
        node.setDrawProb(draw/total);
    }

    /**
     * Gets the number of Leaves of a specific node in the tree
     * @param node -> Node of tree
     * @return -> Number of Leaves
     */
    public int getLeaves(GameBoardNode node){
        int x = 0;
        if(node.isLeaf()){
            return 1;
        }
        for(int i = 0; i < 9; i++){
            if(node.getConfig()[i] != null){
                x += getLeaves(node.getConfig()[i]);
            }
        }
        return x;
    }

    /**
     * Gets the number of Leaves where X is the Winner of a specific node in the tree
     * @param node -> Node of tree
     * @return -> Number of Wins
     */
    public int getWin(GameBoardNode node){
        int x = 0;
        if(node.isLeaf() && node.getWinner() == Box.X){
            return 1;
        }else if(node.isLeaf() && node.getWinner() != Box.X){
            return 0;
        }else{
            for(int i = 0; i < 9; i++){
                if(node.getConfig()[i] != null){
                    x += getWin(node.getConfig()[i]);
                }
            }
        }
        return x;
    }

    /**
     * Gets the number of Leaves where O is the Winner of a specific node in the tree
     * @param node -> Node of tree
     * @return -> Number of Wins
     */
    public int getLoss(GameBoardNode node){
        int x = 0;
        if(node.isLeaf() && node.getWinner() == Box.O){
            return 1;
        }else if(node.isLeaf() && node.getWinner() != Box.O){
            return 0;
        }else{
            for(int i = 0; i < 9; i++){
                if(node.getConfig()[i] != null){
                    x += getLoss(node.getConfig()[i]);
                }
            }
        }
        return x;
    }

    /**
     * Gets the number of Leaves there is a draw of a specific node in the tree
     * @param node -> Node of tree
     * @return -> Number of Draws
     */
    public int getDraw(GameBoardNode node){
        int x = 0;
        if(node.isLeaf() && node.getWinner() == Box.EMPTY){
            return 1;
        }else if(node.isLeaf() && node.getWinner() != Box.EMPTY){
            return 0;
        }else{
            for(int i = 0; i < 9; i++){
                if(node.getConfig()[i] != null){
                    x += getDraw(node.getConfig()[i]);
                }
            }
        }
        return x;
    }



    /**
     * Getter for the Root of the Tree
     * @return -> Root of Tree
     */
    public GameBoardNode getRoot() {
        return root;
    }

    /**
     * Setter for the root of the Tree
     * @param root -> New Root of Tree
     */
    public void setRoot(GameBoardNode root) {
        this.root = root;
    }

    /**
     * Getter for the Cursor of the Tree
     * @return -> Cursor of Tree
     */
    public GameBoardNode getCursor() {
        return cursor;
    }

    /**
     * Setter for the Cursor of the Tree
     * @param cursor -> New Cursor of Tree
     */
    public void setCursor(GameBoardNode cursor) {
        this.cursor = cursor;
    }




}
