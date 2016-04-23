package org.levavi.levaviapp.AppSpecifics;

/**
 * Created by Leo on 4/9/2016.
 */
public class RetrievedItem {

    //make sure that our field names match the names of the properties
    // in the Firebase database
    private String date;
    private String address;
    private String phone;
    private String title;
    private int distance;

    public RetrievedItem(){
        // empty default constructor, necessary for Firebase to be able to deserialize blog posts
    }

    public String getDate() {
        return date;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getTitle() {
        return title;
    }

    public int getDistance() {
        return distance;
    }


}
