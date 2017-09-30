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
    private String ImageUrlDetail;
    private String FileDownloadUrl;
    private String SL;
    private String JJ;
    private String HX;

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

    public String getImageUrlDetail() {
        return ImageUrlDetail;
    }

    public void setImageUrlDetail(String imageUrlDetail) {
        ImageUrlDetail = imageUrlDetail;
    }

    public String getFileDownloadUrl() {
        return FileDownloadUrl;
    }

    public void setFileDownloadUrl(String fileDownloadUrl) {
        FileDownloadUrl = fileDownloadUrl;
    }

    public String getSL() {
        return SL;
    }

    public void setSL(String SL) {
        this.SL = SL;
    }

    public String getJJ() {
        return JJ;
    }

    public void setJJ(String JJ) {
        this.JJ = JJ;
    }

    public String getHX() {
        return HX;
    }

    public void setHX(String HX) {
        this.HX = HX;
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
                ", ImageUrlDetail='" + ImageUrlDetail + '\'' +
                ", FileDownloadUrl='" + FileDownloadUrl + '\'' +
                ", SL='" + SL + '\'' +
                ", JJ='" + JJ + '\'' +
                ", HX='" + HX + '\'' +
                '}';
    }
}
