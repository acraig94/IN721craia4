package com.bit.alan.allaboutdunedinadapter;

import android.graphics.drawable.Drawable;

/**
 * Created by Alan on 1/04/2016.
 */
public class Place
{
    private String name;
    private Drawable image;
    public Drawable Image(){return image;}

    public Place(String name, Drawable image)
    {
        this.name = name;
        this.image = image;
    }
    @Override
    public String toString()
    {
        return name;
    }

}
