package com.example.tictactoeal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static boolean pturn = true;
    static Box player = Box.X;


    GameTree tree = new GameTree();
    public TextView board1;
    public TextView board2;
    public TextView board3;
    public TextView board4;
    public TextView board5;
    public TextView board6;
    public TextView board7;
    public TextView board8;
    public TextView board9;
    public TextView probText;
    //public Button undo;


    public void ready(){
        tree.setRoot(tree.buildTree(tree.getRoot(), player));
        tree.setCursor(tree.getRoot());
        tree.cursorProbability(tree.getCursor());

    }
    public void start(){
        board1 = (TextView)findViewById(R.id.board1);
        board2 = (TextView)findViewById(R.id.board2);
        board3 = (TextView)findViewById(R.id.board3);
        board4 = (TextView)findViewById(R.id.board4);
        board5 = (TextView)findViewById(R.id.board5);
        board6 = (TextView)findViewById(R.id.board6);
        board7 = (TextView)findViewById(R.id.board7);
        board8 = (TextView)findViewById(R.id.board8);
        board9 = (TextView)findViewById(R.id.board9);
        //undo = (Button)findViewById(R.id.undoButton);


        board1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                board1.setEnabled(false);
                tree.makeMove(0);
                board1.setText("X");
                pturn = false;
                if(tree.getCursor().isEnd()){
                    end();
                }else {
                    comp();


                }



            }
        });

        board2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                board2.setEnabled(false);
                tree.makeMove(1);
                board2.setText("X");
                pturn = false;
                if(tree.getCursor().isEnd()){
                    end();
                }else {
                    comp();
                }

            }
        });

        board3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                board3.setEnabled(false);
                tree.makeMove(2);
                board3.setText("X");
                pturn = false;
                if(tree.getCursor().isEnd()){
                    end();
                }else {
                    comp();
                }
            }
        });

        board4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                board4.setEnabled(false);
                tree.makeMove(3);
                board4.setText("X");
                pturn = false;
                if(tree.getCursor().isEnd()){
                    end();
                }else {
                    comp();
                }
            }
        });

        board5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                board5.setEnabled(false);
                tree.makeMove(4);
                board5.setText("X");
                pturn = false;
                if(tree.getCursor().isEnd()){
                    end();
                }else {
                    comp();
                }
            }
        });

        board6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                board6.setEnabled(false);
                tree.makeMove(5);
                board6.setText("X");
                pturn = false;
                if(tree.getCursor().isEnd()){
                    end();
                }else {
                    comp();
                }

            }
        });
        board7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                board7.setEnabled(false);
                tree.makeMove(6);
                board7.setText("X");
                pturn = false;
                if(tree.getCursor().isEnd()){
                    end();
                }else {
                    comp();
                }
            }
        });

        board8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                board8.setEnabled(false);
                tree.makeMove(7);
                board8.setText("X");
                pturn = false;
                if(tree.getCursor().isEnd()){
                    end();
                }else {
                    comp();
                }
            }
        });

        board9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                board9.setEnabled(false);
                tree.makeMove(8);
                board9.setText("X");
                pturn = false;
                if(tree.getCursor().isEnd()){
                    end();
                }else {
                    comp();
                }
            }
        });










    }


    public void comp(){

        if(!pturn){
            double max = 2;
            int nextmove = -1;
            for(int i = 0; i < 9; i++){
                if(tree.getCursor().getConfig()[i]!=null){
                    //System.out.println(max);
                    tree.cursorProbability(tree.getCursor().getConfig()[i]);
                    if(max > tree.getCursor().getConfig()[i].getWinProb()){
                        max = tree.getCursor().getConfig()[i].getWinProb();
                        nextmove = i;
                    }
                }
            }
            for(int i = 0; i < 9; i++){
                if(tree.getCursor().getConfig()[i] != null
                        && tree.getCursor().getConfig()[i].isLeaf()
                        && tree.getCursor().getConfig()[i].getWinner()==Box.O){
                    nextmove = i;
                    break;
                }
                if(tree.getCursor().getConfig()[nextmove].getConfig()[i] != null

                        && tree.getCursor().getConfig()[nextmove].getConfig()[i].getWinner()==Box.X){
                    System.out.println(nextmove + 1);
                    nextmove = i;
                    break;
                }
            }
            tree.makeMove(nextmove);
            if(nextmove==0){
                board1.setEnabled(false);
                board1.setText("O");
            }
            if(nextmove==1){
                board2.setEnabled(false);
                board2.setText("O");
            }
            if(nextmove==2){
                board3.setEnabled(false);
                board3.setText("O");
            }
            if(nextmove==3){
                board4.setEnabled(false);
                board4.setText("O");
            }
            if(nextmove==4){
                board5.setEnabled(false);
                board5.setText("O");
            }
            if(nextmove==5){
                board6.setEnabled(false);
                board6.setText("O");
            }
            if(nextmove==6){
                board7.setEnabled(false);
                board7.setText("O");
            }
            if(nextmove==7){
                board8.setEnabled(false);
                board8.setText("O");
            }
            if(nextmove==8){
                board9.setEnabled(false);
                board9.setText("O");
            }
            if(!tree.getCursor().isEnd()){
                tree.cursorProbability(tree.getCursor());
            }else{
                end();
            }

            String output = "";
            output +="The probability of a Win is ";
            float win = (float)Math.round(tree.getCursor().getWinProb() * 100) / 100;
            output += win + ".";
            output += "\n";
            output +="The probability of a Draw is ";
            float draw = (float)Math.round(tree.getCursor().getDrawProb() * 100) / 100;
            output += draw + ".";
            output += "\n";
            output +="The probability of a Lose is ";
            float lose = (float)Math.round(tree.getCursor().getLoseProb() * 100) / 100;
            output += lose + ".";
            output += "\n";
            if(tree.getCursor().isEnd()){
                if(tree.getCursor().getWinner()==Box.X){
                    output += "The winner is: X.";
                }else if(tree.getCursor().getWinner()==Box.O){
                    output += "The winner is: O.";
                }else{
                    output += "It is a draw.";
                }
            }


            probText = (TextView) findViewById(R.id.probText);
            probText.setText(output);




            pturn = true;
            start();


        }
    }


    public void end(){

        board1.setEnabled(false);
        board2.setEnabled(false);
        board3.setEnabled(false);
        board4.setEnabled(false);
        board5.setEnabled(false);
        board6.setEnabled(false);
        board7.setEnabled(false);
        board8.setEnabled(false);
        board9.setEnabled(false);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ready();
        start();
    }







}
