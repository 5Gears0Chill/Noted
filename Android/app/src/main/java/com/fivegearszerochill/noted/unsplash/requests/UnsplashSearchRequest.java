package com.fivegearszerochill.noted.unsplash.requests;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class UnsplashSearchRequest {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String query;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int page;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int per_page;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String order_by;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String content_filter;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String color;
    @JsonInclude(JsonInclude.Include.NON_NULL)
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
