## 1. 블로그 글 별 조회수를 Redis로 확인

1. 블로그 URL의 Path는 `/articles/{id}` 형식
2. 로그인 여부와 상관 없이 새로고침 될 때마다 조회수 증가
3. 이를 관리하기 위한 적당한 데이터 타입 선정
4. 사용자가 임의의 페이지에 접속할 때 실행될 명령 작성

<br>

### 문자열 : INCR, DECR
```redis
INCR articles:1

-- 오늘의 조회수 따로 관리
INCR articles:1:today
RENAME articles:1:today articles:20XX-XX-XX
```

<br>

## 2. 블로그에 로그인한 사람들의 조회수와 가장 많은 조회수를 기록한 글을 Redis로 확인

1. 블로그 URL의 Path는 `/articles/{id}` 형식
2. 로그인 한 사람들의 계정은 영문으로만 이뤄짐
3. 이를 관리하기 위한 적당한 데이터 타입 선정
4. 사용자가 임의의 페이지에 접속할 때 실행될 명령 작성
5. 만약 상황에 따라 다른 명령이 실행되어야 한다면 주석으로 추가

<br>

### SET
```redis
SADD articles:1 alex
SADD articles:1 brad

-- 조회수
SCARD articles:1  # 2

-- SADD 결과에 따라 명령어 실행 or 노실행
-- 0 : skip
-- 1 : Sorted Set에 넣기
ZADD articles:ranks 1 articles:1
ZINCRBY articles:ranks 1 articles:1

-- 가장 높은 조회수
ZREVRANGE articles:ranks 0 0
ZRANGE articles:ranks 0 0 REV
```

<br>

## 3. Spring Boot 프로젝트 실습1

1. 주문 ID, 판매 물품, 갯수, 총액, 결제 여부에 대한 데이터를 지정하기 위한 `ItemOrder` 클래스를 `RadisHash`로 만들기
   1. 주문 ID - String
   2. 판매 물품 - String
   3. 갯수 - Integer
   4. 총액 - Long
   5. 주문 상태 - String
2. 주문에 대한 CRUD를 진행하는 기능
   1. `ItemOrder`의 속성값들을 ID를 제외하고 클라이언트에 전달
   2. 성공하면 저장된 `ItemOrder`를 사용자에게 응답