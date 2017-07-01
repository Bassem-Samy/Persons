package com.bassem.persons.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Bassem on 7/1/2017.
 */

public class Pagination {
    @SerializedName("limit")
    private String limit;
    @SerializedName("start")
    private String start;
    @SerializedName("next_start")
    private String nextStart;
    @SerializedName("more_items_in_collection")
    private String moreItemsInCollection;

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getNextStart() {
        return nextStart;
    }

    public void setNext_start(String nextStart) {
        this.nextStart = nextStart;
    }

    public String getMoreItemsInCollection() {
        return moreItemsInCollection;
    }

    public void setMoreItemsInCollection(String moreItemsInCollection) {
        this.moreItemsInCollection = moreItemsInCollection;
    }

}
