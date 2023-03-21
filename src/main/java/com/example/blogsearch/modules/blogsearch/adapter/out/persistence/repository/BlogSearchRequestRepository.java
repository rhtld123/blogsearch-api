package com.example.blogsearch.modules.blogsearch.adapter.out.persistence.repository;

import com.example.blogsearch.modules.blogsearch.adapter.out.persistence.entity.BlogSearchRequestEntity;
import com.example.blogsearch.modules.blogsearch.application.port.out.model.BlogPopularSearchKeyword;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogSearchRequestRepository extends JpaRepository<BlogSearchRequestEntity, Long> {

    @Query("SELECT new com.example.blogsearch.modules.blogsearch.application.port.out.model.BlogPopularSearchKeyword( " +
            "COUNT(b.keyword), " +
            "b.keyword) " +
            "FROM BlogSearchRequestEntity b " +
            "GROUP BY b.keyword " +
            "ORDER BY COUNT(b.keyword) DESC")
    List<BlogPopularSearchKeyword> findBlogPopularSearchKeywords(Pageable pageable);
}