package com.professional.mvvmsample.repository.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataModel {
    @SerializedName("Status")
    public int status;
    @SerializedName("Message")
    public String message;
    @SerializedName("data")
    public Data data;

    public class Data {
        @SerializedName("TotalRecords")
        public int totalRecords;
        @SerializedName("Records")
        public List<Record> records;

    }

    public class Record {
        @SerializedName("Id")
        public int id;
        @SerializedName("title")
        public String title;
        @SerializedName("shortDescription")
        public String shortDescription;
        @SerializedName("collectedValue")
        public int collectedValue;
        @SerializedName("totalValue")
        public int totalValue;
        @SerializedName("startDate")
        public String startDate;
        @SerializedName("endDate")
        public String endDate;
        @SerializedName("mainImageURL")
        public String mainImageURL;
    }

}
