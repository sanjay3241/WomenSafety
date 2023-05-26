package com.example.productionproject;

import android.os.Parcel;
import android.os.Parcelable;

public class ContactDetails implements Parcelable {

    public String myId;
    public String FullName;
    public String MNumber;
    public String DType;

    public ContactDetails() {

    }

    public ContactDetails(String id, String fullname, String mnumber, String dtype) {
        myId = id;
        FullName = fullname;
        MNumber = mnumber;
        DType = dtype;
    }

    protected ContactDetails(Parcel in) {
        FullName = in.readString();
        myId = in.readString();
        MNumber = in.readString();
        DType = in.readString();
    }

    public static final Creator<ContactDetails> CREATOR = new Creator<ContactDetails>() {
        @Override
        public ContactDetails createFromParcel(Parcel in) {
            return new ContactDetails(in);
        }

        @Override
        public ContactDetails[] newArray(int size) {
            return new ContactDetails[size];
        }
    };

    public String getId() {
        return myId;
    }

    public void setId(String id) {
        myId = id;
    }

    public String getName() {
        return FullName;
    }

    public void setName(String name) {
        FullName = name;
    }

    public String getNumber() {
        return MNumber;
    }

    public void setNumber(String number) {
        MNumber = number;
    }

    public String getType() {
        return DType;
    }

    public void setType(String type) {
        DType = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(myId);
        parcel.writeString(FullName);
        parcel.writeString(MNumber);
        parcel.writeString(DType);
    }
}
