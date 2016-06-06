package org.levavi.levaviapp.AppSpecifics;

/**
 * fire base helper object for saving and retriving data from the server
 */
public class FirebaseItem {

    //make sure that our field names match the names of the properties
    // in the Firebase database
    private String timeStamp;
    private String address;
    private String phone;
    private String title;
    private String text;
    private String subject;
    private String duration;
    private String user;

    public FirebaseItem(){
        // empty default constructor, necessary for Firebase to be able to deserialize blog posts
    }
    public FirebaseItem(String subject,String date, String text ,String address, String phone
            , String title,String user,String duration){
        this.subject = subject;
        this.timeStamp = date;
        this.address = address;
        this.phone = phone;
        this.title = title;
        this.text = text;
        this.user = user;
        this.duration = duration;
    }

    public String getSubject() {
        return subject;
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

    public String getUser() {
        return user;
    }

    public String getDuration() {
        return duration;
    }
}
