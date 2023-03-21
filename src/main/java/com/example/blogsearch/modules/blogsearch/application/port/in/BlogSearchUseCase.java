package com.example.blogsearch.modules.blogsearch.application.port.in;

import com.example.blogsearch.modules.blogsearch.application.port.in.model.BlogSearchCommand;
import com.example.blogsearch.modules.blogsearch.application.port.in.model.BlogSearchResponse;

public interface BlogSearchUseCase {
    BlogSearchResponse search(BlogSearchCommand blogSearchCommand);
}
