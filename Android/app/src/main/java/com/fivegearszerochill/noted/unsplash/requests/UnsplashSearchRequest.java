package com.fivegearszerochill.noted.unsplash.requests;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class UnsplashSearchRequest {
    @SerializedName("query")
    private String query;
    @SerializedName("page")
    private int page;
    @SerializedName("per_page")
    private int per_page;
    @SerializedName("order_by")
    private String order_by;
    @SerializedName("content_filter")
    private String content_filter;
    @SerializedName("color")
    private String color;
    @SerializedName("orientation")
    private String orientation;

    public static class Builder {
        private String query;
        private int page;
        private int perPage;
        private String orderBy;
        private String contentFilter;
        private String color;
        private String orientation;

        public Builder(String query) {
            this.query = query;
        }


        public Builder setPage(int page) {
            this.page = page;
            return this;
        }

        public Builder setPerPage(int perPage) {
            this.perPage = perPage;
            return this;
        }

        public Builder setOrderBy(String orderBy) {
            this.orderBy = orderBy;
            return this;
        }

        public Builder setContentFilter(String contentFilter) {
            this.contentFilter = contentFilter;
            return this;
        }

        public Builder setColor(String color) {
            this.color = color;
            return this;
        }

        public Builder setOrientation(String orientation) {
            this.orientation = orientation;
            return this;
        }

        public UnsplashSearchRequest build() {
            UnsplashSearchRequest request = new UnsplashSearchRequest();
            request.query = this.query;
            request.page = this.page;
            request.per_page = this.perPage;
            request.order_by = this.orderBy;
            request.content_filter = this.contentFilter;
            request.color = this.color;
            request.orientation = this.orientation;
            return request;
        }
    }

    public Map<String, Object> toMap() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        return objectMapper.convertValue(this, Map.class);
    }
}
