Spring Security의 Form Login의 세션을 클러스터링 해보자.

1. Spring Security의 Form Login 기능을 구현하고, 로그인 정보가 여러 애플리케이션 인스턴스에 걸쳐서 공유되는 것을 확인해보자.
    1. 편의를 위해 csrf 보안은 해제하고 진행하자.
    2. `UserDetailsService`를 직접 구현하지 않고 `InMemoryUserDetailsManager` 사용해도 괜찮다.