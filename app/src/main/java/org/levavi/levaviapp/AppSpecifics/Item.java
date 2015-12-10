package org.levavi.levaviapp.AppSpecifics;

import java.util.HashMap;

/**
 * Pojo for items list adapter
 */
public class Item  implements Comparable {

    private String mDate;
    private String mPrice;
    private String mAddress;
    private String mPhoneNum;
    private String mTitle;
    private int mDistance;

    private HashMap<String,String> mItems;

    public Item(String date,String price,String address,String phoneNum,String title,int distance, HashMap<String,String> items){
        mDate = date;
        mPrice = price;
        mAddress = address;
        mPhoneNum = phoneNum;
        mTitle = title;
        mDistance = distance;
        mItems = items;
    }



    public String getDate() {
        return mDate;
    }


    public String getPrice() {
        return mPrice;
    }


    public String getAddress() {
        return mAddress;
    }


    public String getPhoneNum() {
        return mPhoneNum;
    }


    public String getTitle() {
        return mTitle;
    }

    public int getDistance() {
        return mDistance;
    }

    @Override
    public int compareTo(Object compare) {
        int comparedPark = ((Item) compare).getDistance();
        return this.mDistance - comparedPark;
    }

}
