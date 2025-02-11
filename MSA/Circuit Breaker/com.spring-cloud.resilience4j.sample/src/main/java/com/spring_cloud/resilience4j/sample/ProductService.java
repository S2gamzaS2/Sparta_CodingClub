package com.spring_cloud.resilience4j.sample;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final CircuitBreakerRegistry circuitBreakerRegistry;

    @PostConstruct
    public void registerEventListener() {
        circuitBreakerRegistry.circuitBreaker("productService").getEventPublisher()
                .onStateTransition(event -> log.info("#######CircuitBreaker State Transition: {}", event)) // 상태 전환 이벤트 리스너
                .onFailureRateExceeded(event -> log.info("#######CircuitBreaker Failure Rate Exceeded: {}", event)) // 실패율 초과 이벤트 리스너
                .onCallNotPermitted(event -> log.info("#######CircuitBreaker Call Not Permitted: {}", event)) // 호출 차단 이벤트 리스너
                .onError(event -> log.info("#######CircuitBreaker Error: {}", event)); // 오류 발생 이벤트 리스너
    }

    @CircuitBreaker(name = "productService", fallbackMethod = "fallbackGetProductDetails")
    public Product getProductDetails(String productId) {
        log.info("###Fetching product details for productId: {}", productId);

        if("111".equals(productId)) {
            log.warn("###Received empty body for productId: {}", productId);
            throw new RuntimeException("Empty response body");
        }

        return new Product(productId, "Sample Product : " + productId);
    }

    public Product fallbackGetProductDetails(String productId, Throwable t) {
        log.error("####Fallback triggered for productId: {} due to: {}", productId, t.getMessage());
        return new Product(productId, "Fallback Product : " + productId);
    }



    // 이벤트 설명 표
    // +---------------------------+-------------------------------------------------+--------------------------------------------+
    // | 이벤트                      | 설명                                             | 로그 출력                                    |
    // +---------------------------+-------------------------------------------------+--------------------------------------------+
    // | 상태 전환 (Closed -> Open)   | 연속된 실패로 인해 서킷 브레이커가 오픈 상태로 전환되면 발생  | CircuitBreaker State Transition: ...       |
    // | 실패율 초과                  | 설정된 실패율 임계치를 초과하면 발생                     | CircuitBreaker Failure Rate Exceeded: ...  |
    // | 호출 차단                    | 서킷 브레이커가 오픈 상태일 때 호출이 차단되면 발생         | CircuitBreaker Call Not Permitted: ...     |
    // | 오류 발생                    | 서킷 브레이커 내부에서 호출이 실패하면 발생               | CircuitBreaker Error: ...                  |
    // +---------------------------+-------------------------------------------------+--------------------------------------------+


    // +------------------------------------------+-------------------------------------------+-----------------------------------------------------------------+
    // | 이벤트                                    | 설명                                        | 로그 출력                                                         |
    // +------------------------------------------+-------------------------------------------+-----------------------------------------------------------------+
    // | 메서드 호출                                | 제품 정보를 얻기 위해 메서드를 호출                | ###Fetching product details for productId: ...                  |
    // | (성공 시) 서킷 브레이커 내부에서 호출 성공        | 메서드 호출이 성공하여 정상적인 응답을 반환          |                                                                 |
    // | (실패 시) 서킷 브레이커 내부에서 호출 실패        | 메서드 호출이 실패하여 예외가 발생                 | #######CircuitBreaker Error: ...                                |
    // | (실패 시) 실패 횟수 증가                      | 서킷 브레이커가 실패 횟수를 증가시킴               |                                                                 |
    // | (실패율 초과 시) 실패율 초과                   | 설정된 실패율 임계치를 초과하면 발생               | #######CircuitBreaker Failure Rate Exceeded: ...                |
    // | (실패율 초과 시) 상태 전환 (Closed -> Open)   | 연속된 실패로 인해 서킷 브레이커가 오픈 상태로 전환됨   | #######CircuitBreaker State Transition: Closed -> Open at ...  |
    // | (오픈 상태 시) 호출 차단                      | 서킷 브레이커가 오픈 상태일 때 호출이 차단됨         | #######CircuitBreaker Call Not Permitted: ...                   |
    // | (오픈 상태 시) 폴백 메서드 호출                 | 메서드 호출이 차단될 경우 폴백 메서드 호출          | ####Fallback triggered for productId: ... due to: ...           |
    // +------------------------------------------+-------------------------------------------+-----------------------------------------------------------------+

}
