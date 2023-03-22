<h1>블로그 검색 API</h1>
<hr/>

1. 카카오 API를 활용해서 블로그를 검색합니다.
2. 카카오 API로 조회 실패 시, NAVER API로 조회합니다.
3. API 목록

* 블로그 검색 API
    * <code>GET</code><code>/api/v1/search/blog</code>
    * <code>Request-Param</code>
        * keyword `필수`: 검색할 키워드를 입력합니다.
        * sort: 정렬 방식을 입력합니다. ACCURACY(정확도순), RECENCY(최신순) 두 방식을 지원합니다. 입력하지 않으면 ACCURACY(정확도순)으로 조회됩니다.
        * page: 결과 페이지 번호를 입력합니다. 1~50 사이의 수를 입력해야 하며, 입력하지 않을 때 기본 값은 1입니다.
        * size: 한 페이지에 보여질 문서 수를 입력 합니다. 1~50 사이의 수를 입력해야 하며, 입력 하지 않을 때 기본 값은 10입니다.
        * platform : 검색에 사용할 플랫폼을 입력합니다. 현재는 KAKAO(카카오)만 지원합니다.
    * 검색 이력은 `blog_search_requests` 테이블에 저장됩니다.
    * 카카오로 검색 요청 시 오류가 발생했을 때, 네이버로 검색한 값으로 응답합니다.
    * 응답예시

