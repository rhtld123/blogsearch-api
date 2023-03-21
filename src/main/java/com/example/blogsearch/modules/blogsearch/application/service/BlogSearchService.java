package com.example.blogsearch.modules.blogsearch.application.service;

import com.example.blogsearch.infrastructure.exception.HttpRequestFailException;
import com.example.blogsearch.modules.blogsearch.adapter.out.persistence.entity.BlogSearchRequestEntity;
import com.example.blogsearch.modules.blogsearch.adapter.out.persistence.entity.Platform;
import com.example.blogsearch.modules.blogsearch.application.port.in.BlogSearchUseCase;
import com.example.blogsearch.modules.blogsearch.application.port.in.model.BlogSearchCommand;
import com.example.blogsearch.modules.blogsearch.application.port.in.model.BlogSearchPlatform;
import com.example.blogsearch.modules.blogsearch.application.port.in.model.BlogSearchResponse;
import com.example.blogsearch.modules.blogsearch.application.port.out.BlogSearchKakaoPort;
import com.example.blogsearch.modules.blogsearch.application.port.out.BlogSearchNaverPort;
import com.example.blogsearch.modules.blogsearch.application.port.out.SaveBlogSearchRequestPort;
import com.example.blogsearch.modules.blogsearch.application.port.out.model.BlogSearchKakaoResponse;
import com.example.blogsearch.modules.blogsearch.application.port.out.model.BlogSearchNaverResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlogSearchService implements BlogSearchUseCase {

    private final BlogSearchKakaoPort blogSearchKakaoPort;
    private final BlogSearchNaverPort blogSearchNaverPort;
    private final SaveBlogSearchRequestPort saveBlogSearchRequestPort;

    @Override
    public BlogSearchResponse search(BlogSearchCommand blogSearchCommand) {
        Object response = searchByPlatform(blogSearchCommand);
        return createResponse(response);
    }

    private Object searchByPlatform(BlogSearchCommand blogSearchCommand) {
        if (BlogSearchPlatform.NAVER == blogSearchCommand.getPlatform()) {
            throw new IllegalArgumentException("지원하지 않는 검색 플랫폼 입니다.");
        }
        return searchKakaoOrElseNaver(blogSearchCommand.getKeyword(), blogSearchCommand.getSort().name(), blogSearchCommand.getPage(), blogSearchCommand.getSize());
    }

    private Object searchKakaoOrElseNaver(String keyword, String sort, int page, int size) {
        try {
            BlogSearchKakaoResponse reponse = blogSearchKakaoPort.search(keyword, sort, page, size);
            saveBlogSearchRequestPort.save(BlogSearchRequestEntity.of(keyword, Platform.KAKAO));
            return reponse;
        } catch (HttpRequestFailException e) {
            String naverSort = sort.equals("ACCURACY") ? "sim" : "date";
            BlogSearchNaverResponse reponse = blogSearchNaverPort.search(keyword, naverSort, page, size);
            saveBlogSearchRequestPort.save(BlogSearchRequestEntity.of(keyword, Platform.NAVER));
            return reponse;
        }
    }

    private BlogSearchResponse createResponse(Object response) {
        if (response instanceof BlogSearchNaverResponse) {
            BlogSearchNaverResponse naverResponse = (BlogSearchNaverResponse) response;
            List<BlogSearchNaverResponse.items> items = naverResponse.getItems();
            return BlogSearchResponse.of(naverResponse.getTotal(), naverResponse.getDisplay().intValue(), convertItemsToContents(items), BlogSearchPlatform.NAVER);
        }

        BlogSearchKakaoResponse kakaoResponse = (BlogSearchKakaoResponse) response;
        BlogSearchKakaoResponse.Meta meta = kakaoResponse.getMeta();
        List<BlogSearchKakaoResponse.Documents> documents = kakaoResponse.getDocuments();
        return BlogSearchResponse.of(meta.getTotalCount(), documents.size(), convertDocumentsToContents(documents), BlogSearchPlatform.KAKAO);
    }

    private List<BlogSearchResponse.BlogNaverContent> convertItemsToContents(List<BlogSearchNaverResponse.items> items) {
        return items.stream().map(item -> BlogSearchResponse.BlogNaverContent.of(item.getTitle(), item.getLink(), item.getDescription(), item.getBloggername(), item.getBloggerlink(), item.getPostdate())).collect(Collectors.toList());
    }

    private List<BlogSearchResponse.BlogKakaoContent> convertDocumentsToContents(List<BlogSearchKakaoResponse.Documents> documents) {
        return documents.stream()
                .map(document -> BlogSearchResponse.BlogKakaoContent.of(document.getTitle(), document.getContents(), document.getUrl(), document.getBlogname(), document.getThumbnail(), document.getDatetime()))
                .collect(Collectors.toList());
    }
}
