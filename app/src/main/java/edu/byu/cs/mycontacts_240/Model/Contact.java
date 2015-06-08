package edu.byu.cs.mycontacts_240.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by blaine on 5/27/15.
 */
public class Contact implements Parcelable
{
    public int rowID = -1;
    public String firstName;
    public String lastName;
    public String phone;
    public String address;
    public String city;
    public String zip;
    public String email;

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(rowID);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(phone);
        dest.writeString(address);
        dest.writeString(city);
        dest.writeString(zip);
        dest.writeString(email);

    }

    @Override
    public String toString()
    {
        return this.firstName + " " + this.lastName;
    }

    public static final Parcelable.Creator CREATOR =
            new Parcelable.Creator()
            {
                public Contact createFromParcel(Parcel in)
                {
                    Contact contact = new Contact();
                    contact.rowID = in.readInt();
                    contact.firstName = in.readString();
                    contact.lastName = in.readString();
                    contact.phone = in.readString();
                    contact.address = in.readString();
                    contact.city = in.readString();
                    contact.zip = in.readString();
                    contact.email = in.readString();

                    return contact;
                }

                public Contact[] newArray(int size) {
                    return new Contact[size];
                }
            };
}
