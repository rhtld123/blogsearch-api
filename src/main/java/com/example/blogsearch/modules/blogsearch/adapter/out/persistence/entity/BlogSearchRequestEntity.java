package com.example.blogsearch.modules.blogsearch.adapter.out.persistence.entity;

import com.example.blogsearch.infrastructure.common.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Getter
@Entity
@Table(
        name = "blog_search_requests",
        indexes = {
            @Index(name = "index_blog_search_requests_keyword", columnList = "keyword")
        }
)
@NoArgsConstructor
public class BlogSearchRequestEntity extends BaseEntity {

    @Column(name = "keyword", nullable = false)
    private String keyword;

    private BlogSearchRequestEntity(String keyword) {
        this.keyword = keyword;
    }

    public static BlogSearchRequestEntity from(String keyword) {
        return new BlogSearchRequestEntity(keyword);
    }
}
