package com.bit.alan.topartists;

/**
 * Created by Alan on 13/04/2016.
 */
public class Artist
{
    private String name;
    public String Name() {return name;}
    private int count;
    public int Count(){return count;}

    public Artist(String name, int count)
    {
        this.name = name;
        this.count = count;
    }

}
