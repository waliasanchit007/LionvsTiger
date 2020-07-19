package com.example.lionvstiger;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.Analyzer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    enum Player{
        ONE,TWO
    }

    private Set<Integer> placesCoveredByLion = new HashSet<Integer>() ;
    private Set<Integer> placesCoveredByTiger = new HashSet<Integer>();

    private String tag;

   //no. of clicks done by each player
    private int boxesFilledByLion = 0;
    private int BoxesFilledByTiger = 0;

    //set of sets
    //set containing set of possible win situations
    private Set<Set<Integer>> winnerIfContainsAnyOfThis = new HashSet<Set<Integer>>() ;


    //set containing possible answers
    private Set<Integer> a = new HashSet<Integer>();
    private Set<Integer> b = new HashSet<Integer>();
    private Set<Integer> c = new HashSet<Integer>();
    private Set<Integer> d = new HashSet<Integer>();
    private Set<Integer> e = new HashSet<Integer>();
    private Set<Integer> f = new HashSet<Integer>();
    private Set<Integer> g = new HashSet<Integer>();
    private Set<Integer> h = new HashSet<Integer>();

    Player currentPlayer = Player.ONE;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;
    private ImageView img5;
    private ImageView img6;
    private ImageView img7;
    private ImageView img8;
    private ImageView img9;

//    //Winner Message
//    private AlertDialog.Builder gameOver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         ImageView img1 = findViewById(R.id.img1);
         ImageView img2 = findViewById(R.id.img2);
         ImageView img3 = findViewById(R.id.img3);
         ImageView img4 = findViewById(R.id.img4);
         ImageView img5 = findViewById(R.id.img5);
         ImageView img6 = findViewById(R.id.img6);
         ImageView img7 = findViewById(R.id.img7);
         ImageView img8 = findViewById(R.id.img8);
         ImageView img9 = findViewById(R.id.img9);

        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        img4.setOnClickListener(this);
        img5.setOnClickListener(this);
        img6.setOnClickListener(this);
        img7.setOnClickListener(this);
        img8.setOnClickListener(this);
        img9.setOnClickListener(this);

        a.addAll(Arrays.asList(new Integer[]{1,2,3}));
        b.addAll(Arrays.asList(new Integer[]{4,5,6}));
        c.addAll(Arrays.asList(new Integer[]{7,8,9}));
        d.addAll(Arrays.asList(new Integer[]{1,4,7}));
        e.addAll(Arrays.asList(new Integer[]{2,5,8}));
        f.addAll(Arrays.asList(new Integer[]{3,6,9}));
        g.addAll(Arrays.asList(new Integer[]{1,5,9}));
        h.addAll(Arrays.asList(new Integer[]{3,5,7}));

        winnerIfContainsAnyOfThis.add(a);
        winnerIfContainsAnyOfThis.add(b);
        winnerIfContainsAnyOfThis.add(c);
        winnerIfContainsAnyOfThis.add(d);
        winnerIfContainsAnyOfThis.add(e);
        winnerIfContainsAnyOfThis.add(f);
        winnerIfContainsAnyOfThis.add(g);
        winnerIfContainsAnyOfThis.add(h);


    }
    @Override
    public void onClick(View v){

        ImageView imageIsClicked = (ImageView) v;




        if(currentPlayer == Player.ONE) {
            v.setTranslationX(-2000);
            ((ImageView) v).setImageResource(R.drawable.lion);
            v.animate().translationX(0).rotationY(3600).alpha(1).setDuration(1000);
            currentPlayer = Player.TWO;
            tag = v.getTag().toString();
            placesCoveredByLion.add(Integer.parseInt(tag));
            boxesFilledByLion++;
            if (boxesFilledByLion > 2) {
                for (Set<Integer> temp : winnerIfContainsAnyOfThis) {
                    if(placesCoveredByLion.containsAll(temp))
                    {
//                         gameOver = new AlertDialog.Builder(this);
//                         gameOver.setTitle(R.string.winner_message_lion);
                         Toast.makeText(this,R.string.winner_message_lion,Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
        else if (currentPlayer == Player.TWO){
            v.setTranslationX(-2000);
            ((ImageView) v).setImageResource(R.drawable.tiger);
            v.animate().translationX(0).rotationY(3600).alpha(1).setDuration(1000);
            currentPlayer = Player.ONE;
            tag = v.getTag().toString();
            placesCoveredByTiger.add(Integer.parseInt(tag));
            BoxesFilledByTiger++;

            if (BoxesFilledByTiger > 2) {
                for (Set<Integer> temp : winnerIfContainsAnyOfThis) {
                    if(placesCoveredByTiger.containsAll(temp))
                    {
//                        gameOver = new AlertDialog.Builder(this);
//                        gameOver.setTitle(R.string.winner_message_tiger);
                        Toast.makeText(this,R.string.winner_message_tiger,Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
        //disable overwrite
        v.setClickable(false);

        // winner declaration
        if(BoxesFilledByTiger>4 || boxesFilledByLion>4){
            Toast.makeText(this,R.string.draw,Toast.LENGTH_LONG).show();
//            gameOver  = new AlertDialog.Builder(this);
//            gameOver.setTitle(R.string.draw);
        }

    }
}