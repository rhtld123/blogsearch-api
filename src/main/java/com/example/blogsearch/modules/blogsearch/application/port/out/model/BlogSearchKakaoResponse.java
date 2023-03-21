package com.example.blogsearch.modules.blogsearch.application.port.out.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class BlogSearchKakaoResponse {

    @JsonProperty("meta")
    private Meta meta;
    @JsonProperty("documents")
    private List<Documents> documents;

    private BlogSearchKakaoResponse(Meta meta, List<Documents> documents) {
        this.meta = meta;
        this.documents = documents;
    }

    public static BlogSearchKakaoResponse of(Meta meta, List<Documents> documents) {
        return new BlogSearchKakaoResponse(meta, documents);
    }

    @Getter
    @NoArgsConstructor
    public static class Meta {
        @JsonProperty("total_count")
        private int totalCount;
        @JsonProperty("pageable_count")
        private int pageableCount;
        @JsonProperty("is_end")
        private boolean isEnd;

        private Meta(int totalCount, int pageableCount, boolean isEnd) {
            this.totalCount = totalCount;
            this.pageableCount = pageableCount;
            this.isEnd = isEnd;
        }

        public static Meta of(int totalCount, int pageableCount, boolean isEnd) {
            return new Meta(totalCount, pageableCount, isEnd);
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Documents {
        @JsonProperty("title")
        private String title;
        @JsonProperty("contents")
        private String contents;
        @JsonProperty("url")
        private String url;
        @JsonProperty("blogname")
        private String blogname;
        @JsonProperty("thumbnail")
        private String thumbnail;
        @JsonProperty("datetime")
        private String datetime;

        private Documents(String title, String contents, String url, String blogname, String thumbnail, String datetime) {
            this.title = title;
            this.contents = contents;
            this.url = url;
            this.blogname = blogname;
            this.thumbnail = thumbnail;
            this.datetime = datetime;
        }

        public static Documents of(String title, String contents, String url, String blogname, String thumbnail, String datetime) {
            return new Documents(title, contents, url, blogname, thumbnail, datetime);
        }
    }
}