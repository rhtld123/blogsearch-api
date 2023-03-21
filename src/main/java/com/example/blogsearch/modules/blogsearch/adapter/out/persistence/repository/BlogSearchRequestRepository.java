package com.example.blogsearch.modules.blogsearch.adapter.out.persistence.repository;

import com.example.blogsearch.modules.blogsearch.adapter.out.persistence.entity.BlogSearchRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogSearchRequestRepository extends JpaRepository<BlogSearchRequestEntity, Long> {
}