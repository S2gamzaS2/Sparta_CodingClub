
## Spring Boot 프로젝트 실습4
Spring Security의 Form Login의 세션을 클러스터링

- Spring Security의 Form Login 기능을 구현
- 로그인 정보가 여러 애플리케이션 인스턴스에 걸쳐서 공유되는 것을 확인
    - 편의를 위해 csrf 보안은 해제
    - `UserDetailsService`를 직접 구현하지 않고 `InMemoryUserDetailsManager` 사용