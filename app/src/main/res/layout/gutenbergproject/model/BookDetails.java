package com.sumit.gutenbergproject.model;

import android.os.Parcel;
import android.os.Parcelable;

public class BookDetails implements Parcelable {
    String next, name, image, text, title;

    public BookDetails(){}

    private BookDetails(Parcel in) {
        next = in.readString();
        name = in.readString();
        image = in.readString();
        text = in.readString();
        title = in.readString();
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(next);
        dest.writeString(name);
        dest.writeString(image);
        dest.writeString(text);
        dest.writeString(title);

    }

    public static final Parcelable.Creator<BookDetails> CREATOR = new Parcelable.Creator<BookDetails>() {
        public BookDetails createFromParcel(Parcel in) {
            return new BookDetails(in);
        }

        public BookDetails[] newArray(int size) {
            return new BookDetails[size];
        }
    };
}
