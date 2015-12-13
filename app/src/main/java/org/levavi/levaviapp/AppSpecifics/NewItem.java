package org.levavi.levaviapp.AppSpecifics;

/**
 * Created by Leo on 12/13/2015.
 */
public class NewItem {


    private String mName,mUnits,mPrice;

    public NewItem(String name,String units,String price){
        mName = name;
        mUnits = units;
        mPrice = price;
    }

    public String getName() {
        return mName;
    }

    public String getUnits() {
        return mUnits;
    }

    public String getPrice() {
        return mPrice;
    }
}
