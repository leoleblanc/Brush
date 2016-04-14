package com.example;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Serah on 4/13/2016.
 * This class holds the tooth brushing stats for a particular day.
 */
public class StatObj {

    private String today;
    private int circular_score;
    private int horizontal_score;
    private int vertical_score;
    private int total_score;

    public StatObj(int c_score, int h_score, int v_score)
    {
        total_score = c_score + h_score + v_score;
        circular_score = c_score;
        horizontal_score = h_score;
        vertical_score = v_score;
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        today = df.format(c.getTime());
        System.out.println("WATCH BAZOOKA " + today);
    }

    public StatObj()
    {
        today="";
        total_score=0;
        circular_score=0;
        horizontal_score=0;
        vertical_score=0;
    }

    public String getParcel()
    /**
     * Returns a string format of the information encapsulated by the
     * current obj. Used for passing from the watch to the phone.
     * FORMAT: today;circular_score;horizontal_score;vertical_score;total_score;
     */
    {
        return today+';'+circular_score+';'+horizontal_score+';'+
                vertical_score+';'+total_score+';';
    }

    public void parseParcel(String parcel)
    /**
     * Parses the given string for relevant information and stores them in
     * the correct fields of the object the function is called on.
     * FORMAT: today;circular_score;horizontal_score;vertical_score;total_score;
     */
    {
        int pos = parcel.indexOf(';');
        this.today = parcel.substring(0, pos);
        int end = parcel.indexOf(';', pos + 1);
        this.circular_score = Integer.parseInt(parcel.substring(pos+1,end));
        pos = parcel.indexOf(';', end + 1);
        this.horizontal_score = Integer.parseInt(parcel.substring(end + 1, pos));
        end = parcel.indexOf(';', pos + 1);
        this.vertical_score = Integer.parseInt(parcel.substring(pos + 1, end));
        pos = parcel.indexOf(';', end + 1);
        this.total_score = Integer.parseInt(parcel.substring(end+1,pos));
    }

    public String getDate()
    {
        return this.today;
    }

    public int getCircularScore()
    {
        return this.circular_score;
    }
    public int getHorizontalScore()
    {
        return this.horizontal_score;
    }
    public int getVerticalScore()
    {
        return this.vertical_score;
    }
    public int getTotalScore()
    {
        return this.total_score;
    }


}
