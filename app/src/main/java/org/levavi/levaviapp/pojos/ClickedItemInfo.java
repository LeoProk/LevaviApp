package org.levavi.levaviapp.pojos;

/**
 * this pojo used to set and get click item in list view info
 */
public class ClickedItemInfo {


    private String mTitle;
    private String mText;
    private String mTime;
    private String mSubject;
    private String mLength;
    private String mPhone;
    private String mAddress;
    private String mPrice;

    public ClickedItemInfo(String title,String time,String text,String subject,String length,String phone
            ,String address,String price){
        mTitle = title;
        mTime = time;
        mText = text;
        mSubject = subject;
        mLength = length;
        mPhone = phone;
        mAddress = address;
        mPrice = price;
    }


    public String getTitle() {
        return mTitle;
    }

    public String getTime() {
        return mTime;
    }

    public String getSubject() {
        return mSubject;
    }

    public String getLength() {
        return mLength;
    }

    public String getPhone() {
        return mPhone;
    }

    public String getAddress() {
        return mAddress;
    }


    public String getText() {
        return mText;
    }

    public String getPrice() {
        return mPrice;
    }

}
