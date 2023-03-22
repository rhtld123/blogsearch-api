<h1>블로그 검색 API</h1>

> Github Repository : https://github.com/rhtld123/blogsearch-api

1. 카카오 API를 활용해서 블로그를 검색합니다.
2. 카카오 API로 조회 실패 시, NAVER API로 조회합니다.
3. API 목록

* 블로그 검색 API
    * <code>GET</code> <code>/api/v1/search/blog</code>
    * <code>Request-Param</code>
        * keyword `필수`: 검색할 키워드를 입력합니다.
        * sort: 정렬 방식을 입력합니다. ACCURACY(정확도순), RECENCY(최신순) 두 방식을 지원합니다. 입력하지 않으면 ACCURACY(정확도순)으로 조회됩니다.
        * page: 결과 페이지 번호를 입력합니다. 1~50 사이의 수를 입력해야 하며, 입력하지 않을 때 기본 값은 1입니다.
        * size: 한 페이지에 보여질 문서 수를 입력 합니다. 1~50 사이의 수를 입력해야 하며, 입력 하지 않을 때 기본 값은 10입니다.
        * platform : 검색에 사용할 플랫폼을 입력합니다. 현재는 KAKAO(카카오)만 지원합니다.
    * 검색 이력은 `blog_search_requests` 테이블에 저장됩니다.
    * 카카오로 검색 요청 시 오류가 발생했을 때, 네이버로 검색한 값으로 응답합니다.
    * <code>curl --location --request GET 'localhost:8080/api/v1/search/blog?keyword=카카오&sort=RECENCY&page=1&size=10&platform=KAKAO'</code>
    * 응답예시

<pre>
//카카오 응답
{
  "totalCount": 2,
  "numberOfElements": 2,
  "contents": [
    {
      "title": "",
      "contents": "",
      "url": "",
      "blogName": "",
      "thumbnail": "",
      "dateTime": "2023-03-13T17:09:00"
    },
    {
      "title": "spring-boot-starter-<b>test</b> with JUnit 5",
      "contents": "spring-boot-starter-<b>test</b> with JUnit 5 「」를 사용합니다.spring-boot-starter-<b>test</b>2.0종속성이 되었습니다.2.0.6의 경우 JUnit 4의 종속성이 도입됩니다.★★★를 어떻게 사용할 수 있습니까?spring-boot-starter-<b>test</b>(계조) JUnit 5 신n JUnit 4 ( ( 。 Gradle로부터의 의존관계 출력의 일부를 다음에 나타냅니다...",
      "url": "http://factcode.tistory.com/929",
      "blogName": "factcode",
      "thumbnail": "",
      "dateTime": "2023-03-15T19:57:25"
    }
  ],
  "searchPlatform": "KAKAO"
}
</pre>
<pre>
// 네이버 응답
{
  "totalCount": 2,
  "numberOfElements": 2,
  "contents": [
    {
      "title": "Day 45. GRE로 돌리고 처음 본 Prep <b>test</b> 결과",
      "link": "https://blog.naver.com/hondini/222969723073",
      "description": "시간관계상 온라인 시험으로 신청하고 GRE 공식 사이트에서 제공하는 Prep <b>test</b>를 시간을 재고... 알겠다고욥  어제 Prep <b>test</b> 결과 보고 Verbal 공부 후기 몇개 찾아보니까 금방 6시가 넘어버렸다. 월드컵... ",
      "bloggername": "H의 <Wunderkammer>",
      "bloggerlink": "blog.naver.com/hondini",
      "postdate": "2022-12-29"
    },
    {
      "title": "평가-T셀파-Class <b>Test</b> 이용, 도덕:감정&amp;욕구 놀, 하루 닫기)",
      "link": "https://blog.naver.com/kingofnsb/223049401577",
      "description": "&lt;5교시&gt;----------------------- 수학 1단원 평가(T셀파-Class <b>Test</b> 이용) 수학 1단원 평가를 종이가 아니라 온라인으로 해봤습니다. 천재교육 플랫폼인 T셀파에서는 온라인으로 문제를 선정해서 풀 수 있게 만들어... ",
      "bloggername": "함께 있어 행복한 우리",
      "bloggerlink": "blog.naver.com/kingofnsb",
      "postdate": "2023-03-21"
    },
  ],
  "searchPlatform": "NAVER"
}
</pre>

* 블로그 인기검색어 조회 API
    * <code>GET</code> <code>/api/v1/search/blog/popular-search-keyword</code>
    * <code>Request-Param</code>
        * 별도의 파라미터는 필요하지 않습니다.
    * 최상위 인기검색어 10개를 보여줍니다.
    * <code>curl --location --request GET 'localhost:8080/api/v1/search/blog/popular-search-keyword'</code>
    * 응답예시

```
{
  "data": [
    {
      "count": 20,
      "keyword": "카카오"
    },
    {
      "count": 10,
      "keyword": "네이버"
    }
  ]
}
```

4. 잘못된 요청 응답 규격
    * 예시

<pre>
{
  "message": "지원하지 않는 검색 플랫폼 입니다.",
  "path": "/api/v1/search/blog",
  "method": "GET"
}
</pre> 

5. 다운로드 링크
    * https://github.com/rhtld123/blogsearch-api/raw/master/executable/blogsearch-api.jar