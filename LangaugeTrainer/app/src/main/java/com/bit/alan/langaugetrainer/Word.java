package com.bit.alan.langaugetrainer;

import java.io.Serializable;

/**
 * Created by Alan on 27/03/2016.
 */
public class Word implements Serializable
{
    private String article;
    private String germanWord;
    private int imageID;

    public Word(String article, String germanWord, int imageID)
    {
        this.article = article;
        this.germanWord = germanWord;
        this.imageID = imageID;
    }

    public boolean checkAnswer(String submittedAnswer)
    {
        boolean result = false;

        if (submittedAnswer.equals(article))
            result = true;

        return result;
    }

    public int getImageID() { return imageID; }
    public String getArticle() { return article; }

    @Override
    public String toString()
    {
        return article + " " + germanWord;
    }
}
