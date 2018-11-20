package com.example.hp.earthquake;


public class earthquake {

    private String url;
    private String mag;
    private String location1;
    private String location2;
    private String  date;
    private String  time;
    private String title;
    earthquake(String m,String loc,String loc1,String da,String t,String url,String title)
    {
        mag = m;
         location1 = loc;
         location2 = loc1;
        date = da;
        time = t;
        this.url = url;
        this.title = title;
    }
    public double getM() {
        return Double.parseDouble(mag);

    }
    public String getMag() {
        return mag;
    }

    public String getDate() {
        return date;
    }

    public String getLocation2() {
        return location2;
    }

    public String getLocation1() {
        return location1;
    }

    public String getTime() {
        return time;
    }

    public String getUrl() {
        return url;
    }
    public String getTitle()
    {
     return title;
    }
}



