package com.classical.aono.classicalcat.domain;

import java.io.Serializable;

/**
 * Created by gotha on 2017/9/29.
 */

public class Work implements Serializable {
    private String ID;
    private String Author;
    private String Country;
    private String Name;
    private String ForeignName;
    private String Remark;
    private String ImageUrlList;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getForeignName() {
        return ForeignName;
    }

    public void setForeignName(String foreignName) {
        ForeignName = foreignName;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getImageUrlList() {
        return ImageUrlList;
    }

    public void setImageUrlList(String imageUrlList) {
        ImageUrlList = imageUrlList;
    }

    @Override
    public String toString() {
        return "Work{" +
                "ID='" + ID + '\'' +
                ", Author='" + Author + '\'' +
                ", Country='" + Country + '\'' +
                ", Name='" + Name + '\'' +
                ", ForeignName='" + ForeignName + '\'' +
                ", Remark='" + Remark + '\'' +
                ", ImageUrlList='" + ImageUrlList + '\'' +
                '}';
    }
}
