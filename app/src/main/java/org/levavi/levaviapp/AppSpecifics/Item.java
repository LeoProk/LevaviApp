package org.levavi.levaviapp.AppSpecifics;

import java.util.HashMap;

/**
 * Pojo for items list adapter
 */
public class Item  implements Comparable {

    private String mDate;
    private String mAddress;
    private String mPhoneNum;
    private String mTitle;
    private int mDistance;

    public Item(String date,String price,String address,String phoneNum,String title,int distance){
        mDate = date;
        mAddress = address;
        mPhoneNum = phoneNum;
        mTitle = title;
        mDistance = distance;
    }



    public String getDate() {
        return mDate;
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
