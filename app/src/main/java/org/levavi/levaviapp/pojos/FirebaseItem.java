package org.levavi.levaviapp.pojos;

/**
 * fire base helper object for saving and retriving data from the server
 */
public class FirebaseItem implements Comparable {

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
    private String price;
    private String image;
    //the distance of the item location and current location
    private int mDistance;

    public FirebaseItem(){
        // empty default constructor, necessary for Firebase to be able to deserialize blog posts
    }
    public FirebaseItem(String subject,String date, String text ,String address, String phone
            , String title,String user,String duration,String price,String image){
        this.subject = subject;
        this.timeStamp = date;
        this.address = address;
        this.phone = phone;
        this.title = title;
        this.text = text;
        this.user = user;
        this.duration = duration;
        this.price = price;
        this.image = image;
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

    public String getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }


    public int getDistance() {
        return mDistance;
    }


    @Override
    public int compareTo(Object compare) {
        int comparedPark = ((FirebaseItem) compare).getDistance();
        return mDistance - comparedPark;
    }

}
