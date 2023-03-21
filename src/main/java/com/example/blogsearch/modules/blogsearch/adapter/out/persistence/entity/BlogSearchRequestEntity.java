package com.example.blogsearch.modules.blogsearch.adapter.out.persistence.entity;

import com.example.blogsearch.infrastructure.common.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Column(name = "platform", nullable = false)
    @Enumerated(EnumType.STRING)
    private Platform platform;

    private BlogSearchRequestEntity(String keyword, Platform platform) {
        this.keyword = keyword;
        this.platform = platform;
    }

    public static BlogSearchRequestEntity of(String keyword, Platform platform) {
        return new BlogSearchRequestEntity(keyword, platform);
    }
}
