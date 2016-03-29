package com.bit.alan.langaugetrainer;

import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Alan on 27/03/2016.
 */
public class Manager
{
    private static final int numWords = 11;
    private Word[] questionArray;
    private int score;
    private int currentQuestion;
    private Random rand;
    private View v;

    public Manager()
    {
        score = 0;
        currentQuestion = 0;
        rand = new Random();

        questionArray = shuffleList(makeList());

    }

    public Word[] makeList()
    {

        Word[] newArray = new Word[numWords];

        String[] wordArray = {"Auto", "Haus", "Schaf", "Apfel", "Baum", "Stuhl", "Ente", "Hexe", "Kuh", "Milch", "Strasse"};
        String[] articleArray = {"Das", "Das", "Das", "Der", "Der", "Der", "Die", "Die", "Die", "Die", "Die"};
        //String[] imageNameArray = {"das_auto.jpg", "das_haus.jpg", "das_schaf.jpg", "der_apfel.jpg", "der_baum.jpg", "der_stuhl", "die _ente", "die_hexe", "die_kuh", "die_milch", "die_strasse"};
        int[] imageIdArray =
                {
                        R.drawable.das_auto,
                        R.drawable.das_haus,
                        R.drawable.das_schaf,
                        R.drawable.der_apfel,
                        R.drawable.der_baum,
                        R.drawable.der_stuhl,
                        R.drawable.die_ente,
                        R.drawable.die_hexe,
                        R.drawable.die_kuh,
                        R.drawable.die_milch,
                        R.drawable.die_strasse
                };

        for (int i = 0; i < numWords; i++)
        {
            newArray[i] = new Word(articleArray[i], wordArray[i], imageIdArray[i]);
        }

        return newArray;
    }

    public Word[] shuffleList(Word[] unorded)
    {
        Word[] newList = unorded;

        for (int i = 0; i < 100; i++)
        {
            int item1 = rand.nextInt(numWords);
            int item2 = rand.nextInt(numWords);
            Word temp = newList[item1];
            newList[item1] = newList[item2];
            newList[item2] = temp;
        }

        return newList;
    }

    public Word[] getList(){ return questionArray;}

    public int getScore() { return score;}

    public int getCurrentImage() { return questionArray[currentQuestion].getImageID();}

    public Word getCurrentQuestion() { return questionArray[currentQuestion]; }

    public int getQuestionNumber() { return currentQuestion; }

    public void addScore() { score += 1; }

    public void nextQuestion() { currentQuestion += 1; }

    public boolean isLastQuestion()
    {
        boolean result = false;
        if (currentQuestion == (numWords-1))
            result = true;
        return result;
    }

}
