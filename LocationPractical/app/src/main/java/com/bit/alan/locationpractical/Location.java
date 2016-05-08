package com.bit.alan.locationpractical;

/**
 * Created by Alan on 6/05/2016.
 */
public class Location
{
    private String city;
    private String countryCode;
    private double lat;
    private double lng;

    public Location(String city, String countryCode, double lat, double lng){
        this.city = city;
        this.countryCode = countryCode;
        this.lat = lat;
        this.lng = lng;
    }

    public String GetCity(){
        return city;
    }
    public String GetCountryCode(){
        return countryCode;
    }
    public double GetLat(){
        return lat;
    }
    public double GetLng(){
        return lng;
    }
    public String GetPos(){
        return lat + ", " + lng;
    }
    @Override
    public String toString(){
        return city + ", " + countryCode;
    }
}