<pre>
//카카오 응답
{
  "totalCount": 1826958,
  "numberOfElements": 10,
  "contents": [
    {
      "title": "고양이 테스트 재밌는 MBTI <b>TEST</b> 공유",
      "contents": "​ 안녕하세요! 저는 MBTI에 반응하는걸 떠나 MBTI를 맹신하고 MBTI를 추종하는(?) ​ MBTI중 하나인 ENFP 엔프피 입니다 ㅋ ​ 엔프피는 새로운 MBTI <b>TEST</b> 절대 못참죠 재밌는거 나왔다고하면 무조건 다 해봐야하는 서타일...ㅋㅋㅋ ​ 그래서 오늘두 재밌구 신박한 MBTI 테스트 소개하려고 해요 ! 오늘은 바로 바로 스낵테스트...",
      "url": "https://blog.naver.com/neulsing/223043139613",
      "blogName": "MINI X MADE",
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
    },
    {
      "title": "School and College Ability <b>Test</b> (SCAT)란?",
      "contents": "​ ​ ​ ​ 안녕하세요~ 오늘은 School and College Ability <b>Test</b>(SCAT)에 대해 소개해드릴게요! 미국 존스홉킨스 대학의 CTY 영재 프로그램에 등록하기 위해서 SCAT점수가 필요합니다! ​ ​ SCAT 시험이란? ​ SCAT 시험 (학교 및 대학 능력 시험)은 Johns Hopkins Center for Talented Youth (CTY)에서 영재 프로그램의 2-12 학년...",
      "url": "https://blog.naver.com/jaysreview27/223043096494",
      "blogName": "미국 의료인이 되기 위한 준비모임",
      "thumbnail": "https://search4.kakaocdn.net/argon/130x130_85_c/I2YI3rY7Un4",
      "dateTime": "2023-03-13T16:28:00"
    },
    {
      "title": "(1)T-<b>TEST</b>[천재교육]",
      "contents": "오늘은 T-<b>TEST</b>에 대해서 알아보아요오 T-<b>TEST</b>는 두 집단에 대해 두 모집단의 평균간의 차이가 있다 혹은 없다를 검정을 하는 통계적 검정방법이다 ​ T-<b>TEST</b>는 전제조건이 있는데 먼저 표본이 독립적이여야하며 표본이 정규분포를 따라야하며 두 집단이 존재해야한다! ​ 데이터분석에 있어서 T-<b>TEST</b>를 하려면 먼저 우선적...",
      "url": "https://blog.naver.com/bonobonodi/223034741657",
      "blogName": "뚜데이",
      "thumbnail": "https://search2.kakaocdn.net/argon/130x130_85_c/LAc3kFhWt86",
      "dateTime": "2023-03-04T17:41:00"
    },
    {
      "title": "[pre-project] 8일차 - <b>test</b> 코드 failed",
      "contents": "있었다. 하지만 더 급하고 중요하게 처리해야 하는 문제들이 있어서 후 순위로 밀려났지만 이젠 해결해야 될 때가 됐다. 정확히 말하자면 에러는 아니고 <b>test</b> failed... 오늘 공부하고 파헤쳤던 것을 정리해보자. TODAY 로그 분석 fail이 된 이유 위 이미지의 로그를 직접 옮겨보면 이렇다. 로그를 읽어보니까 header에...",
      "url": "http://yy-log.tistory.com/114",
      "blogName": "미묘한 옝",
      "thumbnail": "https://search1.kakaocdn.net/argon/130x130_85_c/EmnLX3F8U2T",
      "dateTime": "2023-02-24T09:30:31"
    },
    {
      "title": "【GRE】 The Solution for GRE Mathematics Practice <b>Test</b> [41-60]",
      "contents": "The Solution for GRE Mathematics Practice <b>Test</b> [41-60] 추천글 : 【GRE】 Solution for GRE Mathematics Practice <b>Test</b> 41. Let ℓ be the line that is the intersection of the planes x + y + z = 3 and x - y + z = 5 in ℝ3. An equation of the plane that contains (0, 0, 0) and is perpendicular to ℓ is ⑴...",
      "url": "http://nate9389.tistory.com/2289",
      "blogName": "정빈이의 공부방",
      "thumbnail": "https://search3.kakaocdn.net/argon/130x130_85_c/BsKwwHwJKAM",
      "dateTime": "2023-01-21T18:04:34"
    },
    {
      "title": "제89회 매경<b>TEST</b> 정기시험 접수 안내",
      "contents": "​ 안녕하세요 멀리보고 강한 힘을 기르는 강승학원 승승쌤이에요 ​ 비즈니스 사고력 테스트인 국가공인 매경<b>TEST</b> 아시나요? ​ 매경테스트는 매일경제 신문이 만든 테스트인데요. 매경테스트 문제는 국내 외 최고의 대학 교수 출제위원진과 매일경제신문 박사급 기자 그리고 경제·경영 전문가들이 출제하며 석학들로 구성된...",
      "url": "https://blog.naver.com/ksa_education/223041726943",
      "blogName": "강승학원",
      "thumbnail": "",
      "dateTime": "2023-03-11T21:19:00"
    },
    {
      "title": "<b>test</b>",
      "contents": "소백산 비로봉 등산기.. 소백산 비로봉 등산기.. 국내여행기 2012-12-20 01:15:05 2012년을 마무리 하면서, 친구들과 소백산 등반 프로젝트를 구상하였습니다.. ^^ 날짜를 잡고 우린 아침 5시 망원역에서 만나 소백산을 향한 첫걸음을 시작합니다..^^ 이제부터 소백산 등반 여행기를 올립니다.. 친구들과의 여행이라...",
      "url": "http://priority-one.tistory.com/2",
      "blogName": "priority_one 공부 노트",
      "thumbnail": "https://search4.kakaocdn.net/argon/130x130_85_c/4oUVRuoysDy",
      "dateTime": "2022-11-21T18:18:08"
    },
    {
      "title": "Connection Pool( feat. HikariCP <b>test</b>)",
      "contents": "name: &#39;HikariCP&#39;, version: &#39;5.0.1&#39; TestCode Spring에서 jdbc 드라이버와 HikariCP 종속성을 추가했다면, 올바른 작동 확인을 위한 테스트를 진행한다. @<b>Test</b> public void testHikariCP() throws Exception { //Connection 생성을 위한 HikariCP 테스트 HikariConfig config = new HikariConfig(); //HikariConfig...",
      "url": "http://siriusisrunner.tistory.com/17",
      "blogName": "개발도 마라톤처럼",
      "thumbnail": "https://search4.kakaocdn.net/argon/130x130_85_c/E7bHLXsE2QC",
      "dateTime": "2023-03-11T04:47:43"
    },
    {
      "title": "(2)T-<b>TEST</b>[천재교육]",
      "contents": "T-<b>TEST</b> 두번째! 어제 했던 데이터프레임 그대로 들고와서 해볼건데 어제는 정규성을 불만족해서 Mann-Whitney U <b>test</b>를 했다면 오늘은 정규성을 만족하도록 transformation을 거쳐서 t-<b>test</b>를 돌려볼 예정이다! ​ import pandas as pd from scipy import stats import math import numpy as np import seaborn as sns...",
      "url": "https://blog.naver.com/bonobonodi/223035379147",
      "blogName": "뚜데이",
      "thumbnail": "https://search1.kakaocdn.net/argon/130x130_85_c/JDA5h7zA7zP",
      "dateTime": "2023-03-05T16:49:00"
    }
  ],
  "searchPlatform": "KAKAO"
}
</pre>
<pre>
// 네이버 응답
{
  "totalCount": 1560753,
  "numberOfElements": 10,
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
    {
      "title": "입학시험은 어떤 형식인가요? (결국 MAP <b>TEST</b>)",
      "link": "https://blog.naver.com/tanabae/222738592393",
      "description": "MAP <b>Test</b> 를 보는구나... 우리는 예전에 맵테스트를 치뤄본 적이 있었고, (2020년, 2021년) 세 영역... MAP <b>TEST</b> 는 그 아래 표로 나와있는 RIT 점수로 같은 연령대(같은 학년)에 있는 학생들의... ",
      "bloggername": "Knocking on Heaven&apos;s door...",
      "bloggerlink": "blog.naver.com/tanabae",
      "postdate": "2022-05-19"
    },
    {
      "title": "주제통각검사(Thematic Apperception <b>Test</b>)",
      "link": "https://blog.naver.com/nikoniko88/223045266786",
      "description": "주제통각검사 [ Thematic Apperception <b>Test</b> ] 머리와 모건(Murray &amp; Morgan)의 투사적 그림검사 머리와 모건... TAT는 성인 대상 검사이며, 아동용으로는 3~10세의 아동을 대상으로 하는 CAT(Children’s Apperception <b>Test</b>)가... ",
      "bloggername": "반짝반짝 빛나는 직업상담사 blog*^^*",
      "bloggerlink": "blog.naver.com/nikoniko88",
      "postdate": "2023-03-15"
    },
    {
      "title": "[재무모델링] IB/PE/HF의 금융모델링 / 파워포인트 <b>TEST</b>의... ",
      "link": "https://blog.naver.com/themodellers/222979307214",
      "description": "더모델러스에서 수업을 듣는 학생들의 상당 수는, 향후 이직 시의 금융모델링 <b>TEST</b>를 대비하기 위해서 오는 분들이다. 그냥 면접만봐도 엄청난 압박이고, 준비할 것이 산더미인데, 거기에다가 <b>TEST</b>까지... ",
      "bloggername": "The Modellers",
      "bloggerlink": "blog.naver.com/themodellers",
      "postdate": "2023-01-09"
    },
    {
      "title": "DATS V3 Audio componet <b>test</b> system, 스피커 임피던스 측정기",
      "link": "https://blog.naver.com/chardad/223028787578",
      "description": "https://www.daytonaudio.com/product/1650/dats-v3-computer-based-audio-component-<b>test</b>-system 요기에 기능과... Impedance Analyser &gt; <b>Test</b> Lead Calibration 메뉴에서 두 테스트 단자를 연결하고 OK. 우측 화면의 <b>Test</b> Lead... ",
      "bloggername": "찰리아빠의 블로그",
      "bloggerlink": "blog.naver.com/chardad",
      "postdate": "2023-02-27"
    },
    {
      "title": "영국워홀 | Smear <b>Test</b> 받았던 날",
      "link": "https://blog.naver.com/suhyun0523/223014854046",
      "description": "바로 앞에 있는 카페에 들어가 토스트와 레몬에이드를 마시며 영상편집을 했다. *옥스퍼드 브이로그는 어제 드디어 편집을 마무리해 업로드했다. 히힣 * Smear <b>Test</b> &gt; 영국은 3년마다 검사 &gt; 한국은 2년마다 검사",
      "bloggername": "daysuhyun",
      "bloggerlink": "blog.naver.com/suhyun0523",
      "postdate": "2023-02-14"
    },
    {
      "title": "국제학교 준비 시작, IXL, MAP <b>Test</b>, <b>Test</b> Prep, Pre Algebra",
      "link": "https://blog.naver.com/workingmom9/222624358269",
      "description": "일단 Middle 과정의 테스트를 볼 텐데 미국 학교는 MAP <b>test</b> 라는 시험 형태를 많이 활용한다고 한다.... 다행스럽게 MAP <b>test</b> 연습을 해볼 수 있는 사이트가 있다. <b>Test</b> prep &amp; &amp; &amp; MAP 외에도 영국학교 시험인... ",
      "bloggername": "파워맘의 파이어이야기",
      "bloggerlink": "blog.naver.com/workingmom9",
      "postdate": "2022-01-17"
    },
    {
      "title": "펩테스트 Pap <b>test</b> , 자궁경부암검사",
      "link": "https://blog.naver.com/zzast/222938452097",
      "description": "Papanicolaou <b>test</b>) 5분도 안걸린다. Pap <b>test</b>는 자궁 경부의 질 부분에서 비정상적인 세포를 찾는 선별 검사입니다. 사람이 증상을 경험하기 전에 질병을 감지하도록 설계되었기 때문에 선별 검사 도말검사를... ",
      "bloggername": "베누스의 캐나다스케치",
      "bloggerlink": "blog.naver.com/zzast",
      "postdate": "2022-11-26"
    },
    {
      "title": "주니어토플 시험보는 날 [TOEFL PRIMARY <b>TEST</b> STEP2]",
      "link": "https://blog.naver.com/mudaepo1213/223028924663",
      "description": "TOEFL Primary <b>test</b> Junior Level 아이가 보더니 한줄도 읽기가 싫대요.. 아후 저도 너무 싫더라고요. ㅋ... Skill UP for the TOEFL Primary <b>test</b> Step2 TOEFL Step2 초반 어휘 수준은 어렵지 않아요. 어휘가 10문제 정도 나오고... ",
      "bloggername": "+ 디자이너 조샤리 +",
      "bloggerlink": "blog.naver.com/mudaepo1213",
      "postdate": "2023-02-27"
    }
  ],
  "searchPlatform": "NAVER"
}
</pre>

* 블로그 인기검색어 조회 API
    * <code>GET</code><code>/api/v1/search/blog/popular-search-keyword</code>
    * <code>Request-Param</code>
        * 별도의 파라미터는 필요하지 않습니다.
    * 최상위 인기검색어 10개를 보여줍니다.
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
    },
    {
      "count": 1,
      "keyword": "keyword9"
    },
    {
      "count": 1,
      "keyword": "keyword8"
    },
    {
      "count": 1,
      "keyword": "keyword7"
    },
    {
      "count": 1,
      "keyword": "keyword6"
    },
    {
      "count": 1,
      "keyword": "keyword5"
    },
    {
      "count": 1,
      "keyword": "keyword1"
    },
    {
      "count": 1,
      "keyword": "keyword3"
    },
    {
      "count": 1,
      "keyword": "keyword0"
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