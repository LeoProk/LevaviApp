package org.levavi.levaviapp.AppSpecifics;

/**
 * Created by Leo on 4/9/2016.
 */
public class FirebaseItem {

    //make sure that our field names match the names of the properties
    // in the Firebase database
    private String date;
    private String address;
    private String phone;
    private String title;
    private String text;
    private int distance;

    public FirebaseItem(){
        // empty default constructor, necessary for Firebase to be able to deserialize blog posts
    }
    public FirebaseItem(String date, String text ,String address, String phone, String title, int distance){
        this.date = date;
        this.address = address;
        this.phone = phone;
        this.title = title;
        this.text = text;
        this.distance = distance;
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

    public String getText() {
        return text;
    }


}
