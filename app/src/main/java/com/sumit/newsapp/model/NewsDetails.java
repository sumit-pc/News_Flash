package com.sumit.newsapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "news", indices = {@Index(value = {"title"}, unique = true)})
public class NewsDetails implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "regId")
    public int regId;

    @ColumnInfo(name = "author")
    String author;

    @ColumnInfo(name = "title")
    String title;

    @ColumnInfo(name = "description")
    String description;

    @ColumnInfo(name = "url")
    String url;

    @ColumnInfo(name = "urlToImage")
    String urlToImage;

    public NewsDetails(){}

    private NewsDetails(Parcel in) {
        author = in.readString();
        title = in.readString();
        description = in.readString();
        url = in.readString();
        urlToImage = in.readString();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(author);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(url);
        dest.writeString(urlToImage);

    }

    public static final Creator<NewsDetails> CREATOR = new Creator<NewsDetails>() {
        public NewsDetails createFromParcel(Parcel in) {
            return new NewsDetails(in);
        }

        public NewsDetails[] newArray(int size) {
            return new NewsDetails[size];
        }
    };
}
