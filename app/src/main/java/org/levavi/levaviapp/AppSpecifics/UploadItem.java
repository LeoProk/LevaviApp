package org.levavi.levaviapp.AppSpecifics;

/**
 * Pojo for items list adapter
 */
public class UploadItem{

    private String mDate;
    private String mPrice;
    private String mAddress;
    private String mPhoneNum;
    private String mTitle;

    public UploadItem(String date, String price, String address, String phoneNum, String title){
        mDate = date;
        mPrice = price;
        mAddress = address;
        mPhoneNum = phoneNum;
        mTitle = title;
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
    }

}
