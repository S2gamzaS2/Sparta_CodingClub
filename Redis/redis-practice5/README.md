특가 물량, 라이브 등 특정 상황에서는 짧은 시간동안 물품이 엄청나게 많이 팔린다!

1. 마지막에 만든 Cache 프로젝트에 리더보드 기능에서 사용한 `ItemOrder`를 추가해보자. (복습)
    1. 구매된 `Item`들의 구매 순위를 Sorted Set으로 관리하자.
    2. 가장 많이 구매된 `Item` 10개를 조회하는 기능을 만들어보자.
2. `ItemOrder`에 대하여 Write-Behind 캐싱을 구현해보자.
    1. Write-Behind는 Annotation 기반으로 구현할 수 없다.
    2. `RestTemplate`등을 통하여 수동으로 Redis에 저장하고, 조회도 Redis에서 진행하자.
    3. 이후 일정 시간바다 Redis의 데이터를 데이터베이스로 옮기는 기능을 만들어보자.
