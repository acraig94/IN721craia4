package com.bit.alan.locationpractical;

import android.graphics.Point;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

/**
 * Created by Alan on 5/05/2016.
 */
public class CoordinateGenerator {

    private static final double LATMIN = -90;
    private static final double LATMAX = 90;
    private static final double LNGMIN = -180;
    private static final double LNGMAX = 180;

    private Random rand;
    private double lat;
    private double lng;

    public CoordinateGenerator(Random rand){
        this.rand = rand;
        GenerateLat();
        GenerateLng();
    }

    public void GenerateLat(){
        double x = LATMIN + (LATMAX-LATMIN) * rand.nextDouble();
        lat = new BigDecimal(x).setScale(3, RoundingMode.HALF_EVEN).doubleValue();
    }
    public void GenerateLng(){
        double x = LNGMIN + (LNGMAX-LNGMIN) * rand.nextDouble();
        lng = new BigDecimal(x).setScale(3, RoundingMode.HALF_EVEN).doubleValue();
    }
    public double GetLat() {return lat;}
    public double GetLng() {return lng;}

    public String ToString(){
        return "Lat : " + lat + "\n" + "Lng : " + lng;
    }
}
