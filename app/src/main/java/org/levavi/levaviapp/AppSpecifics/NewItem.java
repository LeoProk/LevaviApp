package org.levavi.levaviapp.AppSpecifics;

/**
 * Pojo for new items created
 */
public class NewItem {

    private String mText;
    private String mPhoneNum;
    private String mTitle;
    private String mAddress;
    private long mDate;

   public NewItem(){}

    public NewItem(String address, String phoneNum, String title,String text, long date){
        mDate = date;
        mAddress = address;
        mPhoneNum = phoneNum;
        mTitle = title;
        mText = text;
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


    public String getText() {
        return mText;
    }

    public long getDate() {
        return mDate;
    }

}
