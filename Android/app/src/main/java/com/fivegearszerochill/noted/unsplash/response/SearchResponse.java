package com.fivegearszerochill.noted.unsplash.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponse<TData> {
    @SerializedName("total")
    private int total;
    @SerializedName("total_pages")
    private int totalPages;
    @SerializedName("results")
    private List<TData> results;


    public SearchResponse() {
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<TData> getResults() {
        return results;
    }

    public void setResults(List<TData> results) {
        this.results = results;
    }
}
