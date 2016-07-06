package com.example.administrator.expert_oschina.bearn;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/6/30 0030.
 */
public class Top implements Parcelable{
    private String title;
    private int id;
    private String fromname;
    private String description;
    private String topclass;

    public Top(){}
    protected Top(Parcel in) {
        title = in.readString();
        fromname = in.readString();
        description = in.readString();
        topclass = in.readString();
    }

    public static final Creator<Top> CREATOR = new Creator<Top>() {
        @Override
        public Top createFromParcel(Parcel in) {
            return new Top(in);
        }

        @Override
        public Top[] newArray(int size) {
            return new Top[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFromname() {
        return fromname;
    }

    public void setFromname(String fromname) {
        this.fromname = fromname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTopclass() {
        return topclass;
    }

    public void setTopclass(String topclass) {
        this.topclass = topclass;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(fromname);
        dest.writeString(description);
        dest.writeString(topclass);
    }
}