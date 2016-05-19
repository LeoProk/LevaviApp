package org.levavi.levaviapp.AppSpecifics;

/**
 * Created by Leo on 4/9/2016.
 */
public class FirebaseItem {

    //make sure that our field names match the names of the properties
    // in the Firebase database
    private String timeStamp;
    private String address;
    private String phone;
    private String title;
    private String text;

    public FirebaseItem(){
        // empty default constructor, necessary for Firebase to be able to deserialize blog posts
    }
    public FirebaseItem(String date, String text ,String address, String phone, String title){
        this.timeStamp = date;
        this.address = address;
        this.phone = phone;
        this.title = title;
        this.text = text;
    }

    public String getTimeStamp() {
        return timeStamp;
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

    public String getText() {
        return text;
    }


}
