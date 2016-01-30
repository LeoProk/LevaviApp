package org.levavi.levaviapp.AppSpecifics;

import com.parse.ParseObject;

import org.levavi.levaviapp.Interfaces.FactoryInterface;

/**
 * Created by Leo on 1/30/2016.
 */
final class CreateParseObject implements FactoryInterface {

    private String mUser,mTitle,mAddress,mPhone,mMessage;

    public CreateParseObject(String user, String title, String address, String phone, String message){
        mUser = user;
        mTitle = title;
        mAddress = address;
        mPhone = phone;
        mMessage = message;
    }

    @Override
    public Object doTask() {
        ParseObject items = new ParseObject("Items");
        items.put("user", mUser);
        items.put("title", mTitle);
        items.put("address", mAddress);
        items.put("phone", mPhone);
        items.put("message", mMessage);
        items.saveInBackground();
        return null;
    }
}
