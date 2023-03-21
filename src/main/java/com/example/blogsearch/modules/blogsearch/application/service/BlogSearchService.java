package com.example.blogsearch.modules.blogsearch.application.service;

import com.example.blogsearch.modules.blogsearch.application.port.in.BlogSearchUseCase;
import com.example.blogsearch.modules.blogsearch.application.port.in.model.BlogSearchCommand;
import com.example.blogsearch.modules.blogsearch.application.port.in.model.BlogSearchResponse;
import com.example.blogsearch.modules.blogsearch.application.port.out.BlogSearchKakaoPort;
import com.example.blogsearch.modules.blogsearch.application.port.out.model.BlogSearchKakaoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlogSearchService implements BlogSearchUseCase {

    private final BlogSearchKakaoPort blogSearchKakaoPort;

    @Override
    public BlogSearchResponse search(BlogSearchCommand blogSearchCommand) {
        BlogSearchKakaoResponse response = blogSearchKakaoPort.search(blogSearchCommand.getKeyword(), blogSearchCommand.getSort().name(), blogSearchCommand.getPage(), blogSearchCommand.getSize());
        BlogSearchKakaoResponse.Meta meta = response.getMeta();
        List<BlogSearchKakaoResponse.Documents> documents = response.getDocuments();
        return BlogSearchResponse.of(meta.getTotalCount(), meta.getPageableCount(), meta.isEnd(), convertDocumentsToContents(documents));
    }

    private List<BlogSearchResponse.BlogContent> convertDocumentsToContents(List<BlogSearchKakaoResponse.Documents> documents) {
        return documents.stream()
                .map(document -> BlogSearchResponse.BlogContent.of(document.getTitle(), document.getContents(), document.getUrl(), document.getBlogname(), document.getThumbnail(), convertDatetime(document.getDatetime())))
                .collect(Collectors.toList());
    }

    private LocalDateTime convertDatetime(String datetime) {
        return LocalDateTime.from(Instant.from(DateTimeFormatter.ISO_DATE_TIME.parse(datetime)).atZone(ZoneId.of("Asia/Seoul")));
    }
}
